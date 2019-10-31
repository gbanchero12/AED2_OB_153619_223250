package ObligatorioAlgoritmos2;

public class NodoPunto {
	
	private String tipo;
	private boolean usado;
	private String chipId;
	private double coordX;
	private double coordY;
	
	
	public NodoPunto(String tipo, boolean usado, String chipId, double coordX, double coordY) {
		
		this.tipo = tipo;
		this.usado = usado;
		this.chipId = chipId;
		this.coordX = coordX;
		this.coordY = coordY;
		
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isUsado() {
		return usado;
	}
	public void setUsado(boolean usado) {
		this.usado = usado;
	}
	public String getChipId() {
		return chipId;
	}
	public void setChipId(String chipId) {
		this.chipId = chipId;
	}
	public double getCoordX() {
		return coordX;
	}
	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}
	public double getCoordY() {
		return coordY;
	}
	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}
	
	

}
