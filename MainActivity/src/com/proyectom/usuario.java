package com.proyectom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class usuario {
	int idUsuario;
	String nombre;
	String cuentaFacebook;
	int telefono;
	
	public usuario(){}
	
	public usuario (int idUsuario, String nombre, String cuentaFacebook, int telefono){
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.cuentaFacebook = cuentaFacebook;
		this.telefono = telefono;
	}
	
	public void setidUsuario(int idUsuario){
		this.idUsuario = idUsuario;
	}
	
	public void setnombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setcuentaFacebook(String cuentaFacebook){
		this.cuentaFacebook = cuentaFacebook;
	}
	
	public void settelefono(int telefono){
		this.telefono = telefono;		
	}
	
	public int getidUsuario(){
		return this.idUsuario;
	}
	
	public String getnombre(){
		return this.nombre;
	}
	
	public String getcuentaFacebook(){
		return this.cuentaFacebook;
	}
	
	public int gettelefono(){
		return this.telefono;		
	}
	public int registerDB() {
        String parameters = "http://10.0.2.2/consultausuario.php?action=logear&facebook="+this.cuentaFacebook+"&nombre="+this.nombre+"&tel="+this.telefono;
        parameters = parameters.replaceAll(" ","%20");
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
	        
	        int contentAsString = readIt(connection.getInputStream());
	        return contentAsString;        
         }
         catch(IOException e)
         {
        	 return -1;
         }
	}
	public int readIt(InputStream stream) throws IOException, UnsupportedEncodingException {

		Reader reader = null;
		StringBuilder inputStringBuilder = new StringBuilder();
		reader = new InputStreamReader(stream, "UTF-8");     
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = bufferedReader.readLine();
		while(line != null){
	   		inputStringBuilder.append(line);
			line = bufferedReader.readLine();
	   }
		return Integer.valueOf((inputStringBuilder.toString()));
	}
}
