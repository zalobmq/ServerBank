package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexionBD.Conexion;
import conexionBD.UtilidadXml;
import modelos.Admin;
import modelos.Usuario;


public class UsuarioDAO extends Usuario{

	//SENTENCIAS SQL 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final  static String AÑADIR = "INSERT INTO usuario (id_card,nombre,pin,dni,id_admin)"
											+ "VALUES (?,?,?,?,?)";
	
	private final static String BORRAR = "DELETE FROM usuario WHERE id_card=?";
	
	private final static String LISTAR_TODOS = "SELECT * FROM usuario";
	
	
	private final static String OBTERNER_X_ID_CARD = "SELECT * FROM usuario WHERE id_card=?";
	
	private final static String COMPROBAR_PIN = "SELECT * FROM usuario WHERE (id_card=?) AND (pin=?)";
	
	//CONSTRUCTORES
	Connection con;
	public UsuarioDAO() {
		
	}
	
	public UsuarioDAO(String id_card, int pin) {
		super(id_card,pin);
	
	}
	
	public UsuarioDAO(String id_card, String nombre, int pin, String dni, Admin admin) {
		super(id_card,nombre,pin,dni,admin);
		
	}
	/*
	 * AÑADIR UN USUARIO
	 */
	public int añadir() {
		int result = 0;
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("conexion.xml"));
		if(con != null) {
			
			try {
				PreparedStatement q = con.prepareStatement(AÑADIR);
				q.setString(1, this.id_card);
				q.setString(2, this.nombre);
				q.setInt(3, this.pin);
				q.setString(4, this.DNI);
				q.setInt(5, this.Admin.getId());
				
				result = q.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	/*
	 * BORRAR UN USUARIO.
	 * 
	 * Recibe un String id_card
	 * Devuelve un entero.
	 */
	public int eliminar(String id_card) {
		int rs=0;
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("conexion.xml"));
		if(con != null) {
			try {
				PreparedStatement q = con.prepareStatement(BORRAR);
				q.setString(1, id_card);
				rs = q.executeUpdate();
				this.id_card="";
				this.nombre="";
				this.pin=-1;
				this.DNI="";
				this.Admin = null;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}
	/*
	 * OBTENER UN USUARIO POR ID_CARD
	 * 
	 * Recibe un String id_card
	 * Devuelve un UsuarioDAO
	 */
	public static UsuarioDAO getUsuarioPorId_card(String id_card) {
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("conexion.xml"));
		UsuarioDAO result = new UsuarioDAO();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(OBTERNER_X_ID_CARD);
				q.setString(1, id_card);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					result.setId_card(rs.getString("id_card"));
					result.setNombre(rs.getString("nombre"));
					result.setPin(rs.getInt("pin"));
					result.setDNI(rs.getString("DNI"));
					result.setId_Admin(AdminDAO.getAdminPorId(rs.getInt("id_Admin")));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
	/*
	 * LISTAR TODOS LOS USUARIOS
	 * 
	 * Devuelve una List de usuarios.
	 */
	public static List<Usuario> ListarTodos(){
		List<Usuario> result = new ArrayList<Usuario>();
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("conexion.xml"));
		if (con != null) {

			try {
				PreparedStatement q = con.prepareStatement(LISTAR_TODOS);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					Usuario cl = new Usuario();
					cl.setId_card(rs.getString("id_card"));
					cl.setNombre(rs.getString("nombre"));
					cl.setPin(rs.getInt("pin"));
					cl.setDNI(rs.getString("DNI"));
					cl.setId_Admin(AdminDAO.getAdminPorId(rs.getInt("id_Admin")));

					result.add(cl);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;
	}
	/*
	 * COMPROBAR SI EL  PIN ES VALIDO
	 * 
	 * Recibe un dos enteros un ID y un PIN.
	 * Devuelve un Boolean true si el usuario con ese id tiene ese pin.
	 * 
	 */
	public static Boolean comprobarPin(String id_card, int pin) {
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("conexion.xml"));
		
		UsuarioDAO a = new UsuarioDAO();
		UsuarioDAO b = new UsuarioDAO(id_card, pin);
		boolean result = false;
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(COMPROBAR_PIN);
				q.setString(1, id_card);
				q.setInt(2, pin);
				ResultSet rs = q.executeQuery();
				
				while (rs.next()) {
					a.setId_card(rs.getString("id_card"));
					a.setPin(rs.getInt("pin"));
				}
				
					if (a.equals(b)) {
						result = true;
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
	
}
