package com.echo.ch10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFileDemo {
    public static void main(String[] args) throws Exception {
        String fileName = "";
        ArrayList<String> list= new ArrayList<>();
        int errorCount = 0;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line = bufferedReader.readLine();
        while (errorCount < 40 && line != null){
            if(line.startsWith("ERROR")){
                list.add(line);
                errorCount ++;
            }
            line = bufferedReader.readLine();
        }

        //简洁的方式
        List<String> errorList = Files.lines(Paths.get(fileName))
                .filter(l -> l.startsWith("ERROR"))
                .limit(40)
                .collect(Collectors.toList());
    }
}
