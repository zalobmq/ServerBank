package modelos;

public class Cuenta {

	protected int id;
	protected float saldo;
	protected Usuario id_usuario;

	public Cuenta() {
		super();
	}

	
	public Cuenta(float saldo, Usuario id_usuario) {
		super();
		this.saldo = saldo;
		this.id_usuario = id_usuario;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", saldo=" + saldo + ", id_usuario=" + id_usuario + "]";
	}

}
