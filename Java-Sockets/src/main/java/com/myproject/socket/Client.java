package com.myproject.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

  private Socket client;

  public static void main(String[] args) {
    Client cl = new Client();
    try {
      cl.init();
      cl.sendMessage("Hello World!!");
    } catch (Exception ex) {
      cl.close();
    }
  }

  public void init() throws Exception {
    client = new Socket("127.0.0.1", 3030);
    System.out.println("Connecting to the server");
  }

  public void sendMessage(String msg) throws Exception {
    System.out.println("Sending the message to the server");
    PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
    writer.println(msg);
  }

  public void close() {
    if (client != null) {
      try {
        client.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
