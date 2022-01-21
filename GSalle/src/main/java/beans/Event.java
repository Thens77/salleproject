package beans;

public class Event {
	private int id;
	private String titre;
	private String date;
	private String debut;
	private String fin;

	public Event(int id, String titre ,String date, String debut, String fin) {
		super();
		this.id = id;
		this.titre = titre;
		this.date = date ;
		this.debut = debut;
		this.fin = fin;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDebut() {
		return debut;
	}

	public void setDebut(String debut) {
		this.debut = debut;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", titre=" + titre + ", date=" + date + ", debut=" + debut + ", fin=" + fin + "]";
	}

	
	

}
