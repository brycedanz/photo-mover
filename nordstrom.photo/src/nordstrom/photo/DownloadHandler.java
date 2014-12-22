package nordstrom.photo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Servlet implementation class DownloadHandler
 */
@WebServlet("/download")
public class DownloadHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String filepath = request.getParameter("filepath");	
		if (filepath != null) {
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + filepath);
			
			//write file directly to outputstream
			File file = new File(filepath);
			FileInputStream fileIn = new FileInputStream(file);
			ServletOutputStream out = response.getOutputStream();
			
			//use binary io w/ 1 mb buffer
			byte[] outputBytes = new byte[4096];
			
			//copy binary content directly from input to output stream - 4096b = 1mb
			//using read/write system calls
			while(fileIn.read(outputBytes, 0, 4096) != -1)
			{
				out.write(outputBytes, 0, 4096);
			}
			//close input file
			fileIn.close();
			
			//close output file
			out.flush();
			out.close();
		}
		
	}
}
