
package searchEngine;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.reflect.*;

/**
 * Use regular expressions to match Emails, Web links, Postal Codes, Phonenumbers
 * @author Pavan Kalyan Komarina
 *
 */

public class RegExpression {
	
	// CHANGE HERE!
	public static String folderlocation = "C:\\Users\\91990\\eclipse-workspace\\Assignment4\\src\\W3C Web Pages\\Text";
	
	 public static void findEmailAdresses(String textfile) {

	     // Create a Pattern object
	     Pattern r = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");

	     // Now create matcher object.
	     Matcher m = r.matcher(textfile);
	     while (m.find( )) {
	         System.out.println("Found Email Address : " + m.group(0));
	     } 
	 }

	 public static void findWebLinks(String textfile) {

	     // Create a Pattern object
	     Pattern r = Pattern.compile("(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

	     // Now create matcher object.
	     Matcher m = r.matcher(textfile);
	     while (m.find( )) {
	         System.out.println("Found WebLink : " + m.group(0));
	     } 
	 }
	 
	 public static void findPostalCodes(String textfile) {

	     // Create a Pattern object
	     Pattern r = Pattern.compile("(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$");

	     // Now create matcher object.
	     Matcher m = r.matcher(textfile);
	     while (m.find( )) {
	         System.out.println("Found Postal Code : " + m.group(0));
	     } 
	 }

	 public static void findPhoneNumbers(String textfile) {
		 String pattern = "(\\()?(\\d){3}(\\))?[- ](\\d){3}-(\\d){4}";

	     // Create a Pattern object
	     Pattern r = Pattern.compile(pattern);

	     // Now create matcher object.
	     Matcher m = r.matcher(textfile);
	     while (m.find( )) {
	         System.out.println("Found Phone Number : " + m.group(0));
	     } 
	 }
	 
	 
	 public static void findPatterns(int choice) throws NoSuchMethodException,  
	 InvocationTargetException, IllegalAccessException, IOException {
		
		// create hashmap to store the values
		HashMap<Integer, Method> methodMap = new HashMap<Integer, Method>();
		final File newfolderstructure = new File(folderlocation);
		
		// store the Methods in the HashMap
		try {
				Class[] cArg = new Class[1];
				cArg[0] = String.class;
				methodMap.put(1, RegExpression.class.getMethod("findEmailAdresses", cArg));
				methodMap.put(2, RegExpression.class.getMethod("findWebLinks", cArg));
				methodMap.put(3, RegExpression.class.getMethod("findPostalCodes", cArg));
				methodMap.put(4, RegExpression.class.getMethod("findPhoneNumbers", cArg));
				
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		for (final File fileEntry : newfolderstructure.listFiles()) {
	        if (!fileEntry.isDirectory()) {
	        	String pathname = fileEntry.getPath();
	        	FileReader in = new FileReader(pathname);
	        	
	        	 try (BufferedReader br = new BufferedReader(new FileReader(pathname)))
	             {
	                 String line;
	                 while ((line = br.readLine()) != null) {
	                	 try { 
	                		 // call the appropriate method!
	                		 methodMap.get(choice).invoke(null, line);
	                	 } catch (Exception e) {
	                		 e.printStackTrace();
	                	 }
	                	 }
	             } catch (IOException e) {
	                 e.printStackTrace();
	             }
	        	
	        }
	    }
		
	}
	public static void main(String args[]) throws NoSuchMethodException,  
    InvocationTargetException, IllegalAccessException, IOException {
		System.out.println("Choose a number!\n1 :\tEmail Adresses\n2 :\tWeb Links\n3 :\tPostal Codes\n4 :\tPhonenumbers\n");
		int choice = new Scanner(System.in).nextInt();
		findPatterns(choice);
	}
}