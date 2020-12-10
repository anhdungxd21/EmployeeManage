package Controller;

import employee.Employee;
import file.controller.IOReader;

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
        IOReader readFile =  IOReader.getInstance("data/person.txt");
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



    public static int mainMenu(){
        PrintOut.mainMenu();

        return 0;
    }
}
