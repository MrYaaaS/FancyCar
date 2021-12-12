package fancycar.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fancycar.model.*;
import fancycar.dal.CarModelsDao;

@WebServlet("/findcarmodels")
public class FindCarModels extends HttpServlet  {
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

        List<CarModels> carModels = new ArrayList<CarModels>();
        

        String brand = req.getParameter("brand");
        if (brand == null || brand.trim().isEmpty()) {
            messages.put("success", "Please enter a valid brand.");
        } else {
        	try {
				carModels = carModelsDao.getCarModelsByBrand(brand);
			} catch (SQLException e) {

				e.printStackTrace();
			}

        	messages.put("success", "Displaying results for " + brand);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousFirstName", brand);
        }
        req.setAttribute("Car Models", carModels);
        
        req.getRequestDispatcher("/FindCarModels.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<CarModels> carModels = new ArrayList<CarModels>();
        String brand = req.getParameter("brand").toUpperCase().trim();
        if (brand == null || brand.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	try {
        		carModels = carModelsDao.getCarModelsByBrand(brand);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + brand);
        }
        req.setAttribute("CarModels", carModels);
        
        req.getRequestDispatcher("/FindCarModels.jsp").forward(req, resp);
    }
}
