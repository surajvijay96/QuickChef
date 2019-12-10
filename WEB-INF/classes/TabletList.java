import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TabletList")

public class TabletList extends HttpServlet {

	/* Trending Page Displays all the Tablets and their Information in Game Speed */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		HashMap<String,Tablet> alltablets = new HashMap<String,Tablet> ();

		//Utilities utility = new Utilities(request, pw);
		String name1 = request.getParameter("name");
		String address = request.getParameter("restuarantsaddress");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		// pw.print(name);
		// pw.print(address);	

		
		try{
		     alltablets = MySqlDataStoreUtilities.getTablets();
		}
		catch(Exception e)
		{
			
		}
		//pw.print(alltablets);

	/* Checks the Tablets type whether it is microsft or apple or samsung */

		/* Header, Left Navigation Bar are Printed.

		All the tablets and tablet information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		//utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+ name1 +" Menu</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = alltablets.size();
		for (Map.Entry<String, Tablet> entry : alltablets.entrySet()) {
			Tablet Tablet = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>" + Tablet.getName() + "</h3>");
			pw.print("<strong>" + Tablet.getPrice() + "$</strong><ul>");
			pw.print("<li id='item'><img src='images/tablets/"
					+ Tablet.getImage() + "' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='tablets'>"+
					//"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='hidden' name='name1' value='"+name1+"'>"+
					"<input type='hidden' name='address' value='"+address+"'>"+
					"<input type='hidden' name='latitude' value='"+latitude+"'>"+
					"<input type='hidden' name='longitude' value='"+longitude+"'>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			// pw.print("<li><form method='post' action='Payment'>" +
			// 		"<input type='hidden' name='name1' value='"+name1+"'>"+
			// 		"<input type='hidden' name='address' value='"+address+"'>"+
			// 		"<input type='hidden' name='latitude' value='"+latitude+"'>"+
			// 		"<input type='hidden' name='longitude' value='"+longitude+"'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='tablets'>"+
					//"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+Tablet.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='tablets'>"+
					//"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		//utility.printHtml("Footer.html");
	}
}
