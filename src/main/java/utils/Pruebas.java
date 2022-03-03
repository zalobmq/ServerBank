package utils;

import java.util.ArrayList;
import java.util.List;

import dao.AdminDAO;
import dao.CuentaDAO;
import dao.UsuarioDAO;
import modelos.Admin;
import modelos.Usuario;

public class Pruebas {

	public static void main(String[] args) {
		
	//	AdminDAO a = new AdminDAO();
	//	a = AdminDAO.getAdminPorId(1);
		
		
		//UsuarioDAO u = new UsuarioDAO("2", "Gonzalo", 1234, "1010101E", a);
		//boolean x =	UsuarioDAO.comprobarPin("2", 1234);
		//System.out.println(x);
		
		//AdminDAO a = new AdminDAO(2, 222);
		//a.añadir();
		
		/*
		List<Usuario> lsU = new ArrayList<Usuario>();
		
		lsU = UsuarioDAO.ListarTodos();
		
		System.out.println(lsU.toString());
		*/
		Usuario u= new Usuario("1", 123);
		
		CuentaDAO c = new CuentaDAO(1200, u);
		//c.añadir();
		
		c.Insertar_Retirar(2, true, 2000f);
		//System.out.println(c.verSaldoCuenta());
	}
}
