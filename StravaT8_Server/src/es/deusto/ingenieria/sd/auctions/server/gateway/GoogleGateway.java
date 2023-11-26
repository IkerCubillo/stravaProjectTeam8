package es.deusto.ingenieria.sd.auctions.server.gateway;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoogleGateway {
	
	private static GoogleGateway instance;
	
	public static GoogleGateway getInstance() {
		if(instance == null) {
			instance = new GoogleGateway();
		}
		return instance;
	}

    public boolean googleUserValidation(String email) {
        try {
//            String url = "http://localhost:8080/facebook/validateUser?email=" + email;
//            String resultado = hacerSolicitud(url);
//            return Boolean.parseBoolean(resultado);
        	return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean googlePasswordValidation(String email, String password) {
        try {
//            String url = "http://localhost:8080/facebook/validatePassword?email=" + email + "&password=" + password;
//            String resultado = hacerSolicitud(url);
//            return Boolean.parseBoolean(resultado);
        	return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String hacerSolicitud(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Configurar la solicitud
        con.setRequestMethod("GET");

        // Obtener la respuesta
        int responseCode = con.getResponseCode();
        System.out.println("Código de respuesta: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}