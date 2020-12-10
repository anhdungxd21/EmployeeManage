package Controller;

import employee.Employee;
import file.controller.IOReader;
import file.controller.IOWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    public static HashMap<Integer, Employee> hashMap = new HashMap<Integer, Employee>();
    private static Scanner scanner = new Scanner(System.in);
    /*
     * login logical
     */
    public static boolean login(){
        PrintOut.clearScreen();
        PrintOut.loginMenu();
        System.out.print("username: ");
        String username = scanner.nextLine();
        System.out.print("password: ");
        String password = scanner.nextLine();
        IOReader readFile =  IOReader.getInstance();
        readFile.setPath("data/person.txt");
        StringBuilder userList = readFile.readFile();
        String[] user = userList.toString().split(",");
        for (int i = 0; i < user.length-1; i+=2) {
            if(user[i].equals(username)  && user[i+1].equals(password)){
                PrintOut.clearScreen();
                return true;
            }
        }
        PrintOut.clearScreen();
        return false;
    }

    public static void saveFile(){
        IOWriter ioWriter = IOWriter.getInstance();
        ioWriter.setPath("data/employee.txt");
        ioWriter.writeObject();
    }

    public static void editMenu(){
        PrintOut.clearScreen();
        PrintOut.employeeTable();
        System.out.print("Chon so thu tu can chinh sua:");
        int choice;
        ArrayList<Employee> employees = PrintOut.getArrayList();
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher;
        try{
            choice = Integer.parseInt(scanner.nextLine());
            if(choice < 1 || choice > employees.size()){
                editMenu();
            }
            System.out.print("Nhap ten moi:");
            String name = scanner.nextLine();
            System.out.print("Nhap chuc vu moi:");
            String position = scanner.nextLine();
            String dayInWork;
            do{
                System.out.print("Nhap ngay cong moi:");
                dayInWork = scanner.nextLine();
                matcher = pattern.matcher(dayInWork);
            }while(!matcher.matches());
            employees.get(choice-1).setName(name);
            employees.get(choice-1).setPosition(position);
            employees.get(choice-1).setDayInWork(Integer.parseInt(dayInWork));
            mainMenu();
        }catch (NumberFormatException e){
            editMenu();
        }

    }

    public static int mainMenu(){
        PrintOut.mainMenu();
        String choice = scanner.nextLine();
        switch (choice){
            case "1":
                PrintOut.clearScreen();
                System.out.print("Nhap ten nhan vien: ");
                String name = scanner.nextLine();
                System.out.print("Nhap chuc vu nhan vien: ");
                String position = scanner.nextLine();
                System.out.print("Nhap ngay cong: ");
                int dayInWork = Integer.parseInt(scanner.nextLine());
                PrintOut.addEmployee(name,position,dayInWork);
                saveFile();
                PrintOut.clearScreen();
                mainMenu();
                break;
            case "2":
                editMenu();
                break;
            case "3":
                break;
            case "4":
                login();
                break;
            case "0":
                System.exit(0);
            default:
                PrintOut.clearScreen();
                mainMenu();
        }
        return 0;
    }
}
