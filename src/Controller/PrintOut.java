package Controller;

import employee.Employee;
import file.controller.IOReader;

import java.util.ArrayList;

public class PrintOut {
    private static IOReader someReader = IOReader.getInstance("data/employee.txt");
    private static ArrayList<Employee> arrayList = someReader.readFile(1);
    public static void loginMenu(){
        System.out.println("Employee Manage version beta 0.5");
    }
    public static void mainMenu(){
        employeeTable();
        System.out.println("1.Them nhan vien");
        System.out.println("2.Sua thong tin");
        System.out.println("3.Xoa nha vien");
        System.out.println("4.Loc bang chuc vu");
        System.out.println("5.Thay doi bang");
        System.out.println("0.Exit");
    }
    public static void coution(){
        System.out.println("Bo trong de thoat");
    }
    public static void employeeTable(){

            System.out.println("------------------------------------------------------------");
            System.out.printf("STT  |");
            System.out.printf("\t\tHo va ten       |");
            System.out.printf("\tChuc vu       |");
            System.out.printf("\tNgay cong  |\n");
            System.out.println("------------------------------------------------------------");
            for (int i = 0; i < arrayList.size(); i++) {
                System.out.printf("%-5d|  ", i+1);
                System.out.printf("%-20s|  ", arrayList.get(i).getName());
                System.out.printf("%-15s|  ", arrayList.get(i).getPosition());
                System.out.printf("%-10d|  ", arrayList.get(i).getDayInWork());
                System.out.println();
            }
            System.out.println("------------------------------------------------------------");

    }
    public static void employeeTable(ArrayList<Employee> arrayList){
        System.out.println("------------------------------------------------------------");
        System.out.printf("STT  |");
        System.out.printf("\t\tHo va ten       |");
        System.out.printf("\tChuc vu       |");
        System.out.printf("\tNgay cong  |\n");
        System.out.println("------------------------------------------------------------");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.printf("%-5d|  ", i+1);
            System.out.printf("%-20s|  ", arrayList.get(i).getName());
            System.out.printf("%-15s|  ", arrayList.get(i).getPosition());
            System.out.printf("%-10d|  ", arrayList.get(i).getDayInWork());
            System.out.println();
        }
        System.out.println("------------------------------------------------------------");

    }

    public static ArrayList<Employee> getArrayList() {
        return arrayList;
    }
    public static void setArrayListToNull(){
        arrayList = null;
    }
    public static void setArrayList(ArrayList<Employee> list){
        arrayList = list;
    }
    public static void addEmployee(String name, String position, int dayInWork){
        arrayList.add(new Employee(name, position, dayInWork));
    }
    public static void removeEmployee(int index){
        arrayList.remove(index);
    }

    public static void clearScreen() {
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
    }
    public static void printDirectoryFile(){
        IOReader ioReader = IOReader.getInstance();
        ioReader.setPath("data");
        ioReader.printListFile();
    }
}
