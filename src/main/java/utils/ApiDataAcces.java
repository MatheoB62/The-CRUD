package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiDataAcces {
    public static String requestObject(URL url, String methode) throws Exception {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(methode);

        int status = con.getResponseCode();

        if (status == HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


             return response.toString();
        }
        throw new Exception("La requête a échoué avec le code de réponse : " + status + " " + con.getResponseMessage());
    }
}
