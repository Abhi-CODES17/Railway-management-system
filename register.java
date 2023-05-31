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

public class register extends JFrame implements ActionListener {
    JTextField username = new JTextField();
    JPasswordField pass = new JPasswordField();
    JTextField name = new JTextField();
    JTextField age = new JTextField();
    JTextField phone = new JTextField();
    JButton button;
    Connection con;
    boolean flag=false;
    JFrame f;
    table ref;

    register(table t)
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
        p1.setBounds(0,0,800,500);
       

        username.setBounds(250, 250, 250, 40);
        username.setPreferredSize(new Dimension(100, 100));
        username.setFont(new Font("Times New Roman", Font.BOLD, 25));
        new TextPrompt("User Id", username);
        pass.setBounds(250, 300, 250, 40);
        pass.setPreferredSize(new Dimension(100, 100));
        pass.setFont(new Font("Times New Roman", Font.BOLD, 25));
        new TextPrompt("Password", pass);
        name.setBounds(250, 100, 250, 40);
        name.setPreferredSize(new Dimension(100, 100));
        name.setFont(new Font("Times New Roman", Font.BOLD, 25));
        new TextPrompt("Name", name);
        age.setBounds(250, 150, 250, 40);
        age.setPreferredSize(new Dimension(100, 100));
        age.setFont(new Font("Times New Roman", Font.BOLD, 25));
        new TextPrompt("Age", age);
        phone.setBounds(250, 200, 250, 40);
        phone.setPreferredSize(new Dimension(100, 100));
        phone.setFont(new Font("Times New Roman", Font.BOLD, 25));
        new TextPrompt("phone ", phone);

        button=new JButton();
        button.setVerticalAlignment(JButton.BOTTOM);
        button.setBounds(200, 350, 100, 30);
        button.addActionListener(this);
        button.setText("Submit");
        JLabel m1=new JLabel();
        JLabel m2=new JLabel();
        JLabel m3=new JLabel();
        JLabel m4=new JLabel();
        JLabel m5=new JLabel();

        m1.setText("Name:-");
        m1.setFont(new Font("Times New Roman",Font.ITALIC,20));
        m1.setBounds(80, 150, 100, 50);
        m1.setForeground(Color.white);
        m1.setBackground(new Color(0x123456));
        m1.setOpaque(true);

        m2.setText("Age:-");
        m2.setFont(new Font("Times New Roman",Font.ITALIC,18));
        m2.setBounds(80, 210, 100, 50);
        m2.setForeground(Color.white);
        m2.setBackground(new Color(0x123456));
        m2.setOpaque(true);

        m3.setText("Destination:-");
        m3.setFont(new Font("Times New Roman",Font.ITALIC,18));
        m3.setBounds(80, 210, 100, 50);
        m3.setForeground(Color.white);
        m3.setBackground(new Color(0x123456));
        m3.setOpaque(true);

        m4.setText("Destination:-");
        m4.setFont(new Font("Times New Roman",Font.ITALIC,18));
        m4.setBounds(80, 210, 100, 50);
        m4.setForeground(Color.white);
        m4.setBackground(new Color(0x123456));
        m4.setOpaque(true);

        m5.setText("Destination:-");
        m5.setFont(new Font("Times New Roman",Font.ITALIC,18));
        m5.setBounds(80, 210, 100, 50);
        m5.setForeground(Color.white);
        m5.setBackground(new Color(0x123456));
        m5.setOpaque(true);

        JLabel title=new JLabel();
        title.setText("Registeration...");
        title.setFont(new Font("Times New Roman",Font.ITALIC+Font.BOLD,40));
        title.setBounds(200, 0, 500, 99);
        title.setForeground(Color.white);
        title.setBackground(new Color(0x123456));
        title.setOpaque(true);
        f = new JFrame();
		    f.setTitle("Login ...");
        f.setSize(800, 500);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.add(b);
        f.add(title);
        f.add(button);
        f.add(name);
        f.add(age);
        f.add(phone);
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
          
          String u=username.getText();
          String p=new String(pass.getPassword());
          String n=name.getText();
          String a=age.getText();
          String mo=phone.getText();
          
          Integer ag=Integer.valueOf(a);
          if(mo.length()!=10)
          {
            JOptionPane.showMessageDialog(null, "Please input a valid phone number ","Invalid", JOptionPane.WARNING_MESSAGE);
          }
          
          else if(!u.equals("") && !p.equals("")&&!n.equals(""))
          {
            f.dispose();
                    // JOptionPane.showMessageDialog(null, "  "+seat+"Tickets booked successfully","success", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        con=DriverManager.getConnection(  
      "jdbc:oracle:thin:@localhost:1521:xe","system","pccoe");
                    } catch (SQLException e1) {
                        
                        System.out.println(e1);
                    }
                    try (Statement stmt = con.createStatement()) {

                        // ResultSet rs=stmt.executeQuery("update train_status set avail_seats=avail_seats-"+se+" where train_id="+idtrain.getText());
                        String query = "insert into tuser values(?,?,?,?,?)";
                    PreparedStatement statement;
                    statement = con.prepareStatement(query);
                    // statement.setInt(1,10002);
                        statement.setString(1, u);
                    statement.setString(2, p);
                    statement.setString(3,mo);
                    statement.setString(4,n);
                    statement.setInt(5,ag);
                    statement.execute();
                    con.close();
                    } catch (SQLException e1) {
                        System.out.println(e1);
                        
                    } 
                    JOptionPane.showMessageDialog(null, "Account created successfully","tile", JOptionPane.INFORMATION_MESSAGE);
                    

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