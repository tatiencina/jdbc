package db.inicial.menu;

import db.inicial.util.InputUtil;

public class CRUDMenu {
    static InputUtil input = InputUtil.getInstance();

    public static int showCrudMenu(String category) {
        System.out.println("1) Create a new " + category);
        System.out.println("2) Read the list of " + category + "s");
        System.out.println("3) Update an existing " + category);
        System.out.println("4) Delete a " + category);
        System.out.println("0) Exit category");
        System.out.print("What d you want to do?");
        return input.getInt(" --> ");
    }
}
