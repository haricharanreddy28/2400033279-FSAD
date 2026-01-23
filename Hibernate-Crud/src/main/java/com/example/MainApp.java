package com.example;

import java.util.Scanner;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class MainApp {

    static SessionFactory factory = new Configuration().configure().buildSessionFactory();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== PRODUCT MANAGEMENT =====");
            System.out.println("1. Insert Product");
            System.out.println("2. View Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    insertProduct();
                    break;
                case 2:
                    viewProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    factory.close();
                    System.out.println("Exiting Application...");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        } while (choice != 5);
    }


    static void insertProduct() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Enter Description: ");
        String desc = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        Product p = new Product(name, desc, price, qty);
        session.save(p);

        tx.commit();
        session.close();
        System.out.println("Product Inserted Successfully!");
    }


    static void viewProduct() {
        Session session = factory.openSession();

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        Product p = session.get(Product.class, id);

        if (p != null) {
            System.out.println("ID: " + p.getId());
            System.out.println("Name: " + p.getName());
            System.out.println("Price: " + p.getPrice());
            System.out.println("Quantity: " + p.getQuantity());
        } else {
            System.out.println("Product Not Found!");
        }
        session.close();
    }


    static void updateProduct() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Product ID to Update: ");
        int id = sc.nextInt();

        Product p = session.get(Product.class, id);

        if (p != null) {
            System.out.print("Enter New Price: ");
            p.setPrice(sc.nextDouble());

            System.out.print("Enter New Quantity: ");
            p.setQuantity(sc.nextInt());

            session.update(p);
            tx.commit();
            System.out.println("Product Updated Successfully!");
        } else {
            System.out.println("Product Not Found!");
        }

        session.close();
    }

 
    static void deleteProduct() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Product ID to Delete: ");
        int id = sc.nextInt();

        Product p = session.get(Product.class, id);

        if (p != null) {
            session.delete(p);
            tx.commit();
            System.out.println("Product Deleted Successfully!");
        } else {
            System.out.println("Product Not Found!");
        }

        session.close();
    }
}