package com.web.utils;

import com.web.common.Constatnts;

import java.io.*;
import java.util.Properties;

public class DataStorage {


    Properties prop = new Properties();
    OutputStream output = null;
    InputStream input = null;
    private static String FILE_NAME = Constatnts.DATA_STORE_PROPERTY_FILE;
    public void write(String key,String value) throws IOException {
        FileInputStream in = new FileInputStream(FILE_NAME);
        prop.load(in);
        in.close();
        FileOutputStream output = new FileOutputStream(FILE_NAME);



        prop.setProperty(key, value);
        prop.store(output, null);
        System.out.println("The "+key+" is"+value);
    }


    public String read(String key)  {

        try {
            input = new FileInputStream(FILE_NAME);
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return prop.getProperty(key);
    }


}
