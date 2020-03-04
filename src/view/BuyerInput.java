package view;

import model.Card;
import model.Customer;
import model.Products;
import model.ShoppingCart;

import java.util.Scanner;

import static java.lang.System.exit;

public class BuyerInput {
    public void input() {
        Products products = new Products();
        Customer customer = new Customer();
        ShoppingCart shoppingCart = new ShoppingCart();
        Card card = new Card();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        switch (input) {
            case "show":
                products.getProducts();
                break;
            case "card":
                card.registerCard();
                break;
            case "register":
                System.out.print("Enter name of the customer: ");
                String registerName = scanner.next();
                System.out.print("Enter surname of the customer: ");
                String registerSurname = scanner.next();
                System.out.print("Enter age of the customer: ");
                int registerAge = scanner.nextInt();
                System.out.print("Enter card id of the customer: ");
                int registerCardId = scanner.nextInt();

                customer.registerCustomer(registerName, registerSurname, registerAge, registerCardId);
                break;
            case "check":
                System.out.println("Enter id of the customer: ");
                int checkCustomerId = scanner.nextInt();
                customer.checkBalance(checkCustomerId);
                break;
            case "add":
                System.out.println("Enter id of the customer: ");
                int addCustomerId = scanner.nextInt();
                System.out.println("Enter id of the product: ");
                int addProductId = scanner.nextInt();
                shoppingCart.addProduct(addCustomerId, addProductId);
                break;
            case "cart":
                System.out.println("Enter id of the customer: ");
                int cartCustomerId = scanner.nextInt();
                shoppingCart.checkCart(cartCustomerId);
                break;
            case "buy":
                System.out.println("Enter id of the customer: ");
                int buyCustomerId = scanner.nextInt();
                double price = shoppingCart.getCartPrice(buyCustomerId);
                System.out.println("Are you sure you want to pay - " + price + "$");
                System.out.println("Y - Yes or N - No");
                String buyCheck = scanner.next().toLowerCase();
                switch (buyCheck) {
                    case "y":
                        shoppingCart.buyProduct(shoppingCart.changedBalance(buyCustomerId, price), buyCustomerId);
                        break;
                    case "n":
                        System.out.println("Transaction canceled.");
                        break;
                    default:
                }

                break;
            case "menu":
                BuyerDisplay buyerDisplay = new BuyerDisplay();
                buyerDisplay.getMenu();
                break;
            case "quit":
                exit(0);
                break;
            default:
        }
    }
}
