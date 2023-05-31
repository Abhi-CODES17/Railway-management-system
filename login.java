import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import javax.swing.JFrame;

public class login extends JFrame implements ActionListener {
    JTextField username = new JTextField();
    JPasswordField pass = new JPasswordField();
    JButton button;
    Connection con;
    boolean flag=false;
    JFrame f;
    table ref;
    String u,p;

    login(table t)
    {
      ref=t;
   
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");  
            con=DriverManager.getConnection(  
            "jdbc:oracle:thin:@localhost:1521:xe","system","pccoe");  
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
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
       

        username.setBounds(200, 150, 250, 50);
        username.setPreferredSize(new Dimension(100, 100));
        username.setFont(new Font("Times New Roman", Font.BOLD, 25));
        new TextPrompt("User Id", username);
        pass.setBounds(200, 210, 250, 50);
        pass.setPreferredSize(new Dimension(100, 100));
        pass.setFont(new Font("Times New Roman", Font.BOLD, 25));
        new TextPrompt("Password", pass);

        button=new JButton();
        button.setVerticalAlignment(JButton.BOTTOM);
        button.setBounds(200, 280, 100, 30);
        button.addActionListener(this);
        button.setText("Submit");

        
        f = new JFrame();
		    f.setTitle("Login ...");
        f.setSize(700, 400);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.add(b);
        f.add(button);
        f.add(username);
        f.add(pass);
        f.add(p1);
        f.setBackground(new Color(0x123456));
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button)
        {
          f.dispose();
           u=username.getText();
           p=new String(pass.getPassword());
          
          if(!u.equals("") && !p.equals(""))
          {
            try (Statement stmt = con.createStatement()) {
              ResultSet rs=stmt.executeQuery("select * from tuser where user_id='"+u+"' and password='"+p+"' ");
              // System.out.println("select * from tuser where user_id='"+u+"' and password='"+p+"' ");
              
              while(rs.next())
          {
            // System.out.println(rs.getString(1)+rs.getString(2));
            flag=true;
            break;
          }

          if(flag==true)
          {
            
            JOptionPane.showMessageDialog(null, "Logged in successfully","Validated", JOptionPane.INFORMATION_MESSAGE);
            new option(this);
            // new booking(ref.source,ref.destination,u);
            ref.f.dispose();
            
           

          }
          else{
            JOptionPane.showMessageDialog(null, "Please Enter valid Username And password","LOGIN FAILED", JOptionPane.ERROR_MESSAGE);
            
          }

          
            } catch (SQLException e1) {
              e1.printStackTrace();
            }

          }
          
          
        }
    }
    
}
class TextPrompt extends JLabel implements FocusListener, DocumentListener {
    JTextComponent component;
    Document document;
  
    public TextPrompt(String text, JTextComponent component) {
      this.component = component;
      document = component.getDocument();
  
      setText(text);
      setFont(new Font("Times New Roman", Font.BOLD, 25));
      setBorder(new EmptyBorder(component.getInsets()));
  
      component.addFocusListener((FocusListener) this);
      document.addDocumentListener(this);
  
      component.add(this);

    }

    public void checkForPrompt() {
        if (document.getLength() == 0)
          setSize(component.getSize());
        else
          setSize(0, 0);
      }
    
      public void focusGained(FocusEvent e) {
        checkForPrompt();
      }
    
      public void focusLost(FocusEvent e) {
        setSize(0, 0);
      }
    
    
      public void insertUpdate(DocumentEvent e) {
        checkForPrompt();
      }
    
      public void removeUpdate(DocumentEvent e) {
        checkForPrompt();
      }
    
      public void changedUpdate(DocumentEvent e) {
      }
}