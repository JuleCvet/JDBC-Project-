package jdbctest;

import java.sql.*;
import java.util.Scanner;

public class JdbcTest {

    static Scanner sc = new Scanner(System.in);
    static boolean loop = true;

    static JdbsManagment jm = new JdbsManagment();

    public static void main(String[] args) throws SQLException {

        while (loop) {
            switchChoice(menu());
        }
    }

    @SuppressWarnings("empty-statement")
    private static int readnumber() {
        int number=0;
        boolean loop1 = false;
        do {
            try {
                System.out.println("Your number: ");
                number = sc.nextInt();
                loop1=false;
                
            } catch (Exception e) {

                System.out.println(e);
                System.out.println("Only numeric values allowed\n");
                 sc.nextLine();
                loop1 = true;
            }
        } while (loop1);
        sc.nextLine();
        return number;

    }

//    public static String readText() {
//     String txt = sc.nextLine();
//        while (!name(txt)) {
//                    System.err.println("Invalid input! Enter name: ");
//                    txt = sc.nextLine();
//        }
//        return txt;
//    }
    
    public static boolean isName(String name) {
        String alfabet = "ABCDEFJHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuwxyz ";
        for (int i = 0; i < name.length(); i++) {
            boolean isWord = false;
            for (int j = 0; j < alfabet.length(); j++) {
                if (name.charAt(i) == alfabet.charAt(j)) {
                    isWord = true;
                }
            }
            if (!isWord) {
                return false;
            }
        }
        return true;
    }

    private static int menu() {
        System.out.println("\n************Main Menu*************");
        System.out.println("\n");
        System.out.println("Enter your Choice");
        System.out.println("1. Show all");
        System.out.println("2. Insert");
        System.out.println("3. Update");
        System.out.println("4. Delete");
        System.out.println("5. Find by id");
        System.out.println("6. Update age by id");
        System.out.println("0. Exit");

        int number = readnumber();
        return number;
    }

    private static void switchChoice(int numberFromMain) throws SQLException {
        int id;
        switch (numberFromMain) {
            case 1:
                jm.showAll();
                break;
            case 2:
                System.out.print("Name:");
                String name = sc.nextLine();

                System.out.print("Last name:");
                String lastName = sc.nextLine();

                System.out.print("Age:");
                int age = readnumber();

                jm.insert(name, lastName, age);
                break;
            case 3:
                System.out.print("What is the id of the person whos name you want to update?");

                id = readnumber();

                System.out.print("What is the new name:");
                String name1 = sc.nextLine();
                jm.update(name1, id);
                break;
            case 4:
                System.out.println("What is the id of the person you want to delete?");
                id = readnumber();
                jm.delete(id);
                break;
            case 5:
                System.out.println("What is the id of the person you want to find?");
                id = readnumber();
                jm.findById(id);
                break;
            case 6:
                System.out.println("What is the id of the person you want to update age for?");
                id = readnumber();

                System.out.println("What is the age of the person you want to update age for?");
                age = readnumber();
                jm.updateAge(id, age);
                break;
            case 0:
                System.out.println("Thank you and come back soon!");
                loop = false;
                break;
            default:
                System.out.println("Please enter 1 to 6.");

        }
    }
}
