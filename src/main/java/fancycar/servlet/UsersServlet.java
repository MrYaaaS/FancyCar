package fancycar.servlet;

import fancycar.dal.UsersDao;
import fancycar.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    protected UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        usersDao = usersDao.getInstance();
    }

    /**
     * Create Users
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null || username.trim().isEmpty()) {
            messages.put("result", "Invalid Username");
        } else if (password == null || username.isEmpty()) {
            messages.put("result", "Invalid Password");
        }
        else {
            String firstName = req.getParameter("firstname");
            String lastName = req.getParameter("lastname");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            try {
                Users user = new Users(username, password, firstName, lastName, email, phone);
                user = usersDao.create(user);
                messages.put("result", "Successfully created " + username);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
    }

    /**
     * Read Users / Log in
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Users user = null;
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null || username.isEmpty()) {
            messages.put("result", "No username provided.");
        } else {
            try {
                user = usersDao.getUserByUserName(username);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            if (password.equals(user.getPassword())) {
                messages.put("result", "Logged in successfully as " + username);
                req.getSession(true).setAttribute("user", user);
            } else {
                messages.put("result", "Incorrect password for " + username);
                req.getSession(true).setAttribute("user", null);
            }
        }
        req.getRequestDispatcher("/UserProfile.jsp").forward(req, resp);
    }
}
