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
    private IOReader(String path){
        this.path = path;
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
    public StringBuilder printListFile(){
        File file = new File(path);
        StringBuilder stringBuilder = new StringBuilder();
        if(file.isDirectory()) {
            for (File file1 : file.listFiles()) {
                stringBuilder.append(file1.toString().substring(5)+",");
            }
        }
        return stringBuilder;
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
    public ArrayList<Employee> readFile(Boolean readObject1){
        File file = new File(path);
        ArrayList<Employee> employees = new ArrayList<Employee>();
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
