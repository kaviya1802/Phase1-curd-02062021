package com.simplilearn.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.connection.DBConnection;

/**
 * Servlet implementation class DeleteProduct
 */
@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
			
			PrintWriter out = response.getWriter();
			
			//load properties from config
			
			Properties properties = new Properties();
			properties.load(getServletContext().getResourceAsStream("/config.properties"));
		
			//fetch properties from config and create connection
			DBConnection conn = new DBConnection(properties.getProperty("url"), properties.getProperty("username")
					, properties.getProperty("password"));
			//create statement
			Statement stm = conn.getConnection().createStatement();
			
			//Execute query
			String query = "Delete from eproduct where id = 1";
			int num = stm.executeUpdate(query);
			
			//Print response
			out.println("<html><body>");
			if(num>0) {
				out.println("No of Rows deleted : "+num);
			}else {
				out.println("The product cannot be deleted");
				}
			out.println("</body></html>");
			
			conn.closeConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
