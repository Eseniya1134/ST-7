package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App
{
    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.home") + "/Downloads/chromedriver-mac-arm64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        WebDriver webDriver = new ChromeDriver(options);

        try {
            webDriver.get("https://www.calculator.net/password-generator.html");
            WebElement passwordField = webDriver.findElement(By.id("cpar1"));
            String password = passwordField.getAttribute("value");
            System.out.println("Задание №1");
            System.out.println("Сгенерированный пароль: " + password);
            System.out.println();

            Task2.run(webDriver);
            Task3.run(webDriver);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }
}