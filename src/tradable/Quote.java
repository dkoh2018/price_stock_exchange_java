package tradable;

import book.BookSide;
import exceptions.InvalidTradableException;
import price.Price;

public class Quote {
    private final String user;
    private final String product;
    private final QuoteSide buySide;
    private final QuoteSide sellSide;

    public Quote(String symbol, Price buyPrice, int buyVolume, Price sellPrice, int sellVolume, String userName) throws InvalidTradableException {
        // validate product symbol: 1-5 chars, letters/numbers/periods only
        if (symbol == null || symbol.isEmpty() || symbol.length() > 5 || !symbol.matches("[A-Z0-9.]+")) {
            throw new InvalidTradableException("Invalid product symbol.");
        }
        
        // validate user name: must be exactly 3 uppercase letters
        if (userName == null || userName.length() != 3 || !userName.matches("[A-Z]{3}")) {
            throw new InvalidTradableException("Invalid user name.");
        }
        
        // validate prices and volumes
        if (buyPrice == null || sellPrice == null) {
            throw new InvalidTradableException("Prices cannot be null.");
        }
        if (buyVolume <= 0 || buyVolume >= 10000 || sellVolume <= 0 || sellVolume >= 10000) {
            throw new InvalidTradableException("Volumes must be between 1 and 9999.");
        }

        // set basic quote information
        this.product = symbol;
        this.user = userName;
        
        // create buy and sell quote sides with provided parameters
        this.buySide = new QuoteSide(userName, symbol, buyPrice, buyVolume, BookSide.BUY);
        this.sellSide = new QuoteSide(userName, symbol, sellPrice, sellVolume, BookSide.SELL);
    }

    // return the appropriate quote side based on BUY or SELL request
    public QuoteSide getQuoteSide(BookSide sideIn) {
        if (sideIn == null) {
            throw new InvalidTradableException("Side cannot be null.");
        }
        return (sideIn == BookSide.BUY) ? buySide : sellSide;
    }

    public String getSymbol() {
        return product;
    }
    
    public String getProduct() {
        return product;
    }

    public String getUser() {
        return user;
    }
}