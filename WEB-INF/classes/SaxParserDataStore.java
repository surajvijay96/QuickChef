
import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {

    Tablet tablet;
	Restuarants restuarant;
	static HashMap<String,Restuarants> restuarants;
    static HashMap<String,Tablet> tablets;
    String consoleXmlFileName;
    String elementValueRead;
	String currentElement="";
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String consoleXmlFileName) {
    this.consoleXmlFileName = consoleXmlFileName;
	restuarants = new HashMap<String, Restuarants>();
	tablets=new HashMap<String, Tablet>();
	parseDocument();
	
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(consoleXmlFileName, this);
		
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
	}

   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
	
	// when xml start element is parsed store the id into respective hashmap for console,games etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {
		if (elementName.equalsIgnoreCase("restuarant")) 
		{
			currentElement="restuarant";
			restuarant = new Restuarants();
            restuarant.setname(attributes.getValue("name"));
		}
        if (elementName.equalsIgnoreCase("tablet"))
		{
			currentElement="tablet";
			tablet = new Tablet();
            tablet.setId(attributes.getValue("id"));
        }

    }
	// when xml end element is parsed store the data into respective hashmap for console,games etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
       
 
        if (element.equals("tablet")) {	
			tablets.put(tablet.getId(),tablet);
			return;
		}
		if(element.equals("restuarant")){
			restuarants.put(restuarant.getname(),restuarant);
		}
        
        if (element.equalsIgnoreCase("image")) {
            if(currentElement.equals("tablet"))
				tablet.setImage(elementValueRead);        
			return;
        }
        

		if (element.equalsIgnoreCase("discount")) { 
            if(currentElement.equals("tablet"))
				tablet.setDiscount(Double.parseDouble(elementValueRead));      
			return;
	    }


		if (element.equalsIgnoreCase("condition")) {
            
            if(currentElement.equals("tablet"))
				tablet.setCondition(elementValueRead);
                 
			return;  
		}

		if (element.equalsIgnoreCase("manufacturer")) {
            
            if(currentElement.equals("tablet"))
				tablet.setRetailer(elementValueRead);
                  
			return;
		}

        if (element.equalsIgnoreCase("name")) {
           
            if(currentElement.equals("tablet"))
				tablet.setName(elementValueRead);
                   
			if(currentElement.equals("restuarant"))
				restuarant.setname(elementValueRead);          
			return;
	    }
	
        if(element.equalsIgnoreCase("price")){
			
            if(currentElement.equals("tablet"))
				tablet.setPrice(Double.parseDouble(elementValueRead));
                    
			return;
		}
		if(element.equalsIgnoreCase("img_url")){
			if(currentElement.equals("restuarant"))
				restuarant.setimg_url(elementValueRead);
		}
		if(element.equalsIgnoreCase("rating")){
			if(currentElement.equals("restuarant"))
				restuarant.setrating(elementValueRead);
		}
		if(element.equalsIgnoreCase("address")){
			if(currentElement.equals("restuarant"))
				restuarant.setaddress(elementValueRead);
		}
		if(element.equalsIgnoreCase("longitude")){
			if(currentElement.equals("restuarant"))
				restuarant.setlongitude(elementValueRead);
		}
		if(element.equalsIgnoreCase("latitude")){
			if(currentElement.equals("restuarant"))
				restuarant.setlatitude(elementValueRead);
		}

	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
        public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\Quickchef\\ProductCatalog.xml");
    } 
	
	
	
}
