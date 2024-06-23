package com.myproject.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  private ServerSocket server;

  public static void main(String[] args) {
    Server sr = new Server();
    try {
      sr.initAndStart();
    } catch (Exception e) {
      sr.close();
    }
  }

  public void initAndStart() throws Exception {
    server = new ServerSocket(3030);
    System.out.println("Server is running....");
    while (true) {
      Socket clientSocket = server.accept();
      System.out.println("Client is connected..");
      readMessagefromSocket(clientSocket);

    }
  }

  private void readMessagefromSocket(Socket clientSocket) throws Exception {
    BufferedReader reader =
            new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    for (int chr = reader.read(); reader.ready(); chr = reader.read()) {
      System.out.print((char) chr);
    }
  }

  public void close() {
    if (server != null) {
      try {
        server.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
