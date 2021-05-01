
package wirelessproject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Task1 extends JFrame {
    
    private JLabel area_cov_lbl;
    private JLabel cell_typ_lbl;
    private JLabel rad_cell_lbl;
    private JLabel freq_reuse_fact_lbl;
    
    private JTextField area_cov_txt;
    private JTextField cell_typ_txt;
    private JTextField rad_cell_txt;
    private JTextField freq_reuse_fact_txt;
    
    
    private JLabel num_cell_req_lbl;
    private JLabel num_cha_per_cell_lbl;
    private JLabel total_cha_cap_lbl;
    private JLabel poss_concer_cell_lbl;
    
    
    private JTextField num_cell_req_txt;
    private JTextField num_cha_per_cell_txt;
    private JTextField total_cha_cap_txt;
    private JTextField poss_concer_cell_txt;
    
    
    
   
    
    
    private JRadioButton macro,micro;
    
    
    
    private JButton  result_btn;
    
    
    
    
    

    
    
    
    private Font f;
  
    private Container c;
    
    
    Task1(){
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(0,0,1400,1200);
        this.setTitle("Problem 1");
        
        
        c=this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.orange);
        
        f = new Font("Arial", Font.BOLD,25);
        
        
        
        area_cov_lbl = new JLabel();
        area_cov_lbl.setText("Area Size to Cover          :");
        area_cov_lbl.setBounds(350,50,300, 30);
        area_cov_lbl.setFont(f);
        
        
        area_cov_txt = new JTextField();
        area_cov_txt.setBounds(660, 50, 450, 30);
        area_cov_txt.setFont(f);
        
        
        cell_typ_lbl = new JLabel();
        cell_typ_lbl.setText("Cell Type                         :");
        cell_typ_lbl.setBounds(350, 100, 300, 30);
        cell_typ_lbl.setFont(f);
        
        
        
        macro = new JRadioButton();
        macro.setText("Macro Cell");
        macro.setFont(f);
        macro.setBounds(660, 100, 210, 30);
        macro.setBackground(Color.orange);
        
        
        
        
        
        
        
        micro = new JRadioButton();
        micro.setText("Micro Cell");
        micro.setFont(f);
        micro.setBounds(855, 100, 210, 30);
        micro.setBackground(Color.orange);
        
        
        rad_cell_lbl = new JLabel();
        rad_cell_lbl.setText("Radious of Each Cell      :");
        rad_cell_lbl.setBounds(350,150,300, 30);
        rad_cell_lbl.setFont(f);
        
        
        rad_cell_txt = new JTextField();
        rad_cell_txt.setBounds(660, 150, 450, 30);
        rad_cell_txt.setFont(f);
        
        
        
        freq_reuse_fact_lbl = new JLabel();
        freq_reuse_fact_lbl.setText("Frequency Reuse Factor :");
        freq_reuse_fact_lbl.setBounds(350,200,330, 30);
        freq_reuse_fact_lbl.setFont(f);
        
        
        freq_reuse_fact_txt = new JTextField();
        freq_reuse_fact_txt.setBounds(660, 200, 450, 30);
        freq_reuse_fact_txt.setFont(f);
        
        
        result_btn = new JButton();
        result_btn.setText("Find  Result");
        result_btn.setFont(f);
        result_btn.setBounds(500, 270, 400, 40);
        
       
        
        num_cell_req_lbl = new JLabel();
        num_cell_req_lbl.setText("Number of Cells Required :");
        num_cell_req_lbl.setBounds(350,340,360, 30);
        num_cell_req_lbl.setFont(f);
        
        
       num_cell_req_txt = new JTextField();
       num_cell_req_txt.setBounds(700, 340, 450, 30);
       num_cell_req_txt.setFont(f);
       
       
       
        num_cha_per_cell_lbl = new JLabel();
        num_cha_per_cell_lbl.setText("Number of Channels per Cell:");
        num_cha_per_cell_lbl.setBounds(350,390,360, 30);
        num_cha_per_cell_lbl.setFont(f);
        
        
       num_cha_per_cell_txt = new JTextField();
       num_cha_per_cell_txt.setBounds(700, 390, 450, 30);
       num_cha_per_cell_txt.setFont(f);
       
       
       
       total_cha_cap_lbl = new JLabel();
       total_cha_cap_lbl.setText("Total Channel Capacity :");
       total_cha_cap_lbl.setBounds(350,440,360, 30);
       total_cha_cap_lbl.setFont(f);
        
        
       total_cha_cap_txt = new JTextField();
       total_cha_cap_txt.setBounds(700, 440, 450, 30);
       total_cha_cap_txt.setFont(f);
       
       
       
       poss_concer_cell_lbl = new JLabel();
       poss_concer_cell_lbl.setText("Total Possible Concurent Cell :");
       poss_concer_cell_lbl.setBounds(350,490,360, 30);
       poss_concer_cell_lbl.setFont(f);
        
        
       poss_concer_cell_txt = new JTextField();
       poss_concer_cell_txt.setBounds(700, 490, 450, 30);
       poss_concer_cell_txt.setFont(f);
       
       
       
       
       
       
       
       
        
        
        
        
        
        
        
       
        
        
        
        
        
        
        c.add(area_cov_lbl);
        c.add(area_cov_txt);
        c.add(cell_typ_lbl);
        c.add(macro);
        c.add(micro);
        c.add(rad_cell_lbl);
        c.add(rad_cell_txt);
        c.add(freq_reuse_fact_lbl);
        c.add(freq_reuse_fact_txt);
        c.add(result_btn);
        c.add(num_cell_req_lbl);
        c.add(num_cell_req_txt);
        c.add(num_cha_per_cell_lbl);
        c.add(num_cha_per_cell_txt);
        c.add(total_cha_cap_lbl);
        c.add(total_cha_cap_txt);
        c.add(poss_concer_cell_lbl);
        c.add(poss_concer_cell_txt);
        
        
        
       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
         
        
        
        
        
    }
    
        public static void main(String[] args){
            
            Task1 fr = new Task1();
            fr.setVisible(true);
            
            
            
            
        }
    
    
    
}
