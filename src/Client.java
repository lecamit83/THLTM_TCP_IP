import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	// initialize socket and input output streams 
    private Socket socket = null;
    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream = null;
    
    
    public Client(String ADDRESS, int PORT){
    	 // establish a connection 
    	try {
    		socket = new Socket(ADDRESS, PORT);
    		System.out.println("Connected");
    		// driver to takes data from terminal
    		dataInputStream = new DataInputStream(System.in);
    		// driver to send data to Server
    		dataOutputStream = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
    	// String value after read from driver
//    	String s = "";
//    	while (s.length() == 0) {
//    		try {
//    			// update String
//    			s = dataInputStream.readLine().toString();
//    			// deliver String to Server with UTF format 
//				dataOutputStream.writeUTF(s);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//		}
    	try {
			// Send message to the Server
    		dataOutputStream.writeUTF("xin chao");
			dataOutputStream.flush();
			dataOutputStream.writeUTF("XIN CHAO");
			dataOutputStream.flush();
			
			// Get the return message from the server
			InputStream is = socket.getInputStream();
			InputStreamReader isReader = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isReader);
			String resultMessage = br.readLine();
			System.out.println(resultMessage);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	
    	 // close the connection 
    	try {
			dataInputStream.close();
			dataOutputStream.close();
	    	socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    public static void main(String[] args) {
		Client c = new Client("127.0.0.1", Net.PORT);
	}
}
