package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskThree {
    private dataArray[] data;
    private int size;
    public TaskThree() {
        data = new dataArray[10];
        size = 0;
    }
    public void readFile(File url){
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
        Pattern pattern = Pattern.compile("[a-z]+");
        Matcher matcher = pattern.matcher(textFile);
        while (matcher.find()) {
            add(matcher.group());
        }
        System.out.println(this);
    }
    private void add(String value){
        boolean add = true;
        for (int i=0 ; i<size;i++) {
            if (data[i].value.equals(value)) {
                data[i].key++;
                add = false;
                break;
            }
        }
        if(add) {
            newSize();
            dataArray newDataArray = new dataArray(value);
            data[size] = newDataArray;
            size++;
        }
    }
    private void newSize(){
        if(size == data.length){
            data = Arrays.copyOf(data,data.length*2);
        }
    }
    private static class dataArray implements Comparable<dataArray> {
        private final String value;
        private int key;
        public dataArray(String value) {
            this.value = value;
            key = 1;
        }
        @Override
        public int compareTo(dataArray o) {
            return Integer.compare(o.key, this.key);
        }
        @Override
        public String toString() {
            return value + " " + key;
        }
    }
    @Override
    public String toString() {
        data = Arrays.copyOf(data,size);
        Arrays.sort(data);
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i<size;i++){
            result.append(data[i].toString()).append("\n");
        }
        return result.toString();
    }
}
