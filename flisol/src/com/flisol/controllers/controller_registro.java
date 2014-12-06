package com.flisol.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import com.flisol.models.model_registro;
import com.flisol.views.view_registro;

public class controller_registro implements  ActionListener{

	private static controller_registro objControllerRegistro;
	private view_registro objViewRegistro;
	private model_registro objModelRegistro;
	private String actividad, asistente;
	
	private controller_registro()
	{
		initComponents();
	}
	
	private void initComponents()
	{
		objModelRegistro = model_registro.getInstance();
		objViewRegistro = view_registro.getInstance(objModelRegistro.getActividades(),objModelRegistro.getAsistentes());
		objViewRegistro.setModel(objModelRegistro.consultar());
		objViewRegistro.getBotonRegistrar().addActionListener(this);
		objViewRegistro.getBotonEliminar().addActionListener(this);
		objViewRegistro.getBotonCancelar().addActionListener(this);
	}
	
	public static controller_registro getInstance()
	{
		if(objControllerRegistro == null)
			objControllerRegistro = new controller_registro();
		
		return objControllerRegistro;
	}
	
	public JInternalFrame getViewRegistro()
	{
		return objViewRegistro;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == objViewRegistro.getBotonCancelar())
		{
			limpiar();
		}else if(e.getSource() == objViewRegistro.getBotonRegistrar())
		{
			String resultado = validar();
			
			if(resultado.equals(""))
			{
				if(objModelRegistro.registrar((String) objViewRegistro.getItemActividad(), (String)objViewRegistro.getItemAsistente()))
				{
					JOptionPane.showMessageDialog(objViewRegistro, "Registro completado correctamente");
					objViewRegistro.setModel(objModelRegistro.consultar());
					limpiar();
				}else
				{
					JOptionPane.showMessageDialog(objViewRegistro, "No se pudo registrar, intentalo más tarde","Error",JOptionPane.ERROR_MESSAGE);
				}
			}else
			{
				JOptionPane.showMessageDialog(objViewRegistro, resultado, "Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}else if(e.getSource() == objViewRegistro.getBotonEliminar())
		{
			if(objViewRegistro.getTabla().getSelectedRow() >= 0)
			{
				this.actividad = (String) objViewRegistro.getTabla().getValueAt(objViewRegistro.getTabla().getSelectedRow(), 0);
				this.asistente = (String) objViewRegistro.getTabla().getValueAt(objViewRegistro.getTabla().getSelectedRow(), 1);
				
				int resultado = JOptionPane.showConfirmDialog(objViewRegistro, "¿Estas seguro que deseas eliminar a "+this.asistente+" de la actividad "+this.actividad+" ?");
			
				if(resultado == 0)
				{
					if(objModelRegistro.eliminar(this.actividad,this.asistente))
					{
						JOptionPane.showMessageDialog(objViewRegistro,"Registro eliminado correctamente");
						limpiar();
						objViewRegistro.setModel(objModelRegistro.consultar());
					}else
					{
						JOptionPane.showMessageDialog(objViewRegistro, "No se pudo eliminar el registro, intentelo más tarde","Error",JOptionPane.ERROR_MESSAGE);
					}
				}else
				{
					limpiar();
				}
			}else
			{
				JOptionPane.showMessageDialog(objViewRegistro, "Selecciona un registro de la tabla","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	private String validar()
	{
		String validacion = "";
		
		if(objViewRegistro.getIndexActividad() == 0 || objViewRegistro.getIndexAsistente() == 0)
		{
			validacion = "Completar el formulario";
		}else if(objModelRegistro.verificarDisponibilidad((String) objViewRegistro.getItemActividad(),(String) objViewRegistro.getItemAsistente()))
		{
			validacion = "Al parecer ya esta registrado el asistente a esta actividad.";
		}else if(objModelRegistro.verificarAula((String) objViewRegistro.getItemActividad()) <= 0)
		{
			validacion = "No hay lugares disponibles";
		}else if(objModelRegistro.verificarAsistente((String) objViewRegistro.getItemActividad(),(String) objViewRegistro.getItemAsistente()))
		{
			validacion = "El asistente esta registrado a otra actividad que se llevará a cabo al mismo tiempo.";
		}
		
		return validacion;
	}
	
	private void limpiar()
	{
		this.actividad = null;
		this.asistente = null;
		objViewRegistro.setModel(objModelRegistro.consultar());
		objViewRegistro.setComboActividad(objModelRegistro.getActividades());
		objViewRegistro.setComboAsistente(objModelRegistro.getAsistentes());
		objViewRegistro.setIndexActividad(0);
		objViewRegistro.setIndexAsistente(0);
	}

}
