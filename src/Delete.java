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

public class Delete  extends HttpServlet


{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		resp.setContentType("text/html");
		PrintWriter p	=resp.getWriter();	
		p.print("welcome <h1>Delete</h1>  " );

		String dd=req.getParameter("delete_name");
	

		p.print("Name :"+dd+"<br>");
		
		

		
		
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver registered");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		}
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/serv","root","abc123");

		System.out.println("host registerd");

		PreparedStatement stmt=con.prepareStatement("Delete from serv.ser where name=?");
		stmt.setString(1, dd);
		p.print("data deleted");
		stmt.executeUpdate();
			




		}catch (Exception p1) {
			System.out.println("Enter the no. :" + p1);


			}
		
		
		
		
		
		
		
		
		
		
	}


	
	
	
	
	
	
	
	

		
		
		
		
		
		
		
		
		
		
		
	




	
	
	
	

}
