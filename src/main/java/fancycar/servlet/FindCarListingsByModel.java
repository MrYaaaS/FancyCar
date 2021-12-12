package fancycar.servlet;

import fancycar.dal.CarListingsDao;
import fancycar.model.CarListings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/findcarlistingsbymodel")
public class FindCarListingsByModel extends HttpServlet {
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
        List<CarListings> carListingsList = null;

        String model = req.getParameter("model");

        if (model == null) {
            model = req.getParameter("MODEL");
        }
        if (model == null) {
            messages.put("success", "Please enter a valid model.");
        } else {
            try {
                carListingsList = carListingsDao.getCarListingByModel(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            messages.put("success", "Displaying results for " + model);

        }

        req.setAttribute("CarListings", carListingsList);
        req.getRequestDispatcher("/FindCarListingsByModel.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);
        List<CarListings> carListingsList = null;

        String model = req.getParameter("model");

        if (model == null) {
            model = req.getParameter("MODEL");
        }
        if (model == null) {
            messages.put("success", "Please enter a valid model.");
        } else {
            try {
                carListingsList = carListingsDao.getCarListingByModel(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            messages.put("success", "Displaying results for " + model);

        }

        req.setAttribute("CarListings", carListingsList);
        req.getRequestDispatcher("/FindCarListingsByModel.jsp").forward(req, resp);
    }
}
