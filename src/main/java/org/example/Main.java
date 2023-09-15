package org.example;

import java.io.File;
public class Main {
    public static void main(String[] args) {
        //TASK1
        File file1 = new File("Task1/file.txt");
        TaskOne.readFile(file1);

        //TASK2
        TaskTwo taskTwo = new TaskTwo(new File("Task2/file.txt"),new File("Task2/user.json"));
        taskTwo.createJSON();

        //TASK3
        File file3 = new File("Task3/words.txt");
        TaskThree taskThree = new TaskThree();
        taskThree.readFile(file3);
    }
}