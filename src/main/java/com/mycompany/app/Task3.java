package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Task3 {

    public static void run(WebDriver webDriver) {
        System.out.println("Задание №3");
        try {
            String url = "https://api.open-meteo.com/v1/forecast"
                    + "?latitude=56&longitude=44"
                    + "&hourly=temperature_2m,rain"
                    + "&current=cloud_cover"
                    + "&timezone=Europe%2FMoscow"
                    + "&forecast_days=1"
                    + "&wind_speed_unit=ms";

            webDriver.get(url);
            WebElement elem = webDriver.findElement(By.tagName("pre"));
            String jsonStr = elem.getText();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);
            JSONObject hourly = (JSONObject) obj.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temps = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            String header = String.format("%-4s | %-18s | %-12s | %-12s",
                    "№", "Дата/время", "Температура", "Осадки (мм)");
            String separator = "-----+--------------------+--------------+-------------";

            StringBuilder table = new StringBuilder();
            table.append("Прогноз погоды для Нижнего Новгорода (56, 44)\n");
            table.append(header).append("\n");
            table.append(separator).append("\n");

            for (int i = 0; i < times.size(); i++) {
                String row = String.format("%-4d | %-18s | %-12s | %-12s",
                        i + 1,
                        times.get(i).toString(),
                        temps.get(i).toString(),
                        rains.get(i).toString());
                table.append(row).append("\n");
            }

            System.out.println(table.toString());

            try (PrintWriter writer = new PrintWriter(new FileWriter("result/forecast.txt"))) {
                writer.print(table.toString());
            }
            System.out.println("Таблица сохранена в result/forecast.txt");
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
    }
}
