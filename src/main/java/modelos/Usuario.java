package modelos;

public class Usuario {

	private String id_card;
	private String nombre;
	private int pin;
	private String dni;
	private Admin id_admin;

	public Usuario() {
		super();
	}

	public Usuario(String nombre, int pin, String dni) {
		super();
		this.nombre = nombre;
		this.pin = pin;
		this.dni = dni;
	}

	public Usuario(String id_card, String nombre, int pin, String dni, Admin id_admin) {
		super();
		this.id_card = id_card;
		this.nombre = nombre;
		this.pin = pin;
		this.dni = dni;
		this.id_admin = id_admin;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Admin getId_admin() {
		return id_admin;
	}

	public void setId_admin(Admin id_admin) {
		this.id_admin = id_admin;
	}

	@Override
	public String toString() {
		return "Usuario [id_card=" + id_card + ", nombre=" + nombre + ", pin=" + pin + ", dni=" + dni + ", id_admin="
				+ id_admin + "]";
	}

}
