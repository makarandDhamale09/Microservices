package com.microservice.EmployeeService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<String> list = new ArrayList<>();

    list = Arrays.asList("Chikoo", "Orange", "Strawberry", "Apple", "Banana");

    list.sort(String::compareTo);
    System.out.println(list);

    List<Integer> integers = Arrays.asList(1, 4, 5, 6, 7, 8, 9, 3, 4);
    integers.sort(Integer::compareTo);
    System.out.println(integers);
    int i = Arrays.binarySearch(integers.toArray(), 5);
    System.out.println(i);

    Path path = Path.of("/resources/Test");
    File files = new File(path.toUri());

    try {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));
      bufferedWriter.write("Hello from Java..!!!!");
      bufferedWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
