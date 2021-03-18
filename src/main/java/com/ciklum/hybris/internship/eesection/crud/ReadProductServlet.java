package com.ciklum.hybris.internship.eesection.crud;

import com.ciklum.hybris.internship.eesection.connection.Connector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ReadProductServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (ResultSet resultSet = statement.executeQuery("SELECT * FROM ProductToOrder")) {
            PrintWriter writer = resp.getWriter();
            writer.println("<html>");
            writer.println("<head><title>List of products</title></head><body>");
            if (!resultSet.next()) {
                writer.println("<H3>There are not any products.</H3>");
            } else {
                writer.println("<H3>List of products.</H3>");
                writer.print("<table>");
                writer.print("<tr>");
                writer.println("<th> Name </th>");
                writer.println("<th> Price </th>");
                writer.println("<th> Status </th>");
                writer.print("</tr>");
                while (resultSet.next()) {
                    writer.println("<tr>");
                    writer.println("<td>");
                    writer.print(resultSet.getString("name"));
                    writer.println("</td>");
                    writer.println("<td>");
                    writer.print(resultSet.getInt("price"));
                    writer.println("</td>");
                    writer.println("<td>");
                    writer.print(resultSet.getString("status"));
                    writer.println("</td>");
                    writer.println("</tr>");
                }
                writer.println("</table>");
            }
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
