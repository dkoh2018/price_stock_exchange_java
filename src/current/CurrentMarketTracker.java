package current;

import price.Price;
import price.PriceFactory;


// must be a singleton
public class CurrentMarketTracker {
    private static final CurrentMarketTracker instance = new CurrentMarketTracker();

    private CurrentMarketTracker() {
    }

    public static CurrentMarketTracker getInstance() {
        return instance;
    }

    public void updateMarket(String symbol, Price buyPrice, int buyVolume, Price sellPrice, int sellVolume) {
        Price marketWidth;

        if (buyPrice == null || sellPrice == null) {
            marketWidth = PriceFactory.makePrice(0);
        } else {
            marketWidth = sellPrice.subtract(buyPrice);
        }

        CurrentMarketSide buySide = new CurrentMarketSide(buyPrice, buyVolume);
        CurrentMarketSide sellSide = new CurrentMarketSide(sellPrice, sellVolume);

        System.out.println("*********** Current Market ***********");
        System.out.printf("* %s\t%s - %s [%s]%n", symbol, buySide, sellSide, marketWidth);
        System.out.println("**************************************");

        CurrentMarketPublisher.getInstance().acceptCurrentMarket(symbol, buySide, sellSide);
    }
}