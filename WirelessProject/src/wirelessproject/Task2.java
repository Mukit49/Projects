

package wirelessproject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Task2 extends JFrame {
    
    
    
    private JLabel carrier_freq_lbl;
    private JLabel transmitter_h_lbl;
    private JLabel receiver_h_lbl;
    private JLabel propagation_dist_lbl;
    
    
    private JTextField carrier_freq_txt;
    private JTextField transmitter_h_txt;
    private JTextField receiver_h_txt;
    private JTextField propagation_dist_txt;
    
    
    
    
    
    private Font f;
    private Container c;
    
    
    Task2(){
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(0,0,1400,1200);
        this.setTitle("Problem 2");
        
        
        c=this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.orange);
        
        f = new Font("Arial", Font.BOLD,25);
         
         
         
         
         carrier_freq_lbl = new JLabel();
         carrier_freq_lbl.setText("Carrier Frequency                   :");
         carrier_freq_lbl.setBounds(350,80,400, 40);
         carrier_freq_lbl.setFont(f);
        
        
        carrier_freq_txt = new JTextField();
        carrier_freq_txt.setBounds(750, 80, 300, 40);
        carrier_freq_txt.setFont(f);
        
        
         transmitter_h_lbl = new JLabel();
         transmitter_h_lbl.setText("Height of Transmitter Antena    :");
         transmitter_h_lbl.setBounds(350,140,400, 40);
         transmitter_h_lbl.setFont(f);
        
        
       transmitter_h_txt = new JTextField();
       transmitter_h_txt.setBounds(750, 140, 300, 40);
       transmitter_h_txt.setFont(f);
       
       
       
        receiver_h_lbl = new JLabel();
        receiver_h_lbl.setText("Height of Receiver Antena    :");
        receiver_h_lbl.setBounds(350,200,400, 40);
        receiver_h_lbl.setFont(f);
        
        
       receiver_h_txt = new JTextField();
       receiver_h_txt.setBounds(750, 200, 300, 40);
       receiver_h_txt.setFont(f);
       
       
      propagation_dist_lbl = new JLabel();
      propagation_dist_lbl.setText("Propagation Distance of Antennas :");
      propagation_dist_lbl.setBounds(350,260,400, 40);
      propagation_dist_lbl.setFont(f);
        
        
       propagation_dist_txt = new JTextField();
       propagation_dist_txt.setBounds(750, 260, 300, 40);
       propagation_dist_txt.setFont(f);
       
       
       
       
       
       
       
       
       
       c.add(carrier_freq_lbl);
       c.add(carrier_freq_txt);
       c.add(transmitter_h_lbl);
       c.add(transmitter_h_txt);
       c.add(receiver_h_lbl);
       c.add(receiver_h_txt);
       c.add(propagation_dist_lbl);
       c.add(propagation_dist_txt);
       
       
       
       
       
       
       
       
         
         
        
        
        
        
         
        
        
        
        
    }
    
        public static void main(String[] args){
            
            Task2 fr = new Task2();
            fr.setVisible(true);
            
            
            
            
        }
    
    
    
}

