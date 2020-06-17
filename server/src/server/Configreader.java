package server;

import java.awt.*;
import java.io.*;

public class Configreader {
    public static int port = 0;
    String defaultConfig[] = {"port=4050"};
    File confFile = new File("config.conf");
    public boolean configsExists() {
        if(confFile.exists()) {
            return true;
        }
        else {
            try {
                confFile.createNewFile();
                try(FileWriter writer = new FileWriter(confFile, false))
                {
                    for(int i = 0; i<defaultConfig.length; i++) {
                        writer.write(defaultConfig[i]);
                    }
                    writer.flush();
                }
                return false;
            } catch (IOException e) {
                System.out.println("Не удалось создать конфигурационный файл");
                System.out.println("Возможно у программы нет прав на запись в файл config.conf?");
                e.printStackTrace();
                return false;
            }
        }
    }
    public String readConfig(int parameter) {
        FileReader fr = null;
        try {
            fr = new FileReader(confFile);
            BufferedReader reader = new BufferedReader(fr);
            String line = null;
            for(int i = 0; i<=parameter; i++) {
                line = reader.readLine();
            }
            return line.split("=")[1];
        } catch (Exception e) {
            System.out.println("Не удалось прочитать конфигурационный файл, возвращены стандартные настройки");
            System.out.println("Возможно у программы нет прав на чтение из файла config.conf?");
            e.printStackTrace();
            return defaultConfig[parameter];
        }
    }
}
