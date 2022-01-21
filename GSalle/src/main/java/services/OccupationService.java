package services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import beans.Event;
import beans.Occupation;
import beans.Salle;
import connexion.Connexion;
import dao.IDao;

public class OccupationService implements IDao<Occupation> {

	@Override
	public boolean create(Occupation o) {
		try {
			String req = "insert into occupation values (null,?,?,?,?,?,?)";
			PreparedStatement pr = Connexion.getInstane().getConnection().prepareStatement(req);
			pr.setDate(1, new Date(o.getDate().getTime()));
			pr.setInt(2, o.getSalle().getId());
			pr.setInt(3, o.getCrenn().getId());
			pr.setString(4, o.getTitre());
			pr.setInt(5, o.getClient().getId());
			pr.setString(6, o.getEtat());
			if (pr.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Create : Erreur" + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean delete(Occupation o) {
		String sql = "delete from occupation where id  = ?";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, o.getId());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("delete : erreur sql : " + e.getMessage());

		}
		return false;
	}

	@Override
	public boolean update(Occupation o) {
		String sql = "update occupation set date  = ? ,idsalle = ? , idcrenau = ? , titre=? , idclient= ? , etat= ? where id  = ?";
		try {
			PreparedStatement pr = Connexion.getInstane().getConnection().prepareStatement(sql);
			pr.setDate(1, new Date(o.getDate().getTime()));
			pr.setInt(2, o.getSalle().getId());
			pr.setInt(3, o.getCrenn().getId());
			pr.setString(4, o.getTitre());
			
			pr.setInt(5, o.getClient().getId());
			pr.setString(6, o.getEtat());
			pr.setInt(7, o.getId());
			if (pr.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("update : erreur sql : " + e.getMessage());

		}
		return false;
	}

	@Override
	public Occupation findById(int id) {
		Occupation o = null;
		String sql = "select * from occupation where id  = ?";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			CreneauService cs = new CreneauService();
			SalleService ss = new SalleService();
			ClientService clts=new ClientService();
			if (rs.next()) {
				return new Occupation(rs.getInt("id"), rs.getDate("Date"), ss.findById(rs.getInt("idSalle")),
						cs.findById(rs.getInt("idCrenau")), rs.getString("titre"),rs.getString("etat"),clts.findById(rs.getInt("idclient")));
			}

		} catch (SQLException e) {
			System.out.println("findById " + e.getMessage());
		}
		return null;
	}
	public List<Occupation> findBySalleDate(int salle, java.util.Date date) {
		List<Occupation> occupations = new ArrayList<Occupation>();

		String sql = "select * from occupation where idSalle=? and date=? and etat='accepte'";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, salle);
			ps.setDate(2, new Date (date.getTime()));
			ResultSet rs = ps.executeQuery();
			CreneauService cs = new CreneauService();
			ClientService clts=new ClientService();
			SalleService ss = new SalleService();
			while (rs.next()) {
				occupations.add(new Occupation(rs.getInt("id"), rs.getDate("Date"), ss.findById(rs.getInt("idSalle")),
						cs.findById(rs.getInt("idCrenau")), rs.getString("titre"),rs.getString("etat"),clts.findById(rs.getInt("idclient"))));
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		return occupations;
	}
	public ArrayList<HashMap<String, Integer>> findByDate(int year) {
		//List<Occupation> occupations = new ArrayList<Occupation>();
		ArrayList<HashMap<String, Integer>> dataMap = new ArrayList<HashMap<String, Integer>>();
		
		HashMap<String, Integer> data1 = new HashMap<String, Integer>();
		String sql = "select count(id) as nombre , extract(month from date) as mois from occupation where extract(year from date)=? and etat='accepte' group by extract(month from date)";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, year);
			
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {
				HashMap<String, Integer> data = new HashMap<String, Integer>();
				data.put("mois" ,rs.getInt("mois"));
				data.put("nombre", rs.getInt("nombre"));
				dataMap.add(data);
				//dataMap.add(data1);
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		return dataMap;
	}
	public ArrayList<HashMap<String, Integer>> findBySalleYear(int salle , int year) {
		//List<Occupation> occupations = new ArrayList<Occupation>();
		ArrayList<HashMap<String, Integer>> dataMap = new ArrayList<HashMap<String, Integer>>();
		
		HashMap<String, Integer> data1 = new HashMap<String, Integer>();
		String sql = "select count(id) as nombre , extract(month from date) as mois from occupation where extract(year from date)=? and idsalle=? and etat='accepte' group by extract(month from date)";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, year);
			ps.setInt(2, salle);
			
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {
				HashMap<String, Integer> data = new HashMap<String, Integer>();
				data.put("mois" ,rs.getInt("mois"));
				data.put("nombre", rs.getInt("nombre"));
				dataMap.add(data);
				//dataMap.add(data1);
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		return dataMap;
	}

