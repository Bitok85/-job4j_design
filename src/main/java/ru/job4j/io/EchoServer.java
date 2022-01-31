package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final String STOP = "Exit";
    private static final int PORT = 9000;
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (!server.isClosed()) {
                    Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        String word = str.substring(str.indexOf("=") + 1, str.indexOf(" ", str.indexOf("=") + 1));
                        if (STOP.equalsIgnoreCase(word)) {
                            out.write("HTTP/1.1 200 Exit\r\n\r\n".getBytes());
                            socket.close();
                            server.close();
                            break;
                        } else if ("Hello".equalsIgnoreCase(word)) {
                            out.write("HTTP/1.1 200 Hello\r\n\r\n".getBytes());
                            break;
                        } else {
                            out.write("HTTP/1.1 200 What\r\n\r\n".getBytes());
                            break;
                        }
                    }
                    out.flush();
                } catch (Exception e) {
                    LOG.error("Exception", e);
                }
            }
        }
    }
}
