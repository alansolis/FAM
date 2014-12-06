package com.flisol.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JComboBox;
import com.flisol.connect.conexion;

public class model_registro extends conexion{

	private static model_registro objModelRegistro;
	
	private model_registro()
	{
		super();
	}
	
	public JComboBox<String> getActividades()
	{
		JComboBox<String> objComboActividades = new JComboBox<String>();
		objComboActividades.addItem("Selecciona una actividad");
		
		ResultSet actividades = getQuery("select * from actividad;");
		
		try {
			while(actividades.next())
			{
				objComboActividades.addItem(actividades.getString("titulo"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return objComboActividades;
	}
	
	public JComboBox<String> getAsistentes()
	{
		JComboBox<String> objComboAsistente = new JComboBox<String>();
		objComboAsistente.addItem("Selecciona un asistente");
		
		ResultSet asistentes = getQuery("select * from asistente;");
		
		try {
			while(asistentes.next())
			{
				objComboAsistente.addItem(asistentes.getString("nombre"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return objComboAsistente;
	}
	
	public Boolean verificarDisponibilidad(String actividad, String asistente)
	{
		int id_actividad = 0, id_asistente = 0;
		ResultSet resultadoActividad = getQuery("select * from actividad where titulo='"+actividad+"';");
		
		try {
			while(resultadoActividad.next())
			{
				id_actividad = resultadoActividad.getInt("id");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		ResultSet resultadoAsistente = getQuery("select * from asistente where nombre='"+asistente+"';");
		
		try {
			while(resultadoAsistente.next())
			{
				id_asistente = resultadoAsistente.getInt("id");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		int i=0;
		
		ResultSet resultadoVerificar = getQuery("select * from registra where id_actividad="+id_actividad+" and id_asistente="+id_asistente+";");
		
		try {
			while(resultadoVerificar.next())
			{
				i++;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		if(i>0)
			return true;
		else
			return false;
	}
	
	public int verificarAula(String actividad)
	{
		int lugares = 0;
		ResultSet resultadoAula = getQuery("select * from actividad where titulo='"+actividad+"';");
		
		try {
			while(resultadoAula.next())
			{
				lugares = resultadoAula.getInt("lugares");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return lugares;
	}
	
	public Boolean registrar(String actividad, String asistente)
	{
		int id_actividad = 0, num_lugares = 0;
		ResultSet resultadoActividad = getQuery("select * from actividad where titulo='"+actividad+"';");
		
		try {
			while(resultadoActividad.next())
			{
				id_actividad = resultadoActividad.getInt("id");
				num_lugares = resultadoActividad.getInt("lugares");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		int id_asistente = 0;
		ResultSet resultadoAsistente = getQuery("select * from asistente where nombre='"+asistente+"';");
		
		try {
			while(resultadoAsistente.next())
			{
				id_asistente = resultadoAsistente.getInt("id");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		num_lugares--;
		
		String sql1="update actividad set lugares="+num_lugares+" where id="+id_actividad+";";
		String sql2 = "insert into registra values("+id_asistente+","+id_actividad+");";
		
		setQuery(sql1);
		
		return setQuery(sql2);
	}
	
	public Boolean eliminar(String actividad, String asistente)
	{
		int id_actividad = 0, num_lugares = 0;
		ResultSet resultadoActividad = getQuery("select * from actividad where titulo='"+actividad+"';");
		
		try {
			while(resultadoActividad.next())
			{
				id_actividad = resultadoActividad.getInt("id");
				num_lugares = resultadoActividad.getInt("lugares");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		int id_asistente = 0;
		ResultSet resultadoAsistente = getQuery("select * from asistente where nombre='"+asistente+"';");
		
		try {
			while(resultadoAsistente.next())
			{
				id_asistente = resultadoAsistente.getInt("id");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		num_lugares++;
		
		String sql="update actividad set lugares="+num_lugares+" where id="+id_actividad+";";
		String sql1="delete from registra where id_asistente="+id_asistente+" and id_actividad="+id_actividad+";";
		
		setQuery(sql);
		return setQuery(sql1);
	}
	
	public Boolean verificarAsistente(String actividad, String asistente)
	{
		int id_asistente = 0;
		ResultSet resultadoAsistente = getQuery("select * from asistente where nombre='"+asistente+"';");
		
		try {
			while(resultadoAsistente.next())
			{
				id_asistente = resultadoAsistente.getInt("id");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		ResultSet resultado = getQuery("select * from registra where id_asistente = "+id_asistente+";");
		
		int i = 0;
		
		try {
			while(resultado.next())
			{
				int id_actividad = resultado.getInt("id_actividad");
				
				ResultSet resultadoActividadEncontrado = getQuery("select * from actividad where id="+id_actividad+";");
				
				ResultSet resultadoActividadRecibido = getQuery("select * from actividad where titulo='"+actividad+"';");
				
				while(resultadoActividadRecibido.next())
				{
					while(resultadoActividadEncontrado.next())
					{
						if(resultadoActividadEncontrado.getString("fecha").equals(resultadoActividadRecibido.getString("fecha")))
						{
							String[] horaArrayRecibida  = resultadoActividadRecibido.getString("hora").split(":");
							
							int horaTerminoRecibida = Integer.parseInt(horaArrayRecibida[0]) + resultadoActividadRecibido.getInt("duracion");
							
							String[] horaArrayEncontrada = resultadoActividadEncontrado.getString("hora").split(":");
							
							int horaTerminoEncontrada = Integer.parseInt(horaArrayEncontrada[0]) + resultadoActividadEncontrado.getInt("duracion");
							
							if((horaTerminoEncontrada - Integer.parseInt(horaArrayRecibida[0])) > 0 && 
									(horaTerminoRecibida - Integer.parseInt(horaArrayEncontrada[0])) > 0)
							{
								i++;
							}
						}
					}
					
				}
				
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		if(i>0)
			return true;
		else
			return false;
	}
	
	
	public Vector<Vector<String>> consultar()
	{
		Vector<Vector<String>> datos = new Vector<Vector<String>>();
		
		ResultSet resultado = getQuery("select * from registra;");
		
		try {
			while(resultado.next())
			{
				Vector<String> dato = new Vector<String>();
				

				int id_asistente = resultado.getInt("id_asistente");
				int id_actividad = resultado.getInt("id_actividad");
				
				ResultSet resultadoActividad = getQuery("select * from actividad where id="+id_actividad+";");
				
				while(resultadoActividad.next())
				{
					dato.add(resultadoActividad.getString("titulo"));
				}
				
				ResultSet resultadoAsistente = getQuery("select * from asistente where id="+id_asistente+";");
				
				while(resultadoAsistente.next())
				{
					dato.add(resultadoAsistente.getString("nombre"));
				}
				
				datos.add(dato);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return datos;
	}
	
	public static model_registro getInstance()
	{
		if(objModelRegistro == null)
			objModelRegistro = new model_registro();
		
		return objModelRegistro;
	}
}
