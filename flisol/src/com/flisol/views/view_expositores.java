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

public class view_expositores extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private static view_expositores objViewExpositores;
	private JPanel objPanel;
	private JLabel objLabelNombre, objLabelProcedencia, objLabelEspecialidad;
	private JButton objButtonAgregar, objButtonModificar, objButtonEliminar, objButtonCancelar;
	private JTextField objTextFieldNombre, objTextFieldEspecialidad;
	private JComboBox<String> objComboBoxProcedencia;
	private JTable objTable;
	private Vector<String> cabezera;
	private JScrollPane objScrollPane;
	private DefaultTableModel objModelo;
	
	private view_expositores(Vector<Vector<String>> datos)
	{
		super("Expositores",true,true,false,true);
		initComponents(datos);
	}
	
	private void initComponents(Vector<Vector<String>> datos)
	{
		
		this.setLayout(new FlowLayout());
		this.setSize(600, 600);
		this.resizable = false;
		objPanel = new JPanel();
		objPanel.setPreferredSize(new Dimension(600,300));
		
		objLabelNombre = new JLabel("Nombre");
		objLabelNombre.setBounds(10, 10, 100, 15);
		objLabelProcedencia = new JLabel("Procedencia");
		objLabelProcedencia.setBounds(10, 110, 100, 15);
		objLabelEspecialidad = new JLabel("Especialidad");
		objLabelEspecialidad.setBounds(10, 210, 100, 15);
		
		objButtonAgregar = new JButton("Agregar");
		objButtonAgregar.setBounds(425, 10, 150, 50);
		objButtonModificar = new JButton("Modificar");
		objButtonModificar.setBounds(425, 80, 150, 50);
		objButtonEliminar = new JButton("Eliminar");
		objButtonEliminar.setBounds(425, 150, 150, 50);
		objButtonCancelar = new JButton("Cancelar");
		objButtonCancelar.setBounds(425, 220, 150, 50);
		
		objComboBoxProcedencia = new JComboBox<String>();
		objComboBoxProcedencia.addItem("Selecciona una opción...");
		objComboBoxProcedencia.addItem("interno");
		objComboBoxProcedencia.addItem("externo");
		objComboBoxProcedencia.setBounds(10, 160, 400, 25);
		
		objTextFieldEspecialidad = new JTextField();
		objTextFieldEspecialidad.setBounds(10, 260, 400, 25);
		objTextFieldNombre = new JTextField();
		objTextFieldNombre.setBounds(10, 60, 400, 25);
		
		cabezera = new Vector<String>();
		cabezera.add("ID");
		cabezera.add("Nombre");
		cabezera.add("Procedencia");
		cabezera.add("Especialidad");
		
		objModelo = new DefaultTableModel(datos,cabezera)
		{	
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
	    
		objTable = new JTable(objModelo);
		
		objTable.setBounds(10,10,520,200);
		objScrollPane  = new JScrollPane(objTable);
		objScrollPane.setPreferredSize(new Dimension(580, 250));
				
		objPanel.setLayout(null);
		objPanel.add(objLabelNombre);
		objPanel.add(objTextFieldNombre);
		objPanel.add(objButtonAgregar);
		
		objPanel.add(objLabelProcedencia);
		objPanel.add(objComboBoxProcedencia);
		objPanel.add(objButtonModificar);
		
		objPanel.add(objLabelEspecialidad);
		objPanel.add(objTextFieldEspecialidad);
		objPanel.add(objButtonEliminar);
		
		objPanel.add(objButtonCancelar);
		
		this.add(objPanel);
		this.add(objScrollPane);
	}
	
	public JButton getBotonAgregar()
	{
		return this.objButtonAgregar;
	}
	
	
	public String getNombre()
	{
		return this.objTextFieldNombre.getText();
	}
	
	public String getEspecialidad()
	{
		return this.objTextFieldEspecialidad.getText();
	}
	
	public String getProcedencia()
	{
		return (String) this.objComboBoxProcedencia.getSelectedItem();
	}
	
	public int getIndexProcedencia()
	{
		return this.objComboBoxProcedencia.getSelectedIndex();
	}
	
	public void setNombre(String nombre)
	{
		this.objTextFieldNombre.setText(nombre);
	}
	
	public void setEspecialidad(String especialidad)
	{
		this.objTextFieldEspecialidad.setText(especialidad);
	}
	
	public void setProcedencia(int index)
	{
		this.objComboBoxProcedencia.setSelectedIndex(index);
	}
	
	public void setItemProcedencia(String procedencia)
	{
		this.objComboBoxProcedencia.setSelectedItem(procedencia);
	}
	
	public void setModel(Vector<Vector<String>> datos)
	{
		DefaultTableModel newModel = new DefaultTableModel(datos, cabezera)
		{	
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
	        //all cells false
	        return false;
			}
		};
		
		this.objTable.setModel(newModel);
	}
	
	public JButton getBotonEliminar()
	{
		return this.objButtonEliminar;
	}
	
	public JButton getBotonCancelar()
	{
		return this.objButtonCancelar;
	}
	
	public JButton getBotonModificar()
	{
		return this.objButtonModificar;
	}
	
	public JTable getTabla()
	{
		return this.objTable;
	}
	
	public static synchronized view_expositores getInstance(Vector<Vector<String>> datos)
	{
		
		if(objViewExpositores == null)
			objViewExpositores = new view_expositores(datos);
		
		return objViewExpositores;
	}
}
