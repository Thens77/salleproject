package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Creneau;
import services.CreneauService;

/**
 * Servlet implementation class GCreneauController
 */
@WebServlet("/GCreneauController")
public class GCreneauController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CreneauService cs = new CreneauService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GCreneauController() {
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
		// TODO Auto-generated method stub
		if (request.getParameter("op") != null) {
			if (request.getParameter("op").equals("load")) {

				List<Creneau> creneaux = cs.findAll();
				Gson json = new Gson();
				response.setContentType("application/json");
				response.getWriter().write(json.toJson(creneaux));

			} else if (request.getParameter("op").equals("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				cs.delete(cs.findById(id));
				response.setContentType("application/json");
				List<Creneau> creneaux = cs.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(creneaux));

			} else if (request.getParameter("op").equals("update")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Creneau o = cs.findById(id);
				 SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");  
					Date debut = null;
					Date fin = null ;
					try {
						debut = formatter.parse(request.getParameter("debut")+":00");
						fin = formatter.parse(request.getParameter("fin")+":00");
					} catch (ParseException e) {
						//
						e.printStackTrace();}

				o.setDebut(debut);
				o.setFin(fin);
				cs.update(o);

				response.setContentType("application/json");
				List<Creneau> creneaux = cs.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(creneaux));
			}
		} else {
			if (request.getParameter("debut").equals("") || request.getParameter("fin").equals("")
					) {
				response.setContentType("application/json");
				List<Creneau> creneaux = cs.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(creneaux));

			} else {
				   SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");  
				Date debut = null;
				Date fin = null ;
				try {
					debut = formatter.parse(request.getParameter("debut")+":00");
					fin = formatter.parse(request.getParameter("fin")+":00");
				} catch (ParseException e) {
					//
					e.printStackTrace();
				}
				
				Creneau c = new Creneau(debut, fin);
				cs.create(c);
				response.setContentType("application/json");
				List<Creneau> creneaux = cs.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(creneaux));
			}
		}
	}

}
