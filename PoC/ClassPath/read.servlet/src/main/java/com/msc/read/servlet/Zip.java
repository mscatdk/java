package com.msc.read.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msc.read.zip.ZipVerion;

@WebServlet(name="zipServlet", urlPatterns={"/zip"} )
public class Zip extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6341948582041360126L;
	
	ClassLoader classLoader = Zip.class.getClassLoader();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	try {
    		ZipVerion zip = ((Class<ZipVerion>) classLoader.loadClass("com.msc.read.zip.ZipVerion")).newInstance();
			out.println("Zip: " + zip.getVersion());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println(e.getMessage());
		}
        out.println("Hello World ");
        out.close();
    }
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
    }
	
}
