package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskTwo {
    private final File urlIn;
    private final File urlOut;

    public TaskTwo(File urlIn , File urlOut){
        this.urlIn = urlIn;
        this.urlOut = urlOut;
    }
    public void createJSON(){
        ArrayList<User> listUser = new ArrayList<>();
        StringBuilder textFile = new StringBuilder();

        try(FileInputStream fis = new FileInputStream(urlIn);
            Scanner scanner = new Scanner(fis)
        )
        {
            while(scanner.hasNext()){
                    textFile.append(scanner.nextLine()).append("\n");
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        Pattern pattern = Pattern.compile("[a-zA-z]+\\s\\d+");
        Matcher matcher = pattern.matcher(textFile.toString());
        while (matcher.find()){
            String[] str = matcher.group().split(" ");
            User user = new User(str[0],Integer.parseInt(str[1]));
            listUser.add(user);
        }
        newJson(listUser);
    }

    private static class User{
       public User(String name,int age){
           this.name = name;
           this.age = age;
       }
        private String name;
        private Integer age;
    }

    private void newJson(ArrayList<User> listUser){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listUser);

        //System.out.println(json);

        try {
            FileOutputStream fos = new FileOutputStream(urlOut);
            fos.write(json.getBytes());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
