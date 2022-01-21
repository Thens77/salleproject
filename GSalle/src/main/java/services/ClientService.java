package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import beans.Client;
import beans.Creneau;
import beans.Salle;
import connexion.Connexion;
import dao.IDao;

public class ClientService implements IDao<Client> {

	@Override
	public boolean create(Client o) {
		try {
			String req = "insert into client values (null, ? , ?,?,?,?,?)";
			PreparedStatement pr = Connexion.getInstane().getConnection().prepareStatement(req);
			pr.setString(1, o.getNom());
			pr.setString(2, o.getPrenom());
			pr.setString(3, o.getCin());
			pr.setString(4, o.getLogin());
			pr.setString(5, o.getPass());
			pr.setString(6, o.getStatut());

			if (pr.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("ajout Client echoue");
		}
		return false;
	}

	@Override
	public boolean delete(Client o) {
		String sql = "delete from client where id  = ?";
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
	public boolean update(Client o) {
		String sql = "update client set nom  = ? ,prenom = ? , cin = ? , login = ? , pass = ? where id  = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setString(1,o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getCin());
            ps.setString(4, o.getLogin());
            ps.setString(5, o.getPass());
            ps.setInt(6, o.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("update : erreur sql : " + e.getMessage());

        }
        return false;
	}

	@Override
	public Client findById(int id) {
		Client c = null;
        String sql = "select * from client where id  = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom") , rs.getString("cin"),rs.getString("login"),rs.getString("pass"),rs.getString("statut")
                		);
            }

        } catch (SQLException e) {
            System.out.println("findById " + e.getMessage());
        }
        return null;
	}
	public Client findByLogin(String login) {
		Client c = null;
        String sql = "select * from client where login  = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom") , rs.getString("cin"),rs.getString("login"),rs.getString("pass") , rs.getString("statut")
                		);
            }

        } catch (SQLException e) {
            System.out.println("findById " + e.getMessage());
        }
        return null;
	}

	
	public List<Client> findAllC() {
		 List<Client>  clients = new ArrayList<Client>();

	        String sql = "select * from client where statut='Client'";
	        try {
	            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);;
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                clients.add(new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom") , rs.getString("cin"),rs.getString("login"),rs.getString("pass") ,rs.getString("statut")
	                		));
	            }

	        } catch (SQLException e) {
	            System.out.println("findAll " + e.getMessage());
	        } 
	        return clients;
	}
	public List<Client> findAll() {
		 List<Client>  clients = new ArrayList<Client>();

	        String sql = "select * from client ";
	        try {
	            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);;
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                clients.add(new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom") , rs.getString("cin"),rs.getString("login"),rs.getString("pass") ,rs.getString("statut")
	                		));
	            }

	        } catch (SQLException e) {
	            System.out.println("findAll " + e.getMessage());
	        } 
	        return clients;
	}

}
