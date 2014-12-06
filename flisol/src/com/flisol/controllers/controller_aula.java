package com.flisol.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import com.flisol.models.model_aula;
import com.flisol.views.view_aula;

public class controller_aula implements ActionListener{

	private static controller_aula objControllerAula;
	private view_aula objViewAula;
	private model_aula objModelAula;
	private String id, nombre, tipo, num_lugares;
		
	private controller_aula()
	{
		initComponents();
	}
	
	private void initComponents()
	{
		objModelAula = model_aula.getInstance();
		objViewAula = view_aula.getInstance(objModelAula.contultar());
		objViewAula.setVisible(true);
		objViewAula.getBotonAgregar().addActionListener(this);
		objViewAula.getBotonModificar().addActionListener(this);
		objViewAula.getBotonEliminar().addActionListener(this);
		objViewAula.getBotonCancelar().addActionListener(this);
		
		this.id = null;
		this.nombre = null;
		this.num_lugares = null;
		this.tipo = null;
	}
	
	public static controller_aula getInstance()
	{
		if(objControllerAula == null)
			objControllerAula = new controller_aula();
		return objControllerAula;
	}
	
	public JInternalFrame getViewAula()
	{
		return objViewAula;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == objViewAula.getBotonAgregar())
		{
			
			String resultado = validar();
			
			if(resultado.equals(""))
			{
				if(objModelAula.agregar(objViewAula.getNombre(), objViewAula.getLugares(), (String) objViewAula.getTipo()))
				{
					objViewAula.setModel(objModelAula.contultar());
					limpiar();
				}else
				{
					JOptionPane.showMessageDialog(objViewAula, "No se pudo agregar el aula","Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}else
			{
				JOptionPane.showMessageDialog(objViewAula, resultado);
			}
			
		}else if(arg0.getSource() == objViewAula.getBotonEliminar())
		{
			if(objViewAula.getTabla().getSelectedRow() >= 0)
			{
				this.id = String.valueOf(objViewAula.getTabla().getValueAt(objViewAula.getTabla().getSelectedRow(),0));	
				this.nombre = String.valueOf(objViewAula.getTabla().getValueAt(objViewAula.getTabla().getSelectedRow(),1));
				this.num_lugares = String.valueOf(objViewAula.getTabla().getValueAt(objViewAula.getTabla().getSelectedRow(),2));
				this.tipo = String.valueOf(objViewAula.getTabla().getValueAt(objViewAula.getTabla().getSelectedRow(),3));
				
				int respuesta = JOptionPane.showConfirmDialog(objViewAula, "¿Estas seguro que deseas eliminar el aula "+this.nombre+"?");
				
				if(respuesta == 0)
				{
					if(objModelAula.eliminar(this.id))
					{
						objViewAula.setModel(objModelAula.contultar());
						JOptionPane.showMessageDialog(objViewAula, "Aula eliminada correctamente","Operación exitosa",JOptionPane.INFORMATION_MESSAGE);
						limpiar();
					}else
					{

						JOptionPane.showMessageDialog(objViewAula, "No se pudo eliminar el aula","Error",JOptionPane.ERROR);
					}
				}
			}
			
		}else if(arg0.getSource() == objViewAula.getBotonModificar())
		{
			if(objViewAula.getTabla().getSelectedRow()>=0)
			{
				if(this.id == null)
				{
					this.id = String.valueOf(objViewAula.getTabla().getValueAt(objViewAula.getTabla().getSelectedRow(),0));	
					this.nombre = String.valueOf(objViewAula.getTabla().getValueAt(objViewAula.getTabla().getSelectedRow(),1));
					this.num_lugares = String.valueOf(objViewAula.getTabla().getValueAt(objViewAula.getTabla().getSelectedRow(),2));
					this.tipo = String.valueOf(objViewAula.getTabla().getValueAt(objViewAula.getTabla().getSelectedRow(),3));
					
					objViewAula.setNombre(this.nombre);
					objViewAula.setLugares(this.num_lugares);
					objViewAula.setTipo(this.tipo);
				}else
				{
					String resultado = validar();
					
					if(resultado.equals(""))
					{
						if(objModelAula.modificar(this.id, objViewAula.getNombre(), objViewAula.getLugares(), (String)objViewAula.getTipo()))
						{
							objViewAula.setModel(objModelAula.contultar());
							limpiar();
							JOptionPane.showMessageDialog(objViewAula, "Aula modificada correctamente.","Operación exitosa",JOptionPane.INFORMATION_MESSAGE);
						}else
						{
							limpiar();
							JOptionPane.showMessageDialog(objViewAula, "No se pudo modificar el aula, intentalo más tarde.","Error",JOptionPane.ERROR);
						}
					}else
					{
						JOptionPane.showMessageDialog(objViewAula, resultado,"Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}else if(arg0.getSource() == objViewAula.getBotonCancelar())
		{
			limpiar();
			
			this.id = null;
			this.nombre = null;
			this.num_lugares = null;
			this.tipo = null;

		}
	}
	
	private void limpiar()
	{
		objViewAula.setTipoIndex(0);
		objViewAula.setNombre("");
		objViewAula.setLugares("");
	}
	
	private String validar()
	{
		String validacion = "";
		
		String patronTexto = "[A-Z][a-z][0-9]";
	
		Pattern pTexto = Pattern.compile(patronTexto);
		
		Matcher matcherNombre = pTexto.matcher(objViewAula.getNombre());
		
		if(objViewAula.getTipoIndex() == 0 || objViewAula.getNombre().equals("") || objViewAula.getLugares().equals("") || objViewAula.getNombre() == null || objViewAula.getLugares() == null)
		{
			validacion = "Completar formulario";
		}else if(matcherNombre.matches())
		{
			validacion = "El nombre solo debe contener letras";
		}else if(!isNumeric(objViewAula.getLugares()))
		{
			validacion = "Los lugares solo admiten números";
		}
		
		return validacion;
	}
	
	private boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}

}
