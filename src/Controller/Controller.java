package Controller;

import employee.Employee;
import file.controller.IOReader;
import file.controller.IOWriter;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    private static Pattern pattern = Pattern.compile("[0-9]{1,}");
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
            }while(dayInWork.length()>0 && !matcher.matches());
            if(name.length() > 0){
                employees.get(choice-1).setName(name);
            }
            if(position.length()>0){
                employees.get(choice-1).setPosition(position);
            }
            if(dayInWork.length() > 0){
                employees.get(choice-1).setDayInWork(Integer.parseInt(dayInWork));
            }
            saveFile();
            PrintOut.clearScreen();
            mainMenu();
        }catch (NumberFormatException e){
            editMenu();
        }

    }

    public static void removeMenu(){
        PrintOut.clearScreen();
        PrintOut.employeeTable();
        Matcher matcher;
        String choice;
        ArrayList<Employee> employees = PrintOut.getArrayList();
        do{
            System.out.print("Chon so thu tu nhan vien de xoa: ");
            choice = scanner.nextLine();
            if(choice.length()<1){
                PrintOut.clearScreen();
                mainMenu();
            }
            matcher = pattern.matcher(choice);
        }while (!matcher.matches());
        try{
            PrintOut.removeEmployee(Integer.parseInt(choice) - 1);
        }catch (IndexOutOfBoundsException e){
            removeMenu();
        }
        PrintOut.clearScreen();
        mainMenu();
    }

    public static int mainMenu(){
        PrintOut.mainMenu();
        String choice = scanner.nextLine();
        switch (choice){
            case "1":
                Matcher matcher;
                PrintOut.clearScreen();
                System.out.print("Nhap ten nhan vien: ");
                String name = scanner.nextLine();
                System.out.print("Nhap chuc vu nhan vien: ");
                String position = scanner.nextLine();
                String dayInWork;
                do{
                    System.out.print("Nhap ngay cong: ");
                    dayInWork = scanner.nextLine();
                    matcher = pattern.matcher(dayInWork);
                }while (!matcher.matches());

                PrintOut.addEmployee(name,position,Integer.parseInt(dayInWork));
                saveFile();
                PrintOut.clearScreen();
                mainMenu();
                break;
            case "2":
                editMenu();
                break;
            case "3":
                removeMenu();
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
