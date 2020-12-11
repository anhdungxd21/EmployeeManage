/*
 * Case-study module 2
 * 11/12/2020
 * author: Le Anh Dung
 * class : C0920i1
 * Employee Management version beta 0.5
 */

package Controller;

import employee.Employee;
import file.controller.IOReader;
import file.controller.IOWriter;
import org.omg.IOP.IOR;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    private static final Pattern pattern = Pattern.compile("[0-9]{1,}");
    private static final Scanner scanner = new Scanner(System.in);
    private static String path;
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
        readFile.setPath("person.txt");
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
        ioWriter.writeObject();
    }

    public static void saveFile(String path,ArrayList<Employee> arrayList){
        IOWriter ioWriter = IOWriter.getInstance();
        ioWriter.setPath("data/"+path);
        ioWriter.writeObject(arrayList);
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
    public static void editMenu(ArrayList<Employee> employees,String something){
        System.out.print("Chọn thứ tự nhân viên cần sửa:");
        int choice;
        try{
            choice = Integer.parseInt(scanner.nextLine());
            int index = -1;
            if(choice < 1 || choice > employees.size()){
                editMenu(employees);
            }
            for (int i = 0; i < PrintOut.getArrayList().size(); i++) {
                if(PrintOut.getArrayList().get(i).getName().contains(employees.get(choice).getName())){
                    index = i;
                }
            }
            if(index > -1){
                PrintOut.getArrayList().remove(index);
            }
        }catch (NumberFormatException e){
            editMenu(employees);
        }
    }
    public static void editMenu(ArrayList<Employee> employees){
        System.out.print("Chọn thứ tự nhân viên cần sửa:");
        int choice;
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher;
        try{
            choice = Integer.parseInt(scanner.nextLine());
            if(choice < 1 || choice > employees.size()){
                editMenu(employees);
            }
            System.out.println("Để trống để bỏ qua.");
            System.out.print("Nhập tên mới:");
            String name = scanner.nextLine();
            System.out.print("Nhập chức vụ mới:");
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
        }catch (NumberFormatException e){
            editMenu(employees);
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

    public static void filterFile(){
        PrintOut.clearScreen();
        PrintOut.employeeTable();
        PrintOut.coution();
        System.out.print("Nhap chuc vu can loc:");
        String input = scanner.nextLine();
        if(input.length() > 0) {


            ArrayList<Employee> employeesSubList = new ArrayList<Employee>();
            ArrayList<Employee> employees = PrintOut.getArrayList();
            for (int i = 0; i < employees.size(); i++) {
                String position = employees.get(i).getPosition().toUpperCase();
                String compare = input.toUpperCase();
                if (compare.compareTo(position) == 0) {
                    employeesSubList.add(employees.get(i));
                }
            }
            PrintOut.clearScreen();
            PrintOut.employeeTable(employeesSubList);
            saveSubTable(employeesSubList);
        }else{
            PrintOut.clearScreen();
            mainMenu();
        }
    }

    public static void saveSubTable(ArrayList<Employee> employees){
        String choice;
        Pattern check1 = Pattern.compile("[01]{1}");
        Pattern checkNameFile = Pattern.compile("([a-zA-Z]{1,}[0-9]{0,})|([a-zA-Z]{1,}[0-9]{0,})[.][a-z0-9]{1,}");
        Matcher matcher;
        Matcher matchNameFile;
        do{
            System.out.println("1.Luu bang moi ra file moi");
            System.out.println("0.Exit");
            choice = scanner.nextLine();
            matcher = check1.matcher(choice);
        }while (!matcher.matches());
        switch (choice){
            case "1":
                String path;
                do{
                    System.out.print("Nhap ten file: ");
                    path = scanner.nextLine();
                    matchNameFile = checkNameFile.matcher(path);
                }while (!matchNameFile.matches());
                if(path.length() > 0){
                    saveFile(path,employees);
                }
                PrintOut.clearScreen();
                mainMenu();
                break;
            case "0":
                PrintOut.clearScreen();
                mainMenu();
                break;
        }

    }
    public static void changeTable(){
        PrintOut.clearScreen();
        PrintOut.printDirectoryFile();
        System.out.println("Không nhập gì hoặc nhập sai để trở về Trang Chính.");
        System.out.print("Nhập tên thư mục:");
        String path = scanner.nextLine();
        boolean flag = false;
        for(String stringPath:PrintOut.getFileList()){
            if(stringPath.toUpperCase().contains(path.toUpperCase())||stringPath.contains(path.toLowerCase())){
                path = stringPath;
            }
        }
        if(path.length() >0) {
            IOReader ioReader = IOReader.getInstance();
            ioReader.setPath("data/" + path);
            IOWriter ioWriter = IOWriter.getInstance();
            ioWriter.setPath("data/" + path);
            ArrayList<Employee> employees = ioReader.readFile(true);
            PrintOut.setArrayList(employees);
            PrintOut.clearScreen();
            mainMenu();
        }else{
            PrintOut.clearScreen();
            mainMenu();
        }
    }
    public static void searchEmployeeEditMenu(ArrayList<Employee> newEmployees){
        PrintOut.clearScreen();
        PrintOut.employeeTable(newEmployees);
        System.out.println("\n1.Chỉnh sửa thông tin nhân viên.");
        System.out.println("2.Xóa nhân viên.");
        System.out.println("0.Trở về trang chính.");
        System.out.print("Lựa chọn: ");
        String choice = scanner.nextLine();
        switch (choice){
            case "1":
                editMenu(newEmployees);
                PrintOut.clearScreen();
                PrintOut.employeeTable(newEmployees);
                searchEmployeeEditMenu(newEmployees);
                saveFile();
                break;
            case "2":
                editMenu(newEmployees,"somthing");
                PrintOut.clearScreen();
                break;
            case "0":
                PrintOut.clearScreen();
                break;
            default:
                searchEmployeeEditMenu(newEmployees);
        }
    }
    public static void searchEmployee(){
        PrintOut.clearScreen();
        PrintOut.employeeTable();
        System.out.print("Nhập tên nhân viên:");
        String name = scanner.nextLine();
        ArrayList<Employee> employees = PrintOut.getArrayList();
        ArrayList<Employee> newEmployees = new ArrayList<Employee>();
        for(Employee employee:employees ){
            String objectName = employee.getName();
            if(objectName.toUpperCase().contains(name.toUpperCase())){
                newEmployees.add(employee);
            }
        }
        searchEmployeeEditMenu(newEmployees);
        return;
    }
    public static int mainMenu(){
        PrintOut.mainMenu();
        String choice = scanner.nextLine();
        switch (choice){
            case "1":
                Matcher matcher;
                PrintOut.clearScreen();
                PrintOut.employeeTable();
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
                saveFile();
                break;
            case "4":
                //filterFile
                filterFile();
                break;
            case "5":
                changeTable();
                break;
            case "6":
                searchEmployee();
                mainMenu();
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
