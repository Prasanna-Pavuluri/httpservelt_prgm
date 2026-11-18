package com.demo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class EmailVerification
 */
@WebServlet("/EmailVerification")
public class EmailVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailVerification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String email=request.getParameter("email");
		
		String url="jdbc:mysql://localhost:3306/mydata";
		String user="root";
		String password="tiger";
		String query="select * from details where email=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//stage1
			Connection con=DriverManager.getConnection(url,user,password);
			
			//stage-2
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,email);
			
			//satge-3
			ResultSet rs=ps.executeQuery();
			
			
			//stage-4
			if(rs.next()) {
				RequestDispatcher rd=request.getRequestDispatcher("second.html");
				rd.include(request, response);
			}
			else
			{
				out.println("<h3>Not a valid email</h3>");
				RequestDispatcher rd=request.getRequestDispatcher("first.html");
				rd.include(request, response);
			}
			
			
			
			
			//satge-5
			ps.close();
			rs.close();
			con.close();
			
			
			
			
		}catch(Exception e) {
			out.println("<h3>"+e.getMessage()+"</h3>");
		}
		
		
	}

}
