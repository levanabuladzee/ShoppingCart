package view;

import model.Products;

import java.util.Scanner;

import static java.lang.System.exit;

public class SellerInput {
    public void input() {
        Products products = new Products();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        switch (input) {
            case "show":
                products.getProducts();
                break;
            case "add":
                System.out.print("Enter name of the product: ");
                String addName = scanner.next();
                System.out.print("Enter price of the product: ");
                double addPrice = scanner.nextDouble();

                products.addProduct(addName, addPrice);
                break;
            case "update":
                System.out.print("Enter name of the product: ");
                String updateName = scanner.next();
                System.out.print("Enter price of the product: ");
                double updatePrice = scanner.nextDouble();
                System.out.print("Enter id of the product: ");
                int updateId = scanner.nextInt();

                products.updateProduct(updateName, updatePrice, updateId);
                break;
            case "remove":
                System.out.print("Enter id of the product: ");
                int removeId = scanner.nextInt();

                products.removeProduct(removeId);
                break;
            case "increase":
                System.out.print("Enter id of the product: ");
                int increaseId = scanner.nextInt();
                System.out.print("Enter price of the increase: ");
                double increasePrice = scanner.nextDouble();

                products.increasePrice(increaseId, increasePrice);
                break;
            case "menu":
                SellerDisplay sellerDisplay = new SellerDisplay();
                sellerDisplay.getMenu();
                break;
            case "quit":
                exit(0);
                break;
            default:
        }
    }
}
