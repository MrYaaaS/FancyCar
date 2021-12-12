package fancycar.servlet;

import fancycar.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/resetpassword")
public class UsersResetPasswordServlet extends UsersServlet {

    /**
     * Reset Users Password
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        HttpSession session = req.getSession(true);
        Users user = (Users) session.getAttribute("user");
        String password = req.getParameter("newpassword");
        if (user == null) {
            messages.put("result", "Invalid Signed In User");
        } else if (password == null || password.isEmpty()) {
            messages.put("result", "Invalid Password");
            messages.put("disableSubmit", "true");
        }
        else {
            try {
                user.setPassword(password);
                user = usersDao.updatePassword(user);
                session.setAttribute("user", user);
                messages.put("successMsg",
                    "Successfully updated password for " + user.getUserName());
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/UserProfile.jsp").forward(req, resp);
    }

    /**
     * Reset Users Password
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        HttpSession session = req.getSession(true);
        Users user = (Users) session.getAttribute("user");
        String password = req.getParameter("newpassword");
        if (user == null) {
            messages.put("result", "Invalid Signed In User");
        } else if (password == null || password.isEmpty()) {
            messages.put("result", "Invalid Password");
        }
        else {
            try {
                user.setPassword(password);
                user = usersDao.updatePassword(user);
                session.setAttribute("user", user);
                messages.put("result", "Successfully updated password for " + user.getUserName());
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/UserResetPassword.jsp").forward(req, resp);
    }

}
