package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.Client;
import beans.Salle;
import services.ClientService;

/**
 * Servlet implementation class GClientController
 */
@WebServlet("/GClientController")
public class GClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClientService cs = new ClientService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GClientController() {
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
				List<Client> clients = cs.findAllC();
				Gson json = new Gson();
				response.setContentType("application/json");
				response.getWriter().write(json.toJson(clients));

			} else if (request.getParameter("op").equals("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				cs.delete(cs.findById(id));
				response.setContentType("application/json");
				List<Client> clients = cs.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(clients));

			}else if (request.getParameter("op").equals("signin")) {
				String login =request.getParameter("login");
				String pass =request.getParameter("pass");
				String nom =request.getParameter("nom");
				String prenom =request.getParameter("prenom");
				 
				for(Client c : cs.findAll()) {
					if(login.equals(c.getLogin()) && pass.equals(c.getPass())) {
						Client client =c;
						HttpSession session = request.getSession();
						session.setAttribute("login", login);
						session.setAttribute("nom", client.getNom());
						session.setAttribute("prenom",  client.getPrenom());
						session.setAttribute("statut",  client.getStatut());
						session.setAttribute("id",  client.getId());
						
						Gson json = new Gson();
						response.getWriter().write(json.toJson(client));
						
					}
				}
				

			}else if (request.getParameter("op").equals("deco")) {
				request.getSession().invalidate();
				request.removeAttribute("login");
				//response.sendRedirect("auth.jsp");
				

			}
			else if (request.getParameter("op").equals("update")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Client c = cs.findById(id);
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String cin = request.getParameter("cin");
				String login = request.getParameter("login");
				String pass = request.getParameter("pass");

				c.setNom(nom);
				c.setPrenom(prenom);
				c.setCin(cin);
				c.setLogin(login);
				c.setPass(pass);
				cs.update(c);
				response.setContentType("application/json");
				List<Client> clients = cs.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(clients));
			}
		} else {
			if (request.getParameter("nom").equals("") || request.getParameter("prenom").equals("") || request.getParameter("cin").equals("") || request.getParameter("login").equals("") || request.getParameter("pass").equals("")) {
				response.setContentType("application/json");
				List<Client> clients = cs.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(clients));

			} else {
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String cin = request.getParameter("cin");
				String login = request.getParameter("login");
				String pass = request.getParameter("pass");
				Client c = new Client(nom, prenom, cin, login, pass, "Client");
				cs.create(c);
				response.setContentType("application/json");
				List<Client> clients = cs.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(clients));
			}
		}
	}
}
