package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final String STOP = "Bye";
    private static final int PORT = 9000;

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (!server.isClosed()) {
                    Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        String word = str.substring(str.indexOf("=") + 1, str.indexOf(" ", str.indexOf("=") + 1));
                        if (STOP.equalsIgnoreCase(word)) {
                            socket.close();
                            server.close();
                        }
                    }
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
