package com.simplilearn.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.connection.DBConnection;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/UpdateProductwithParam")
public class UpdateProductwithParam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductwithParam() {
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
			
			String query = "update eproduct set name = ? where  id = ?";
			//create statement
			PreparedStatement pstm = conn.getConnection().prepareStatement(query);
			
			pstm.setString(1, "HP Laptop G450");
			pstm.setInt(2, 2);
			
			//Execute query
			
			int num = pstm.executeUpdate();
			
			//Print response
			out.println("<html><body>");
			if(num>0) {
				out.println("No of Rows affected : "+num);
			}else {
				out.println("The product cannot be updated");
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
