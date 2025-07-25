package tradable;

import book.BookSide;
import exceptions.InvalidTradableException;
import price.Price;

public class QuoteSide implements Tradable {

    private final String user;
    private final String product;
    private final Price price;
    private final BookSide side;
    private final int originalVolume;
    private int remainingVolume;
    private int cancelledVolume;
    private int filledVolume;
    private final String id;

    public QuoteSide(String user, String product, Price price, int originalVolume, BookSide side) throws InvalidTradableException{
        if (user == null || user.length() != 3 || !user.matches("[A-Z]{3}")) {
            throw new InvalidTradableException("Invalid user code.");
        }
        
        if (product == null || product.isEmpty() || product.length() > 5 || !product.matches("[A-Z0-9.]+")) {
            throw new InvalidTradableException("Invalid product symbol.");
        }
        
        if (price == null) {
            throw new InvalidTradableException("Price cannot be null.");
        }
        if (originalVolume <= 0 || originalVolume >= 10000) {
            throw new InvalidTradableException("Original volume must be between 1 and 9999.");
        }
        if (side == null) {
            throw new InvalidTradableException("Side cannot be null.");
        }

        this.user = user;
        this.product = product;
        this.price = price;
        this.originalVolume = originalVolume;
        this.side = side;

        this.remainingVolume = originalVolume;
        this.cancelledVolume = 0;
        this.filledVolume = 0;

        this.id = user + product + price.toString() + System.nanoTime();
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getProduct() {
        return product;
    }

    @Override
    public Price getPrice() {
        return price;
    }

    @Override
    public BookSide getSide() {
        return side;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getOriginalVolume() {
        return originalVolume;
    }

    @Override
    public int getRemainingVolume() {
        return remainingVolume;
    }

    @Override
    public int getCancelledVolume() {
        return cancelledVolume;
    }

    @Override
    public int getFilledVolume() {
        return filledVolume;
    }

    @Override
    public void setRemainingVolume(int newVol) {
        this.remainingVolume = newVol;
    }

    @Override
    public void setCancelledVolume(int newVol) {
        this.cancelledVolume = newVol;
    }

    @Override
    public void setFilledVolume(int newVol) {
        this.filledVolume = newVol;
    }
    
    @Override
    public TradableDTO makeTradableDTO() {
        return new TradableDTO(this);
    }

    @Override
    public String toString() {
        return String.format("%s %s side quote for %s: %s, Orig Vol: %d, Rem Vol: %d, Fill Vol: %d, CXL Vol: %d, ID: %s",
                user,
                side,
                product,
                price,
                originalVolume,
                remainingVolume,
                filledVolume,
                cancelledVolume,
                id);
    }
}