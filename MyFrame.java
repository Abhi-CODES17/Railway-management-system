import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class MyFrame extends JFrame implements ActionListener {
    JButton button;
    public String sour;
    public String dest;
   
    public boolean flag=false;
    JTextField source = new JTextField();
    JTextField destination = new JTextField();
    MyFrame()
    {

//IRCTC LABEL
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

        //BUTTON SUBMIT
        ImageIcon icon = new ImageIcon(new ImageIcon("img.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        button =new JButton();
        button.setBounds(250,300,150,50);
        button.addActionListener(this);
        button.setText("Search");
        button.setFocusable(false);
        button.setIcon(icon);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setFont(new Font("Times New Roman",Font.BOLD,20));
        button.setIconTextGap(-5);
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(128,128,128));
        button.setBorder(BorderFactory.createEtchedBorder());



        Image logo = Toolkit.getDefaultToolkit().getImage("im.png"); 

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(0x123456));
        p1.setBounds(0,0,620,620);

        JLabel m1=new JLabel();
        JLabel m2=new JLabel();

        m1.setText("Source :-");
        m1.setFont(new Font("Times New Roman",Font.ITALIC,20));
        m1.setBounds(80, 150, 100, 50);
        m1.setForeground(Color.white);
        m1.setBackground(new Color(0x123456));
        m1.setOpaque(true);

        m2.setText("Destination:-");
        m2.setFont(new Font("Times New Roman",Font.ITALIC,18));
        m2.setBounds(80, 210, 100, 50);
        m2.setForeground(Color.white);
        m2.setBackground(new Color(0x123456));
        m2.setOpaque(true);

        JLabel title=new JLabel();
        title.setText("WELCOME...");
        title.setFont(new Font("Times New Roman",Font.ITALIC+Font.BOLD,40));
        title.setBounds(200, 0, 350, 140);
        title.setForeground(Color.white);
        title.setBackground(new Color(0x123456));
        title.setOpaque(true);


        //source text
        source.setBounds(200, 150, 250, 50);
        source.setPreferredSize(new Dimension(100, 100));
        source.setFont(new Font("Times New Roman", Font.BOLD, 25));
        new TextPrompt("Source", source);
        destination.setBounds(200, 210, 250, 50);
        destination.setPreferredSize(new Dimension(100, 100));
        destination.setFont(new Font("Times New Roman", Font.BOLD, 25));
        new TextPrompt("Destination", destination);


        this.setTitle("Book Your Ticket Here");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(620, 620);
        this.setVisible(true);
        this.setIconImage(logo);
        this.setLayout(null);
        this.add(source);
        this.add(destination);
        this.add(button);
        this.add(m1);
        this.add(m2);
        this.add(title);
        this.add(b);
        this.add(p1);
       
       


    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==button)
        {
            // System.out.println("Shri Swmai Samarth");
            sour=source.getText();
            sour=sour.toLowerCase();
            dest=destination.getText();
            dest=dest.toLowerCase();
            System.out.println();
            if(!sour.equals("source") && !dest.equals("destination") && !sour.equals("") && !dest.equals(""))
            {
                flag=true;
                this.dispose();
                table t=new table(this);
                
                // Trains train=new Trains(sour, dest);
                //  this.dispose();

            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid field","Error", JOptionPane.WARNING_MESSAGE);

            }
            // Trains t=new Trains();
            // button.setEnabled(false); for once clicking only
        }
    }
    public boolean up()
    {
        return flag;
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
