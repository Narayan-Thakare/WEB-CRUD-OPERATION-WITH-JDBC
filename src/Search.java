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

public class Search  extends HttpServlet


{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		resp.setContentType("text/html");
		PrintWriter p	=resp.getWriter();	
		p.print("welcome <h1>Search</h1>  " );

		String sn=req.getParameter("searchname");
	

		p.print("Name :"+sn+"<br>");
		

		p.print("<form action='updat' >");
		p.print("<input type='submit' value ='Click me'>");
	
		

try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	System.out.println("driver registered");
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
}
try {
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/serv","root","abc123");

System.out.println("host registerd");
ResultSet rs=null;
	PreparedStatement  stmt=con.prepareStatement("SELECT*FROM serv.ser where name LIKE ?");
	stmt.setString(1, "" + sn + "%");

	 rs=stmt.executeQuery();


	 while(rs.next()) {
		 System.out.println("data come");
			p.print( "name :"+ rs.getString(1)+ "     "+"Address :"+rs.getString(2)+"   "+"Contact No. :"+rs.getString(3)+"<br>");
	}





}catch (Exception p1) {
	System.out.println("Enter the no. :" + p1);


	}
	
	
		
		
		
		
		
		
		
		
		
		
		
		
	}




	
	
	
	

}
