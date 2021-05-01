package com.example.asus.networkingproject;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.net.wifi.aware.WifiAwareManager;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {

     Button btnonoff,btndiscover,btnsend;
     ListView listview;
     TextView read_message_box;
     TextView connection_status;
     EditText write_message;
     Button change_btn;
     int colCode;

     public String TAG = "MUKIT";
     public String textFromFile = "";


     int bgcolor;

     public ArrayList<String>chatFullList = new ArrayList<String>();


    private static final String FILE_NAME = "saveChat.txt";

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};





    WifiManager wifi_manager;
    WifiP2pManager mManager;
    WifiP2pManager.Channel mChannel;


   BroadcastReceiver mReceiver;
   IntentFilter mIntentfilter;

   List<WifiP2pDevice> peers=new ArrayList<WifiP2pDevice>();
   String[] deviceNameArray;
   WifiP2pDevice[] deviceArray;


   static final int MESSAGE_READ=1;

   ServerClass serverClass;
   ClientClass clientClass;
   SendReceive sendReceive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        write_message = findViewById(R.id.message_edit_text_id);
        change_btn=(Button)findViewById(R.id.change_background_id);

        colCode= ContextCompat.getColor(MainActivity.this,R.color.colorPrimary);

        initialwork();
        exqListener();


        verifyDataFolder();
        verifyStoragePermissions();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.example,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.save_id){

        }

        if(item.getItemId()==R.id.share_file_id){

        }

        if(item.getItemId()==R.id.background_id){



        }


        return super.onOptionsItemSelected(item);
    }

    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_READ:
                    byte[] readBuff= (byte[]) msg.obj;
                    String tempMsg=new String(readBuff,0,msg.arg1);
                    if (tempMsg.charAt(0) == '#') {
                        tempMsg = tempMsg.replace("#@", "");
                        Toast.makeText(getApplicationContext(), "File saved in " + getFilesDir(), Toast.LENGTH_SHORT).show();
                        String fileText = tempMsg;
                        writeToFile("file", fileText, true);
                    }

                    else {
                        read_message_box.setText(tempMsg);
                        chatFullList.add(tempMsg);
                    }

                    break;


            }

            return true;
        }
    });

    private void exqListener() {

        btnonoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wifi_manager.isWifiEnabled()){
                    wifi_manager.setWifiEnabled(false);
                    btnonoff.setText(" On");



                }

                else

                {
                    wifi_manager.setWifiEnabled(true);
                    btnonoff.setText("Wifi Off");
                }
            }
        });
        btndiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        connection_status.setText("Discovery Started");

                    }

                    @Override
                    public void onFailure(int reason) {
                        connection_status.setText("Discovery Starting Failure");


                    }
                });

            }
        });

       listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

               final WifiP2pDevice device=deviceArray[i];
               WifiP2pConfig config=new WifiP2pConfig();
               config.deviceAddress=device.deviceAddress;


               mManager.connect(mChannel, config, new WifiP2pManager.ActionListener() {
                   @Override
                   public void onSuccess() {
                       Toast.makeText(getApplicationContext(),"connected to device "+device.deviceName,Toast.LENGTH_SHORT).show();

                   }

                   @Override
                   public void onFailure(int i) {
                       Toast.makeText(getApplicationContext(),"Not connected",Toast.LENGTH_SHORT).show();

                   }
               });


           }
       });

       btnsend.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String msg=write_message.getText().toString();
               sendReceive.write(msg.getBytes());
           }
       });








    }

    private void initialwork(){

            btnonoff=(Button)findViewById(R.id.wifi_on_btn);
            btndiscover=(Button)findViewById(R.id.discover_btn_id);
            btnsend=(Button)findViewById(R.id.send_btn_id);



            listview=(ListView)findViewById(R.id.list_view_id);

            read_message_box=(TextView)findViewById(R.id.message_text_id);
            connection_status=(TextView)findViewById(R.id.connection_statusid);
            write_message=(EditText)findViewById(R.id.message_edit_text_id);


            wifi_manager=(WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);

           mManager=(WifiP2pManager)getSystemService(Context.WIFI_P2P_SERVICE);
           mChannel=mManager.initialize(this,getMainLooper(),null);

           mReceiver=new WifiDirectBroadcast(mManager,mChannel,this);

          mIntentfilter=new IntentFilter();
          mIntentfilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
          mIntentfilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
          mIntentfilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
          mIntentfilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);




        }

         WifiP2pManager.PeerListListener peerListListener=new WifiP2pManager.PeerListListener() {
            @Override
            public void onPeersAvailable(WifiP2pDeviceList peersList) {

                if(!peersList.getDeviceList().equals(peers)){
                    peers.clear();
                    peers.addAll(peersList.getDeviceList());

                    deviceNameArray=new String[peersList.getDeviceList().size()];
                    deviceArray=new WifiP2pDevice[peersList.getDeviceList().size()];
                    int index=0;


                    for(WifiP2pDevice device : peersList.getDeviceList()){


                        deviceNameArray[index]=device.deviceName;
                        deviceArray[index]=device;

                        index++;


                    }


                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,deviceNameArray);
                    listview.setAdapter(adapter);





                }


                if(peers.size()==0){

                    Toast.makeText(getApplicationContext(),"No Device Found",Toast.LENGTH_SHORT).show();
                    return;
                }




            }
        };


    public WifiP2pManager.ConnectionInfoListener connectionInfoListener=new WifiP2pManager.ConnectionInfoListener() {
        @Override
        public void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
            final InetAddress groupOwnwerAddress=wifiP2pInfo.groupOwnerAddress;

            if(wifiP2pInfo.groupFormed && wifiP2pInfo.isGroupOwner){

                    connection_status.setText("Host");
                    serverClass=new ServerClass();
                    serverClass.start();



            }
            else if(wifiP2pInfo.groupFormed)
            {

                connection_status.setText("Client");
                clientClass=new ClientClass(groupOwnwerAddress);
                clientClass.start();


            }


        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver,mIntentfilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);


    }

    public class ServerClass extends Thread{
        Socket socket;
        ServerSocket serverSocket;
        @Override
        public void run(){
            try {
                serverSocket=new ServerSocket(8888);
                socket=serverSocket.accept();
                sendReceive=new SendReceive(socket);
                sendReceive.start();


            } catch (IOException e) {
                e.printStackTrace();
            }


        }



    }

    public void onSendTextFileClicked(View v){
        generateFileManagerWindow();
    }

    private void generateFileManagerWindow() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, 7);
    }

    private class SendReceive extends Thread{
        private Socket socket;
        private InputStream inputStream;
        private OutputStream outputStream;


        public SendReceive(Socket skt){

            socket=skt;
            try {
                inputStream=socket.getInputStream();
                outputStream=socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void run() {
            byte[]  buffer=new byte[1024];
            int bytes;

            while(socket!=null){
                try {
                    bytes=inputStream.read(buffer);
                    if(bytes>0){
                        handler.obtainMessage(MESSAGE_READ,bytes,-1,buffer).sendToTarget();

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        public void write(byte[] bytes){
            try {
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


    public class ClientClass extends Thread{

        Socket socket;
        String hostadd;

        public ClientClass(InetAddress hostAddress){
            hostadd=hostAddress.getHostAddress();
            socket=new Socket();


        }

        @Override
        public void run() {
            try {
                socket.connect(new InetSocketAddress(hostadd,8888),500);
                sendReceive=new SendReceive(socket);
                sendReceive.start();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {

            Uri uri = data.getData();
            String fileText = getTextFromUri(uri);
            textFromFile = "#@"+fileText;

            new Thread(new Runnable() {
                @Override
                public void run() {


                    //String msg = "124@@@"+fileText;

                    Log.d(TAG, textFromFile);

                    sendReceive.write(textFromFile.getBytes());
                }
            }).start();

        }


    }

    public String getTextFromUri(Uri uri){
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(getContentResolver().openInputStream(uri)));
            String line = "";

            while ((line = reader.readLine()) != null) {
                builder.append("\n"+line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

    public void verifyStoragePermissions() {
        // Check if we have write permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(
                        PERMISSIONS_STORAGE,
                        REQUEST_EXTERNAL_STORAGE
                );
            }
        }
    }
    private void verifyDataFolder() {
        File folder = new File(Environment.getExternalStorageDirectory() + "/Peer 2 Peer");
        File folder1 = new File(folder.getPath() + "/Conversations");
        File folder2 = new File(folder.getPath() + "/Saved txt files");
        if(!folder.exists() || !folder.isDirectory()) {
            folder.mkdir();
            folder1.mkdir();
            folder2.mkdir();
        }
        else if(!folder1.exists())
            folder1.mkdir();
        else if(!folder2.exists())
            folder2.mkdir();
    }

    public void onSaveClicked(View v) {


        String path = Environment.getExternalStorageDirectory().toString();
        File file = null;
        String newline = "\n";


        file = new File(path + "/Peer 2 Peer/Saved txt files", FILE_NAME);

        Toast.makeText(this, "Chat conversation is saved successfully", Toast.LENGTH_SHORT).show();

        FileOutputStream stream;
        try {
            stream = new FileOutputStream(file, false);
            for (int i = 0; i < chatFullList.size(); i++) {

                // pw.write("It is here");
                // String l = chatFullList.get(i).getIp().concat(" : ");
                stream.write(chatFullList.get(i).getBytes());
                stream.write(newline.getBytes()); }
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void writeToFile(String fileName, String data, boolean timeStamp) {

        Long time= System.currentTimeMillis();
        String timeMill = " "+time.toString();
        String path = Environment.getExternalStorageDirectory().toString();
        File file = null;
        if(timeStamp)
            file = new File(path+"/Peer 2 Peer/Conversations", fileName+timeMill+".txt");
        else
            file = new File(path+"/Peer 2 Peer/Saved txt files", fileName);
        FileOutputStream stream;
        try {
            stream = new FileOutputStream(file, false);
            stream.write(data.getBytes());
            stream.close();
            // showToast("file saved in: "+file.getPath());
            Toast.makeText(this, "Saving Your Conversation.....", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Log.d(TAG, e.toString());
        } catch (IOException e) {
            Log.d(TAG, e.toString());
        }
    }


}



