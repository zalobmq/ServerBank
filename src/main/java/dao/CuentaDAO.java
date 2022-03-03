package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import conexionBD.Conexion;
import conexionBD.UtilidadXml;
import modelos.Cuenta;
import modelos.Transaccion;
import modelos.Usuario;

public class CuentaDAO extends Cuenta{

	// SENTENCIAS

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static String CREAR_CUENTA = "INSERT INTO cuenta (id,saldo,usuario_id)" + "VALUES (?,?,?)";

	private final static String BORRAR_CUENTA = "DELETE FROM cuenta WHERE id=?";

	private final static String INGRESAR_RETIRAR = "UPDATE cuenta SET saldo=? WHERE id=?";
	
	private final static String CUENTA_X_ID = "SELECT * FROM cuenta WHERE id=?";
		
	Connection con;
	public CuentaDAO() {
		super();
	}

	
	public CuentaDAO(float saldo, Usuario id_usuario,List<Transaccion> lista_Transacciones) {
		super(saldo,id_usuario,lista_Transacciones);
		
	}
	/*
	 * AÑADIR UN USUARIO
	 */
	public int añadir() {
		int result = 0;
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("conexion.xml"));
		if(con != null) {
			
			try {
				PreparedStatement q = con.prepareStatement(CREAR_CUENTA);
				q.setInt(1, this.id);
				q.setFloat(2, this.saldo);
				q.setString(3, this.id_usuario.getId_card());				
				result = q.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/*
	 * BORRAR UN CUENTA.
	 * 
	 * Recibe un String id_card
	 * Devuelve un entero.
	 */
	public int eliminar(int id) {
		int rs=0;
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("conexion.xml"));
		if(con != null) {
			try {
				PreparedStatement q = con.prepareStatement(BORRAR_CUENTA);
				q.setInt(1, id);
				rs = q.executeUpdate();
				this.id=-1;
				this.saldo=0f;
				this.id_usuario=null;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	public int Insertar_Retirar(int id ,boolean opcion , float cantidad) {
		
		//INGRESAR -> true , RETIRAR -> false
		int result = 0;
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("conexion.xml"));
		if(con != null) {
			
			if (opcion) {
				float ingresar = CuentaDAO.getCuentaPorId(id).getSaldo() + cantidad;
				//TRANSACCION DE INGRESAR DINERO
				TransaccionDAO tr = new TransaccionDAO(CuentaDAO.getCuentaPorId(id),cantidad, opcion);
				tr.guardarTrans();
				try {
					PreparedStatement q = con.prepareStatement(INGRESAR_RETIRAR);
					q.setFloat(1, ingresar);
					q.setInt(2, id);
					result = q.executeUpdate();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				
				float ingresar = CuentaDAO.getCuentaPorId(id).getSaldo() - cantidad;
				//TRANSACCION DE RETIRAR DINERO
				TransaccionDAO tr = new TransaccionDAO(CuentaDAO.getCuentaPorId(id),cantidad, opcion);
				tr.guardarTrans();
				try {
					PreparedStatement q = con.prepareStatement(INGRESAR_RETIRAR);
					q.setFloat(1, ingresar);
					q.setInt(2, id);
					result = q.executeUpdate();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	/*
	 * OBTENER UN USUARIO POR ID_CARD
	 * 
	 * Recibe un String id_card
	 * Devuelve un UsuarioDAO
	 */
	public static CuentaDAO getCuentaPorId(int id) {
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("conexion.xml"));
		CuentaDAO result = new CuentaDAO();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(CUENTA_X_ID);
				q.setInt(1, id);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					result.setId(rs.getInt("id"));
					result.setSaldo(rs.getFloat("saldo"));
					result.setId_usuario(UsuarioDAO.getUsuarioPorId_card(rs.getString("usuario_id")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
	

}
