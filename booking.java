import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class booking implements ActionListener {
    JButton button;
    JTextField idtrain = new JTextField();
    JTextField seats = new JTextField();
    JLabel m1;
    JLabel m2;
    JFrame f;
    JTable j;
    Connection con;
    String a,b,u;

    booking(String source,String destination,String user)
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
        ResultSet rs=stmt.executeQuery("select train_id ,train_name,boarding_station,arrival_time,departure_time,avail_seats  from train join route using (train_id) join train_status using(train_id) where boarding_station='"+source+"' and destination_station='"+destination+"'");  
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
            String avseat=String.valueOf(rs.getInt(6));
            String row[]={id,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),avseat};
            model.addRow(row);
        }  
         stmt.close();
        con.close(); 
        }catch(Exception e){ System.out.println(e);}  

        
		f = new JFrame();
		f.setTitle("Available Trains....");
		
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
        new TextPrompt("Train ID", idtrain);
        seats.setBounds(200, 310, 250, 50);
        seats.setPreferredSize(new Dimension(100, 100));
        seats.setFont(new Font("Times New Roman", Font.BOLD, 25));
        new TextPrompt("Total Seats", seats);
    
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

         m1=new JLabel();
         m2=new JLabel();

        m1.setText("Train Id :-");
        m1.setFont(new Font("Times New Roman",Font.ITALIC,20));
        m1.setBounds(50, 250, 100, 50);
        m1.setForeground(Color.white);
        m1.setBackground(new Color(0x123456));
        m1.setOpaque(true);

        m2.setText("Seats :-");
        m2.setFont(new Font("Times New Roman",Font.ITALIC,18));
        m2.setBounds(50, 310, 100, 50);
        m2.setForeground(Color.white);
        m2.setBackground(new Color(0x123456));
        m2.setOpaque(true);

        button=new JButton();
        button.setVerticalAlignment(JButton.BOTTOM);
        button.setBounds(50, 400, 100, 30);
        button.addActionListener(this);
        button.setText("BOOK");
        
        JLabel title=new JLabel();
        title.setText("Confirm \n booking");
        title.setFont(new Font("Times New Roman",Font.ITALIC+Font.BOLD,40));
        title.setBounds(200, 0, 500, 140);
        title.setForeground(Color.white);
        title.setBackground(new Color(0x123456));
        title.setOpaque(true);

        f.add(title);
        f.add(button);
        f.add(b);
        f.add(sp);
        f.add(idtrain);
        f.add(seats);
        f.add(m1);
        f.add(m2);
        f.add(p1);
       
        
        f.setBackground(new Color(0x123456));
        // JOptionPane.showMessageDialog(null, "Pleasse Login or Register to continue booking...","NOTE", JOptionPane.INFORMATION_MESSAGE);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button)
        {
            int i=0;
            String se=seats.getText();
            Integer seat=Integer.valueOf(se);
            
            
            i=JOptionPane.showConfirmDialog(null, "Please Confirm Train ID and Number of seats", "Confirm sourc and destination", JOptionPane.YES_NO_CANCEL_OPTION);
         
            if(i==0)
            {
                if(seat>6 )
                {
                    JOptionPane.showMessageDialog(null, "Number of seats to be booked must be less than 6.","Not Valid Entry", JOptionPane.WARNING_MESSAGE);
                    
                }
                else
                {
                    f.dispose();
                    JOptionPane.showMessageDialog(null, "  "+seat+"Tickets booked successfully","success", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        con=DriverManager.getConnection(  
      "jdbc:oracle:thin:@localhost:1521:xe","system","pccoe");
                    } catch (SQLException e1) {
                        
                        System.out.println(e1);
                    }
                    try (Statement stmt = con.createStatement()) {

                        ResultSet rs=stmt.executeQuery("update train_status set avail_seats=avail_seats-"+se+" where train_id="+idtrain.getText());
                        String query = "insert into ticket values(ticket_id.nextval,?,?,?,?)";
                    PreparedStatement statement;
                    statement = con.prepareStatement(query);
                    // statement.setInt(1,10002);
                        statement.setString(1, u);
                    statement.setString(2, a);
                    statement.setString(3,b);
                    statement.setInt(4,seat);
                    statement.execute();
                    con.close();
                    } catch (SQLException e1) {
                        System.out.println(e1);
                        
                    } 
                    
                   
                    


                }
            }

            
        }
        
    }
    
}
