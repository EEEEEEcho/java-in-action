package com.echo.ch09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcessFileDemo {
    public static void main(String[] args) throws Exception{
        String re1 = processFile((BufferedReader b) -> b.readLine());
        System.out.println(re1);
        String re2 = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println(re2);
    }

    public static String processFile(BufferReaderProcessor p) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return p.process(br);
        }
    }
}
