package db.inicial.menu;

import db.inicial.util.InputUtil;
import db.inicial.util.PrintUtil;
import jdk.internal.util.xml.impl.Input;

public class MainMenu {

    public static int showMenu() {
        InputUtil input = InputUtil.getInstance();
        PrintUtil.drawSign("Main Menu", "*");
        System.out.println("Choose a category to work with");
        System.out.println("1)Students 2)Teachers 3)Courses 5) Enrollments 0)Exit");
        return input.getInt("Selection:");
    }
}
