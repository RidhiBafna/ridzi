package com.dhi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utility.DBUtil;

public class CategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}

	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println("<table border='1' width='100%' cellspacing='0' cellpadding='10'>");
		out.println("<tr>");
		out.println("<th>ID</th>");
		out.println("<th>NAME</th>");
		out.println("<th>DESCRIPTION</th>");
		out.println("<th>ACTION</th>");
		out.println("</tr>");
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try
		{
			con= DBUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from category");
			
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>" + rs.getInt("id") + "</td>");
				out.println("<td>" + rs.getString("name") + "</td>");
				out.println("<td>" + rs.getString("description") + "</td>");
				out.println("<td><a href='DeleteServlet?id=" + rs.getInt("id") + "'>Delete</td>");
				out.println("</tr>");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBUtil.closeAllDBResources(rs, stmt, con);
		}
		
		
		out.println("</table>");
	}

}
