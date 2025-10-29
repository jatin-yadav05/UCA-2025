import java.net.*;

public class UDPServer{
	public static void main(String args[]){
		DatagramSocket socket = null;

		try{
			socket = new DatagramSocket(9876);
			byte[] receiveData = new byte[1024];

			System.out.println("UDP Server is running... Waiting for messages...");

			while(true){
				DatagramPacket receivedPacket = new DatagramPacket(receiveData, receiveData.length);
				socket.receive(receivedPacket);

				String clientMsg = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
				System.out.println("Recieved msg from client : "+clientMsg);

				String response = "Hello client, I got you msg : " +clientMsg;
				byte[] sendData = response.getBytes();

				InetAddress clientAddress = receivedPacket.getAddress();
				int clientPort = receivedPacket.getPort();

				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
				socket.send(sendPacket);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(socket!=null && !socket.isClosed()) {
				socket.close();
			}
		}
	}
}

