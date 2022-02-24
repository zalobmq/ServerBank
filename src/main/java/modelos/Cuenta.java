package modelos;

public class Cuenta {

	private int id;
	private float saldo;
	private Usuario id_usuario;

	public Cuenta() {
		super();
	}

	public Cuenta(int id, float saldo, Usuario id_usuario) {
		super();
		this.id = id;
		this.saldo = saldo;
		this.id_usuario = id_usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public Usuario getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Usuario id_usuario) {
		this.id_usuario = id_usuario;
	}

	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", saldo=" + saldo + ", id_usuario=" + id_usuario + "]";
	}

}
