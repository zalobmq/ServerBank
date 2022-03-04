package Conexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import dao.AdminDAO;
import dao.CuentaDAO;
import dao.TransaccionDAO;
import dao.UsuarioDAO;
import modeloPaqueteEnvio.PaqueteEnv;
import modelos.Admin;
import modelos.Transaccion;
import utils.GeneradorIdCard;

public class Connection {

	public static void main(String[] args) {

		ServerSocket servidor;
		Socket cliente = null;

		try {
			servidor = new ServerSocket(1995);
			cliente = servidor.accept();
			
			readServerInputs(cliente);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

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
				PaqueteEnv paquete = (PaqueteEnv) dataInputStream.readObject();
				System.out.println(paquete);
				
				UsuarioDAO usuarioPaquete1;
				CuentaDAO cuentaPaquete;
				TransaccionDAO transaccionPaquete;
				UsuarioDAO uEnviar;
				PaqueteEnv newPaquete;
				
				int opcion = paquete.getOpcion();
					switch (opcion) {
					//-->	LOGIN
					case 3:
						
						 usuarioPaquete1 = (UsuarioDAO) paquete.getObjeto1();
						 uEnviar = new UsuarioDAO();
						
						boolean x = uEnviar.comprobarPin(usuarioPaquete1.getId_card(), usuarioPaquete1.getPin());
						
						if (x) {
							
							//EXISTE EL USUARIO
							System.out.println("EL USUARIO INTRODUCIDO ES CORRECTO");
							UsuarioDAO usuarioBD = new UsuarioDAO();
							usuarioBD.getUsuarioPorId_card(uEnviar.getId_card());
							
							//Paquete enviado	
							newPaquete = new PaqueteEnv(3, usuarioBD, true);
							
						 	//enviar al cliente
							sendDataToClient(cliente, newPaquete);
							
						}else {
							System.out.println("EL USUARIO INTRODUCIDO ES INCORRECTO");
							
							//Paquete enviado	
							newPaquete = new PaqueteEnv(3, new Object(), false);
							
						 	//enviar al cliente
							sendDataToClient(cliente, newPaquete);
						}
						break;
					// --> REGISTRAR
						
					case 4:
						//usuario recibido
						 usuarioPaquete1 = (UsuarioDAO) paquete.getObjeto1();
						 String id_cardGenerada = 
								 GeneradorIdCard.generador(usuarioPaquete1.getDNI(), usuarioPaquete1.getNombre());
						 System.out.println("SE HA GENERADO UNA NUEVA ID-CARD PARA EL USUARIO REGISTRADO");
						 
						 uEnviar = new UsuarioDAO();
						 uEnviar.setId_card(id_cardGenerada);
						 uEnviar.setId_Admin(AdminDAO.getAdminPorId(1));
						 
						 	uEnviar.añadir();
						 System.out.println("USUARIO AÑADIDO A LA BD");
						 //cuenta creada 
						 cuentaPaquete = new CuentaDAO(0f, uEnviar, new ArrayList<Transaccion>());
						 System.out.println("CUANTA CREADA");
						 	cuentaPaquete.añadir();
						System.out.println("CUANTA AÑADIDA");

						 //Paquete enviado	
						 newPaquete = new PaqueteEnv(4, uEnviar,cuentaPaquete, true);
						 	//enviar al cliente
						 		sendDataToClient(cliente,newPaquete);
				
						break;
						//--> INGRESAR DINERO
					case 6:
						
						cuentaPaquete = (CuentaDAO) paquete.getObjeto1();
						//true es para añadir
						//CUENTA CON EL INCRESO HECHO Y CAMBIADO EN LA BD
						System.out.println("Cuenta obtenida"+cuentaPaquete.toString());

						cuentaPaquete.Insertar_Retirar(cuentaPaquete.getId(), true, paquete.getCantidad());
						System.out.println("INGRESO REALIZADO CORRECTAMENTE");
						System.out.println("Cuenta enviada"+cuentaPaquete.toString());

						//paquete actualizado
						newPaquete = new PaqueteEnv(6, cuentaPaquete, true);
				 		sendDataToClient(cliente,newPaquete);

						
						break;
						// --> RETIRAR DINERO
					case 7:
						cuentaPaquete = (CuentaDAO) paquete.getObjeto1();
						//true es para añadir
						//CUENTA CON EL INCRESO HECHO Y CAMBIADO EN LA BD
						System.out.println("Cuenta obtenida"+cuentaPaquete.toString());
						cuentaPaquete.Insertar_Retirar(cuentaPaquete.getId(), false, paquete.getCantidad());
						System.out.println("INGRESO REALIZADO CORRECTAMENTE");
						System.out.println("Cuenta enviada"+cuentaPaquete.toString());

						//paquete actualizado
						newPaquete = new PaqueteEnv(7, cuentaPaquete, true);
				 		sendDataToClient(cliente,newPaquete);
						break;
						
						// --> TRANSACCIONES
					case 8:
						
						cuentaPaquete = (CuentaDAO) paquete.getObjeto1();
						//Lista de transacciones de una cuenta
						List<Transaccion> ls = TransaccionDAO.ListarTransCuenta(cuentaPaquete);
						cuentaPaquete.setLista_Transacciones(ls);
						System.out.println("LISTA DE TRANSACCIONES OPTENIDA");
						System.out.println("LISTA: "+ls);
						//Paquete con la lista insertada
						newPaquete = new PaqueteEnv(8, cuentaPaquete, true);
				 		sendDataToClient(cliente,newPaquete);
						break;
					default:
						break;
					}
				
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
	
	private static void sendDataToClient(Socket client,Object objeto) {
        if (client != null && !client.isClosed()) {
            ObjectOutputStream objectOutputStream;
            try {
                objectOutputStream = new ObjectOutputStream(client.getOutputStream());
                objectOutputStream.writeObject(objeto);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
