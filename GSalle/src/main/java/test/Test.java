package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.Client;
import beans.Creneau;
import beans.Event;
import beans.Occupation;
import beans.Salle;
import services.*;

public class Test {
	public static void main(String[] args) throws ParseException {
		
		ClientService cs = new ClientService();
		for(Client c : cs.findAll()) {
			System.out.println(c);
		}
}
}