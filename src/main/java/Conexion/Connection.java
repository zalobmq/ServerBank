package Conexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;

import modelos.Admin;

public class Connection {

	public static void main(String[] args) {

		ServerSocket servidor;
		Socket cliente = null;

		try {
			servidor = new ServerSocket(1995);
			cliente = servidor.accept();
			
			Admin a=new Admin(21,22);
			sendDataToClient(cliente, a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		readServerInputs(cliente);

	}

	private static void readServerInputs(final Socket cliente) {
		new Thread(() -> {
			System.out.println("Server");
			try {
				while (!cliente.isClosed()) {

					leer(cliente);
				}
			} catch (Exception ex) {
				try {
					cliente.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		}).start();

	}

	public static  void leer(Socket cliente) {
		try {
			ObjectInputStream dataInputStream = new ObjectInputStream(cliente.getInputStream());
			try {
				Admin e = (Admin) dataInputStream.readObject();
				System.out.println(e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				cliente.close();
				dataInputStream.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void sendDataToClient(Socket client,Object o) {
        if (client != null && !client.isClosed()) {
            ObjectOutputStream objectOutputStream;
            try {
                objectOutputStream = new ObjectOutputStream(client.getOutputStream());
                objectOutputStream.writeObject(o);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
