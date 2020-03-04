import view.SellerDisplay;
import view.SellerInput;

public class Seller {
    public static void main(String[] args) {
        SellerDisplay sellerDisplay = new SellerDisplay();
        sellerDisplay.getMenu();
        SellerInput sellerInput = new SellerInput();

        while (true) {
            sellerInput.input();
        }
    }
}
