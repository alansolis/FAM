package com.flisol.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import com.flisol.models.model_expositores;
import com.flisol.views.view_expositores;

public class controller_expositores implements ActionListener{

	private static controller_expositores objControllerExpositores;
	private view_expositores objViewExpositores;
	private model_expositores objModelExpositores;
	private String id , nombre, especialidad, procedencia;
	
	private controller_expositores()
	{
		initComponents();
	}
	
	private void initComponents()
	{
		objModelExpositores = model_expositores.getInstance();		
		objViewExpositores = view_expositores.getInstance(objModelExpositores.consultar());
		objViewExpositores.getBotonAgregar().addActionListener(this);
		objViewExpositores.getBotonEliminar().addActionListener(this);
		objViewExpositores.getBotonCancelar().addActionListener(this);
		objViewExpositores.getBotonModificar().addActionListener(this);
		
		this.id = null;
		this.nombre = null;
		this.especialidad = null;
		this.procedencia = null;
	}
	
	public view_expositores getViewExpositores()
	{
		return objViewExpositores;
	}
	
	public static controller_expositores getInstance()
	{
		if(objControllerExpositores == null)
			objControllerExpositores = new controller_expositores();
		
		return objControllerExpositores;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == objViewExpositores.getBotonAgregar())
		{
			String respuestaValidar = validar();
			
			if(respuestaValidar.equals(""))
			{
				Boolean resultado = objModelExpositores.agregar(objViewExpositores.getNombre(),objViewExpositores.getProcedencia(),objViewExpositores.getEspecialidad());
				if(resultado)
				{
					limpiar();
					objViewExpositores.setModel(objModelExpositores.consultar());
					JOptionPane.showMessageDialog(objViewExpositores, "Expositor agregado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(objViewExpositores, "No se pudó agregar al expositor", "Error", JOptionPane.ERROR_MESSAGE);		
				}
				
			}else
			{
				JOptionPane.showMessageDialog(objViewExpositores, respuestaValidar);
			}
		}else if(arg0.getSource() == objViewExpositores.getBotonEliminar())
		{
			this.id = String.valueOf(objViewExpositores.getTabla().getValueAt(objViewExpositores.getTabla().getSelectedRow(),0));
			this.nombre =  String.valueOf(objViewExpositores.getTabla().getValueAt(objViewExpositores.getTabla().getSelectedRow(),1));
			int resultado = JOptionPane.showConfirmDialog(objViewExpositores, "¿Estas seguro que deseas eliminar a "+this.nombre+"?");
			
			if(resultado == 0)
			{
				if(objModelExpositores.eliminar(this.id))
				{
					objViewExpositores.setModel(objModelExpositores.consultar());
					JOptionPane.showMessageDialog(objViewExpositores, "Expositor eliminado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
				}else
				{
					JOptionPane.showMessageDialog(objViewExpositores, "No se pudo eliminar el expositor", "Error", JOptionPane.ERROR_MESSAGE);	
				}
			}
			
			this.id = null;
			this.nombre = null;
		}else if(arg0.getSource() == objViewExpositores.getBotonModificar())
		{
			
			if(this.id == null)
			{
				
				this.id = String.valueOf(objViewExpositores.getTabla().getValueAt(objViewExpositores.getTabla().getSelectedRow(),0));
				this.nombre =  String.valueOf(objViewExpositores.getTabla().getValueAt(objViewExpositores.getTabla().getSelectedRow(),1));
				this.procedencia = String.valueOf(objViewExpositores.getTabla().getValueAt(objViewExpositores.getTabla().getSelectedRow(),2));
				this.especialidad =  String.valueOf(objViewExpositores.getTabla().getValueAt(objViewExpositores.getTabla().getSelectedRow(),3));
					
				objViewExpositores.setNombre(this.nombre);
				objViewExpositores.setItemProcedencia(this.procedencia);
				objViewExpositores.setEspecialidad(this.especialidad);
			}else
			{
				
				String resultadoValidar = validar();
				if(resultadoValidar.equals(""))
				{
					
					if(objModelExpositores.modificar(this.id,objViewExpositores.getNombre(), objViewExpositores.getProcedencia(), objViewExpositores.getEspecialidad()))
					{
						limpiar();
						objViewExpositores.setModel(objModelExpositores.consultar());
						JOptionPane.showMessageDialog(objViewExpositores, "Expositor modificado correctamente");
						
						this.id = null;
						this.nombre = null;
						this.procedencia = null;
						this.especialidad = null;
					}else
					{
						limpiar();
						objViewExpositores.setModel(objModelExpositores.consultar());
						JOptionPane.showMessageDialog(objViewExpositores, "No se pudo modificar el expositor, intentelo más tarde.", "Error", JOptionPane.ERROR_MESSAGE);	
					}
					
				}else
				{
					JOptionPane.showMessageDialog(objViewExpositores, resultadoValidar);
				}
			}
				
			
			
		}else if(arg0.getSource() == objViewExpositores.getBotonCancelar())
		{
			limpiar();
		}
	}

	private String validar()
	{
		String validado = "";
		String patronTexto = "[^A-Za-zñáéíóú ]";
		
		Pattern pTexto = Pattern.compile(patronTexto);
		
		Matcher matcherNombre = pTexto.matcher(objViewExpositores.getNombre());
		Matcher matcherEspecialidad = pTexto.matcher(objViewExpositores.getEspecialidad());
		
		if(objViewExpositores.getNombre().equals("") || objViewExpositores.getEspecialidad().equals("") || objViewExpositores.getIndexProcedencia() == 0)
		{
			validado = "Completar el formulario.";
		}else if(objViewExpositores.getNombre() == null || objViewExpositores.getEspecialidad() == null)
		{
			validado = "Completar el formulario.";
		}else if(matcherNombre.find())
		{
			validado = "El nombre no es valido";
		}else if(matcherEspecialidad.find())
		{
			validado = "La especialidad solo debe contener texto.";
		}
		
		return validado;
	}
	
	private void limpiar()
	{
		objViewExpositores.setNombre("");
		objViewExpositores.setEspecialidad("");
		objViewExpositores.setProcedencia(0);
		
		this.id = null;
		this.nombre = null;
		this.especialidad = null;
		this.procedencia = null;
	}
}
