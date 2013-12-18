package com.proyectom;

public class usuario {
	int idUsuario;
	String nombre;
	String cuentaFacebook;
	int telefono;
	
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
}
