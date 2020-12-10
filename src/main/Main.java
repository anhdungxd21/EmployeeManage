package main;

import Controller.Controller;

public class Main {
    public static void main(String[] args) {
        boolean loginProgram = true;
        while(loginProgram){
            if(Controller.login()){
                loginProgram = false;
            }
        }
        Controller.mainMenu();
    }
}
