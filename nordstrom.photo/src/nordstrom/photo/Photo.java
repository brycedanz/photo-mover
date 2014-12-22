package nordstrom.photo;
import java.util.Calendar;
import java.util.Date;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;


//Holds an individual photo
public class Photo {
	private String createdBy;
	private Date createdAt;
	private String filepath;
	
	public Photo() {
				
		}
	
	public String getCreatedBy() {
		return this.createdBy;
	}
	
	public void setCreatedBy(String name) {
		this.createdBy = name;
	}
	
	public String getCreatedAt() {
		
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String date = formatter.format(this.createdAt);
	    
		return date;
	}
	
	public void setCreatedAt(Date date) {
		this.createdAt = date;
	}
	
	public String getFilepath() {
		return this.filepath;
	}
	
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
}
