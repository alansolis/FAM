package com.flisol.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conexion {
	
	private static conexion objConexion;
	private String usuario, password;
	private static String dataBase, url;
	private Connection conexion;
	
	protected conexion()
	{
		this.usuario = "root";
		this.password = "alan92";
		dataBase = "flisol";
		url = "jdbc:mysql://localhost/"+dataBase;
		conexion = null;
		conectarse();
	}
	
	private void conectarse()
	{
		
		try {
			Class.forName("com.mysql.jdbc.Connection");
			conexion = (Connection) DriverManager.getConnection(url,this.usuario,this.password);
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		
		if(this.conexion != null)
			System.out.println("Conexión exitosa a "+url+" . . . . . . . . . . . . . . . . [OK]");
	}
	
	public ResultSet getQuery(String query)
	{
		Statement state = null;
		ResultSet resultado = null;
		
		try {
			state = (Statement) conexion.createStatement();
			resultado = state.executeQuery(query);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return resultado;
	}
	
	public Boolean setQuery(String query)
	{
		Statement state = null;
		
		try {
			state = (Statement) conexion.createStatement();
			state.execute(query);
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
			
		return true;
	}
	
	public static conexion getInstance()
	{
		if(objConexion == null)
			objConexion = new conexion();
		
		return objConexion;
	}
	
}
