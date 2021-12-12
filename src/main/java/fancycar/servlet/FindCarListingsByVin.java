package fancycar.servlet;

import fancycar.dal.CarListingsDao;
import fancycar.dal.CarModelsDao;
import fancycar.model.CarListings;
import fancycar.model.CarModels;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/findcarlistingsbyvin")
public class FindCarListingsByVin extends HttpServlet {
    protected CarListingsDao carListingsDao;
    @Override
    public void init() throws ServletException {
        carListingsDao = CarListingsDao.getInstance();
    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);
        CarListings carListings = null;

        String vin = req.getParameter("vin");

        if (vin == null) {
            vin = req.getParameter("VIN");
        }
        if (vin == null) {
            messages.put("success", "Please enter a valid vin.");
        } else {
            try {
                carListings = carListingsDao.getCarListingByVin(vin);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            messages.put("success", "Displaying results for " + vin);

        }
        req.setAttribute("CarListings", carListings);
        req.getRequestDispatcher("/FindCarListingsByVin.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);


        CarListings carListings = null;

        String vin = req.getParameter("vin");
        if (vin == null) {
            vin = req.getParameter("VIN");
        }
        if (vin == null) {
            messages.put("success", "Please enter a valid vin.");
        } else {

            try {

                carListings = carListingsDao.getCarListingByVin(vin);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + vin);
        }
        req.setAttribute("CarListings", carListings);

        req.getRequestDispatcher("/FindCarListingsByVin.jsp").forward(req, resp);
    }
}
