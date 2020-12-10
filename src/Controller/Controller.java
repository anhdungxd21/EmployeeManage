package Controller;

import employee.Employee;
import file.controller.IOReader;
import file.controller.IOWriter;

import java.util.HashMap;
import java.util.Scanner;

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
                break;
            case "3":
                break;
            case "0":
                login();
            default:
                PrintOut.clearScreen();
                mainMenu();
        }
        return 0;
    }
}
