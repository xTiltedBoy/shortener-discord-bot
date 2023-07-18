package com.miguelrz.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpPetition {

    final static Logger LOGGER = LoggerFactory.getLogger(HttpPetition.class);

    public static String getPetition(String code) {

        try {

            URL urlToVisit = new URL("https://api.miguelrz.com/q/shortenUrl/" + code);
            HttpURLConnection connection = (HttpURLConnection) urlToVisit.openConnection();
            connection.setRequestMethod("GET");

            InputStream strmContent = connection.getInputStream();
            byte[] arrStream = strmContent.readAllBytes();
            StringBuilder dataJSON = new StringBuilder();
            dataJSON.append("[");

            for (byte aux : arrStream) {
                dataJSON.append((char) aux);
            }
            dataJSON.append("]");

            JSONArray jsonArray = new JSONArray(dataJSON.toString());
            JSONObject json = jsonArray.getJSONObject(0);

            return json.getString("sourceUrl");

        } catch (Exception e) {
            LOGGER.error("Ha habido un problema al realizar la peticion");
            return "";
        }

    }

    public static String postPetition(String clientUrl) {

        try {

            String url = "https://api.miguelrz.com/standard-entry/";

            URL urlToVisit = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlToVisit.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Crear el objeto JSON que deseas enviar
            String jsonPayload = "{\"sourceUrl\": \""+ clientUrl + "\"}";

            // Adjuntar el JSON a la solicitud
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(jsonPayload);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            response.append("[");

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            response.append("]");

            JSONArray jsonArray = new JSONArray(response.toString());
            JSONObject json = jsonArray.getJSONObject(0);

            return json.getString("shortenUrl");

        } catch (Exception e) {
            LOGGER.error("Ha habido un problema al realizar la peticion");
            return "";
        }

    }

    public static String postPetition(String clientUrl, String customUrl) {

        try {

            String url = "https://api.miguelrz.com/custom-entry/";

            URL urlToVisit = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlToVisit.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Crear el objeto JSON que deseas enviar
            String jsonPayload = "{\"shortenUrl\": \"" + customUrl + "\", \"sourceUrl\": \"" + clientUrl + "\"}";

            // Adjuntar el JSON a la solicitud
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(jsonPayload);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            response.append("[");

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            response.append("]");

            JSONArray jsonArray = new JSONArray(response.toString());
            JSONObject json = jsonArray.getJSONObject(0);

            return json.getString("shortenUrl");

        } catch (Exception e) {
            LOGGER.error("Ha habido un problema al realizar la peticion");
            return "";
        }

    }

}
