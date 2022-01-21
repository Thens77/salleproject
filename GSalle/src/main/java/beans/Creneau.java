package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Creneau {

	private int id  ; 
	private Date debut ;
	private Date fin;
	
	public Creneau(int id, Date debut, Date fin) {
		super();
		this.id = id;
		this.debut = debut;
		this.fin = fin;
	}
	
	public Creneau(Date debut, Date fin) {
		super();
		this.debut = debut;
		this.fin = fin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDebut() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String d  = sdf.format(debut);
		return d;
	}
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	public String getFin() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String d  = sdf.format(fin);
		return d;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	@Override
	public String toString() {
		return "Crenn [id=" + id + ", debut=" + getDebut() + ", fin=" + getFin() + "]";
	}
	
	
}
