package file.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;

public class Reader {

    private static volatile Reader instance;
    private File file;
    private BufferedReader bufferedReader;

    private Reader(String path){
        file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            bufferedReader = new BufferedReader(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Reader getInstance(String path) {
        if(instance == null){
            synchronized (Reader.class){
                if(instance == null){
                    instance = new Reader(path);
                }
            }
        }
        return instance;
    }
}
