package client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    public static void main(String args[]) {
        DataOutputStream request = null;
        System.out.println("Запуск клиентской части обработчика документов");
        try (Socket socket = new Socket("89.108.64.70", 3000)) {
            System.out.println("Подключено к серверу обработки документов");
            String requestParameters = "";
            for(int i = 0; i<args.length; i++) {
                requestParameters = requestParameters+args[i]+"/";
            }
            request = new DataOutputStream(socket.getOutputStream());
            request.writeUTF("getreceipt/"+requestParameters);
            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            byte[] bytes = new byte[1048576];
            int real_len = in.read(bytes);
            FileOutputStream fos = new FileOutputStream("receipt.docx");
            fos.write(bytes, 0, real_len);
            System.out.println("Документ получен");
            in.close();
            fos.close();
            request.close();
        } catch (Exception e) {
            System.out.println("Возникла критическая ошибка: " + e);
            e.printStackTrace();
            System.exit(-1);
        }
    }
}