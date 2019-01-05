package com.musala.javacourse181112.PIK3.ZadachaServer_Socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientClass {
    public static void main(String[] args) {
        boolean l=false;
        try {
            Socket socket = new Socket("192.168.43.122", 1210);
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            Scanner serverScanner = new Scanner(socket.getInputStream());
            while (true) {
                try {
                    String s = scanner.nextLine();
                    printStream.println(s);
                    String s1 = serverScanner.nextLine();
                    System.out.println(s1);
                    l = true;
                } catch (Exception e) {
                    if (l) {
                        System.out.println(3);
                        l = false;
                    }
                }
            }
        }catch (Exception ignore){
        }
    }
}
