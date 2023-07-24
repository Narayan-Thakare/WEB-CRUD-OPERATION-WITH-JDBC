import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class update extends HttpServlet

{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		resp.setContentType("text/html");
		PrintWriter p=resp.getWriter();
		p.print("Welcome to about");
p.print("<br>");
String fn=req.getParameter("first name");
String ln=req.getParameter("last name");
String cn=req.getParameter("Number");

p.print("Name :"+fn+"<br>");
p.print("last Name : "+ln+"<br>");
		p.print("co. no  : "+cn+"<br>");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver registered");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		}
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/serv","root","abc123");

		System.out.println("host registerd");

	PreparedStatement	stmt=con.prepareStatement("Update serv.ser set address=?,contact=? where name=?");
		stmt.setString(1, ln);
		stmt.setString(2, cn);
		stmt.setString(3, fn);
stmt.executeUpdate();			
p.print("updated");



		}catch (Exception p1) {
			System.out.println("Enter the no. :" + p1);


			}
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
