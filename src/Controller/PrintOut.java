package Controller;

import employee.Employee;
import file.controller.IOReader;

import java.util.ArrayList;

public class PrintOut {
    public static void loginMenu(){
        System.out.println("Employee Manage version 0.1");
    }
    public static void mainMenu(){
        employeeTable();
        //System.out.printf("%-10d%-10s%-10s%-10d");
        System.out.println("1.Them");
        System.out.println("2.sua");
        System.out.println("3.xoa");
        System.out.println("0.Exit");
    }

    public static void employeeTable(){
        IOReader ioReader = IOReader.getInstance("data/employee.txt");
        ioReader.setPath("data/employee.txt");
        ArrayList<Employee> arrayList = ioReader.readFile(1);
        System.out.println("------------------------------------------------------------");
        System.out.printf("STT  |");
        System.out.printf("\t\tHo va ten       |");
        System.out.printf("\t\tChuc vu   |");
        System.out.printf("\tNgay cong  |\n");
        System.out.println("------------------------------------------------------------");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.printf("%-5d|  ",arrayList.get(i).getId());
            System.out.printf("%-20s|  ",arrayList.get(i).getName());
            System.out.printf("%-15s|  ",arrayList.get(i).getPosition());
            System.out.printf("%-10d|  ",arrayList.get(i).getDayInWork());
            System.out.println();
        }
        System.out.println("------------------------------------------------------------");
    }


    public static void clearScreen() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}
