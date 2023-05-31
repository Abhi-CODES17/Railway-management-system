import java.awt.Color;
import java.awt.Dimension;
// import java.awt.FlowLayout;
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
// import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class booked implements ActionListener {
    JButton button;
    JTextField idtrain = new JTextField();
    JTextField seats = new JTextField();
    JLabel m1;
    JLabel m2;
    JFrame f;
    JTable j;
    Connection con;
    String a,b,u;

    booked(String source,String destination,String user)
    {
        a=source;
        b=destination;
        u=user;
	j=new JTable();
    try{ 
        Class.forName("oracle.jdbc.driver.OracleDriver");  
         con=DriverManager.getConnection(  
        "jdbc:oracle:thin:@localhost:1521:xe","system","pccoe");  
        Statement stmt=con.createStatement();   
        ResultSet rs=stmt.executeQuery("select *  from ticket  where source='"+source+"' and destination='"+destination+"' and user_id='"+user+"'");  
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
            String avseat=String.valueOf(rs.getInt(5));
            String row[]={id,rs.getString(2),rs.getString(3),rs.getString(4),avseat};
            model.addRow(row);
        }  
         stmt.close();
        con.close(); 
        }catch(Exception e){ System.out.println(e);}  

        
		f = new JFrame();
		f.setTitle("Cancelling");
		
	j.setBounds(150, 140, 500, 100);
	
	
	JScrollPane sp = new JScrollPane(j);
    sp.setBounds(150, 140, 500, 100);
	
	
	f.setSize(800, 500);
	f.setVisible(true);
    f.setLayout(null);
    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   
        idtrain.setBounds(200, 250, 250, 50);
        idtrain.setPreferredSize(new Dimension(100, 100));
        idtrain.setFont(new Font("Times New Roman", Font.BOLD, 25));
        new TextPrompt("Ticket ID", idtrain);
        
    
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
        p1.setBounds(0,0,800,500);
        JLabel title=new JLabel();
        title.setText("Your Tickets");
        title.setFont(new Font("Times New Roman",Font.ITALIC+Font.BOLD,40));
        title.setBounds(200, 0, 500, 140);
        title.setForeground(Color.white);
        title.setBackground(new Color(0x123456));
        title.setOpaque(true);

        f.add(title);
       
        f.add(b);
        f.add(sp);
        f.add(p1);
       
        
        f.setBackground(new Color(0x123456));
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
    }
    
}
