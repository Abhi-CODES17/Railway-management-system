import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class jbutton extends JFrame implements ActionListener {
    JButton button = new JButton("Submit");
    JTextField text = new JTextField();
    // TextField te = new TextField("hi");  
    jbutton()
    {
        
        
        
        button.setBounds(0,150,80,20);
        text.setBounds(100, 150, 250, 50);
        text.setPreferredSize(new Dimension(100, 100));
        button.addActionListener(this);
        this.add(button);
        this.add(text);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.pack();
        
        // 
        this.setSize(400, 400);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button)
        {
            System.out.println("hi");
            String a=text.getText();
            System.out.println(a);
        }
       
    }
    
}
