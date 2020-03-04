import view.BuyerDisplay;
import view.BuyerInput;

public class Buyer {
    public static void main(String[] args) {
        BuyerDisplay buyerDisplay = new BuyerDisplay();
        buyerDisplay.getMenu();
        BuyerInput buyerInput = new BuyerInput();
        while (true) {
            buyerInput.input();
        }
    }
}
