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
@WebServlet("/ShowEdit")
public class ShowEdit extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name"); // Get the unique identifier (e.g., name) from the request parameter

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<style>");
		out.println("/* Add your CSS styles here */");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Edit Record</h1>");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/serv", "root", "abc123");

			ResultSet rs = null;
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM serv.ser WHERE name = ?");
			stmt.setString(1, name); // Set the unique identifier in the SQL query
			rs = stmt.executeQuery();

			if (rs.next()) {
				// Pre-fill the form fields with the retrieved data
				String address = rs.getString("address");
				String contact = rs.getString("contact");

				out.println("<form method='get' action='ShowEditUpadte'>");
				out.println("<input type='hidden' name='ename' value='" + name + "'>");
				out.println("<label>Name:</label>");
				out.println("<input type='text' name='name' value='" + name + "'><br>");
				out.println("<label>Address:</label>");
				out.println("<input type='text' name='address' value='" + address + "'><br>");
				out.println("<label>Contact No.:</label>");
				out.println("<input type='text' name='contact' value='" + contact + "'><br>");
				out.println("<input type='submit' value='Update'>");
				out.println("</form>");
			} else {
				out.println("Record not found.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		out.println("</body>");
		out.println("</html>");
		
		
		
		
		
		
	}
}
