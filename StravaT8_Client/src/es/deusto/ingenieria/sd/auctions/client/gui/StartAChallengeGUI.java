package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class StartAChallengeGUI extends JFrame{
	
	private JTable tabla;
	
	public StartAChallengeGUI() {
		
		
		setResizable(false);
		setLocationRelativeTo(null);
		// Establecer el diseño a AbsoluteLayout
        setLayout(new FlowLayout());
        
        //Boton
        JButton botonAceptar = new JButton("Acept Challenge");
        botonAceptar.setBackground(Color.WHITE);
        
        //Creacion del modelo de la tabla
        DefaultTableModel m = new DefaultTableModel();
        m.addColumn("Reserva");
        
        //Tabla y ScrollPane
        tabla = new JTable(m) {
        	private static final long serialVersionUID = 1L;
        	public boolean isCellEditable(int row, int col) {
        		return false;
        	} 
        };
        
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(0, 128, 1187, 304);
        
        //Cargar Challenges en la tabla
        
        //Tamaños de los elemenetos
        scrollPane.setPreferredSize(new Dimension(1187, 304));
        botonAceptar.setPreferredSize(new Dimension(89, 23));
        
        
        //Challenge selection
        ListSelectionModel model = tabla.getSelectionModel();
        tabla.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					int filaSeleccionada = tabla.getSelectedRow();
					prueba = filaSeleccionada;
					
				}
				
			}
		});
        
        
        
        
	}
}
