package com.flisol.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.flisol.connect.conexion;

public class model_asistentes extends conexion{

	private static model_asistentes objModelAsistentes;
	
	private model_asistentes()
	{
		super();
	}
	
	public Boolean guardar(String nombre, String grupo, String ocupacion, String procedencia)
	{
		return setQuery("insert into asistente values(null,'"+nombre+"','"+grupo+"','"+ocupacion+"','"+procedencia+"');");
	}
	
	public Boolean modificar(String id,String nombre, String grupo, String ocupacion, String procedencia)
	{
		return setQuery("update asistente set nombre='"+nombre+"', grupo='"+grupo+"', ocupacion='"+ocupacion+"',procedencia='"+procedencia+"' where id="+id+";");
	}
	
	public Vector<Vector<String>> consultar()
	{
		Vector<Vector<String>> datos = new Vector<Vector<String>>();
		ResultSet resultado = getQuery("SELECT * FROM asistente;");
		
		try {
			while(resultado.next())
			{
				Vector<String> dato = new Vector<String>();
				
				dato.add(String.valueOf(resultado.getInt("id")));
				dato.add(resultado.getString("nombre"));
				dato.add(resultado.getString("grupo"));
				dato.add(resultado.getString("ocupacion"));
				dato.add(resultado.getString("procedencia"));
				
				datos.add(dato);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return datos;
	}
	
	public Boolean eliminar(String id)
	{
		return setQuery("delete from asistente where id="+id+";");
	}
	
	public static model_asistentes getInstance()
	{
		if(objModelAsistentes == null)
			objModelAsistentes = new model_asistentes();
		
		return objModelAsistentes;
	}
	
}
