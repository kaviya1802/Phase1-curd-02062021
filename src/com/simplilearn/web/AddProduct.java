package com.simplilearn.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.connection.DBConnection;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.sendRedirect("addproduct.html");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
try {
			
			String name =  request.getParameter("productName");
			String price = request.getParameter("productPrice");
			PrintWriter out = response.getWriter();
			
			//load properties from config
			
			Properties properties = new Properties();
			properties.load(getServletContext().getResourceAsStream("/config.properties"));
		
			//fetch properties from config and create connection
			DBConnection conn = new DBConnection(properties.getProperty("url"), properties.getProperty("username")
					, properties.getProperty("password"));
		
			
			String query = "insert into eproduct(name, price) values(?, ?);";
			//create statement
			PreparedStatement pstm = conn.getConnection().prepareStatement(query);
			
			pstm.setString(1, name);
			pstm.setDouble(2, Double.parseDouble(price));
			
			int num = pstm.executeUpdate();
			
			//Print response
			out.println("<html><body>");
			if(num>0) {
				out.println("No of Rows affected : "+num);
			}else {
				out.println("The product cannot be added");
				}
			out.println("</body></html>");
			
			conn.closeConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
