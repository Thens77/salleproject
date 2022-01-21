package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Salle;
import services.SalleService;

/**
 * Servlet implementation class GSalleController
 */
@WebServlet( "/GSalleController")
public class GSalleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SalleService ss = new SalleService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GSalleController() {
    	
        super();
        
        // TODO Auto-generated constructor stub
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

    	if (request.getParameter("op") != null) {
    		 if (request.getParameter("op").equals("load")) {
    			 
    			
    			 List<Salle> salles = ss.findAll();
                 Gson json = new Gson();
                 response.setContentType("application/json");
                 response.getWriter().write(json.toJson(salles));
                
    		 }else if(request.getParameter("op").equals("delete")){
                 int id = Integer.parseInt(request.getParameter("id"));
                 ss.delete(ss.findById(id));
                 response.setContentType("application/json");
                 List<Salle> salles = ss.findAll();
                 Gson json = new Gson();
                 response.getWriter().write(json.toJson(salles));
                 
             }else if(request.getParameter("op").equals("update")){
                 int id = Integer.parseInt(request.getParameter("id"));
                 Salle o=  ss.findById(id);
                  String code = request.getParameter("code");
              String capacite = request.getParameter("capacite");
              String type = request.getParameter("type");
                 
                o.setCode(code);
                o.setCapacite(capacite);
                o.setType(type);
                ss.update(o);
                response.setContentType("application/json");
                 List<Salle> salles = ss.findAll();
                  Gson json = new Gson();
                  response.getWriter().write(json.toJson(salles));
              }
    	}else {
    		if(request.getParameter("code").equals("") || request.getParameter("capacite").equals("") || request.getParameter("type").equals("")) {
    			 response.setContentType("application/json");
    			 List<Salle> salles = ss.findAll();
                 Gson json = new Gson();
                 response.getWriter().write(json.toJson(salles));
    			
    		}
    		else {
            String code = request.getParameter("code");
            String capacite = request.getParameter("capacite");
            String type = request.getParameter("type");
            Salle s = new Salle(code, capacite, type);
            ss.create(s);
            response.setContentType("application/json");
            List<Salle> salles = ss.findAll();
            Gson json = new Gson();
            response.getWriter().write(json.toJson(salles));
    		}
        }
	}

}
