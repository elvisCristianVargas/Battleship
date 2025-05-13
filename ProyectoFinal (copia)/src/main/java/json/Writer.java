package json;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Writer {
	
	public Writer() {
		
	}
	
	/**
	 * Create a another object user in the JSON with the correspondent games.
	 * @param usua, username
	 * @param passwd, password
	 */
	
	public static void crearCuentaUsuario(String usua, String passwd) {
		String contenido=null;
		try {
			contenido = new String(Files.readAllBytes(Paths.get("data/user.json")));
//			InputStream is = ClassLoader.getSystemResourceAsStream("data/user.json");
//			contenido = new String(is.readAllBytes(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject(contenido);
		JSONArray usuarios = json.getJSONArray("usuarios");
		
		JSONObject nuevoUsuario = new JSONObject();
	    nuevoUsuario.put("nombre", usua);
	    nuevoUsuario.put("passwd", passwd);
	    
	    JSONArray juegos = new JSONArray();

        JSONObject juego1 = new JSONObject();
        juego1.put("juego", "Hundir la Flota");
        juego1.put("puntuacionMaxima", 0);
        juego1.put("puntuacionMinima", 0);

        JSONObject juego2 = new JSONObject();
        juego2.put("juego", "wordle");
        juego2.put("puntuacionMaxima", 0);
        juego2.put("puntuacionMinima", 0);

        juegos.put(juego1);
        juegos.put(juego2);
        
        nuevoUsuario.put("juegos", juegos);
        
        usuarios.put(nuevoUsuario);
        
        try (FileWriter file = new FileWriter("data/user.json")) {
            file.write(json.toString(4));
            file.flush();
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Overwrite the puntuation if it is more than the maximum putuation or less than the minimum.
	 * @param usua, username.
	 * @param punt, actual putuation of the player in the game.
	 * @param juego, game.
	 */
	
	public static void escribirPuntuacion(String usua, int punt, String juego) {
		String contenido=null;
		try {
//			InputStream is = ClassLoader.getSystemResourceAsStream("data/user.json");
//			contenido = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			contenido = new String(Files.readAllBytes(Paths.get("data/user.json")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject(contenido);
		JSONArray users = json.getJSONArray("usuarios");
		
		for (int i = 0; i < users.length(); i++) {
			JSONObject usuario = users.getJSONObject(i);
			if (usuario.getString("nombre").equals(usua)) {
				JSONArray games = usuario.getJSONArray("juegos");
				
				for (int j = 0; j < games.length(); j++) {
					JSONObject game = games.getJSONObject(j);
					
					if (game.getString("juego").equals(juego) && game.getInt("puntuacionMaxima") < punt) {
						game.put("puntuacionMaxima", punt);
						if (game.getInt("puntuacionMinima") == 0) {
							game.put("puntuacionMinima", punt);
						}
					}else if(game.getString("juego").equals(juego) && game.getInt("puntuacionMinima") > punt) {
						game.put("puntuacionMinima", punt);
					}
				}
			}
		}
        
        try (FileWriter file = new FileWriter("data/user.json")) {
            file.write(json.toString(4));
            file.flush();
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * To overwrite the wins of the correspondent player.
	 * @param usua, username
	 * @param juego, game
	 * @param ganador, winner
	 */
	
	public static void ganador4Raya(String usua, String juego, String ganador) {
		String contenido=null;
		try {
//			InputStream is = ClassLoader.getSystemResourceAsStream("data/user.json");
//			contenido = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			contenido = new String(Files.readAllBytes(Paths.get("data/user.json")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject(contenido);
		JSONArray users = json.getJSONArray("usuarios");
		
		for (int i = 0; i < users.length(); i++) {
			JSONObject usuario = users.getJSONObject(i);
			if (usuario.getString("nombre").equals(usua)) {
				JSONArray games = usuario.getJSONArray("juegos");
				
				for (int j = 0; j < games.length(); j++) {
					JSONObject game = games.getJSONObject(j);
					
					if (game.getString("juego").equals(juego)) {
						game.put(ganador, game.getInt("ganador") + 1);
					}
				}
			}
		}
        
        try (FileWriter file = new FileWriter("data/user.json")) {
            file.write(json.toString(4));
            file.flush();
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
