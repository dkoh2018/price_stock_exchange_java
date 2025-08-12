package users;

import java.util.HashMap;

import current.CurrentMarketObserver;
import current.CurrentMarketSide;
import tradable.TradableDTO;

public class User implements CurrentMarketObserver {
    private final String userId;
    private final HashMap<String, TradableDTO> tradables = new HashMap<>();
    private final HashMap<String, CurrentMarketSide[]> currentMarkets = new HashMap<>();


    public User(String id) {
        setUserId(id);
        this.userId = id;
    }

    public String getUserId() {
        return userId;
    }

    private void setUserId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (id.length() != 3) {
            throw new IllegalArgumentException("User ID must be exactly 3 characters");
        }
        if (!id.matches("[a-zA-Z]{3}")) {
            throw new IllegalArgumentException("User ID must contain only letters");
        }
    }
    
    public void updateTradable(TradableDTO o) {
        if (o != null) {
            tradables.put(o.getId(), o);
        }
    }

    @Override
    public void updateCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide) {
        currentMarkets.put(symbol, new CurrentMarketSide[]{buySide, sellSide});
    }

    public String getCurrentMarkets() {
        StringBuilder sb = new StringBuilder();
        for (String symbol : currentMarkets.keySet()) {
            CurrentMarketSide[] sides = currentMarkets.get(symbol);
            sb.append(symbol).append(" ").append(sides[0]).append(" - ").append(sides[1]).append("\n");
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User Id: ").append(this.userId).append("\n");
        for (TradableDTO tradable : tradables.values()) {
            sb.append(tradable.toString()).append("\n");
        }
        return sb.toString();
    }
}
