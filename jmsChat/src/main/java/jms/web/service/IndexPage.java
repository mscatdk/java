package jms.web.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="Index Page", urlPatterns={"/index"})
public class IndexPage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8249359740873869601L;

	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("views/index.jsp").forward(req, res);
	}
	
}