	@Override
	public List<Occupation> findAll() {
		List<Occupation> occupations = new ArrayList<Occupation>();

		String sql = "select * from occupation";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			;
			ResultSet rs = ps.executeQuery();
			CreneauService cs = new CreneauService();
			ClientService clts=new ClientService();
			SalleService ss = new SalleService();
			while (rs.next()) {
				occupations.add(new Occupation(rs.getInt("id"), rs.getDate("Date"), ss.findById(rs.getInt("idSalle")),
						cs.findById(rs.getInt("idCrenau")), rs.getString("titre"),rs.getString("etat") , clts.findById(rs.getInt("idclient"))));
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		return occupations;
	}
	public List<Occupation> findNew() {
		List<Occupation> occupations = new ArrayList<Occupation>();

		String sql = "select * from occupation where etat = 'nouveau'";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			;
			ResultSet rs = ps.executeQuery();
			CreneauService cs = new CreneauService();
			ClientService clts=new ClientService();
			SalleService ss = new SalleService();
			while (rs.next()) {
				occupations.add(new Occupation(rs.getInt("id"), rs.getDate("Date"), ss.findById(rs.getInt("idSalle")),
						cs.findById(rs.getInt("idCrenau")), rs.getString("titre"), rs.getString("etat") , clts.findById(rs.getInt("idclient"))));
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		return occupations;
	}
	public List<Occupation> findByClient(int id) {
		List<Occupation> occupations = new ArrayList<Occupation>();

		String sql = "select * from occupation where idclient = ? ";
		
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			CreneauService cs = new CreneauService();
			ClientService clts=new ClientService();
			SalleService ss = new SalleService();
			while (rs.next()) {
				occupations.add(new Occupation(rs.getInt("id"), rs.getDate("Date"), ss.findById(rs.getInt("idSalle")),
						cs.findById(rs.getInt("idCrenau")), rs.getString("titre"),rs.getString("etat") , clts.findById(rs.getInt("idclient"))));
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		return occupations;
	}
	public List<Occupation> findNByClient(int id) {
		List<Occupation> occupations = new ArrayList<Occupation>();

		String sql = "select * from occupation where idclient = ? and etat = 'nouveau' ";
		
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			CreneauService cs = new CreneauService();
			ClientService clts=new ClientService();
			SalleService ss = new SalleService();
			while (rs.next()) {
				occupations.add(new Occupation(rs.getInt("id"), rs.getDate("Date"), ss.findById(rs.getInt("idSalle")),
						cs.findById(rs.getInt("idCrenau")), rs.getString("titre"),rs.getString("etat") , clts.findById(rs.getInt("idclient"))));
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		return occupations;
	}
	
	public List<Event> evenements(int s) {
		List<Event> evenements = new ArrayList<Event>();

		String sql = "SELECT occupation.id,titre , idSalle , idCrenau,date  , heure_debut , heure_fin , etat from occupation, crenau where occupation.idCrenau = crenau.id and idsalle = ? and etat='accepte'";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, s);
			ResultSet rs = ps.executeQuery();
			CreneauService cs = new CreneauService();
			SalleService ss = new SalleService();
			while (rs.next()) {
				evenements.add(new Event(rs.getInt("id"),rs.getString("titre"),rs.getString("date"), rs.getString("heure_debut"),rs.getString("heure_fin")));
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		return evenements;
	}
	
	
}
