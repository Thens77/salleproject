package beans;

public class Salle {
	
	private int id ;
	private String code;
	private String capacite ;
	private String type ;
	
	public Salle(String code, String capacite, String type) {
		super();
		this.code = code;
		this.capacite = capacite;
		this.type = type;
	}
	public Salle(int id, String code, String capacite, String type) {
		super();
		this.id = id;
		this.code = code;
		this.capacite = capacite;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCapacite() {
		return capacite;
	}
	public void setCapacite(String capacite) {
		this.capacite = capacite;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Salle [id=" + id + ", code=" + code + ", capacite=" + capacite + ", type=" + type + "]";
	}
	
	

}
