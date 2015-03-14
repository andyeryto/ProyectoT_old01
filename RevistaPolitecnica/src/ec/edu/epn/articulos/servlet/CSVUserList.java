package ec.edu.epn.articulos.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CSVUserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CSVUserList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	final ServletOutputStream out = response.getOutputStream();	 
	    response.setContentType("application/octet-stream");
	    File file = new File(request.getParameter("file"));
	    if(!request.getParameter("file").isEmpty() & file.exists()){
		    BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
		    byte[] buf = new byte[4 * 1024]; // 4K buffer
		    int bytesRead;
		    while ((bytesRead = is.read(buf)) != -1) {
		    out.write(buf, 0, bytesRead);
		    }
		    is.close();
		    out.close();	
		    file.delete();
	    }

	}
}