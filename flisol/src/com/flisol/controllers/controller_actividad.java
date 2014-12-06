package com.flisol.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import com.flisol.models.model_actividad;
import com.flisol.views.view_actividad;

public class controller_actividad implements ActionListener{

	private static controller_actividad objControllerActividad;
	private view_actividad objViewActividad;
	private model_actividad objModelActividad;
	private String id, titulo, fecha, hora, duracion, aula, expositor, tipo;
	
	private controller_actividad()
	{
		initComponents();
	}
	
	private void initComponents()
	{
		objModelActividad = model_actividad.getInstance();
		objViewActividad = view_actividad.getInstance(objModelActividad.getComboExpositores(),
		objModelActividad.getComboAula(),objModelActividad.consultar());
		objViewActividad.getBotonAgregar().addActionListener(this);
		objViewActividad.getBotonModificar().addActionListener(this);
		objViewActividad.getBotonEliminar().addActionListener(this);
		objViewActividad.getBotonCancelar().addActionListener(this);
	
	}
	
	public static controller_actividad getInstance()
	{
		if(objControllerActividad == null)
			objControllerActividad = new controller_actividad();
		
		return objControllerActividad;
	}
	
	public JInternalFrame getViewActividad()
	{
		return this.objViewActividad;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == objViewActividad.getBotonAgregar())
		{
			String resultado = validar();
			if(resultado.equals(""))
			{
				if(objModelActividad.agregar(objViewActividad.getTitulo(), objViewActividad.getItemExpositor(), objViewActividad.getItemAula(),
						objViewActividad.getFecha(), objViewActividad.getHora(), objViewActividad.getDuracion(), objViewActividad.getItemTipo()))
				{
					objViewActividad.setModel(objModelActividad.consultar());
					JOptionPane.showMessageDialog(objViewActividad, "Actividad agregada correctamente");
					limpiar();
				}else
				{
					JOptionPane.showMessageDialog(objViewActividad, "No se pudo agregar la actividad, intentalo más tarde","Error",JOptionPane.ERROR_MESSAGE);
				}
			}else
			{
				JOptionPane.showMessageDialog(objViewActividad, resultado, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if(arg0.getSource() == objViewActividad.getBotonCancelar())
		{
			limpiar();
		}else if(arg0.getSource() == objViewActividad.getBotonEliminar())
		{
			if(objViewActividad.getTabla().getSelectedRow() >= 0)
			{
				this.id = (String) objViewActividad.getTabla().getValueAt(objViewActividad.getTabla().getSelectedRow(), 0);
				this.titulo = (String) objViewActividad.getTabla().getValueAt(objViewActividad.getTabla().getSelectedRow(), 1);
				
				int eliminar = JOptionPane.showConfirmDialog(objViewActividad, "¿Estas seguro que deseas eliminar la actividad "+this.titulo+"?");
				
				if(eliminar==0)
				{
					if(objModelActividad.eliminar(this.id))
					{
						JOptionPane.showMessageDialog(objViewActividad, "Actividad eliminada correctamente");
						objViewActividad.setModel(objModelActividad.consultar());
						limpiar();
					}else
					{
						JOptionPane.showMessageDialog(objViewActividad, "No se pudo eliminar la actividad, intentelo más tarde","Error",JOptionPane.ERROR_MESSAGE);
					}
				}else
				{
					limpiar();
				}
			}else
			{
				JOptionPane.showMessageDialog(objViewActividad, "Selecciona una actividad de la tabla","Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(arg0.getSource() == objViewActividad.getBotonModificar())
		{
			if(this.id == null)
			{
				if(objViewActividad.getTabla().getSelectedRow() >= 0 )
				{
					this.id = (String) objViewActividad.getTabla().getValueAt(objViewActividad.getTabla().getSelectedRow(), 0);
					this.titulo = (String) objViewActividad.getTabla().getValueAt(objViewActividad.getTabla().getSelectedRow(), 1);
					this.expositor = (String) objViewActividad.getTabla().getValueAt(objViewActividad.getTabla().getSelectedRow(), 2);
					this.aula = (String) objViewActividad.getTabla().getValueAt(objViewActividad.getTabla().getSelectedRow(), 3);
					this.fecha = (String) objViewActividad.getTabla().getValueAt(objViewActividad.getTabla().getSelectedRow(), 4);
					this.hora = (String) objViewActividad.getTabla().getValueAt(objViewActividad.getTabla().getSelectedRow(), 5);
					this.duracion = (String) objViewActividad.getTabla().getValueAt(objViewActividad.getTabla().getSelectedRow(), 6);
					this.tipo = (String) objViewActividad.getTabla().getValueAt(objViewActividad.getTabla().getSelectedRow(), 7);
					
					objViewActividad.setTitulo(this.titulo);
					objViewActividad.setItemExpositor(this.expositor);
					objViewActividad.setItemAula(this.aula);
					objViewActividad.setFecha(this.fecha);
					objViewActividad.setHora(this.hora);
					objViewActividad.setDuracion(this.duracion);
					objViewActividad.setItemTipo(this.tipo);
				}else
				{
					JOptionPane.showMessageDialog(objViewActividad, "Selecciona una actividad de la clase","Error",JOptionPane.ERROR_MESSAGE);
				}	
			}else
			{
				String resultado = validarModificar(this.id);
				
				if(resultado.equals(""))
				{
					if(objModelActividad.modificar(this.id, objViewActividad.getTitulo(), objViewActividad.getItemExpositor(),
							objViewActividad.getItemAula(), objViewActividad.getFecha(), objViewActividad.getHora(), objViewActividad.getDuracion(), objViewActividad.getItemTipo()))
					{
						objViewActividad.setModel(objModelActividad.consultar());
						limpiar();
						JOptionPane.showMessageDialog(objViewActividad, "Actividad modificada correctamente");
					}else
					{
						limpiar();
						JOptionPane.showMessageDialog(objViewActividad, "No se pudo modificar la actividad", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}else
				{
					JOptionPane.showMessageDialog(objViewActividad, resultado, "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	public String validar()
	{
		String validar = "", patronTexto = "[^A-Za-zñáéíóú0-9 ]", patronFecha = "\\d{4,4}-\\d{2,2}-\\d{2,2}",
				patronEntero = "^(?:\\+|-)?\\d+$", patronHora = "^(0[1-9]|1\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$";
		Pattern pTexto = Pattern.compile(patronTexto);
		Boolean pFecha = Pattern.matches(patronFecha, objViewActividad.getFecha());
		Boolean pEntero = Pattern.matches(patronEntero,objViewActividad.getDuracion());
		Boolean pHora = Pattern.matches(patronHora, objViewActividad.getHora());
		String[] fechaArray = objViewActividad.getFecha().split("-");
		Calendar fecha = new GregorianCalendar();
		int anio = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH);
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		
		mes++;
		
		Matcher matcherTexto = pTexto.matcher(objViewActividad.getTitulo());
		
		if(objViewActividad.getTitulo().equals("") || objViewActividad.getTitulo() == null ||
				objViewActividad.getFecha().equals("") || objViewActividad.getFecha() == null ||
				objViewActividad.getHora().equals("") || objViewActividad.getHora() == null ||
				objViewActividad.getDuracion().equals("") || objViewActividad.getDuracion() == null ||
				objViewActividad.getIndexAula() == 0 || objViewActividad.getIndexExpositor() == 0 ||
				objViewActividad.getIndexTipo() == 0)
		{
			validar = "Completar el formulario";
		}else if(matcherTexto.find())
		{
			validar = "El titulo solo debe contener letras";
		}else if(pFecha == false)
		{
			validar = "Formato de fecha incorrecto, ejemplo: 2014-01-01";
		}else if(Integer.parseInt(fechaArray[1]) > 12 || Integer.parseInt(fechaArray[2]) > 31)
		{
			validar = "La fecha no existe";
		}else if(Integer.parseInt(fechaArray[0]) < anio)
		{
			validar = "La fecha de la actividad no puede ser menor a la actual";
		}else if(Integer.parseInt(fechaArray[0]) == anio && Integer.parseInt(fechaArray[1]) < mes)
		{
			validar = "La fecha de la actividad no puede ser menor a la actual";
		}else if(Integer.parseInt(fechaArray[0]) == anio && Integer.parseInt(fechaArray[1]) == mes && Integer.parseInt(fechaArray[2]) < dia)
		{
			validar = "La fecha de la actividad no puede ser menor a la actual";
		}
		else if(pHora == false)
		{
			validar = "Formato de hora incorrecto, ejemplo: 13:00:00";
		}else if(pEntero == false)
		{
			validar = "La duración debe ser un número entero";
			
		}else if(Integer.parseInt(objViewActividad.getDuracion()) < 1)
		{
			validar = "La duración debe ser igual o mayor a 1 hora";
		}else if(Integer.parseInt(objViewActividad.getDuracion()) > 5)
		{
			validar = "La duración no debe se mayor a 5 horas";
		}else if(objModelActividad.verificarExpositor(objViewActividad.getItemExpositor(), objViewActividad.getFecha(), objViewActividad.getHora(), objViewActividad.getDuracion()))
		{
			validar = "Al parecer este expositor estará en otra actividad en ese momento, verificar fecha y hora.";
		}else if(objModelActividad.verificarAula(objViewActividad.getItemAula(), objViewActividad.getFecha(), objViewActividad.getHora(), objViewActividad.getDuracion()))
		{
			validar = "Al parecer hay un aula que ocupará el lugar en ese momento, verificar fecha y hora.";
		}
		
		return validar;
		
	}
	
	public String validarModificar(String id)
	{
		String validar = "", patronTexto = "[^A-Za-zñáéíóú0-9 ]", patronFecha = "\\d{2,4}-\\d{1,2}-\\d{1,2}",
				patronEntero = "^(?:\\+|-)?\\d+$", patronHora = "^(0[1-9]|1\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$";
		Pattern pTexto = Pattern.compile(patronTexto);
		Boolean pFecha = Pattern.matches(patronFecha, objViewActividad.getFecha());
		Boolean pEntero = Pattern.matches(patronEntero,objViewActividad.getDuracion());
		Boolean pHora = Pattern.matches(patronHora, objViewActividad.getHora());
		String[] fechaArray = objViewActividad.getFecha().split("-");
		Calendar fecha = new GregorianCalendar();
		int anio = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH);
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		
		mes++;
		
		Matcher matcherTexto = pTexto.matcher(objViewActividad.getTitulo());
		
		if(objViewActividad.getTitulo().equals("") || objViewActividad.getTitulo() == null ||
				objViewActividad.getFecha().equals("") || objViewActividad.getFecha() == null ||
				objViewActividad.getHora().equals("") || objViewActividad.getHora() == null ||
				objViewActividad.getDuracion().equals("") || objViewActividad.getDuracion() == null ||
				objViewActividad.getIndexAula() == 0 || objViewActividad.getIndexExpositor() == 0 ||
				objViewActividad.getIndexTipo() == 0)
		{
			validar = "Completar el formulario";
		}else if(matcherTexto.find())
		{
			validar = "El titulo solo debe contener letras";
		}else if(pFecha == false)
		{
			validar = "Formato de fecha incorrecto, ejemplo: 2014-01-01";
		}else if(Integer.parseInt(fechaArray[0]) < anio)
		{
			validar = "La fecha de la actividad no puede ser menor a la actual";
		}else if(Integer.parseInt(fechaArray[0]) == anio && Integer.parseInt(fechaArray[1]) < mes)
		{
			validar = "La fecha de la actividad no puede ser menor a la actual";
		}else if(Integer.parseInt(fechaArray[0]) == anio && Integer.parseInt(fechaArray[1]) == mes && Integer.parseInt(fechaArray[2]) < dia)
		{
			validar = "La fecha de la actividad no puede ser menor a la actual";
		}
		else if(pHora == false)
		{
			validar = "Formato de hora incorrecto, ejemplo: 13:00:00";
		}else if(pEntero == false)
		{
			validar = "La duración debe ser un número entero";
			
		}else if(Integer.parseInt(objViewActividad.getDuracion()) < 1)
		{
			validar = "La duración debe ser igual o mayor a 1 hora";
		}else if(Integer.parseInt(objViewActividad.getDuracion()) > 5)
		{
			validar = "La duración no debe se mayor a 5 horas";
		}else if(objModelActividad.verificarExpositorModificar(id,objViewActividad.getItemExpositor(), objViewActividad.getFecha(), objViewActividad.getHora(), objViewActividad.getDuracion()))
		{
			validar = "Al parecer este expositor estará en otra actividad en ese momento, verificar fecha y hora.";
		}else if(objModelActividad.verificarAulaModificar(id,objViewActividad.getItemAula(), objViewActividad.getFecha(), objViewActividad.getHora(), objViewActividad.getDuracion()))
		{
			validar = "Al parecer hay un aula que ocupará el lugar en ese momento, verificar fecha y hora.";
		}
		
		return validar;
		
	}
	
	public void limpiar()
	{
		this.id = null;
		this.titulo = null;
		this.fecha = null;
		this.hora = null;
		this.duracion = null;
		this.aula = null;
		this.tipo = null;
		this.expositor = null;
		
		objViewActividad.setTitulo("");
		objViewActividad.setFecha("");
		objViewActividad.setHora("");
		objViewActividad.setDuracion("");
		objViewActividad.setModel(objModelActividad.consultar());
		objViewActividad.setComboExpositor(objModelActividad.getComboExpositores());
		objViewActividad.setComboAula(objModelActividad.getComboAula());
		objViewActividad.setIndexAula(0);
		objViewActividad.setIndexExpositor(0);
		objViewActividad.setIndexTipo(0);
	}

}
