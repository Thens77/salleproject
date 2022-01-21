package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.lang.model.util.SimpleAnnotationValueVisitor14;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.Client;
import beans.Creneau;
import beans.Event;
import beans.Occupation;
import beans.Salle;
import services.ClientService;
import services.CreneauService;
import services.OccupationService;
import services.SalleService;

/**
 * Servlet implementation class GReservationController
 */
@WebServlet("/GReservationController")
public class GReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SalleService ss = new SalleService() ;
	CreneauService cs = new CreneauService();
	OccupationService os = new OccupationService();
	ClientService cls = new ClientService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GReservationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("op") != null) {
			if (request.getParameter("op").equals("load1")) {
				List<Salle> salles = ss.findAll();
                Gson json = new Gson();
                response.setContentType("application/json");
                response.getWriter().write(json.toJson(salles));
			}
			else if (request.getParameter("op").equals("loadNDemC")) {
				HttpSession session = request.getSession();
				int id =Integer.parseInt(session.getAttribute("id").toString())   ;
				List<Occupation> occupations = os.findNByClient(id);
                Gson json = new Gson();
                response.setContentType("application/json");
                response.getWriter().write(json.toJson(occupations));
			}
			else if (request.getParameter("op").equals("loadAll")) {
				List<Occupation> occupations = os.findAll();
                Gson json = new Gson();
                response.setContentType("application/json");
                response.getWriter().write(json.toJson(occupations));
			}
			else if (request.getParameter("op").equals("loadN")) {
				List<Occupation> newoc = os.findNew();
                Gson json = new Gson();
                response.setContentType("application/json");
                response.getWriter().write(json.toJson(newoc));
			}
			else if (request.getParameter("op").equals("loadDem")) {
				HttpSession session = request.getSession();
				int id =Integer.parseInt(session.getAttribute("id").toString())   ;
				List<Occupation> mesdemandes = os.findByClient(id);
                Gson json = new Gson();
                response.setContentType("application/json");
                response.getWriter().write(json.toJson(mesdemandes));
			}
			else if (request.getParameter("op").equals("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				os.delete(os.findById(id));
				response.setContentType("application/json");
				List<Occupation> occupations = os.findByClient(id);;
				Gson json = new Gson();
				response.getWriter().write(json.toJson(occupations));
			}
			else if (request.getParameter("op").equals("accept")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Occupation o = os.findById(id);
				o.setEtat("accepte");
				os.update(o);
				List<Occupation> newoc = os.findNew();
                Gson json = new Gson();
                response.setContentType("application/json");
                response.getWriter().write(json.toJson(newoc));
			}
			else if (request.getParameter("op").equals("refus")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Occupation o = os.findById(id);
				o.setEtat("refuse");
				os.update(o);
				List<Occupation> newoc = os.findNew();
                Gson json = new Gson();
                response.setContentType("application/json");
                response.getWriter().write(json.toJson(newoc));
			}
			else if (request.getParameter("op").equals("loadDispoC")) {
				int s = Integer.parseInt(request.getParameter("salle"));
				
				List<Creneau> creneaux =null;
				
					try {
						creneaux = cs.findDispo(s , new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
                Gson json = new Gson();
                response.setContentType("application/json");
                response.getWriter().write(json.toJson(creneaux));
			}
			
			else if (request.getParameter("op").equals("load3")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Salle s = ss.findById(id);
                Gson json = new Gson();
                response.setContentType("application/json");
                response.getWriter().write(json.toJson(s));
			}
			else if (request.getParameter("op").equals("load4")) {
				int s = Integer.parseInt(request.getParameter("salle"));
				List<Event> evenements = os.evenements(s);
                Gson json = new Gson();
                response.setContentType("application/json");
                response.getWriter().write(json.toJson(evenements));
			}
		} else {
			String titre = request.getParameter("titre");
			String user = request.getParameter("user");
			int salle =Integer.parseInt(request.getParameter("salle")) ;
            int crenau =Integer.parseInt(request.getParameter("creneau")) ;
            Client client = cls.findByLogin(user);
            Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Occupation o = new Occupation(date, ss.findById(salle), cs.findById(crenau), titre , "nouveau" , client);
            os.create(o);
            response.setContentType("application/json");
            List<Occupation> occupations = os.findAll();
            Gson json = new Gson();
            response.getWriter().write(json.toJson(occupations));
		}
	}

}
