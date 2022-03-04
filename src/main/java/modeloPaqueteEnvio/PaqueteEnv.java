package modeloPaqueteEnvio;

import java.io.Serializable;

public class PaqueteEnv implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int opcion;
    protected Object objeto1;
    protected Object objeto2;
    protected boolean comprobante;
    protected float cantidad;
    
    
    public PaqueteEnv() {
        super();
    }

    public PaqueteEnv(int opcion, Object objeto1, boolean comprobante) {
        super();
        this.opcion = opcion;
        this.objeto1 = objeto1;
        this.comprobante = comprobante;
    }

    public PaqueteEnv(int opcion, Object objeto1, Object objeto2, boolean comprobante) {
        super();
        this.opcion = opcion;
        this.objeto1 = objeto1;
        this.objeto2 = objeto2;
        this.comprobante = comprobante;
    }
    
    

    public PaqueteEnv(int opcion, Object objeto1, boolean comprobante, float cantidad) {
		super();
		this.opcion = opcion;
		this.objeto1 = objeto1;
		this.comprobante = comprobante;
		this.cantidad = cantidad;
	}

	public PaqueteEnv(int opcion, Object objeto1, Object objeto2, boolean comprobante, float cantidad) {
		super();
		this.opcion = opcion;
		this.objeto1 = objeto1;
		this.objeto2 = objeto2;
		this.comprobante = comprobante;
		this.cantidad = cantidad;
	}

	public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public Object getObjeto1() {
        return objeto1;
    }

    public void setObjeto1(Object objeto1) {
        this.objeto1 = objeto1;
    }

    public Object getObjeto2() {
        return objeto2;
    }

    public void setObjeto2(Object objeto2) {
        this.objeto2 = objeto2;
    }

    public boolean isComprobante() {
        return comprobante;
    }

    public void setComprobante(boolean comprobante) {
        this.comprobante = comprobante;
    }
    
    

    public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	@Override
    public String toString() {
        return "PaqueteEnv [opcion=" + opcion + ", objeto1=" + objeto1 + ", objeto2=" + objeto2 + ", comprobante="
                + comprobante + "]";
    }
    
    
}