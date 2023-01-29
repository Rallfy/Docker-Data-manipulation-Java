package org.example;


import dao.impl.LaptopDAOImpl;
import dao.interfaces.LaptopDAO;
import entities.Laptop;
import enums.Type;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in).useDelimiter("\n");
    static LaptopDAO laptopDAO = new LaptopDAOImpl();

    public static EntityManager createEm(String unitName) {
        return Persistence.createEntityManagerFactory(unitName).createEntityManager();
    }

    public static void main(String[] args) {
        
        while (true) {
            System.out.println("Welcome! Please select one option:");
            System.out.println("1. Add a laptop");
            System.out.println("2. View laptops");
            System.out.println("3. Quit!");
            int option = input.nextInt();
            switch (option) {
                case 1:
                    while (true) {
                        System.out.println("1. Add a GAMING laptop");
                        System.out.println("2. Add a BUSINESS laptop");
                        System.out.println("3. Add a DUAL laptop");
                        System.out.println("4. Back");
                        int option3 = input.nextInt();
                        switch (option3) {
                            case 1:
                                System.out.println("Enter laptop name:");
                                String text = input.next();
                                Laptop laptop = new Laptop(text, Type.GAMING);
                                laptopDAO.create(laptop);
                                laptopOptions(laptop);
                                break;
                            case 2:
                                System.out.println("Enter laptop name:");
                                String text2 = input.next();
                                Laptop laptop2 = new Laptop(text2, Type.BUSINESS);
                                laptopDAO.create(laptop2);
                                laptopOptions(laptop2);
                                break;
                            case 3:
                                System.out.println("Enter laptop name:");
                                String text3 = input.next();
                                Laptop laptop3 = new Laptop(text3, Type.DUAL);
                                laptopDAO.create(laptop3);
                                laptopOptions(laptop3);
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Please select a valid option!");
                        }
                        break;
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("1. View all GAMING laptops");
                        System.out.println("2. View all BUSINESS laptops");
                        System.out.println("3. View all DUAL laptops");
                        System.out.println("4. View all laptops");
                        System.out.println("5. Back");
                        int option2 = input.nextInt();
                        switch (option2) {
                            case 1:
                                System.out.println("------------------------------------------------------------------------------------------");
                                laptopDAO.getGamingLaptops().forEach(laptop1 -> System.out.println(laptop1.toString()));
                                System.out.println("------------------------------------------------------------------------------------------");
                                break;
                            case 2:
                                System.out.println("------------------------------------------------------------------------------------------");
                                laptopDAO.getBusinessLaptops().forEach(laptop2 -> System.out.println(laptop2.toString()));
                                System.out.println("------------------------------------------------------------------------------------------");
                                break;
                            case 3:
                                System.out.println("------------------------------------------------------------------------------------------");
                                laptopDAO.getDualLaptops().forEach(laptop3 -> System.out.println(laptop3.toString()));
                                System.out.println("------------------------------------------------------------------------------------------");
                                break;
                            case 4:
                                System.out.println("------------------------------------------------------------------------------------------");
                                laptopDAO.getAll().forEach(laptop4 -> System.out.println(laptop4.toString()));
                                System.out.println("------------------------------------------------------------------------------------------");
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Please select a valid option!");
                        }
                        break;
                    }
                    break;
                case 3:
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Please select a valid option!");
            }
        }
    }

    public static void laptopOptions(Laptop laptop) {
        while (true) {
            System.out.println("1. Done");
            System.out.println("2. Edit");
            System.out.println("3. Delete");
            int option = input.nextInt();
            switch (option) {
                case 1:
                    return;
                case 2:
                    System.out.print("Edit laptop name: ");
                    System.out.println(laptop.getName());
                    String name = input.next();
                    laptop.setName(name);
                    laptopDAO.update(laptop);
                    break;
                case 3:
                    laptopDAO.delete(laptop);
                    return;
                default:
                    System.out.println("Please select a valid option!");
            }
        }
    }
}