	
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/RestuarantData")
public class RestuarantData extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{

PrintWriter pw= response.getWriter();
response.setContentType("text/html");			
 pw.println("<html>");
 pw.println("<body>");

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		//utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		pw.print("<tr>");
		pw.print("<td><div id='shop_item'>");
			Restuarants data= (Restuarants)request.getAttribute("data");
			pw.print("<h3>" + data.getname() + "</h3>");
			pw.print("<strong>" + data.getaddress() + "$</strong><ul>");
			pw.print("<li id='item'><img src='"
					+ data.getimg_url() + "' alt='' /></li>");
			pw.print("<li><form method='post' action='TabletList'>" +
					//"<input type='hidden' name='name' value='"+data.getKey()+"'>"+
					"<input type='hidden' name='type' value='restuarants'>"+
                    "<input type='hidden' name='restuarantsname' value='"+data.getname()+"'>"+
                    "<input type='hidden' name='maker' value='"+data.getaddress()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy From this Restuarant'></form></li>");
			// pw.print("<li><form method='post' action='WriteReview'>"+
			// "<input type='hidden' name='name' value='"+data.getId()+"'>"+
			// 		"<input type='hidden' name='type' value='"+data.getType()+"'>"+
			// 		"<input type='hidden' name='maker' value='"+data.getRetailer()+"'>"+
			// 		"<input type='hidden' name='price' value='"+data.getPrice()+"'>"+
			// 		"<input type='hidden' name='access' value=''>"+
			// 	    	"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			// pw.print("<li><form method='post' action='ViewReview'>"+
			// 		"<input type='hidden' name='name' value='"+data.getId()+"'>"+
			// 		"<input type='hidden' name='type' value='"+data.getType()+"'>"+
			// 		"<input type='hidden' name='maker' value='"+data.getRetailer()+"'>"+		
			// 		"<input type='hidden' name='price' value='"+data.getPrice()+"'>"+
			// 		"<input type='hidden' name='access' value=''>"+
			// 	    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
		
			pw.print("</ul></div></td>");
			pw.print("</tr>");
			pw.print("</table></div></div></div>");		
		utility.printHtml("Footer.html");
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	

}