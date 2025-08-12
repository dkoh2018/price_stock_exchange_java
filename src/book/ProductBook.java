package book;

import current.CurrentMarketTracker;
import exceptions.InvalidProductBookException;
import exceptions.InvalidTradableException;
import price.Price;
import tradable.Quote;
import tradable.Tradable;
import tradable.TradableDTO;

public class ProductBook {

    private final String product;
    private final ProductBookSide buySide;
    private final ProductBookSide sellSide;

    public ProductBook(String product) throws InvalidProductBookException, InvalidTradableException {
        if (product == null || product.isEmpty() || product.length() > 5 || !product.matches("[A-Z0-9.]+")) {
            throw new InvalidProductBookException("Invalid product symbol.");
        }
        
        this.product = product;
        this.buySide = new ProductBookSide(BookSide.BUY);
        this.sellSide = new ProductBookSide(BookSide.SELL);
    }

    public TradableDTO add(Tradable t) throws InvalidProductBookException, InvalidTradableException {
        if (t == null) {
            throw new InvalidProductBookException("Tradable cannot be null.");
        }
        
        ProductBookSide side = (t.getSide() == BookSide.BUY) ? buySide : sellSide;
        
        TradableDTO dto = side.add(t);
        tryTrade();
        updateMarket();
        return dto;
    }

    public TradableDTO[] add(Quote q) throws InvalidProductBookException, InvalidTradableException {
        if (q == null) {
            throw new InvalidProductBookException("Quote cannot be null.");
        }
        
        TradableDTO[] dtos = new TradableDTO[2];
        removeQuotesForUser(q.getUser()); 

        Tradable buyQuote = q.getQuoteSide(BookSide.BUY);
        dtos[0] = buySide.add(buyQuote);
        
        Tradable sellQuote = q.getQuoteSide(BookSide.SELL);
        dtos[1] = sellSide.add(sellQuote);
        
        tryTrade();
        return dtos;
    }

    public TradableDTO cancel(BookSide side, String orderId) {
        if (orderId == null) {
            String msg = (side == BookSide.BUY ? "Failed to cancel BUY order" : "Failed to cancel SELL order");
            System.out.println(msg + "\n");
            return null;
        }
        
        ProductBookSide bookSide = (side == BookSide.BUY) ? buySide : sellSide;
        TradableDTO result = bookSide.cancel(orderId);
        
        if (result == null) {
            String msg = (side == BookSide.BUY ? "Failed to cancel BUY order" : "Failed to cancel SELL order");
            System.out.println(msg + "\n");
        }

        updateMarket();
        return result;
    }

    public TradableDTO[] removeQuotesForUser(String userName) {
        TradableDTO[] dtos = new TradableDTO[2];
        if (userName == null) {
            System.out.println("Failed to cancel null quote\n");
            return dtos;
        }
        dtos[0] = buySide.removeQuotesForUser(userName);
        dtos[1] = sellSide.removeQuotesForUser(userName);

        updateMarket();
        return dtos;
    }

    public void tryTrade() throws InvalidTradableException {
        while (true) {
            Price topBuyPrice = buySide.topOfBookPrice();
            Price topSellPrice = sellSide.topOfBookPrice();

            if (topBuyPrice == null || topSellPrice == null || topSellPrice.greaterThan(topBuyPrice)) {
                break;
            }

            int buySideVol = buySide.topOfBookVolume();
            int sellSideVol = sellSide.topOfBookVolume();
            int volumeToTrade = Math.min(buySideVol, sellSideVol);

            if (volumeToTrade == 0) {
                break;
            }

            buySide.tradeOut(topBuyPrice, volumeToTrade);
            sellSide.tradeOut(topBuyPrice, volumeToTrade);
        }
    }

    private void updateMarket() {
        Price buyPrice = buySide.topOfBookPrice();
        int buyVolume = buySide.topOfBookVolume();

        Price sellPrice = sellSide.topOfBookPrice();
        int sellVolume = sellSide.topOfBookVolume();

        CurrentMarketTracker.getInstance().updateMarket(product, buyPrice, buyVolume, sellPrice, sellVolume);
    }

    public String getTopOfBookString(BookSide side) {
        ProductBookSide bookSide = (side == BookSide.BUY) ? buySide : sellSide;
        if (bookSide.topOfBookPrice() == null) {
            return "Top of " + side + " book: $0.00 x 0";
        }
        return String.format("Top of %s book: %s x %d", side, bookSide.topOfBookPrice(), bookSide.topOfBookVolume());
    }

    @Override
    public String toString() {
        return "--------------------------------------------\n" +
               "Product Book: " + product + "\n" +
                buySide.toString() +
                sellSide.toString() +
               "--------------------------------------------";
    }
}