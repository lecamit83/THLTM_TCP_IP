import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Net {
	
	public static int PORT = 1234;
	private Socket socket   = null; 
    private ServerSocket server   = null;
    private DataInputStream in =  null;
	public Net(int PORT){
		try {
			// starts server and waits for a connection 
			server = new ServerSocket(PORT); 
			System.out.println("Server started");
			System.out.println("Waiting for a client ...");
			
			socket = server.accept(); 
			System.out.println("Client accepted");
			
			 // driver takes input from the client socket 
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream())); 
			
            //Print 
            // Doing something
            // Get String
            String stringFromClient = in.readUTF();
            // Process string
            stringFromClient = stringFromClient.toUpperCase();
            
            //Sending the response back to the client.
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            bw.write(stringFromClient);
            bw.flush();
            
			System.out.println("Closing connection");
			// close connection 
            socket.close(); 
  
		} catch (Exception e) {
			// TODO: handle exception
			 System.out.println(e);
		}
	}
	public static void main(String[] args) {
		Net s = new Net(PORT);
	}
}
