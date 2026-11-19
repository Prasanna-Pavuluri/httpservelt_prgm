package com.demo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class PasswordVerification
 */
@WebServlet("/PasswordVerification")
public class PasswordVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordVerification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*for http servelt - basic
		 
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String passwords=request.getParameter("password");
		
		String url="jdbc:mysql://localhost:3306/mydata";
		String user="root";
		String password="tiger";
		String query="select * from details where password=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//stage1
			Connection con=DriverManager.getConnection(url,user,password);
			
			//stage-2
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,passwords);
			
			//satge-3
			ResultSet rs=ps.executeQuery();
			
			
			//stage-4
			if(rs.next()) {
				RequestDispatcher rd=request.getRequestDispatcher("welcome.html");
				rd.include(request, response);
			}
			else
			{
				out.println("<h3>Not a valid password</h3>");
				RequestDispatcher rd=request.getRequestDispatcher("second.html");
				rd.include(request, response);
			}
			
			
			
			
			//satge-5
			ps.close();
			rs.close();
			con.close();
			
			
			
			
		}catch(Exception e) {
			out.println("<h3>"+e.getMessage()+"</h3>");
		}
		 */
		
		//for http seevelt with session obj - printing email and apassowrd also
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String passwords=request.getParameter("password");
		
	    HttpSession session= request.getSession();
		
		String url="jdbc:mysql://localhost:3306/mydata";
		String user="root";
		String password="tiger";
		String query="select * from details where password=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//stage1
			Connection con=DriverManager.getConnection(url,user,password);
			
			//stage-2
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,passwords);
			
			//satge-3
			ResultSet rs=ps.executeQuery();
			
			
			//stage-4
			if(rs.next()) {
				
				session.setAttribute("p", passwords);
				
				String mail=(String)session.getAttribute("e");
				String pass_word=(String)session.getAttribute("p");
				
				out.println("<h3>Welcome user "+mail+" & "+pass_word+"</h3>");
				
				
				RequestDispatcher rd=request.getRequestDispatcher("welcome.html");
				rd.include(request, response);
			}
			else
			{
				out.println("<h3>Not a valid password</h3>");
				RequestDispatcher rd=request.getRequestDispatcher("second.html");
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
