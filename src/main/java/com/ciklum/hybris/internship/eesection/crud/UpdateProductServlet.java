package com.ciklum.hybris.internship.eesection.crud;

import com.ciklum.hybris.internship.eesection.connection.Connector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class UpdateProductServlet extends HttpServlet {
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
                "UPDATE ProductToOrder SET price = ? WHERE name = ?")) {
            String name = req.getParameter("name");
            preparedStatement.setInt(1, Integer.parseInt(req.getParameter("price")));
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            PrintWriter writer = resp.getWriter();
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Added Product</title></head><body>");
            writer.println("<H3>Price of your product " + name + " is changed and put to database.</H3>");
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
