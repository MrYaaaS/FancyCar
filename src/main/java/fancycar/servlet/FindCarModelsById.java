package fancycar.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fancycar.dal.CarModelsDao;
import fancycar.model.*;

@WebServlet("/findcarmodelbyid")
public class FindCarModelsById extends HttpServlet{
	protected CarModelsDao carModelsDao;
	@Override
	public void init() throws ServletException {
		carModelsDao = CarModelsDao.getInstance();
	}
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        CarModels car = null;

        String idString = req.getParameter("id");
        if (idString == null) {
            messages.put("success", "Please enter a valid model id.");
        } else {
        	try {
        		int id = Integer.parseInt(idString);
        		car = carModelsDao.getCarModelById(id);
			} catch (SQLException e) {

				e.printStackTrace();
			}

        	messages.put("success", "Displaying results for " + idString);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousId", idString);
        }
        req.setAttribute("CarModels", car);
        req.getRequestDispatcher("/FindCarModelsById.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        
        CarModels car = null;
        int id = -1;

       String idString = req.getParameter("id");
       if (idString == null) {
           messages.put("success", "Please enter a valid name.");
       } else {
        	
        	try {
        		id = Integer.parseInt(idString);
        		car = carModelsDao.getCarModelById(id);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
           }
        	messages.put("success", "Displaying results for " + idString);
        }
        req.setAttribute("CarModels", car);
        
        req.getRequestDispatcher("/FindCarModelsById.jsp").forward(req, resp);
    }
}

