package com.rz.core.practice.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

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
        int oneChar = 0;
        try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8")) {
            while ((oneChar = inputStreamReader.read()) != -1) {
                if (((char) oneChar) != '\r') {
                    System.out.print((char) oneChar);
                }
            }
        }
    }

    private void show3(File file) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8")) {
            char[] chars = new char[30];
            int oneChar = 0;
            while ((oneChar = inputStreamReader.read(chars)) != -1) {
                if ((oneChar == chars.length) && (chars[chars.length - 1] != '\r')) {
                    System.out.print(chars);
                } else {
                    for (int i = 0; i < oneChar; i++) {
                        if (chars[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(chars[i]);
                        }
                    }
                }
            }
        }
    }
}
