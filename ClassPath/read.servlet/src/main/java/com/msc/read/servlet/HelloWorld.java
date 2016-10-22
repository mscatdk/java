package com.msc.read.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msc.read.dep.App;
import com.msc.read.msc.MyData;

@WebServlet(name="HelloWorldServlet", urlPatterns={"/hello"} )
public class HelloWorld extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6341948582041360126L;
	
	ClassLoader classLoader = HelloWorld.class.getClassLoader();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	try {
    		App app = ((Class<App>) classLoader.loadClass("com.msc.read.dep.App")).newInstance();
			MyData myData = ((Class<MyData>)classLoader.loadClass("com.msc.read.msc.MyData")).newInstance();
			
			out.println("App: " + app.getVersion());
			out.println("MyData: " + myData.getData());
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
