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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class view_actividad extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private static view_actividad objViewActividad;
	private JPanel objPanel;
	private JScrollPane objScrollPane;
	private JTextField objTextFieldTitulo,objTextFieldFecha,objTextFieldHora, objTextFieldDuracion;
	private JComboBox<String> objComboBoxAula,objComboBoxExpositor, objComboBoxTipo;
	private JLabel objLabelTitulo, objLabelFecha, objLabelHora, objLabelDuracion, objLabelAula,objLabelTipo, objLabelExpositor;
	private JTable objTabla;
	private DefaultTableModel objModelo;
	private Vector<String> headers;
	private JButton objBotonAgregar, objBotonModificar, objBotonEliminar, objBotonCancelar;
	
	
	private view_actividad(JComboBox<String> combo, JComboBox<String> comboAula, Vector<Vector<String>> datos)
	{
		super("Actividad",true,true,false,true);
		initComponents(combo, comboAula, datos);
	}
	
	private void initComponents(JComboBox<String> combo, JComboBox<String> comboAula, Vector<Vector<String>> datos)
	{
		this.setSize(600,700);
		this.resizable = false;
		this.setLayout(new FlowLayout());
		
		objPanel = new JPanel();
		objPanel.setLayout(null);
		objPanel.setPreferredSize(new Dimension(600,400));
		
		objLabelTitulo = new JLabel("Titulo");
		objLabelTitulo.setBounds(10, 10, 400, 25);
		
		objTextFieldTitulo = new JTextField();
		objTextFieldTitulo.setBounds(10, 35, 400, 25);
		
		objLabelExpositor = new JLabel("Expositores");
		objLabelExpositor.setBounds(10, 60, 400, 25);
		
		objComboBoxExpositor = combo;
		objComboBoxExpositor.setBounds(10, 85, 400, 25);
		
		objLabelAula = new JLabel("Aula");
		objLabelAula.setBounds(10, 110, 400, 25);
		
		objComboBoxAula = comboAula;
		objComboBoxAula.setBounds(10,135,400,25);
		
		objLabelTipo = new JLabel("Tipo");
		objLabelTipo.setBounds(10, 160, 400, 25);
		
		objComboBoxTipo = new JComboBox<String>();
		objComboBoxTipo.addItem("Selecciona una opción . . .");
		objComboBoxTipo.addItem("taller");
		objComboBoxTipo.addItem("conferencia");
		objComboBoxTipo.setBounds(10,185,400,25);
		
		objLabelFecha = new JLabel("Fecha");
		objLabelFecha.setBounds(10, 220, 400, 25);
		
		objTextFieldFecha = new JTextField();
		objTextFieldFecha.setBounds(10,245,400,25);
		
		objLabelHora = new JLabel("Hora");
		objLabelHora.setBounds(10, 270, 400, 25);
		
		objTextFieldHora = new JTextField();
		objTextFieldHora.setBounds(10,300,400,25);
		
		objLabelDuracion = new JLabel("Duración");
		objLabelDuracion.setBounds(10, 325, 400, 25);
		
		objTextFieldDuracion = new JTextField();
		objTextFieldDuracion.setBounds(10,350,400,25);
		
		objBotonAgregar = new JButton("Agregar");
		objBotonAgregar.setBounds(420, 10, 170, 75);
		
		objBotonModificar = new JButton("Modificar");
		objBotonModificar.setBounds(420, 110, 170, 75);
		
		objBotonEliminar = new JButton("Eliminar");
		objBotonEliminar.setBounds(420, 210, 170, 75);
		
		objBotonCancelar = new JButton("Cancelar/Consultar");
		objBotonCancelar.setBounds(420, 310, 170, 75);
		
		objPanel.add(objLabelTitulo);
		objPanel.add(objTextFieldTitulo);
		objPanel.add(objLabelExpositor);
		objPanel.add(objComboBoxExpositor);
		objPanel.add(objLabelAula);
		objPanel.add(objComboBoxAula);
		objPanel.add(objLabelTipo);
		objPanel.add(objComboBoxTipo);
		objPanel.add(objLabelFecha);
		objPanel.add(objTextFieldFecha);
		objPanel.add(objLabelHora);
		objPanel.add(objTextFieldHora);
		objPanel.add(objLabelDuracion);
		objPanel.add(objTextFieldDuracion);
		objPanel.add(objBotonAgregar);
		objPanel.add(objBotonModificar);
		objPanel.add(objBotonEliminar);
		objPanel.add(objBotonCancelar);
		this.add(objPanel);
		
		headers = new Vector<String>();
		headers.add("ID");
		headers.add("Titulo");
		headers.add("Expositor");
		headers.add("Aula");
		headers.add("Fecha");
		headers.add("Hora");
		headers.add("Duracion");
		headers.add("Tipo");
		headers.add("Lugares");
		
		this.objModelo = new DefaultTableModel(datos,headers){	
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
		
		objTabla = new JTable(objModelo);
		
		objScrollPane = new JScrollPane(objTabla);
		objScrollPane.setPreferredSize(new Dimension(580,250));
		
		this.add(objScrollPane);
	}
	
	public void setModel(Vector<Vector<String>> datos)
	{
		this.objModelo = new DefaultTableModel(datos, this.headers){	
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
		
		this.objTabla.setModel(this.objModelo);
		
	}
	
	public JButton getBotonAgregar()
	{
		return objBotonAgregar;
	}
	
	public void setComboExpositor(JComboBox<String> combo)
	{
		int total = combo.getItemCount();
		objComboBoxExpositor.removeAllItems();
		int i = 0;
		while(i<total)
		{
			objComboBoxExpositor.addItem(combo.getItemAt(i));
			i++;
		}
	}
	public void setComboAula(JComboBox<String> combo)
	{
		int total = combo.getItemCount();
		objComboBoxAula.removeAllItems();
		int i = 0;
		while(i<total)
		{
			objComboBoxAula.addItem(combo.getItemAt(i));
			i++;
		}
	}
	
	public JButton getBotonModificar()
	{
		return objBotonModificar;
	}
	
	public JButton getBotonEliminar()
	{
		return objBotonEliminar;
	}
	
	public JButton getBotonCancelar()
	{
		return objBotonCancelar;
	}
	
	public void setTitulo(String titulo)
	{
		this.objTextFieldTitulo.setText(titulo);
	}
	
	public void setFecha(String fecha)
	{
		this.objTextFieldFecha.setText(fecha);
	}
	
	public void setHora(String Hora)
	{
		this.objTextFieldHora.setText(Hora);
	}
	
	public void setDuracion(String duracion)
	{
		this.objTextFieldDuracion.setText(duracion);
	}
	
	public String getTitulo()
	{
		return this.objTextFieldTitulo.getText();
	}
	
	public String getFecha()
	{
		return this.objTextFieldFecha.getText();
	}
	
	public String getHora()
	{
		return this.objTextFieldHora.getText();
	}
	
	public JTable getTabla()
	{
		return this.objTabla;
	}
	
	public String getDuracion()
	{
		return this.objTextFieldDuracion.getText();
	}
	
	public void setItemExpositor(String expositor)
	{
		this.objComboBoxExpositor.setSelectedItem(expositor);
	}
	
	public void setIndexExpositor(int expositor)
	{
		this.objComboBoxExpositor.setSelectedIndex(expositor);
	}
	
	public void setItemAula(String aula)
	{
		this.objComboBoxAula.setSelectedItem(aula);
	}
	
	public void setIndexAula(int aula)
	{
		this.objComboBoxAula.setSelectedIndex(aula);
	}
	
	public void setItemTipo(String tipo)
	{
		this.objComboBoxTipo.setSelectedItem(tipo);
	}
	
	public void setIndexTipo(int tipo)
	{
		this.objComboBoxTipo.setSelectedIndex(tipo);
	}
	
	public Object getItemExpositor()
	{
		return this.objComboBoxExpositor.getSelectedItem();
	}
	
	public int getIndexExpositor()
	{
		return this.objComboBoxExpositor.getSelectedIndex();
	}
	
	public Object getItemAula()
	{
		return this.objComboBoxAula.getSelectedItem();
	}
	
	public int getIndexAula()
	{
		return this.objComboBoxAula.getSelectedIndex();
	}
	
	public Object getItemTipo()
	{
		return this.objComboBoxTipo.getSelectedItem();
	}
	
	public int getIndexTipo()
	{
		return this.objComboBoxTipo.getSelectedIndex();
	}

	public static view_actividad getInstance(JComboBox<String> combo,JComboBox<String> comboAula, Vector<Vector<String>> datos)
	{
		if(objViewActividad == null)
			objViewActividad = new view_actividad(combo,comboAula, datos);
		
		return objViewActividad;
	}
}
