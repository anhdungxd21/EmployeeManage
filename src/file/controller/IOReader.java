package file.controller;

import Controller.Controller;
import employee.Employee;

import java.io.*;
import java.util.ArrayList;

public class IOReader {

    private static volatile IOReader ioReader;
    private String path;
    private StringBuilder list = new StringBuilder();
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private ArrayList<Employee> employees = new ArrayList<Employee>();

    private IOReader(){

    }

    public static IOReader getInstance() {
        if(ioReader == null){
            synchronized (IOReader.class){
                if(ioReader == null){
                    ioReader = new IOReader();
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

    public ArrayList<Employee> readFile(int readObject1){
        File file = new File(path);
        try {
            if (!file.exists()) {
                System.out.println("File not found");
            }else {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                String line;
                String[] lines;
                while ((line = bufferedReader.readLine()) != null) {
                    lines = line.split(",");
                    employees.add(new Employee(lines[0],lines[1],Integer.parseInt(lines[2])));
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(bufferedReader != null){
                try {
                    fileReader.close();
                    bufferedReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
        return employees;
    }
}
