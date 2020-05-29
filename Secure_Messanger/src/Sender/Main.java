package Sender;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Base64;


public class Main  implements ActionListener {
    JFrame frame;
    String BasicBase64format;
    JLabel txthead;
    JPanel btnpanel;
    JButton btnsend,btnselect,btnencode;
    GridBagLayout grid;
    GridBagConstraints gbc;
    JTextArea txtarea;
    String key;

    public Main() {

        btnencode = new JButton("ENCODE");
        btnsend = new JButton("SEND");
        btnselect = new JButton("SELECT FILE");
        btnencode.addActionListener(this);
        btnselect.addActionListener(this);
        btnsend.addActionListener(this);
        txtarea = new JTextArea("data shows here");



        txtarea.setSize(300,400);
        txtarea.setLocation(10,20);
        txtarea.setEditable(false);

        txtarea.setLineWrap(true);

        btnpanel = new JPanel();

         grid = new GridBagLayout();
         gbc = new GridBagConstraints();
        btnpanel.setLayout( grid);
        gbc.fill= GridBagConstraints.HORIZONTAL;
        gbc.ipady=10;
        gbc.weighty=1;
        gbc.gridx = 0;
        gbc.gridy =0;
        btnpanel.add(btnselect,gbc);
        gbc.gridy = 1;
        btnpanel.add(btnencode,gbc);
        gbc.gridy = 2;
        btnpanel.add(btnsend,gbc);

        frame = new JFrame("sender");
 
     
        frame.getContentPane().setLayout(new BorderLayout());
        txthead = new JLabel("SEND DATA");
        txthead.setFont(txthead.getFont().deriveFont(34.0f));
        txthead.setHorizontalAlignment(SwingConstants.CENTER);

    


        frame.add(txtarea,BorderLayout.CENTER);
        frame.add(txthead,BorderLayout.NORTH);
       
        frame.add(btnpanel,BorderLayout.WEST);
        frame.setSize(600,500);
        frame.setVisible(true);
        frame.setResizable(true);
        
        JScrollPane scrollableTextArea = new JScrollPane(txtarea);  
        
       // scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
  
        frame.getContentPane().add(scrollableTextArea);  
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String []args){
        new Main();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnselect){
            txtarea.setText("");
            FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String file = dialog.getFile();
            String path = dialog.getDirectory();

            try {
                FileReader fr = new FileReader(path+file);
                BufferedReader in = new BufferedReader(fr);

                String line;
                while((line=in.readLine())!=null){

                 txtarea.append(line+"\n");

                }


                fr.close();
                in.close();
            }
            catch (Exception ex){
                System.out.println(ex);
            }
        }
        ////encoding button///
        if ( e.getSource() == btnencode){
             BasicBase64format = Base64.getEncoder().encodeToString(txtarea.getText().getBytes());
            txtarea.setText(BasicBase64format);
        }
        if(e.getSource()==btnsend){
            SendMsg sm = new SendMsg();
            key =JOptionPane.showInputDialog(frame,"enter the unlock key");
            sm.sendMsg(BasicBase64format,key);
			JOptionPane.showMessageDialog(frame,"message Sent");
        }
    }
}

