package com.flisol.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import com.flisol.connect.conexion;

public class model_expositores extends conexion{

	private static model_expositores objModelExpositores;
	
	private model_expositores()
	{
		super();
	}
	
	public Boolean agregar(String nombre,String procedencia,String especialidad)
	{
		return setQuery("insert into expositor values(null,'"+nombre+"', '"+procedencia+"', '"+especialidad+"');");
	}
	
	public Boolean eliminar(String id)
	{
		return setQuery("delete from expositor where id="+id+";");
	}
	
	public Boolean modificar(String id, String nombre, String procedencia, String especialidad)
	{
		return setQuery("Update expositor SET nombre='"+nombre+"' , procedencia='"+procedencia+"' , especialidad='"+especialidad+"' where id="+id+";");
	}
	
	public Vector<Vector<String>> consultar()
	{
		ResultSet resultado = getQuery("SELECT * FROM expositor");
		
		Vector<Vector<String>> datos=new Vector<Vector<String>>();
		
		try {
			while(resultado.next())
			{
				Vector<String> dato = new Vector<String>();
				
				dato.add(String.valueOf(resultado.getInt("id")));
				dato.add(resultado.getString("nombre"));
				dato.add(resultado.getString("procedencia"));
				dato.add(resultado.getString("especialidad"));
				
				datos.add(dato);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return datos;
	}
	
	public static model_expositores getInstance()
	{
		if(objModelExpositores == null)
			objModelExpositores = new model_expositores();
		
		return objModelExpositores;
	}
	
}
