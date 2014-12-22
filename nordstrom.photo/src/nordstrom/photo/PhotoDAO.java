package nordstrom.photo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.sql.Time;

/**
 * 
 * @author bryce
 * Implements DAO design pattern - database operations for photo objects
 *
 */

public class PhotoDAO {
	
	public PhotoDAO() {
		
	}
	
	public Connection getConnection() {
		
	      Connection conn = null;
	      try {
	    	 Class.forName("com.mysql.jdbc.Driver");
	         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nordstrom_photo_db", "root", "root");
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	         System.exit(-1);
	      }
	      
	      return conn;
	}
	
	public void delete(String filepath) {
		
		try {
		//delete filepath from photos
		String deleteQuery = "DELETE FROM photos WHERE filepath=\"%s\"";
		deleteQuery = String.format(deleteQuery, filepath);
		
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(deleteQuery);
		close(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insert(String uploadedBy, String filepath) {
		ResultSet rs = null;
		boolean UniqueFilename = false;
		
		//get current date/time
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		
		String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(now); //change to DB appropriate date


		String selectQuery = "SELECT * FROM photos WHERE filepath =\"%s\"";
		String insertQuery = "INSERT INTO photos (uploadedby, filepath, uploaddate) VALUES ('%s', '%s', '%s');";
		insertQuery = String.format(insertQuery, uploadedBy, filepath, dateStr);
		selectQuery = String.format(selectQuery, filepath);
 				
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			//check to see if path exists in db
			rs = stmt.executeQuery(selectQuery);
	
			//indicates a match in db for filepath - add current date/time to filename
			if (rs.next()) {    
				//find extension
				int index = filepath.contains(".") ? filepath.lastIndexOf('.') : filepath.length();
				filepath = filepath.substring(0, index) + "_"+ System.currentTimeMillis() + filepath.substring(index);
				insertQuery = "INSERT INTO photos (uploadedby, filepath, uploaddate) VALUES ('%s', '%s', '%s');";
				insertQuery = String.format(insertQuery, uploadedBy, filepath, dateStr);
				} 
			else {
				UniqueFilename = true;
			}
				
			stmt.executeUpdate(insertQuery);
			close(conn);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void close(Connection conn) {
	try { 
	//close connection
	 conn.close();
	 //manually shutdown abandoned connection thread cleanup thread
	 com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	
	}
	
	public ArrayList<Photo> getPhotos() {
		ArrayList<Photo> photos = new ArrayList<Photo>();		
		
		try {
			ResultSet rs = null;
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			String selectQuery = "SELECT * FROM photos;";
			rs = stmt.executeQuery(selectQuery);
			
			while (rs.next()) {
				String curname = rs.getString("uploadedby");
				Timestamp timestamp = rs.getTimestamp("uploaddate");
				String curfilepath = rs.getString("filepath");
				
				Photo curPhoto = new Photo();
				curPhoto.setCreatedBy(curname);
				curPhoto.setCreatedAt(timestamp);
				curPhoto.setFilepath(curfilepath);
				
				photos.add(curPhoto);
			}
			close(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return photos;
	}


}
