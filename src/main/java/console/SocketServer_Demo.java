package console;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer_Demo {

    private static int port = 9876;
    private static String serverIP;
    private static Scanner scanner;

    public static void main(String args[]) throws Exception {
        scanner = new Scanner(System.in);
        serverIP = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Server IP : " + serverIP);

        System.out.println("等待 Client 連入");
        try (   // 1.建立 ServerSocket
                ServerSocket server = new ServerSocket(port);
                // 2.建立 Socket 連線
                Socket socket = server.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());) {

            System.out.println("Client 已連入");
            while (true) {
                // 3.讀取 Client 資訊
                String message = (String) ois.readObject();
                if (message.equalsIgnoreCase("exit")) {
                    break;
                } else {
                    System.out.println("Client 說：" + message);
                }

                // 4.將資料傳給 Client
                System.out.println("請輸入訊息");
                oos.writeObject(scanner.next());

            }
            System.out.println("關閉 Socket Server!!");

        } catch (Exception e) {
        }

    }

}
