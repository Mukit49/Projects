import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asus.eventmanager.R;

public class Sector_head_dialog extends AppCompatDialogFragment {


    private EditText  code_txt;
    private Button   done_btn;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder_sector_head = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();


       View view =inflater.inflate(R.layout.layout_dialog,null);

       builder_sector_head.setView(view).setTitle("Login").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {



           }
       }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

           }
       });

        code_txt=view.findViewById(R.id.event_head_code_id);
        done_btn=view.findViewById(R.id.header_code_done_btn_id);


       return builder_sector_head.create();


    }




}

