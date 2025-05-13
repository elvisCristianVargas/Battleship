package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignInSignUp extends JFrame {

	private JPanel contentPane;
	private JButton btnCCuenta;
	private JButton btnInicio;
	private String usuarioRegistrado;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SignInSignUp frame = new SignInSignUp();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public SignInSignUp() {
		setTitle("Acceso");
		crearGUI();
		eventos();
		this.setVisible(true);
		setResizable(false);
	}
	
	public SignInSignUp(String usuario) {
		setTitle("Acceso");
		crearGUI();
		eventos();
		this.setVisible(true);
		setResizable(false);
		this.usuarioRegistrado = usuario;
	}
	
	/**
	 * Create the events for the buttons to register or sign in.
	 */
	
	public void eventos() {
		btnInicio.addActionListener((e)->{
			if (usuarioRegistrado == null) {
				VentanaInicioSesion v = new VentanaInicioSesion();
				v.setVisible(true);
				this.setVisible(false);
			}else {
				VentanaInicioSesion v = new VentanaInicioSesion(this.usuarioRegistrado);
				v.setVisible(true);
				this.setVisible(false);
			}
		});
		btnCCuenta.addActionListener((e)->{
			VentanaCCuenta vC = new VentanaCCuenta();
			vC.setVisible(true);
			this.setVisible(false);
		});
	}
	
	/**
	 * Creates the graphic interface.
	 */
	
	public void crearGUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 200);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnInicio = new JButton("Inicio de Sesión");
		btnInicio.setBounds(12, 32, 166, 25);
		contentPane.add(btnInicio);
		
		btnCCuenta = new JButton("Crear Cuenta");
		btnCCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCCuenta.setBounds(12, 103, 166, 25);
		contentPane.add(btnCCuenta);
	}

}
