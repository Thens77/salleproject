package services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import beans.Creneau;
import beans.Salle;
import beans.Occupation;
import connexion.Connexion;
import dao.IDao;

public class CreneauService implements IDao<Creneau> {

	@Override
	public boolean create(Creneau o) {
		 try {
	            String req = "insert into crenau values (null, ? , ?)";
	            PreparedStatement pr = Connexion.getInstane().getConnection().prepareStatement(req);
	            pr.setString(1,o.getDebut());
	            pr.setString(2,o.getFin());
	           
	            if (pr.executeUpdate() == 1) {
	                return true;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            System.out.println("ajout creneu echoue");
	        }
	        return false;
	}

	@Override
	public boolean delete(Creneau o) {
		 String sql = "delete from crenau where id  = ?";
	        try {
	            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
	            ps.setInt(1, o.getId());
	            if (ps.executeUpdate() == 1) {
	                return true;
	            }
	        } catch (SQLException e) {
	            System.out.println("delete : erreur sql : " + e.getMessage());

	        }
	        return false;	}

	@Override
	public boolean update(Creneau o) {
		String sql = "update crenau set heure_debut  = ? ,heure_fin = ? where id  = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setString(1,o.getDebut());
            ps.setString(2, o.getFin());
            ps.setInt(3, o.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("update : erreur sql : " + e.getMessage());

        }
        return false;
	}

	@Override
	public Creneau findById(int id) {
		Salle s = null;
        String sql = "select * from crenau where id  = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Creneau(rs.getInt("id"), new SimpleDateFormat("HH:mm:ss").parse(rs.getString("heure_debut")), new SimpleDateFormat("HH:mm:ss").parse(rs.getString("heure_fin"))
                		);
            }

        } catch (SQLException e) {
            System.out.println("findById " + e.getMessage());
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}

	@Override
	public List<Creneau> findAll() {
		 List<Creneau>  crenns = new ArrayList<Creneau>();

	        String sql = "select * from crenau";
	        try {
	            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);;
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                crenns.add( new Creneau(rs.getInt("id"), new SimpleDateFormat("HH:mm:ss").parse(rs.getString("heure_debut")) ,  new SimpleDateFormat("HH:mm:ss").parse(rs.getString("heure_fin"))
	                		));
	            }

	        } catch (SQLException e) {
	            System.out.println("findAll " + e.getMessage());
	        } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return crenns;
	}
	
	public List<Creneau> findDispo(int salle , java.util.Date d) {
		OccupationService os = new OccupationService();
		 List<Creneau>  dispos = new ArrayList<Creneau>();

		
		
		 for(Creneau c : findAll()) {
			 int check=0;
			// System.out.println(c);
			 for(Occupation o : os.findBySalleDate(salle,d)) {
				  check = 0 ;
				// System.out.println(o);
				 if(     (c.getDebut().compareTo(o.getCrenn().getDebut())<=0 && c.getFin().compareTo(o.getCrenn().getDebut())>0 )|| 
						 (c.getDebut().compareTo(o.getCrenn().getDebut())<=0 && c.getFin().compareTo(o.getCrenn().getFin())>=0 ) ||
						 (c.getDebut().compareTo(o.getCrenn().getFin())<0 && c.getFin().compareTo(o.getCrenn().getFin())>=0 )||
						 (c.getDebut().compareTo(o.getCrenn().getDebut())>=0 && c.getFin().compareTo(o.getCrenn().getFin())<=0 )) {
					// System.out.println("skip");
					 check = 1;
					 break ;
				 }
				 
			 } 
			 
			 if(check==0) {
				 dispos.add(c);
			 }
		 }
	        
	        return dispos;
	}

}
