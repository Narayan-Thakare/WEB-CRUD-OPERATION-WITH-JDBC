

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowEditUpadte")
public class ShowEditUpadte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print("narayan");
		  String ename = request.getParameter("ename");
	        String name = request.getParameter("name");
	        String address = request.getParameter("address");
	        String contact = request.getParameter("contact");

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        try {
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/serv", "root", "abc123");

	            // Update the record in the database
	            PreparedStatement updateStmt = con.prepareStatement(
	                    "UPDATE serv.ser SET name = ?, address = ?, contact = ? WHERE name = ?");
	            updateStmt.setString(1, name);
	            updateStmt.setString(2, address);
	            updateStmt.setString(3, contact);
	            updateStmt.setString(4, ename);

	            int rowsUpdated = updateStmt.executeUpdate();

	            if (rowsUpdated > 0) {
	                out.println("<h2>Record updated successfully!</h2>");
	                
	                // Redirect the user to the "Show" page after a successful update
	                response.sendRedirect("Show");
	            } else {
	                out.println("<h2>Record not found or update failed.</h2>");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	     
		
		
		
		
	}

}
