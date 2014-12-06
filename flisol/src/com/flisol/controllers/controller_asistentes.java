package com.flisol.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import com.flisol.models.model_asistentes;
import com.flisol.views.view_asistentes;

public class controller_asistentes implements ActionListener{

	
	private static controller_asistentes objControllerAsistentes;
	private view_asistentes objViewAsistentes;
	private model_asistentes objModelAsistentes;
	private String id,nombre, grupo, ocupacion, procedencia;
	
	private controller_asistentes()
	{
		initComponents();
	}
	
	private void initComponents()
	{
		objModelAsistentes  = model_asistentes.getInstance();
		objViewAsistentes = view_asistentes.getInstance(objModelAsistentes.consultar());
		objViewAsistentes.getBotonAgregar().addActionListener(this);
		objViewAsistentes.getBotonEliminar().addActionListener(this);
		objViewAsistentes.getBotonCancelar().addActionListener(this);
		objViewAsistentes.getBotonModificar().addActionListener(this);
		
		this.id = null;
		this.nombre = null;
		this.grupo = null;
		this.procedencia = null;
		this.ocupacion = null;
	}
	
	public static controller_asistentes getInstance()
	{
		if(objControllerAsistentes == null)
			objControllerAsistentes = new controller_asistentes();
		
		return objControllerAsistentes;
	}
	
	public JInternalFrame getViewAsistentes()
	{
		return objViewAsistentes;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == objViewAsistentes.getBotonAgregar())
		{
			String resultado = validar();
			
			if(resultado.equals(""))
			{
				if(objModelAsistentes.guardar(objViewAsistentes.getNombre(), objViewAsistentes.getGrupo(), objViewAsistentes.getOcupacion(), objViewAsistentes.getProcedencia()))
				{
					objViewAsistentes.setModel(objModelAsistentes.consultar());
					JOptionPane.showMessageDialog(objViewAsistentes, "Asistente agregado correctamente");
					limpiar();
				}else
				{
					JOptionPane.showMessageDialog(objViewAsistentes, "No se pudo agregar el asistente","Error",JOptionPane.ERROR_MESSAGE);
				}
			}else
			{
				JOptionPane.showMessageDialog(objViewAsistentes,resultado,"Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource() == objViewAsistentes.getBotonEliminar())
		{
			if(objViewAsistentes.getTabla().getSelectedRow() >= 0)
			{
				this.id = String.valueOf(objViewAsistentes.getTabla().getValueAt(objViewAsistentes.getTabla().getSelectedRow(),0));
				this.nombre = String.valueOf(objViewAsistentes.getTabla().getValueAt(objViewAsistentes.getTabla().getSelectedRow(),1));
				
				int resultado = JOptionPane.showConfirmDialog(objViewAsistentes, "¿Estas seguro que deseas eliminar a "+this.nombre+"?");
				
				if(resultado == 0)
				{
					if(objModelAsistentes.eliminar(this.id))
					{
						objViewAsistentes.setModel(objModelAsistentes.consultar());
						JOptionPane.showMessageDialog(objViewAsistentes, "Asistente eliminado satisfactoriamente");
					}else
					{
						JOptionPane.showMessageDialog(objViewAsistentes, "No se pudo eliminar el asistente, intentalo más tarde","Error",JOptionPane.ERROR_MESSAGE);
					}
					
					limpiar();
				}else
				{
					limpiar();
				}
			}else 
			{
				JOptionPane.showConfirmDialog(objViewAsistentes, "Seleccione un asistente de la tabla");
			}
		}else if(e.getSource() == objViewAsistentes.getBotonModificar())
		{
			if(this.id == null)
			{
				if(objViewAsistentes.getTabla().getSelectedRow() >= 0)
				{
					this.id = String.valueOf(objViewAsistentes.getTabla().getValueAt(objViewAsistentes.getTabla().getSelectedRow(),0));
					this.nombre = String.valueOf(objViewAsistentes.getTabla().getValueAt(objViewAsistentes.getTabla().getSelectedRow(),1));
					this.grupo = String.valueOf(objViewAsistentes.getTabla().getValueAt(objViewAsistentes.getTabla().getSelectedRow(),2));
					this.ocupacion = String.valueOf(objViewAsistentes.getTabla().getValueAt(objViewAsistentes.getTabla().getSelectedRow(),3));
					this.procedencia = String.valueOf(objViewAsistentes.getTabla().getValueAt(objViewAsistentes.getTabla().getSelectedRow(),4));
					
					objViewAsistentes.setNombre(this.nombre);
					objViewAsistentes.setGrupo(this.grupo);
					objViewAsistentes.setOcupacion(this.ocupacion);
					objViewAsistentes.setProcedencia(this.procedencia);
				}else
				{
					JOptionPane.showMessageDialog(objViewAsistentes, "Selecciona un asistente de la tabla");
				}
			}else
			{
				String resultado = validar();
				
				if(resultado.equals(""))
				{
					if(objModelAsistentes.modificar(this.id, objViewAsistentes.getNombre(), objViewAsistentes.getGrupo(), objViewAsistentes.getOcupacion(), objViewAsistentes.getProcedencia()))
					{
						objViewAsistentes.setModel(objModelAsistentes.consultar());
						limpiar();
						JOptionPane.showMessageDialog(objViewAsistentes, "Asistente modificado correctamente");
					}else
					{
						JOptionPane.showMessageDialog(objViewAsistentes, "No se pudo modificar el asistente");
					}
				}else
				{
					JOptionPane.showMessageDialog(objViewAsistentes, resultado,"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}else if(e.getSource() == objViewAsistentes.getBotonCancelar())
		{
			limpiar();
		}
		
	}
	
	private void limpiar()
	{
		this.id = null;
		this.nombre = null;
		this.grupo = null;
		this.procedencia = null;
		this.ocupacion = null;
		
		objViewAsistentes.setNombre("");
		objViewAsistentes.setOcupacion("");
		objViewAsistentes.setGrupo("");
		objViewAsistentes.setProcedencia("");
	}
	
	private String validar()
	{
		String validacion = "", patronTexto = "[^A-Za-zñáéíóú ]", patronTextoNumeros = "[^A-Za-zñáéíóú0-9]";
		Pattern pTexto = Pattern.compile(patronTexto);
		Pattern pTextoNumero = Pattern.compile(patronTextoNumeros);
		
		Matcher nombre = pTexto.matcher(objViewAsistentes.getNombre());
		Matcher grupo = pTextoNumero.matcher(objViewAsistentes.getGrupo());
		Matcher ocupacion = pTexto.matcher(objViewAsistentes.getOcupacion());
		Matcher procedencia = pTexto.matcher(objViewAsistentes.getProcedencia());
		
		if(objViewAsistentes.getNombre().equals("") || objViewAsistentes.getGrupo().equals("") ||	
				objViewAsistentes.getOcupacion().equals("") || objViewAsistentes.getProcedencia().equals(""))
		{

			validacion = "Completar el formulario";
		}else if(nombre.find())
		{
			validacion = "El nombre solo debe contener letras";
		}else if(grupo.find())
		{
			validacion = "El grupo solo debe llevar número y letras";
		}else if(ocupacion.find())
		{
			validacion = "La ocupacion solo debe llevar letras";
		}else if(procedencia.find())
		{
			validacion = "La procedencia solo debe llevar letras";
		}
		
		return validacion;
	}

}
