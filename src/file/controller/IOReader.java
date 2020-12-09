package file.controller;

import java.io.*;

public class IOReader {

    private static volatile IOReader ioReader;
    private String path;
    private StringBuilder list = new StringBuilder();
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private IOReader(String path){
        this.path = path;
    }

    public static IOReader getInstance(String path) {
        if(ioReader == null){
            synchronized (IOReader.class){
                if(ioReader == null){
                    ioReader = new IOReader(path);
                }
            }
        }
        return ioReader;
    }
    public void setPath(String path){
        this.path = path;
    }
    //Read file and return String Array
    public StringBuilder readFile(){
        File file = new File(path);
        try {
            if (!file.exists()) {
                System.out.println("File not found");
                return null;
            }else {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    list.append(line);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                    fileReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
        return list;
    }
}
