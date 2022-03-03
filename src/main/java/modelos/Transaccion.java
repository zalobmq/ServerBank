package modelos;

import java.sql.Date;
import java.time.LocalDate;

public class Transaccion {

	protected int id;
	protected float importe;
	//INGRESAR -> true , RETIRAR -> false
	protected boolean tipo;
	protected Cuenta id_cuenta;

	public Transaccion() {
		super();
	}

	public Transaccion(Cuenta cuenta,float importe, boolean tipo) {
		super();
		this.id_cuenta = cuenta;
		this.importe = importe;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public boolean isTipo() {
		return tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}

	
	

	public Cuenta getId_cuenta() {
		return id_cuenta;
	}

	public void setId_cuenta(Cuenta id_cuenta) {
		this.id_cuenta = id_cuenta;
	}

	@Override
	public String toString() {
		return "Transaccion [id=" + id + ", importe=" + importe + ", tipo=" + tipo + ", id_cuenta="
				+ id_cuenta + "]";
	}

	

}
