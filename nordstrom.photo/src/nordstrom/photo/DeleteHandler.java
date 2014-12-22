package nordstrom.photo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.nio.file.*;


/**
 * Servlet implementation class DeleteHandler
 */
@WebServlet("/delete")
public class DeleteHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//read in fp parameter to delete
		Path filepath = Paths.get(request.getParameter("filepath"));	
		//delete record in db
		PhotoDAO dao = new PhotoDAO();
		dao.delete(filepath.toString());
		
		//delete file
		try {
		    Files.delete(filepath);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		//return to view
		getServletContext().getRequestDispatcher("/view").forward(request, response);
	}

}
