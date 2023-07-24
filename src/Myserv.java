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

public class Myserv extends HttpServlet
{

	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
PrintWriter p	=resp.getWriter();	
p.print("welcome <h1>narayan </h1>  " );

String fn=req.getParameter("first name");
String ln=req.getParameter("last name");
String cn=req.getParameter("Number");

p.print("Name :"+fn+"<br>");
p.print(" sir Name : "+ln+"<br>");
p.print("co. no  : "+cn+"<br>");

try {
	Class.forName("com.mysql.cj.jdbc.Driver");
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
try {
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/serv","root","abc123");


	 PreparedStatement stmt=con.prepareStatement("Insert into serv.ser values(?,?,?)");
	 stmt.setString(1, fn);
	 stmt.setString(2, ln);
	 stmt.setString(3, cn);
	 int a=stmt.executeUpdate();
	 if(a>0){
		 System.out.println("data inserted");
	 }

ResultSet rs=null;
	  stmt=con.prepareStatement("Select * from serv.ser");
	 rs=stmt.executeQuery();
	 

	 while(rs.next()) {
			p.print( "name :"+ rs.getString(1)+ "     "+"Address :"+rs.getString(2)+"   "+"Contact No. :"+rs.getString(3)+"<br>");
		}



	 
	 
	 
	 
	 
	 
	 
	 
	 



} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}












	}
	
	

}
