package modelos;

public class Admin {

	private int id;
	private int pin;

	public Admin() {
		super();
	}

	public Admin(int id, int pin) {
		super();
		this.id = id;
		this.pin = pin;
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

	@Override
	public String toString() {
		return "Admin [id=" + id + ", pin=" + pin + "]";
	}

}
