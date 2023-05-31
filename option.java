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

import javax.swing.JFrame;

public class option extends JFrame implements ActionListener {
    JTextField username = new JTextField();
    JTextField pass = new JTextField();
    JButton button;
    JButton button2;
    JButton button3;
    Connection con;
    boolean flag=false;
    JFrame f;
    login ref;

    option(login t)
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
       

        // username.setBounds(200, 150, 250, 50);
        // username.setPreferredSize(new Dimension(100, 100));
        // username.setFont(new Font("Times New Roman", Font.BOLD, 25));
        // new TextPrompt("User Id", username);
        // pass.setBounds(200, 210, 250, 50);
        // pass.setPreferredSize(new Dimension(100, 100));
        // pass.setFont(new Font("Times New Roman", Font.BOLD, 25));
        // new TextPrompt("Password", pass);

        button=new JButton();
        button.setVerticalAlignment(JButton.BOTTOM);
        button.setBounds(250, 140, 200, 50);
        button.addActionListener(this);
        button.setText("BOOK TICKET");
        button2=new JButton();
        button2.setVerticalAlignment(JButton.BOTTOM);
        button2.setBounds(250, 200, 200, 50);
        button2.addActionListener(this);
        button2.setText("CANCEL TICKET");
        button3=new JButton();
        button3.setVerticalAlignment(JButton.BOTTOM);
        button3.setBounds(250, 260, 200, 50);
        button3.addActionListener(this);
        button3.setText("VIEW TICKETS");

        
        f = new JFrame();
		    f.setTitle("Login ...");
        f.setSize(700, 400);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.add(b);
        f.add(button);
        f.add(button2);
        f.add(button3);
        // f.add(username);
        // f.add(pass);
        f.add(p1);
        f.setBackground(new Color(0x123456));
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button)
        {
          
          new booking(ref.ref.source,ref.ref.destination,ref.u);
        }
        if(e.getSource()==button2)
        {
            
            new cancel_ticket(ref.ref.source,ref.ref.destination,ref.u);

        }
        if(e.getSource()==button3)
        {
         
          new booked(ref.ref.source,ref.ref.destination,ref.u);
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