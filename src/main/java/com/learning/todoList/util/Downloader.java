package com.learning.todoList.util;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

//@Component
public class Downloader {

    //@PostConstruct
    public static void download() {
        System.out.println("Downloader is working");
        String fileUrl = "https://wetransfer.com/downloads/d014b819b7a35203b8e2eb7994c8c25520250701145705/1ea2e27ef649d425a551f9934e6e828820250701145706/6280e5?t_exp=1751641026&t_lsid=6cb41050-aed1-4402-a4e8-bfb05e4be36c&t_network=email&t_rid=ZW1haWx8Njc0NDdkOTdiNjM1NTFjNmY2OGVhNjQ4&t_s=download_link&t_ts=1751381826&utm_campaign=TRN_TDL_01&utm_source=sendgrid&utm_medium=email&trk=TRN_TDL_01";
        String savePath = "D:\\downloaded_file.zip"; // Укажи имя файла с правильным расширением

        try {
            downloadFile(fileUrl, savePath);
            System.out.println("Файл успешно скачан!");
        } catch (IOException e) {
            System.err.println("Ошибка при скачивании файла: " + e.getMessage());
        }
    }

    private static void downloadFile(String fileUrl, String savePath) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setInstanceFollowRedirects(true); // Разрешаем редиректы

        int responseCode = httpConn.getResponseCode();

        // Проверяем, что ответ успешный (HTTP 200 OK)
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");

            if (disposition != null) {
                // Извлекаем имя файла из заголовка Content-Disposition
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10, disposition.length() - 1);
                }
            } else {
                // Если заголовка нет, извлекаем имя файла из URL
                fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            }

            System.out.println("Скачиваем файл: " + fileName);

            try (BufferedInputStream in = new BufferedInputStream(httpConn.getInputStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {

                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            }
        } else {
            throw new IOException("Сервер вернул код ответа: " + responseCode);
        }

        httpConn.disconnect();
    }
}

