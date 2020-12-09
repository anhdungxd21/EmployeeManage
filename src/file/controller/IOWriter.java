package file.controller;

import java.io.*;

public class IOWriter {
    private static volatile IOWriter ioWriter;
    private String path;
    private StringBuilder list;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    private IOWriter(String path){
        this.path = path;
    }

    public static IOWriter getInstance(String path) {
        if(ioWriter == null){
            synchronized (IOWriter.class){
                if(ioWriter == null){
                    ioWriter = new IOWriter(path);
                }
            }
        }
        return ioWriter;
    }
    public void setPath(String path){
        this.path = path;
    }
    //Write file and return String Array
    public void writeFile(String[] array){
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file,true);
            bufferedWriter = new BufferedWriter(fileWriter);
            for(String line:array){
                bufferedWriter.append(line);
                bufferedWriter.append("\n");
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(bufferedWriter != null){
                try {
                    bufferedWriter.close();
                    fileWriter.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
    }
}
