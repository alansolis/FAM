package com.flisol.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.flisol.connect.conexion;

public class model_aula extends conexion{

	private static model_aula objModelAula;
	
	private model_aula()
	{
		super();
	}
	
	public Boolean agregar(String nombre, String lugares, String tipo)
	{
		return setQuery("insert into aula values(null,'"+nombre+"',"+lugares+",'"+tipo+"');");
	}
	
	public Vector<Vector<String>> contultar()
	{
		Vector<Vector<String>> objDatos = new Vector<Vector<String>>();
		ResultSet resultado = getQuery("select * from aula;");
		
		try {
			while(resultado.next())
			{
				Vector<String> dato = new Vector<String>();
				
				dato.add(String.valueOf(resultado.getInt("id")));
				dato.add(resultado.getString("nombre"));
				dato.add(resultado.getString("num_lugares"));
				dato.add(resultado.getString("tipo"));
				
				objDatos.add(dato);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return objDatos;
	}
	
	public Boolean eliminar(String id)
	{
		return setQuery("delete from aula where id="+id+";");
	}
	
	public Boolean modificar(String id, String nombre, String num_lugares, String tipo)
	{
		return setQuery("update aula set nombre='"+nombre+"', num_lugares="+num_lugares+", tipo='"+tipo+"' where id="+id+";");
	}
	
	public static model_aula getInstance()
	{
		if(objModelAula == null)
			objModelAula = new model_aula();
		
		return objModelAula;
	}
}
