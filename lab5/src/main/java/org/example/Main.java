package org.example;

import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MageRepository mageRepository = new MageRepository(new ArrayList<>());
        MageController mageController = new MageController(mageRepository);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("find")){
                System.out.println("Enter the name of the mage you want to find");
                String name = scanner.nextLine();
                System.out.println(mageController.find(name));
            }
            else if (input.equals("delete")){
                System.out.println("Enter the name of the mage you want to delete");
                String name = scanner.nextLine();
                System.out.println(mageController.delete(name));
            }
            else if (input.equals("save")){
                System.out.println("Enter the name of the mage you want to save");
                String name = scanner.nextLine();
                System.out.println("Enter the level of the mage you want to save");
                int level = scanner.nextInt();
                scanner.nextLine();
                System.out.println(mageController.save(name, level));
            }
            else if (input.equals("exit")){
                System.out.println("Exiting...");
                break;
            }
            else{
                System.out.println("Invalid input");
            }
        }
    }
}
