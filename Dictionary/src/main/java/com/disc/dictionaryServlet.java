package com.disc;

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

/**
 * Servlet implementation class dictionaryServlet
 */
@WebServlet("/dictionaryServlet")
public class dictionaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dictionaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter pw =response.getWriter();
		String word =request.getParameter("word");
		String meaning =request.getParameter("meaning");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/rushi", "root", "Rushi999");
			String query ="INSERT INTO dictionary (word,meaning )values (?,?)";
			PreparedStatement  pt = con.prepareStatement(query);
			
			
			
			pt.setString(1, word);
			pt.setString(2, meaning);
			
			//System.out.println("word" +word);
			//System.out.println("meaing" +meaning);

			
			 int a =pt.executeUpdate();
			 if(a>0)
			 {
				pw.write (" word added in dictionary");
			 }
			 else {
				pw.write("no row affected");
			 }
			
			
			
			con.close();
			pt.close();
		}
		catch(SQLException e)
		
		{
		e.printStackTrace()	;
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		pw.close();
		
		
		
		//doGet(request, response);
	}

}
