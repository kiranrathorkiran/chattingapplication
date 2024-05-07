
package chattingapplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class Client implements ActionListener {
 JTextField text;
 static JPanel a1;

  static Box virtical = Box.createVerticalBox();
static JFrame JF=new JFrame();
 static DataOutputStream dout;
    Client() {
        JF.setLayout(null);
        
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7,94,84));
        JF.add(p1);
        p1.setBounds(0, 0, 450, 60);
        p1.setLayout(null);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2=i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel back=new JLabel(i3);
        back.setBounds(5,10,20,20);
        p1.add(back);
        
        back.addMouseListener(new MouseAdapter() {
        
        public void mouseClicked(MouseEvent ae)
        {
            System.exit(0);
        }
        });
        
          ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
        Image i5=i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon i6=new ImageIcon(i5);
        JLabel profile=new JLabel(i6);
        profile.setBounds(40,3,50,50);
        p1.add(profile);
        
        
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8=i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel video=new JLabel(i9);
       video.setBounds(270,15,30,30);
       p1.add(video);
        
        
       
       ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11=i10.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i12=new ImageIcon(i11);
        JLabel phone=new JLabel(i12);
       phone.setBounds(320,15,30,30);
       p1.add(phone);
       
       
       ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14=i13.getImage().getScaledInstance(9,30,Image.SCALE_DEFAULT);
        ImageIcon i15=new ImageIcon(i14);
        JLabel dot3=new JLabel(i15);
       dot3.setBounds(400,15,9,30);
       p1.add(dot3);
       
       
       JLabel name=new JLabel("Nidhi");
       name.setBounds(110,10, 100, 20);
       name.setForeground(Color.white);
       name.setFont(new Font("SAN_SARIF",Font.BOLD,18));
       p1.add(name);
       
       
        JLabel staus=new JLabel("Active now");
       staus.setBounds(110,40, 100,10);
       staus.setForeground(Color.white);
      staus.setFont(new Font("SAN_SARIF",Font.BOLD,10));
       p1.add(staus);
       
        a1=new JPanel();
       a1.setBounds(5,80,440,520);
       JF.add(a1);
       
       
       text=new JTextField();
       text.setBounds(5,610,300, 40);
        text.setFont(new Font("SAN_SARIF",Font.BOLD,20));
       JF.add(text);
       
       
       JButton send=new JButton("Send");
       send.setBounds(330,610,100, 40);
       send.setBackground(new Color(7,94,84));
        send.setFont(new Font("SAN_SARIF",Font.BOLD,16));
        send.setForeground(Color.white);
        
        send.addActionListener(this);
       JF.add(send);
       
 
        
       JF.setUndecorated(true);
       JF. setSize(450,650);
        JF.getContentPane().setBackground(Color.white);
        JF.setLocation(500, 50);
        JF.setVisible(true);
    }
    
     public void actionPerformed(ActionEvent e) {
         try{
     String msg= text.getText();
    // System.out.print(msg);
    a1.setLayout(new BorderLayout());
    JLabel output=new JLabel(msg);
    JPanel p2=formet(msg);
  
    a1.setLayout(new BorderLayout());
     JPanel right =new  JPanel(new BorderLayout());
    right.add(p2,BorderLayout.LINE_END);
    virtical.add(right);
    virtical.add(Box.createVerticalStrut(15));
    
    
    a1.add(virtical,BorderLayout.PAGE_START);
       dout.writeUTF(msg);
        
     text.setText("");              
    //repaint();
    //invalidate();
    
    JF.validate();
         }
         catch(Exception e2)
         {e2.printStackTrace();
             
         }
   
    }
     
     public static JPanel formet(String msg)
     {
        JPanel panel =new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        
        JLabel out=new JLabel("<HTML><p Style=\"width:100px\">"+msg+"</P></HTML>");
         out.setFont(new Font("SAN_SARIF",Font.BOLD,16));
         out.setBackground(Color.green);
         out.setOpaque(true);
         out.setBorder(new EmptyBorder(15,15,15,30));
         out.setForeground(Color.white);
        panel.add(out);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat();
        
        JLabel time=new JLabel();
        time.setText(sdf.format(cal.getTime()));
         time.setFont(new Font("SAN_SARIF", Font.BOLD, 10));
        
        panel.add(time);
        return panel;
     }

    public static void main(String ar[]) {
        new Client();
        
        
        
        
         try {
            
            Socket socket=new Socket("127.0.0.1",6001);
            
              DataInputStream din = new DataInputStream(socket.getInputStream());
                 dout = new DataOutputStream(socket.getOutputStream());


                     while (true) {
                         a1.setLayout(new BorderLayout());
                    String msg = din.readUTF();
                    JPanel panel = formet(msg);

                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    virtical.add(left);
                    virtical.add(Box.createVerticalStrut(15));
                    a1.add(virtical,BorderLayout.PAGE_START);
                     JF.validate();
                    
     
        

                }
        } 
         catch (Exception e) {
            e.printStackTrace();
        }

        
    }

  
}
