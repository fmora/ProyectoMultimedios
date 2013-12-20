package com.proyectom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;


public class ciudades {
	Vector<String> ciudades;
	
	public ciudades(){
		String parameters = "http://10.0.2.2/consultaciudad.php";   
        ciudades = new Vector<String>();
		try
        {
        	System.setProperty("java.net.preferIPv4Stack" , "true");
        	URL url = new URL(parameters);
        	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        	connection.setRequestMethod("GET");
	        connection.setConnectTimeout(10000);
	        connection.setReadTimeout(5000);
	        connection.setDoInput(true);	        		        
	        connection.connect();	        
	        readIt(connection.getInputStream());	   
         }
         catch(IOException e)
         {
         }
	}
	public void readIt(InputStream stream) throws IOException, UnsupportedEncodingException {

		Reader reader = null;
		//StringBuilder inputStringBuilder = new StringBuilder();
		reader = new InputStreamReader(stream, "UTF-8");     
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = bufferedReader.readLine();
		int idciudad,length;
		String ciudad;
		ciudades = new Vector<String>();
		ciudades.setSize(100);
		
		length = 0;
		while (length < 6){
			line = bufferedReader.readLine();
			length++;
		}
		
		length = 0;
		while(!line.equals("</body>")){
	   		//inputStringBuilder.append(line);
			//inputStringBuilder.append('\n');
			idciudad=Integer.parseInt(line);
			line = bufferedReader.readLine();
			ciudad = line;
			line = bufferedReader.readLine();
			
			ciudades.setElementAt(ciudad, idciudad);
			length++;
	   }
		ciudades.setSize(length+1);
		//return inputStringBuilder.toString();
	}
}
