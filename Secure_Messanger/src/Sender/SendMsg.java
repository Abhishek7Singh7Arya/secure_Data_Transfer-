package Sender;


import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendMsg {

    public void sendMsg(String sample ,String key)  {
        try {
			Socket socket = new Socket("localhost",4000);
			DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
     
			dout.writeUTF(sample);

			dout.writeUTF(key);

			dout.flush();
			dout.close();
			socket.close();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


}
