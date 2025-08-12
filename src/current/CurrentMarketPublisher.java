package current;

import java.util.ArrayList;
import java.util.HashMap;

// use singleton + observer pattern

public class CurrentMarketPublisher {
    private static final CurrentMarketPublisher instance = new CurrentMarketPublisher();


    private final HashMap<String, ArrayList<CurrentMarketObserver>> subs = new HashMap<>();

    private CurrentMarketPublisher() {}

    public static CurrentMarketPublisher getInstance() {
        return instance;
    }

    public void subscribeCurrentMarket(String symbol, CurrentMarketObserver cmo) {
        if (symbol == null || cmo == null) {
            return;
        }

        ArrayList<CurrentMarketObserver> observers = subs.get(symbol);

        if (observers == null) {
            observers = new ArrayList<>();
            subs.put(symbol, observers);
        }

        if (!observers.contains(cmo)) {
            observers.add(cmo);
        }
    }

    public void unSubscribeCurrentMarket(String symbol, CurrentMarketObserver cmo) {
        if (symbol == null || cmo == null) {
            return;
        }

        ArrayList<CurrentMarketObserver> observers = subs.get(symbol);

        if (observers != null) {
            observers.remove(cmo);
            if (observers.isEmpty()) {
                subs.remove(symbol);
            }
        }
    }

    public void acceptCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide) {
        ArrayList<CurrentMarketObserver> observers = subs.get(symbol);

        if (observers != null) {
            for (CurrentMarketObserver observer : observers) {
                observer.updateCurrentMarket(symbol, buySide, sellSide);
            }
        }
    }





}
