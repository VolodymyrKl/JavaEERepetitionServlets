package com.ciklum.hybris.internship.eesection.crud;

import com.ciklum.hybris.internship.eesection.connection.Connector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class DeleteProductServlet extends HttpServlet {
    private Connection connection;
    private Statement statement;

    @Override
    public void init() {
        try {
            connection = Connector.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM ProductToOrder WHERE name = ?")) {
            String name = req.getParameter("name");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            PrintWriter writer = resp.getWriter();
            writer.println("<html>");
            writer.println("<head><title>Removed product</title></head><body>");
            writer.println("<H3>Product is removed from database.</H3>");
            writer.print("<a href=\"home.html\">To menu</a>");
            writer.println("</body></html>");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
