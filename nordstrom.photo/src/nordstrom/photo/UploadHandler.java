package nordstrom.photo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
//import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


//import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.io.*;
import java.nio.file.*;

/**
 * Servlet implementation class UploadHandler
 */
@WebServlet("/upload")
public class UploadHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadHandler() {
        super();
        
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
		String uploadedBy = null;
		String fileloc = null;
	
		//needed for file processing - from Apache commons tools
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		/* The photo upload will come in as multipart content type - using
		Apache commons library to handle uploads */
		
		//validate that the request has multipart content
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			getServletContext().getRequestDispatcher("/add.jsp").forward(request, response);
			return;
		}
		
		try {
			List<FileItem> fields = upload.parseRequest(request);
			Iterator<FileItem> it = fields.iterator();
			
			while (it.hasNext()) {

				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				
				//If it's a form field, get String representation of relevant attribute 
				if (isFormField) {
					
					String FieldName = fileItem.getFieldName();
					switch (FieldName) {
					case "name":
						uploadedBy = fileItem.getString();
					}
				}
				//if it's not a form field, it will be the upload - write to file using binary io
				else {
					//store file in uploads
					fileloc = "/home/bryce/Desktop/uploads/" + fileItem.getName();
				    
					//don't save an empty file, return to read view instead 
					if (fileItem.getName() == "") {
						getServletContext().getRequestDispatcher("/view").forward(request, response);
						return;
					}
					
					//check to see if file already exists
					Path path = Paths.get(fileloc);

					/* If so, append the millisecond timestamp to avoid collisions 
					TODO: this should be a bit more robust, collisions still technichally
					possible at high load  
					*/
					if (Files.exists(path)) {
						int index = fileloc.contains(".") ? fileloc.lastIndexOf('.') : fileloc.length();
						fileloc = fileloc.substring(0, index) + "_"+ System.currentTimeMillis() + fileloc.substring(index);
					}
					
					File uploadedFile = new File(fileloc);
				    fileItem.write(uploadedFile);
				}

			}
			
			//if no file, do not save path to db
			if (!(fileloc == null)) {
			//Store new photo object with relative file path to database for retrieval later
			PhotoDAO dao = new PhotoDAO();
			dao.insert(uploadedBy, fileloc);			
			}		
		
		} catch (Exception e) {
			e.printStackTrace();
		}		
		getServletContext().getRequestDispatcher("/PhotoAppHandler").forward(request, response);
	}
}