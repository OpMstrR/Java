package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        // Створення категорій
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");
        // Створення об'єктів класу Product з вказівкою кате
        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);


        // Оголошення товарів і категорій з попереднього коду
        Cart cart = new Cart();

        // Історія замовлень
        List<Order> orderHistory = new ArrayList<>();

        List<Product> products = new ArrayList<>();

        products.add(product1);
        products.add(product2);
        products.add(product3);

        while (true) {
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Зробити замовлення");
            System.out.println("5 - Видалити товар з кошика");
            System.out.println("6 - Історія замовлень");
            System.out.println("7 - Пошук товару");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(product1);
                    System.out.println(product2);
                    System.out.println(product3);
                    break;
                case 2:
                    System.out.println(products);
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int id = scanner.nextInt();
                    // Проста логіка додавання, для прикладу використаємо ID для вибору
                    if (id == 1) cart.addProduct(product1);
                    else if (id == 2) cart.addProduct(product2);
                    else if (id == 3) cart.addProduct(product3);
                    else System.out.println("Товар з таким ID не знайдено");
                    break;
                case 3:
                    System.out.println(cart);
                    break;
                case 4:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);

                        orderHistory.add(order);

                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);

                        cart.clear(); // Метод для очищення кошика, який потрібно реалізувати в класі Cart
                    }
                    break;
                // Видалення товару з кошика
                case 5:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній.");
                    } else {
                        System.out.println("\n===== КОШИК =====");
                        for (Product product : cart.getProducts()) {
                            System.out.println("ID: " + product.getId() + " | " + product.getName() + " | " + product.getPrice());
                        }
                        System.out.print("Введіть ID товару, який потрібно видалити: ");
                        int deleteId = scanner.nextInt();
                        Product productToDelete = null;
                        for (Product product : cart.getProducts()) {
                            if (product.getId() == deleteId) {
                                productToDelete = product;
                                break;
                            }
                        }
                        if (productToDelete != null) {
                            cart.removeProduct(productToDelete);
                            System.out.println("Товар видалено з кошика.");
                        } else {
                            System.out.println("Товар з таким ID не знайдено в кошику.");
                        }
                    }
                    break;
                // Історія замовлень
                case 6:
                    System.out.println("\n===== ІСТОРІЯ ЗАМОВЛЕНЬ =========");
                    if (orderHistory.isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                        System.out.println("===========================");
                    } else {
                        for (int i = 0; i < orderHistory.size(); i++) {
                            System.out.println("\nЗамовлення №" + (i + 1));
                            Order order = orderHistory.get(i);
                            System.out.println("Статус: " + order.getStatus());
                            System.out.println("Товари:");
                            for (Product product : order.getProducts()) {
                                System.out.println("- " + product.getName() + " | " + product.getPrice());
                            }
                            System.out.println("Загальна вартість: " + order.getTotalPrice());
                        }
                    }
                    break;
                // Пошук товару
                case 7:
                    System.out.println("\n===== ПОШУК ТОВАРУ =====");
                    System.out.println("1 - Пошук за назвою");
                    System.out.println("2 - Пошук за категорією");
                    System.out.print("Виберіть спосіб пошуку: ");
                    int searchType = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введіть пошуковий запит: ");
                    String search = scanner.nextLine().toLowerCase();
                    boolean found = false;
                    for (Product product : products) {
                        if (searchType == 1) {
                            // Пошук за назвою
                            if (product.getName().toLowerCase().contains(search)) {
                                System.out.println(product);
                                found = true;
                            }
                        } else if (searchType == 2) {
                            // Пошук за категорією
                            if (product.getCategory().getName().toLowerCase().contains(search)) {
                                System.out.println(product);
                                found = true;
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("Товарів за вашим запитом не знайдено.");
                    }
                    break;

                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;
                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
}


        /* Виведення інформації про товари
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);


         // Створення кошика
         Cart cart = new Cart();
         // Додавання товарів до кошика
         cart.addProduct(product1);
         cart.addProduct(product2);
         cart.addProduct(product3);
         // Виведення інформації про товари в кошику
         System.out.println(cart);
         // Припустимо, користувач вирішив видалити навушники з кошика
         cart.removeProduct(product3);
         // Виведення оновленої інформації про товари в кошику
         System.out.println("\nПісля видалення Навушників:");
         System.out.println(cart);*/



