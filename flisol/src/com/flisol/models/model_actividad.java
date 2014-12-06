package com.flisol.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JComboBox;
import com.flisol.connect.conexion;

public class model_actividad extends conexion{

	private static model_actividad objModelActividad;
	
	private model_actividad()
	{
		super();
	}
	
	public JComboBox<String> getComboExpositores()
	{
		
		ResultSet resultado = getQuery("SELECT * FROM expositor");
		
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("Selecciona un expositor...");
		try {
			while(resultado.next())
			{
				combo.addItem(resultado.getString("nombre"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return combo;
	}
	
	public Boolean modificar(String id, String titulo, Object expositor, Object aula, String fecha, String hora, String duracion, Object tipo)
	{
		ResultSet resultadoExpositor = getQuery("SELECT * FROM expositor where nombre='"+expositor+"';");
		int id_expositor = 0;
		try {
			while(resultadoExpositor.next())
			{
				id_expositor = resultadoExpositor.getInt("id");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		ResultSet resultadoAula = getQuery("SELECT * FROM aula where nombre='"+aula+"';");
		int id_aula = 0, num_lugares = 0;
		try {
			while(resultadoAula.next())
			{
				id_aula = resultadoAula.getInt("id");
				num_lugares = resultadoAula.getInt("num_lugares");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		ResultSet resultadoLugares = getQuery("SELECT * FROM actividad where id="+id+";");
		
		
		try {
			while(resultadoLugares.next())
			{
				if(resultadoLugares.getInt("id_aula") == id_aula)
				{
					num_lugares = resultadoLugares.getInt("lugares");
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return setQuery("update actividad set titulo='"+titulo+"', id_expositor="+id_expositor+",id_aula="+id_aula+",fecha='"+fecha+"'," +
				"hora='"+hora+"',duracion='"+duracion+"',tipo='"+tipo+"', lugares="+num_lugares+" where id="+id+";");
	}
	
	public JComboBox<String> getComboAula()
	{
		
		ResultSet resultado = getQuery("SELECT * FROM aula");
		
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("Selecciona un aula...");
		try {
			while(resultado.next())
			{
				combo.addItem(resultado.getString("nombre"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return combo;
	}
	
	public Boolean agregar(String titulo, Object expositor, Object aula, String fecha, String hora, String duracion, Object tipo)
	{
		ResultSet resultadoExpositor = getQuery("SELECT * FROM expositor where nombre='"+expositor+"';");
		int id_expositor = 0;
		try {
			while(resultadoExpositor.next())
			{
				id_expositor = resultadoExpositor.getInt("id");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		ResultSet resultadoAula = getQuery("SELECT * FROM aula where nombre='"+aula+"';");
		int id_aula = 0, num_lugares = 0;
		try {
			while(resultadoAula.next())
			{
				id_aula = resultadoAula.getInt("id");
				num_lugares = resultadoAula.getInt("num_lugares");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return setQuery("insert into actividad values(null,'"+titulo+"',"+id_expositor+","+id_aula+",'"+fecha+"','"+hora+"','"+duracion+"','"+tipo+"',"+num_lugares+");");
	}
	
	public Vector<Vector<String>> consultar()
	{
		Vector<Vector<String>> datos = new Vector<Vector<String>>();
		
		ResultSet resultado = getQuery("SELECT * FROM actividad;");
		
		try {
			while(resultado.next())
			{
				Vector<String> dato = new Vector<String>();
				dato.add(String.valueOf(resultado.getInt("id")));
				dato.add(resultado.getString("titulo"));
				
				ResultSet resultadoExpositor = getQuery("SELECT * FROM expositor where id="+resultado.getInt("id_expositor")+";");
				while(resultadoExpositor.next())
				{
					dato.add(resultadoExpositor.getString("nombre"));
				}
				
				ResultSet resultadoAula = getQuery("SELECT * FROM aula where id="+resultado.getInt("id_aula")+";");
				while(resultadoAula.next())
				{
					dato.add(resultadoAula.getString("nombre"));
				}
				
				dato.add(resultado.getString("fecha"));
				dato.add(resultado.getString("hora"));
				dato.add(String.valueOf(resultado.getInt("duracion")));
				dato.add(resultado.getString("tipo"));
				dato.add(String.valueOf(resultado.getInt("lugares")));
				
				datos.add(dato);
				
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return datos;
	}
	
	public Boolean verificarExpositor(Object object, String fecha, String hora, String duracion)
	{
		ResultSet resultadoExpositor = getQuery("select * from expositor where nombre='"+object+"'");
		
		int id_expositor = 0;
		
		try {
			while(resultadoExpositor.next())
			{
				id_expositor = resultadoExpositor.getInt("id");
			}
		} catch (SQLException e1) {
			System.out.println(e1);
		}
		
		ResultSet actividadHoy = getQuery("select * from actividad where id_expositor = "+id_expositor+" AND fecha = '"+fecha+"';");
		
		int i = 0;
		
		
		try {
			while(actividadHoy.next())
			{
				String[] horaArrayRecibida  = hora.split(":");
				
				int horaTerminoRecibida = Integer.parseInt(horaArrayRecibida[0]) + Integer.parseInt(duracion);
				
				String[] horaArrayEncontrada = actividadHoy.getString("hora").split(":");
				
				int horaTerminoEncontrada = Integer.parseInt(horaArrayEncontrada[0]) + actividadHoy.getInt("duracion");
				
				int direfencia = horaTerminoRecibida - horaTerminoEncontrada;
				
				if((horaTerminoEncontrada - Integer.parseInt(horaArrayRecibida[0])) > 0 && 
						(horaTerminoRecibida - Integer.parseInt(horaArrayEncontrada[0])) > 0)
				{
					i++;
				}
				
			}
		} catch (SQLException e1) {
			System.out.println(e1);
		}
		
		if(i>0)
			return true;
		else
			return false;
	}
	
	public Boolean verificarExpositorModificar(String id,Object object, String fecha, String hora, String duracion)
	{
		ResultSet resultadoExpositor = getQuery("select * from expositor where nombre='"+object+"'");
		
		int id_expositor = 0;
		
		try {
			while(resultadoExpositor.next())
			{
				id_expositor = resultadoExpositor.getInt("id");
			}
		} catch (SQLException e1) {
			System.out.println(e1);
		}
		
		ResultSet actividadHoy = getQuery("select * from actividad where id_expositor = "+id_expositor+" AND fecha = '"+fecha+"' AND id != "+id+";");
		
		int i = 0;
		
		
		try {
			while(actividadHoy.next())
			{
				String[] horaArrayRecibida  = hora.split(":");
				
				int horaTerminoRecibida = Integer.parseInt(horaArrayRecibida[0]) + Integer.parseInt(duracion);
				
				String[] horaArrayEncontrada = actividadHoy.getString("hora").split(":");
				
				int horaTerminoEncontrada = Integer.parseInt(horaArrayEncontrada[0]) + actividadHoy.getInt("duracion");
				
				if((horaTerminoEncontrada - Integer.parseInt(horaArrayRecibida[0])) > 0 && 
						(horaTerminoRecibida - Integer.parseInt(horaArrayEncontrada[0])) > 0)
				{
					i++;
				}
				
			}
		} catch (SQLException e1) {
			System.out.println(e1);
		}
		
		if(i>0)
			return true;
		else
			return false;
	}
	
	public Boolean verificarAula(Object object, String fecha, String hora, String duracion)
	{
		ResultSet resultadoAula = getQuery("select * from aula where nombre='"+object+"'");
		
		int id_aula = 0;
		
		try {
			while(resultadoAula.next())
			{
				id_aula = resultadoAula.getInt("id");
			}
		} catch (SQLException e1) {
			System.out.println(e1);
		}
		
		ResultSet actividadHoy = getQuery("select * from actividad where id_aula = "+id_aula+" AND fecha = '"+fecha+"';");
		
		int i = 0;
		
		
		try {
			while(actividadHoy.next())
			{
				String[] horaArrayRecibida  = hora.split(":");
				
				int horaTerminoRecibida = Integer.parseInt(horaArrayRecibida[0]) + Integer.parseInt(duracion);
				
				String[] horaArrayEncontrada = actividadHoy.getString("hora").split(":");
				
				int horaTerminoEncontrada = Integer.parseInt(horaArrayEncontrada[0]) + actividadHoy.getInt("duracion");
				
				if((horaTerminoEncontrada - Integer.parseInt(horaArrayRecibida[0])) > 0 && 
						(horaTerminoRecibida - Integer.parseInt(horaArrayEncontrada[0])) > 0)
				{
					i++;
				}
				
			}
		} catch (SQLException e1) {
			System.out.println(e1);
		}
		
		if(i>0)
			return true;
		else
			return false;
	}
	
	public Boolean verificarAulaModificar(String id,Object object, String fecha, String hora, String duracion)
	{
		ResultSet resultadoAula = getQuery("select * from aula where nombre='"+object+"'");
		
		int id_aula = 0;
		
		try {
			while(resultadoAula.next())
			{
				id_aula = resultadoAula.getInt("id");
			}
		} catch (SQLException e1) {
			System.out.println(e1);
		}
		
		ResultSet actividadHoy = getQuery("select * from actividad where id_aula = "+id_aula+" AND fecha = '"+fecha+"' AND id != "+id+";");
		
		int i = 0;
		
		
		try {
			while(actividadHoy.next())
			{
				String[] horaArrayRecibida  = hora.split(":");
				
				int horaTerminoRecibida = Integer.parseInt(horaArrayRecibida[0]) + Integer.parseInt(duracion);
				
				String[] horaArrayEncontrada = actividadHoy.getString("hora").split(":");
				
				int horaTerminoEncontrada = Integer.parseInt(horaArrayEncontrada[0]) + actividadHoy.getInt("duracion");
				
				if((horaTerminoEncontrada - Integer.parseInt(horaArrayRecibida[0])) > 0 && 
						(horaTerminoRecibida - Integer.parseInt(horaArrayEncontrada[0])) > 0)
				{
					i++;
				}
				
			}
		} catch (SQLException e1) {
			System.out.println(e1);
		}
		
		if(i>0)
			return true;
		else
			return false;
	}
	
	public Boolean eliminar(String id)
	{
		return setQuery("delete from actividad where id="+id+";");
	}
	
	public static model_actividad getInstance()
	{
		if(objModelActividad == null)
			objModelActividad = new model_actividad();
		
		return objModelActividad;
	}
}
