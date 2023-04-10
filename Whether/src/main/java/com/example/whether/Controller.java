package com.example.whether;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import org.json.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
public class Controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text pressure;

    @FXML
    private Text temp_feels;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_max;

    @FXML
    private Text tenp_min;


    @FXML
    void initialize() {
        getData.setOnAction(actionEvent -> {
            String getUserCity = city.getText().trim();
            if (!getUserCity.equals("")) {
                String ouptup = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=cbd9863021981b58cca7f35eea38aab1&units=metric");
                System.out.println(ouptup);
                if (!ouptup.isEmpty()) {
                    JSONObject obj = new JSONObject(ouptup);
                    temp_info.setText("Tемпература :" + obj.getJSONObject("main").getDouble("temp"));
                    temp_feels.setText("Ощущается :" + obj.getJSONObject("main").getDouble("feels_like"));
                    temp_max.setText("Максимум :" + obj.getJSONObject("main").getDouble("temp_max"));
                    tenp_min.setText("Минимум :" + obj.getJSONObject("main").getDouble("temp_min"));
                    pressure.setText("Давление :" + obj.getJSONObject("main").getDouble("pressure"));
                }
            }
        });
    }
    private static String getUrlContent(String UrlAddress) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(UrlAddress);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null)
                content.append(line).append("\n");
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("City is unfounded!");
        }
        return content.toString();
    }
}