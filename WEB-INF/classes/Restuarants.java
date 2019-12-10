import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Restuarants")



public class Restuarants extends HttpServlet{
	private String id;
	private String name;
	private String img_url;
	private String rating;
	private String latitude;
	private String longitude;
	private String address;

	
	public Restuarants(String id, String name,String img_url, String rating, String latitude, String longitude,String address){
		this.id=id;
		this.name=name;
		this.img_url = img_url;
		this.rating=rating;
		this.latitude=latitude;
		this.longitude = longitude;
		this.address = address;
	}
	
	public Restuarants(){
		
	}
	public String getid() {
		return id;
	}
	public void setid(String id) {
		this.id = id;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getimg_url() {
		return img_url;
	}
	public void setimg_url(String img_url) {
		this.img_url = img_url;
    }
    public String getrating() {
		return rating;
	}
	public void setrating(String rating) {
		this.rating = rating;
	}
	public String getlatitude() {
		return latitude;
	}
	public void setlatitude(String latitude) {
		this.latitude = latitude;
    }
    public String getlongitude() {
		return longitude;
	}
	public void setlongitude(String longitude) {
		this.longitude = longitude;
    }
    public String getaddress() {
		return address;
	}
	public void setaddress(String address) {
		this.address = address;
	}
}
