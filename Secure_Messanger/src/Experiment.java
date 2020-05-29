import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

class Experiment{
    public static void main(String[] args) throws IOException {



        ServerSocket ss = new ServerSocket(4000);
        Socket s=ss.accept();//establishes connection
        System.out.println("connected");
        DataInputStream dis=new DataInputStream(s.getInputStream());
        String  str=(String)dis.readUTF();

// create an encoded String to decode

        // print encoded String
        System.out.println("Encoded String:\n"
                + str);

        // decode into String from encoded format
        byte[] actualByte = Base64.getDecoder()
                .decode(str);

        String actualString = new String(actualByte);

        // print actual String
        System.out.println("actual String:\n"
                + actualString);

        ss.close();
    }
}