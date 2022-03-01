package modelos;

import java.util.ArrayList;
import java.util.List;

public class Admin {

	protected int id;
	protected int pin;
	protected List<Usuario> lista_usuarios;
	public Admin() {
		super();
	}

	public Admin(int id, int pin) {
		super();
		this.id = id;
		this.pin = pin;
		this.lista_usuarios = new ArrayList<Usuario>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}
	

	public List<Usuario> getLista_usuarios() {
		return lista_usuarios;
	}

	public void setLista_usuarios(List<Usuario> lista_usuarios) {
		this.lista_usuarios = lista_usuarios;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", pin=" + pin + ", lista_usuarios=" + lista_usuarios + "]";
	}

	

}
