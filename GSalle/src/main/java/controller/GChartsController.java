package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Occupation;
import beans.Salle;
import services.OccupationService;
import services.SalleService;

/**
 * Servlet implementation class GChartsController
 */
@WebServlet("/GChartsController")
public class GChartsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OccupationService os = new OccupationService();
	SalleService ss = new SalleService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GChartsController() {
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
			if (request.getParameter("op").equals("loadByYear") ){
				int year = Integer.parseInt(request.getParameter("year"));
				ArrayList<HashMap<String, Integer>> occ = os.findByDate(year);

				Gson json = new Gson();
				String jsonString = json.toJson(occ);

				response.setContentType("application/json");

				response.getWriter().write(jsonString);

			} else if (request.getParameter("op").equals("load1")) {
				List<Salle> salles = ss.findAll();
				Gson json = new Gson();
				response.setContentType("application/json");
				response.getWriter().write(json.toJson(salles));
			}else if (request.getParameter("op").equals("loadBySalleYear")) {
				List<Occupation> occupations = os.findAll();
				int salle = Integer.parseInt(request.getParameter("salle"));
				int year = Integer.parseInt(request.getParameter("year"));
				ArrayList<HashMap<String, Integer>> occ = os.findBySalleYear(salle, year);
				Gson json = new Gson();
				String jsonString = json.toJson(occ);

				response.setContentType("application/json");

				response.getWriter().write(jsonString);
			}
		} 
	}
}
