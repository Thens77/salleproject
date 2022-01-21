package beans;

import java.util.Date;

public class Occupation {
	private int id;
	private Date date;
	private Salle salle;
	private Creneau crenn;
	private String titre ;
	private Client client ;
	private String etat;

	public Occupation(int id, Date date, Salle salle, Creneau crenn,String titre ,  String etat,Client client) {
		super();
		this.id = id;
		this.date = date;
		this.salle = salle;
		this.crenn = crenn;
		this.titre = titre;
		this.client=client;
		this.etat = etat ;
	}

	public Occupation(Date date, Salle salle, Creneau crenn,String titre ,  String etat ,Client client) {
		super();
		this.date = date;
		this.salle = salle;
		this.crenn = crenn;
		this.titre = titre;
		this.client=client;
		this.etat = etat ;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Creneau getCrenn() {
		return crenn;
	}

	public void setCrenn(Creneau crenn) {
		this.crenn = crenn;
	}

	@Override
	public String toString() {
		return "Occupation [id=" + id + ", date=" + date + ", salle=" + salle + ", crenn=" + crenn + ", titre=" + titre
				+ ", client=" + client + ", etat=" + etat + "]";
	}

	
	
	
}
