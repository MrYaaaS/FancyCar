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
import java.util.List;
import java.util.Map;

@WebServlet("/findcarlistingsbydetails")
public class FindCarListingsByDetails extends HttpServlet {
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
        List<CarListings> carListings = null;
        Map<String, String> map = new HashMap<>();

        String model = req.getParameter("model");
        String year = req.getParameter("year");
        String color = req.getParameter("color");
        String mile = req.getParameter("mile");
        String price = req.getParameter("price");

        map.put("model", model);
        map.put("year", year);
        map.put("color", color);
        map.put("mile", mile);
        map.put("price", price);

        try {
            carListings = carListingsDao.getCarListingByDetails(map);
        } catch (SQLException e) {
            messages.put("success", "Please try again");
            e.printStackTrace();
        }
        messages.put("success", "Displaying results");


        req.setAttribute("CarListings", carListings);
        req.getRequestDispatcher("/FindCarListingsByDetails.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);
        List<CarListings> carListings = null;
        Map<String, String> map = new HashMap<>();

        String model = req.getParameter("model");
        String year = req.getParameter("year");
        String color = req.getParameter("color");
        String mile = req.getParameter("mile");
        String price = req.getParameter("price");

        map.put("model", model);
        map.put("year", year);
        map.put("color", color);
        map.put("mile", mile);
        map.put("price", price);

        try {
            carListings = carListingsDao.getCarListingByDetails(map);
        } catch (SQLException e) {
            messages.put("success", "Please try again");
            e.printStackTrace();
        }
        messages.put("success", "Displaying results");

        req.setAttribute("CarListings", carListings);
        req.getRequestDispatcher("/FindCarListingsByDetails.jsp").forward(req, resp);
    }
}
