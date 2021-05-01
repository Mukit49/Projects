
package wirelessproject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class WirelessProject extends JFrame {
    
    private ImageIcon icon;
    private ImageIcon image1;
    
    private Container c;
    private Cursor cursor;
    
    
    private JLabel label1;
    private JLabel label2;
    
    private JButton  task1_btn;
    private JButton  task2_btn;
    private Font f;
    
    
    
    WirelessProject(){
        initComponents(); 
        
        
    }
    
    public void initComponents(){
        
        //background color; 
        
        c=this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.yellow);
        
        
        image1 = new ImageIcon(getClass().getResource("front3.png"));
        
        f = new Font("Arial", Font.BOLD,50);
        
        cursor = new Cursor(Cursor.HAND_CURSOR);
        
        
        
        label2= new JLabel(image1);
        label2.setBounds(450,30,image1.getIconWidth(), image1.getIconHeight());
        
        
        
        
        
        label1 =new JLabel();
        label1.setText("Wireless Communication");
        label1.setBounds(400,330, 1000,50);
        label1.setForeground(Color.red);
        //label1.setOpaque(true); to enable background color
        //label1.setBackground(Color.green);
        //label1.setToolTipText("Hey this is wireless ");//used for show a text ;
        label1.setFont(f);
        
        
        
        
        
        
        task1_btn = new JButton();
        task1_btn.setText("TASK  1");
        task1_btn.setCursor(cursor);
        task1_btn.setBounds(525,400,350,50);
        
        task1_btn.addActionListener(new ActionListener(){
        
                  public void actionPerformed(ActionEvent e){
                      
                      
                      Task1  tsk = new Task1();
                      tsk.setVisible(true);
                      
                      
                  }
        });
        
        
        task2_btn = new JButton();
        task2_btn.setText("TASK 2");
        task2_btn.setCursor(cursor);
        task2_btn.setBounds(525, 480, 350, 50);
        
        
        
        task2_btn.addActionListener(new ActionListener(){
        
                  public void actionPerformed(ActionEvent e){
                      
                      
                      Task2  tsk = new Task2();
                      tsk.setVisible(true);
                      
                      
                  }
        });
        
        
        
        
        c.add(label1);
        c.add(task1_btn);
        c.add(task2_btn);
        c.add(label2);
        
        
        
        
        
     
        icon= new ImageIcon(getClass().getResource("icon2.png"));
        this.setIconImage(icon.getImage());
        
        
        
        
    }
    

    
    public static void main(String[] args) {
        WirelessProject frame= new WirelessProject();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400,1200);
        frame.setLocationRelativeTo(null);
        frame.setLocation(0, 0); 
        
        //setbounds  this this made by adding set location and set size
        
        //frame.setBounds( 0, 0, 1400, 1200);
        frame.setTitle("Wireless Connection");
        
        //to stop resizing.
        
        frame.setResizable(false);
        
       
         
        
        
         
        
    }

    
    
}
