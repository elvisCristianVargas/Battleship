package ventanas;

import java.awt.Color;
import java.util.Base64;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import json.Reader;
import json.Writer;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class VentanaCCuenta extends JFrame {

	private JPanel contentPane;
	private JTextField usuario;
	private JButton btnAtrs;
	private JButton btnSubmit;
	private JPasswordField password;
	private JLabel incorrect;
	private String contrasena = "*[A-Z]*[a-z]*[0-9]";
	private String user;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaCCuenta frame = new VentanaCCuenta();
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
	public VentanaCCuenta() {
		crearGUI();
		eventos();
		setResizable(false);
	}
	public VentanaCCuenta(String user) {
		crearGUI();
		eventos();
		setResizable(false);
		this.user = user;
	}
	
	/**
	 * Create the event to go back or to Sign Up
	 */
	
	public void eventos() {
		btnAtrs.addActionListener((e)->{
			SignInSignUp s = new SignInSignUp();
			s.setVisible(true);
			this.setVisible(false);
		});
		btnSubmit.addActionListener((e)->{
			if (this.user == null) {
				if (Reader.lectorUsuarioCrearCuenta(usuario.getText())) {
					incorrect.setForeground(Color.RED);
					incorrect.setText("El nombre de usuario ya existe");
				}else {
					if(usuario.getText().isBlank() || password.getText().isBlank()) {
						incorrect.setForeground(Color.RED);
						incorrect.setText("Tienes que introducir un usuario y contraseña");
					}else {
						String cifrado = Base64.getEncoder().encodeToString(password.getText().getBytes());
						Writer.crearCuentaUsuario(usuario.getText(), cifrado);
						Friv f = new Friv(usuario.getText());
						this.setVisible(false);
						f.setVisible(true);
					}
				}
			}else {
				if (Reader.lectorUsuarioCrearCuenta(usuario.getText())) {
					incorrect.setForeground(Color.RED);
					incorrect.setText("El nombre de usuario ya existe");
				}else {
					if(usuario.getText().isBlank() || password.getText().isBlank()) {
						incorrect.setForeground(Color.RED);
						incorrect.setText("Tienes que introducir un usuario y contraseña");
					}else {
						String cifrado = Base64.getEncoder().encodeToString(password.getText().getBytes());
						Writer.crearCuentaUsuario(usuario.getText(), cifrado);
						PanelTablero pt = new PanelTablero(7, 7, null, this.user, usuario.getText());
						this.setVisible(false);
						pt.setVisible(true);
					}
				}
			}
		});
	}
	
	/**
	 * Creates the graphic interface.
	 */
	
	public void crearGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNuevoUsuario = new JLabel("Nuevo Usuario");
		lblNuevoUsuario.setBounds(166, 12, 126, 15);
		contentPane.add(lblNuevoUsuario);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(32, 74, 126, 15);
		contentPane.add(lblNombre);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setBounds(32, 151, 126, 15);
		contentPane.add(lblContrasea);
		
		usuario = new JTextField();
		usuario.setBounds(259, 72, 114, 19);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(239, 219, 117, 25);
		contentPane.add(btnSubmit);
		
		btnAtrs = new JButton("Atrás");
		btnAtrs.setBounds(78, 219, 117, 25);
		contentPane.add(btnAtrs);
		
		password = new JPasswordField();
		password.setBounds(259, 149, 114, 19);
		contentPane.add(password);
		
		incorrect = new JLabel("");
		incorrect.setBounds(118, 192, 220, 15);
		contentPane.add(incorrect);
	}

}
