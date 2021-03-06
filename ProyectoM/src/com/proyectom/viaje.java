package com.proyectom;

import java.util.Vector;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class viaje {
	int idViaje;
	int idConductor;
	Vector<Integer> idPasajero;
	int idOrigen;
	String ciudadOrigen;
	int idDestino;
	String ciudadDestino;
	String fecha;
	String ptoPartida;
	String ptoPartidagps;
	String ptoLlegada;
	String ptoLlegadagps;

		public viaje(int idViaje,int idConductor,int idOrigen,int idDestino,String fecha,String ptoPartida, String ptoPartidagps,String ptoLlegada,String ptoLlegadagps){
			this.idViaje = idViaje;
			this.idConductor = idConductor;
			this.idPasajero = new Vector<Integer>();
			this.idOrigen = idOrigen;
			this.idDestino = idDestino;
			this.fecha = fecha;
			this.ptoPartida = ptoPartida;
			this.ptoPartidagps = ptoPartidagps;
			this.ptoLlegada = ptoLlegada;
			this.ptoLlegadagps = ptoLlegadagps;
		}
		
		public void setidViaje(int idViaje){
			this.idViaje = idViaje;
		}
		public void setidConductor(int idConductor){
			this.idConductor = idConductor;
		}
		public void setidOrigen(int idOrigen, String ciudadOrigen){
			this.idOrigen = idOrigen;
			this.ciudadOrigen = ciudadOrigen;
		}
		public void setidDestino(int idDestino, String ciudadDestino){
			this.idDestino = idDestino;
			this.ciudadDestino = ciudadDestino;
		}
		public void setfecha(String fecha){
			this.fecha = fecha;
		}
		public void setptoPartida(String ptoPartida, String ptoPartidagps){
			this.ptoPartida = ptoPartida;
			this.ptoPartidagps = ptoPartidagps;
		}
		public void setptoLlegada(String ptoLlegada, String ptoLlegadagps){
			this.ptoLlegada = ptoLlegada;
			this.ptoLlegadagps = ptoLlegadagps;
		}
		public int getidViaje(){
			return this.idViaje;
		}
		public int getidConductor(){
			return this.idConductor;
		}
		public Vector<Integer> getidPasajero(){
			return this.idPasajero;
		}
		public int getidOrigen(){
			return this.idOrigen;
		}
		public int getidDestino(){
			return this.idDestino;
		}
		public String getfecha(){
			return this.fecha;
		}
		public String getptoPartida(){
			return this.ptoPartida;
		}
		public String getptoPartidagps(){
			return this.ptoPartidagps;
		}
		public String getptoLlegada(){
			return this.ptoLlegada;
		}
		public String getptoLlegadagps(){
			return this.ptoLlegadagps;
		}
		public void addPasajero(int idPasajero){
			this.idPasajero.add(idPasajero);
		}
		
		public void registerDB (){
			Connection con = null;
			PreparedStatement pst = null;
			
			String url = "jdbc:mysql://localhost:3306/carpooling";
			String user = "app";
			String password = "fernando";
			
			try{
				con = DriverManager.getConnection(url,user,password);
				pst = con.prepareStatement("INSERT INTO viaje(idconductor,idorigen,iddestino,fecha,ptopartida,ptopartidagps,ptollegada,ptollegadagps) VALUES (? ? ? ? ? ? ? ?)");
				pst.setInt(1,this.idConductor);
				pst.setInt(2,this.idOrigen);
				pst.setInt(3,this.idDestino);
				pst.setString(4,this.fecha);
				pst.setString(5,this.ptoPartida);
				pst.setString(6,this.ptoPartidagps);
				pst.setString(7,this.ptoLlegada);
				pst.setString(8,this.ptoLlegadagps);
				
				pst.executeUpdate();
			}catch(SQLException ex){
				Logger lgr = Logger.getLogger(PreparedStatement.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(),ex);
			}
			finally {

	            try {
	                if (pst != null) {
	                    pst.close();
	                }
	                if (con != null) {
	                    con.close();
	                }

	            } catch (SQLException ex) {
	                Logger lgr = Logger.getLogger(PreparedStatement.class.getName());
	                lgr.log(Level.SEVERE, ex.getMessage(), ex);
	            }
	        }
		}
		
}
