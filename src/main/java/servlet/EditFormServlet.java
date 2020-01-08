package servlet;

import exception.DBException;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/edit"})
public class EditFormServlet extends BaseServlet {


    @Override
    protected void doEX(HttpServletRequest request, HttpServletResponse response)
            throws IOException, DBException, ServletException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        User exUser = userService.getUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/userForm.jsp");
        request.setAttribute("user", exUser);
        dispatcher.forward(request, response);
    }
}
