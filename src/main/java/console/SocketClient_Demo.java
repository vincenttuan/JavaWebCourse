package console;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient_Demo {

    private static Scanner scanner;

    public static void main(String[] args) throws Exception {
        scanner = new Scanner(System.in);
        System.out.println("請輸入Server IP");
        String serverIP = scanner.next();

        try (
                // 1.建立Socket連線
                Socket socket = new Socket(serverIP, 9876);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {

            while (true) {

                // 2.將資料傳給 Server
                System.out.println("請輸入訊息：");
                String message = scanner.next();
                oos.writeObject(message);
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                // 3.讀取 Server 傳來的資料
                String replyMessage = (String) ois.readObject();
                System.out.println("Server 說：" + replyMessage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
