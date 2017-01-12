package com.rz.core.practice.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.springframework.util.StreamUtils;

public class FileHelper {
    public static void main(String[] args) {
        FileHelper fileHelper = new FileHelper();
        try {
            fileHelper.test();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("End FileHelper");
    }

    private void test() throws IOException {
        File directory = new File("D:" + File.separator);
        if (true == directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                System.out.println(file.getPath());
                if (true == file.getPath().equals("D:" + File.separator + "notifycenterTable.sql")) {
                    this.show1(file);
                    this.show2(file);
                    this.show3(file);
                    this.show4(file);
                }
            }
        }
    }

    private void show1(File file) throws FileNotFoundException, IOException {
        int index = 0;
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (null != (line = bufferedReader.readLine())) {
                System.out.println("line " + index + ": " + line);
                index++;
            }
        }
    }

    private void show2(File file) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        int bytesRead = 0;
        try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            while ((bytesRead = inputStreamReader.read()) != -1) {
                if (((char) bytesRead) != '\r') {
                    System.out.print((char) bytesRead);
                }
            }
        }
    }

    private void show3(File file) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            char[] buffer = new char[4096];
            int bytesRead = 0;
            while ((bytesRead = inputStreamReader.read(buffer)) != -1) {
                if ((bytesRead == buffer.length) && (buffer[buffer.length - 1] != '\r')) {
                    System.out.print(buffer);
                } else {
                    for (int i = 0; i < bytesRead; i++) {
                        if (buffer[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(buffer[i]);
                        }
                    }
                }
            }
        }
    }
    
    private void show4(File file) throws FileNotFoundException, IOException{
        try (InputStream fileInputStream = new FileInputStream(file)) {
            System.out.println(StreamUtils.copyToString(fileInputStream, StandardCharsets.UTF_8));
        }
    }
    
//    private void show5(File file) throws FileNotFoundException, IOException{
//        try (InputStream fileInputStream = new FileInputStream(file)) {
//            System.out.println(FileUtils.copyToString(file, StandardCharsets.UTF_8));
//        }
//    }
}
