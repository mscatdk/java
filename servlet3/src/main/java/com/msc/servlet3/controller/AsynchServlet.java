package com.msc.servlet3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 
@WebServlet(urlPatterns = {"/synch"}, asyncSupported = true)
public class AsynchServlet extends HttpServlet  {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6060711469743447319L;
 
       public void doGet(HttpServletRequest request, HttpServletResponse response) {
           final AsyncContext ac = request.startAsync();
           ac.setTimeout(1 * 10 * 1000);
           ac.addListener(new MyAsyncListener(ac));
           
           ThreadPoolExecutor executor = (ThreadPoolExecutor) request
        		                   .getServletContext().getAttribute("executor");
           
           executor.execute(new AsyncRequestProcessor(ac, 8000));
       }
 
       public void doPost(HttpServletRequest request, HttpServletResponse response) {
          doGet(request,response);
       }
 
       class MyAsyncListener implements AsyncListener {
    	   final AsyncContext ac;
    	   
    	   public MyAsyncListener(AsyncContext ac) {
    		   this.ac = ac;
    	   }
    	   
           public void onError(AsyncEvent arg0) throws IOException {
               System.out.println("onError");
           }
             
           public void onComplete(AsyncEvent event) throws IOException {
               System.out.println("onComplete"); 
           }
           
           public void onTimeout(AsyncEvent event) throws IOException {
               System.out.println("onTimeout"); 
       		System.out.println("AppAsyncListener onTimeout");
    		//we can send appropriate response to client
    		ServletResponse response = ac.getResponse();
    		PrintWriter out = response.getWriter();
    		out.write("TimeOut Error in Processing");
           }
           
           public void onStartAsync(AsyncEvent arg0) throws IOException {
               System.out.println("onStartAsync"); 
           }
       }
  
}
