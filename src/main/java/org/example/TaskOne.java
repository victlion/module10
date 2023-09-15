package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskOne {
    public static void readFile(File url){
        StringBuilder textFile = new StringBuilder();

        try(FileInputStream fis = new FileInputStream(url);
            Scanner scanner = new Scanner(fis)
        )
        {
            while(scanner.hasNext()){
                textFile.append(scanner.nextLine()).append("\n");
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }

        Pattern pattern = Pattern.compile("\\(\\d{3}\\)\\s\\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(textFile.toString());
        while (matcher.find()){
            System.out.println(matcher.group());
        }
    }
}
