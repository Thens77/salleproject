package beans;

public class Client {

	private int id;
	private String nom;
	private String prenom;
	private String cin;
	private String login ;
	private String pass ;
	private String statut ;

	public Client(int id, String nom, String prenom, String cin  , String login,String pass,String statut) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.login = login ;
		this.pass = pass ;
		this.statut = statut;
		}

	public Client(String nom, String prenom, String cin ,String login, String pass,String statut) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.login = login ;
		this.pass = pass ;
		this.statut = statut;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", login=" + login
				+ ", pass=" + pass + ", statut=" + statut + "]";
	}

	

	
}
