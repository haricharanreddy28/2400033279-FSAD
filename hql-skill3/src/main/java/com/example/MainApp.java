package com.example;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class MainApp {

    static SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n===== PRODUCT MANAGEMENT (HQL LAB) =====");
            System.out.println("1. Insert Product");
            System.out.println("2. Insert Sample Products");
            System.out.println("3. View Product By ID");
            System.out.println("4. Sort Products By Price ASC");
            System.out.println("5. Sort Products By Price DESC");
            System.out.println("6. Sort Products By Quantity");
            System.out.println("7. Pagination Demo");
            System.out.println("8. Aggregate Operations");
            System.out.println("9. Filter By Price Range");
            System.out.println("10. LIKE Queries");
            System.out.println("11. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    insertProduct();
                    break;

                case 2:
                    insertSampleProducts();
                    break;

                case 3:
                    viewProduct();
                    break;

                case 4:
                    sortByPriceAsc();
                    break;

                case 5:
                    sortByPriceDesc();
                    break;

                case 6:
                    sortByQuantity();
                    break;

                case 7:
                    paginationDemo();
                    break;

                case 8:
                    aggregateOperations();
                    break;

                case 9:
                    filterByPriceRange();
                    break;

                case 10:
                    likeQueries();
                    break;

                case 11:
                    factory.close();
                    System.out.println("Application Closed");
                    break;

                default:
                    System.out.println("Invalid Choice");

            }

        } while (choice != 11);

    }

    static void insertProduct() {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        sc.nextLine();

        System.out.print("Enter Name: ");
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

        System.out.println("Product Inserted Successfully");

    }

    static void insertSampleProducts() {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(new Product("Laptop","Electronics",55000,10));
        session.save(new Product("Mouse","Electronics",500,50));
        session.save(new Product("Keyboard","Electronics",1200,30));
        session.save(new Product("Chair","Furniture",3500,15));
        session.save(new Product("Table","Furniture",8000,5));
        session.save(new Product("Bottle","Accessories",300,0));

        tx.commit();
        session.close();

        System.out.println("Sample Products Added");

    }

    static void viewProduct() {

        Session session = factory.openSession();

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        Product p = session.get(Product.class,id);

        if(p != null)
        {
            printProduct(p);
        }
        else
        {
            System.out.println("Product Not Found");
        }

        session.close();

    }

    static void sortByPriceAsc() {

        Session session = factory.openSession();

        Query<Product> q =
                session.createQuery("FROM Product ORDER BY price ASC",Product.class);

        q.list().forEach(MainApp::printProduct);

        session.close();

    }

    static void sortByPriceDesc() {

        Session session = factory.openSession();

        Query<Product> q =
                session.createQuery("FROM Product ORDER BY price DESC",Product.class);

        q.list().forEach(MainApp::printProduct);

        session.close();

    }

    static void sortByQuantity() {

        Session session = factory.openSession();

        Query<Product> q =
                session.createQuery("FROM Product ORDER BY quantity DESC",Product.class);

        q.list().forEach(MainApp::printProduct);

        session.close();

    }

    static void paginationDemo() {

        Session session = factory.openSession();

        Query<Product> q =
                session.createQuery("FROM Product",Product.class);

        q.setFirstResult(0);
        q.setMaxResults(3);

        q.list().forEach(MainApp::printProduct);

        session.close();

    }

    static void aggregateOperations() {

        Session session = factory.openSession();

        Long total =
                session.createQuery("SELECT COUNT(p) FROM Product p",Long.class)
                        .getSingleResult();

        System.out.println("Total Products: "+total);

        session.close();

    }

    static void filterByPriceRange() {

        Session session = factory.openSession();

        Query<Product> q =
                session.createQuery("FROM Product WHERE price BETWEEN :min AND :max",Product.class);

        q.setParameter("min",1000.0);
        q.setParameter("max",10000.0);

        q.list().forEach(MainApp::printProduct);

        session.close();

    }

    static void likeQueries() {

        Session session = factory.openSession();

        session.createQuery(
                "FROM Product WHERE name LIKE 'L%'",Product.class)
                .list().forEach(MainApp::printProduct);

        session.close();

    }

    static void printProduct(Product p)
    {

        System.out.println(
                p.getId()+" | "+
                p.getName()+" | "+
                p.getDescription()+" | "+
                p.getPrice()+" | "+
                p.getQuantity());

    }

}