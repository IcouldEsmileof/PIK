package com.musala.javacourse181112.PIK3.ZadachaServer_Socket;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerClass {
    public static void main(String[] args) {
        boolean l=false;
        try {
            ServerSocket serverSocket = new ServerSocket(1210);
            Socket socket = serverSocket.accept();
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            while (true) {

                try {

                    String s = scanner.nextLine();
                    System.out.println(1);
                    printStream.println("What does \"" + s + "\" mean?");
                    l = true;

                } catch (Exception e) {
                    if (l) {
                        System.out.println(2);
                        l = false;
                    }
                }
            }
        }catch (Exception ignore){
        }
    }
}
