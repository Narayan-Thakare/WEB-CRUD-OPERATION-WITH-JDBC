import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Show")
public class Show extends HttpServlet {
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<style>");
        out.println("table { border-collapse: collapse; width: 70%; margin: auto; }");
        out.println("th, td { border: 1px solid black; padding: 8px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Welcome Narayan</h1>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/serv", "root", "abc123");

            ResultSet rs = null;
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM serv.ser");
            rs = stmt.executeQuery();

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Name</th>");
            out.println("<th>Address</th>");
            out.println("<th>Contact No.</th>");
            out.println("<th>Edit</th>");
            out.println("<th>Delete</th>");
            out.println("</tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString(1) + "</td>");
                out.println("<td>" + rs.getString(2) + "</td>");
                out.println("<td>" + rs.getString(3) + "</td>");

                // Edit button that redirects to an edit page
                out.println("<td><a href='ShowEdit?name=" + rs.getString("name") + "'>Edit</a></td>");

                // Delete button that triggers a delete action
                out.println("<td><a href='DeleteRecord?name=" + rs.getString(1) + "'>Delete</a></td>");

                out.println("</tr>");
            }

            out.println("</table>");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println("</body>");
        out.println("</html>");
    }
}
