package com.msc.upload.service.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.Part;

import com.msc.upload.service.config.Config;

@MultipartConfig(location=Config.BASE_DIR, fileSizeThreshold=1024*1024, 
maxFileSize=1024*1024*500, maxRequestSize=1024*1024*500*5)
@WebServlet(name="Upload file", urlPatterns={"/upload"} )
public class Upload extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7025048686868565395L;
	private final static Logger LOGGER = Logger.getLogger(Upload.class.getName());

	
	protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws IOException, ServletException {
        LOGGER.warning("POST -> BEGIN");
        
        Part part = req.getPart("content");
        String fileName = getFileName(part);
        
        System.out.println(fileName);
        
        part.write(Config.BASE_DIR + fileName);
        res.sendRedirect(req.getContextPath() + "/index");
        LOGGER.info("POST -> END");
	}
	
	private String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}
	

}
