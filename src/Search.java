import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Search extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Search Results</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Welcome to the Search</h1>");

        String sn = req.getParameter("searchname");

        out.println("<p>Name: " + sn + "</p>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver registered");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/serv", "root", "abc123");
            System.out.println("Connected to the database");

            ResultSet rs = null;
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM serv.ser WHERE name LIKE ?");
            stmt.setString(1, "%" + sn + "%"); // Use '%' before and after the search term

            rs = stmt.executeQuery();

            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Name</th>");
            out.println("<th>Address</th>");
            out.println("<th>Contact No.</th>");
            out.println("</tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString(1) + "</td>");
                out.println("<td>" + rs.getString(2) + "</td>");
                out.println("<td>" + rs.getString(3) + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

        } catch (Exception e) {
            out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        out.println("</body>");
        out.println("</html>");
    }
}
