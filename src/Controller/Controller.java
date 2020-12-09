package Controller;

import file.controller.IOReader;

import java.util.Scanner;

public class Controller {
    private static Scanner scanner = new Scanner(System.in);
    public static boolean login(){
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
                System.out.println("true");
                return true;
            }
        }
        return false;
    }
}
