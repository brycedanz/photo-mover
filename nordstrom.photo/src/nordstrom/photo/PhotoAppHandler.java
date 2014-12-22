package nordstrom.photo;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import java.util.ArrayList;


/**
 * Servlet implementation class PhotoAppHandler
 */
@WebServlet(urlPatterns = {"/PhotoAppHandler", "/view", "/index.html"})
public class PhotoAppHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoAppHandler() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 ArrayList<Photo> photos = new ArrayList<Photo>();
		 
		 //get photos from DB via DAO
		 PhotoDAO dao = new PhotoDAO();
		 photos = dao.getPhotos();

		 //add photos to request
		 request.setAttribute("photos", photos);
		 
		 //pass to index.jsp main read view
		 getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}
