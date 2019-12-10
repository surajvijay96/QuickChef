import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
//@WebServlet("/Search")
public class Search extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        Hashtable<String,Restuarants> hm = new Hashtable<String,Restuarants>();

        PrintWriter pw = response.getWriter();


        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Quickchef","root","root");

            PreparedStatement ps = con.prepareStatement("select * from restaurants");

            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next())

            {
                hm.put(rs.getString(2),new Restuarants(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));

            }

            
        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        pw.print("<form action='Options' method='post'");
        pw.print("<table>");
        pw.print("<tr>");
        pw.print("<td> Address Search </td>");
        pw.print("<td> <input type='text' name='cityname'> </td>");
        pw.print("</tr>");
        //pw.print("<tr><td colspan='2'><input type='submit' class='btnbuy' name='Submit' value='SubmitReview'></td></tr></table>");
        pw.print("<tr><td><input type='submit' class='btnbuy' name='Submit' value='Submit'></td></tr></table>");
        pw.print("</form>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>" + "Restuarants" + " </a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Restuarants> entry : hm.entrySet()) {
			Restuarants Restuarants = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>" + Restuarants.getname() + "</h3>");
            pw.print("<strong>" + Restuarants.getaddress() + "$</strong><ul>");
            pw.print("<h3>" + Restuarants.getrating() + "</h3>");
			pw.print("<li id='item'><img src='"
					+ Restuarants.getimg_url() + "' alt='' /></li>");
			pw.print("<li><form method='post' action='TabletList'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='restuarants'>"+
                    "<input type='hidden' name='restuarantsname' value='"+Restuarants.getname()+"'>"+
                    "<input type='hidden' name='restuarantsaddress' value='"+Restuarants.getaddress()+"'>"+
                    "<input type='hidden' name='latitude' value='"+Restuarants.getlatitude()+"'>"+
                    "<input type='hidden' name='longitude' value='"+Restuarants.getlongitude()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy From this Restuarant'></form></li>");
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		//utility.printHtml("Footer.html");

        } catch (Exception e2)

        {

            e2.printStackTrace();

        } finally {
            pw.close();

        }

    }



}