package servlet;

import exception.DBException;
import model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/delete"})
public class DeleteServlet extends BaseServlet {


    @Override
    protected void doEX(HttpServletRequest request, HttpServletResponse response)
            throws IOException, DBException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        User dUser = new User(id);
        userService.deleteUser(dUser);
        response.sendRedirect("list");

    }
}
