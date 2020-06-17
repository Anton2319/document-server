package server;

import generator.DocumentWorker;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ServerSocket server = null;
        Configreader configreader = new Configreader();
        int port;
        if(configreader.configsExists()) {
            port = Integer.parseInt(configreader.readConfig(Configreader.port));
        }
        else {
            port = 4050;
        }
        DocumentWorker documentWorker = new DocumentWorker();
        try {
            server = new ServerSocket(port);
            System.out.println("Сокет сервера запущен на порту "+port);
        } catch(Exception e) {
            System.out.println("Невозможно запустить сервер: "+e);
            System.exit(-1);
        }
        while(true) {
            try {
                Socket client = server.accept();
                DataInputStream dis = new DataInputStream(client.getInputStream());
                String request = (String) dis.readUTF();
                String requestText = request.split("/")[0];;
                if(requestText.equals("getreceipt")) {
                    DataOutputStream responce = new DataOutputStream(client.getOutputStream());
                    OutputStream fileResponce = client.getOutputStream();
                    String[] requestParams = null;
                    System.out.println("Задание получено с IP-адреса "+client.getLocalAddress());
                    if(request.split("/").length < 4) {
                        System.out.println("Ошибка запроса - нет или недостаточно параметров");
                    }
                    else if(request.split("/").length == 4) {
                        //Получение параметров
                        requestParams = request.split("/");
                        File file = new File(requestParams[1]+".docx");
                        FileOutputStream fos = new FileOutputStream(file);
                        documentWorker.generateReceipt(requestParams[1], requestParams[2], requestParams[3]).write(fos);
                        InputStream in = new FileInputStream(file);
                        //Отправляем файл
                        System.out.println("Отправка данных");
                        int count;
                        byte[] buffer = new byte[1048576];
                        while ((count = in.read(buffer)) > 0)
                        {
                            fileResponce.write(buffer, 0, count);
                        }
                        System.out.println("Задание выполнено");
                        in.close();
                        fos.close();
                        responce.close();
                        fileResponce.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Возникла критическая ошибка: " + e);
                e.printStackTrace();
            }
        }
    }
}