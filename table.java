import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class table implements ActionListener {
    JButton button;
    JButton blogin;
    String source;
    String destination;
    JFrame f;
    table(MyFrame a)
    {
    source=a.sour;
     destination = a.dest;

    
	JTable j=new JTable();
    try{ 
        Class.forName("oracle.jdbc.driver.OracleDriver");  
        Connection con=DriverManager.getConnection(  
        "jdbc:oracle:thin:@localhost:1521:xe","system","pccoe");  
        Statement stmt=con.createStatement();   
        ResultSet rs=stmt.executeQuery("select * from train join route using(train_id) where boarding_station='"+a.sour+"' and destination_station='"+a.dest+"'");  
        ResultSetMetaData rsmd=rs.getMetaData();
        DefaultTableModel model=(DefaultTableModel) j.getModel();
        int cols=rsmd.getColumnCount();
        String []columnname=new String[cols];
        for(int i=0;i<cols;i++)
        {
            columnname[i]=rsmd.getColumnName(i+1);
            
        }
        model.setColumnIdentifiers(columnname);

        while(rs.next())
        {
            // System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)); 
            String id=String.valueOf(rs.getInt(1));
            String row[]={id,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)};
            model.addRow(row);
        }  
         stmt.close();
        con.close(); 
        }catch(Exception e){ System.out.println(e);}  

        
		f = new JFrame();
		f.setTitle("Available Trains....");
		
	j.setBounds(150, 140, 450, 100);
	
	
	JScrollPane sp = new JScrollPane(j);
    sp.setBounds(150, 140, 450, 100);
	
	
	f.setSize(700, 400);
	f.setVisible(true);
    f.setLayout(null);
    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   
    button=new JButton();
    button.setText("REGISTER");
    button.setVerticalAlignment(JButton.BOTTOM);
    button.setBounds(150, 300, 100, 30);
    button.addActionListener(this);
    blogin=new JButton();
    blogin.setText("LOGIN");
    blogin.setVerticalAlignment(JButton.BOTTOM);
    blogin.setBounds(300, 300, 100, 30);
    blogin.addActionListener(this);
    
    ImageIcon imageIcon = new ImageIcon(new ImageIcon("im.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        Border border=BorderFactory.createLineBorder(new Color(0x123456),2, true);
        JLabel b=new JLabel();
        b.setIcon(imageIcon);
        b.setText("vasudha eva kutumba");
        b.setBackground(new Color(0x123440));
        b.setOpaque(true);
        b.setForeground(Color.white);
        b.setFont(new Font("Times New Roman",Font.ITALIC,15));
        b.setVerticalTextPosition(JLabel.BOTTOM);
        b.setHorizontalTextPosition(JLabel.CENTER);
        b.setVerticalAlignment(JLabel.TOP);
        b.setHorizontalAlignment(JLabel.LEFT);
        b.setBorder(border);
        b.setBounds(0,0,150,140);
        
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(0x123456));
        p1.setBounds(0,0,700,400);

        JLabel title=new JLabel();
        title.setText("TRAINS AVAILABLE");
        title.setFont(new Font("Times New Roman",Font.ITALIC+Font.BOLD,40));
        title.setBounds(200, 0, 500, 140);
        title.setForeground(Color.white);
        title.setBackground(new Color(0x123456));
        title.setOpaque(true);
        
        f.add(b);
        f.add(sp);
        f.add(button);
        f.add(title);
        f.add(blogin);
        f.add(p1);
       
        
        f.setBackground(new Color(0x123456));
        JOptionPane.showMessageDialog(null, "Pleasse Login to continue booking...","NOTE", JOptionPane.INFORMATION_MESSAGE);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button)
        {
            new register(this);
        }
        else if(e.getSource()==blogin)
        {
            
            login l=new login(this);
            f.dispose();
            
        }
    }
    
}
