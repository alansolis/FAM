package com.flisol.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class view_registro extends JInternalFrame{

	
	private static final long serialVersionUID = 1L;
	private static view_registro objViewRegistro;
	private JPanel objPanel;
	private JScrollPane objScrollPanel;
	private JLabel objLabelAsistente, objLabelActividad;
	private JComboBox<String> objComboBoxAsistente, objComboBoxActividad;
	private JTable objTabla;
	private DefaultTableModel objModelo;
	private JButton objBotonRegistrar,objBotonEliminar, objBotonCancelar;
	private Vector<String> headers;
	
	private view_registro(JComboBox<String> comboActividades, JComboBox<String> comboAsistentes)
	{
		super("Registro",true,true,false,true);
		initComponents(comboActividades,comboAsistentes);
	}
	
	private void initComponents(JComboBox<String> comboActividades, JComboBox<String> comboAsistentes)
	{
		this.setSize(500, 600);
		this.setLayout(new FlowLayout());
		
		objPanel = new JPanel();
		objPanel.setLayout(null);
		objPanel.setPreferredSize(new Dimension(500,300));
		
		headers = new Vector<String>();
		headers.add("Actividad");
		headers.add("Asistente");
		
		objModelo = new DefaultTableModel(new Vector<Vector<String>>(),headers)
		{	
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
		
		objTabla = new JTable(objModelo);
		
		objScrollPanel = new JScrollPane(objTabla);
		objScrollPanel.setPreferredSize(new Dimension(480,250));
		
		objLabelActividad = new JLabel("Actividad");
		objLabelActividad.setBounds(10, 10, 300, 25);
		
		objComboBoxActividad = comboActividades;
		objComboBoxActividad.setBounds(10, 50, 300, 25);
		
		objLabelAsistente = new JLabel("Asistente");
		objLabelAsistente.setBounds(10, 110, 300, 25);
		
		objComboBoxAsistente = comboAsistentes;
		objComboBoxAsistente.setBounds(10, 150, 300, 25);
		
		objBotonRegistrar = new JButton("Registrar");
		objBotonRegistrar.setBounds(330, 10, 150, 70);
		
		objBotonEliminar = new JButton("Eliminar");
		objBotonEliminar.setBounds(330, 110, 150, 70);
		
		objBotonCancelar = new JButton("Cancelar/Consultar");
		objBotonCancelar.setBounds(330, 210, 150, 70);
		
		objPanel.add(objLabelActividad);
		objPanel.add(objComboBoxActividad);
		objPanel.add(objLabelAsistente);
		objPanel.add(objComboBoxAsistente);
		objPanel.add(objBotonRegistrar);
		objPanel.add(objBotonEliminar);
		objPanel.add(objBotonCancelar);
		
		this.add(objPanel);
		this.add(objScrollPanel);
	}
	
	public static view_registro getInstance(JComboBox<String> comboActividades, JComboBox<String> comboAsistentes)
	{
		if(objViewRegistro == null)
			objViewRegistro = new view_registro(comboActividades, comboAsistentes);
		
		return objViewRegistro;
	}
	
	public JButton getBotonRegistrar()
	{
		return this.objBotonRegistrar;
	}
	
	public JButton getBotonCancelar()
	{
		return this.objBotonCancelar;
	}
	
	public JButton getBotonEliminar()
	{
		return this.objBotonEliminar;
	}
	
	public JTable getTabla()
	{
		return this.objTabla;
	}
	
	public void setItemActividad(String actividad)
	{
		this.objComboBoxActividad.setSelectedItem(actividad);
	}
	
	public void setIndexActividad(int actividad)
	{
		this.objComboBoxActividad.setSelectedIndex(actividad);
	}
	
	public void setItemAsistente(String asistente)
	{
		this.objComboBoxAsistente.setSelectedItem(asistente);
	}
	
	public void setIndexAsistente(int asistente)
	{
		this.objComboBoxAsistente.setSelectedIndex(asistente);
	}
	
	public Object getItemActividad()
	{
		return this.objComboBoxActividad.getSelectedItem();
	}
	
	public Object getItemAsistente()
	{
		return this.objComboBoxAsistente.getSelectedItem();
	}
	
	public int getIndexActividad()
	{
		return this.objComboBoxActividad.getSelectedIndex();
	}
	
	public int getIndexAsistente()
	{
		return this.objComboBoxAsistente.getSelectedIndex();
	}
	
	public void setComboActividad(JComboBox<String> combo)
	{
		int total = combo.getItemCount();
		objComboBoxActividad.removeAllItems();
		int i = 0;
		while(i<total)
		{
			objComboBoxActividad.addItem(combo.getItemAt(i));
			i++;
		}
	}
	
	public void setComboAsistente(JComboBox<String> combo)
	{
		int total = combo.getItemCount();
		objComboBoxAsistente.removeAllItems();
		int i = 0;
		while(i<total)
		{
			objComboBoxAsistente.addItem(combo.getItemAt(i));
			i++;
		}
	}
	
	public void setModel(Vector<Vector<String>> datos)
	{
		objModelo = new DefaultTableModel(datos,headers)
		{	
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
		
		objTabla.setModel(objModelo);
	}
	
}
