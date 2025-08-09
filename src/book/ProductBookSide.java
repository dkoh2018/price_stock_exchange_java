package book;

import exceptions.InvalidTradableException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import managers.UserManager;
import price.Price;
import tradable.Tradable;
import tradable.TradableDTO;

public class ProductBookSide {

    private final BookSide side;
    private final TreeMap<Price, ArrayList<Tradable>> bookEntries;

    public ProductBookSide(BookSide side) throws InvalidTradableException {
        if (side == null) {
            throw new InvalidTradableException("Side cannot be null.");
        }
        
        this.side = side;
        
        if (side == BookSide.BUY) {
            this.bookEntries = new TreeMap<>(Collections.reverseOrder());
        } else {
            this.bookEntries = new TreeMap<>();
        }
    }

    public TradableDTO add(Tradable o) {
        if (o == null) {
            return null;
        }
        
        ArrayList<Tradable> entries = bookEntries.computeIfAbsent(o.getPrice(), k -> new ArrayList<>());
        entries.add(o);
        
        // System.out.println("**ADD: " + o.toString());
        
        TradableDTO dto = o.makeTradableDTO();
        UserManager.getInstance().updateTradable(o.getUser(), dto);
        
        return dto;
    }

    public TradableDTO cancel(String tradableId) {
        if (tradableId == null) {
            return null;
        }
        for (Price price : bookEntries.keySet()) {
            ArrayList<Tradable> tradables = bookEntries.get(price);
            for (int i = 0; i < tradables.size(); i++) {
                Tradable t = tradables.get(i);
                if (t.getId().equals(tradableId)) {
                    // System.out.println("**CANCEL: " + t.toString());
                    
                    tradables.remove(i);
                    t.setCancelledVolume(t.getCancelledVolume() + t.getRemainingVolume());
                    t.setRemainingVolume(0);
                    if (tradables.isEmpty()) {
                        bookEntries.remove(price);
                    }
                    TradableDTO dto = t.makeTradableDTO();
                    UserManager.getInstance().updateTradable(t.getUser(), dto);
                    return dto;
                }
            }
        }
        return null;
    }

    public TradableDTO removeQuotesForUser(String userName) {
        if (userName == null) {
            return null;
        }
        
        ArrayList<TradableDTO> canceledTradables = new ArrayList<>();
        
        for (ArrayList<Tradable> tradables : bookEntries.values()) {
            for (int i = tradables.size() - 1; i >= 0; i--) {
                Tradable t = tradables.get(i);
                if (t.getUser().equals(userName)) {
                    // System.out.println("**CANCEL: " + t.toString());
                    
                    tradables.remove(i);
                    t.setCancelledVolume(t.getCancelledVolume() + t.getRemainingVolume());
                    t.setRemainingVolume(0);
                    
                    TradableDTO dto = t.makeTradableDTO();
                    canceledTradables.add(dto);
                    UserManager.getInstance().updateTradable(t.getUser(), dto);
                }
            }
        }
        
        // Remove empty price levels
        bookEntries.entrySet().removeIf(entry -> entry.getValue().isEmpty());
        
        if (!canceledTradables.isEmpty()) {
            return canceledTradables.get(0); // Return the first one for backward compatibility
        }
        
        return null;
    }

    public Price topOfBookPrice() {
        if (bookEntries.isEmpty()) {
            return null;
        }
        return bookEntries.firstKey();
    }

    public int topOfBookVolume() {
        if (bookEntries.isEmpty()) {
            return 0;
        }
        ArrayList<Tradable> topList = bookEntries.get(topOfBookPrice());
        int totalVolume = 0;
        for (Tradable t : topList) {
            totalVolume += t.getRemainingVolume();
        }
        return totalVolume;
    }
    public void tradeOut(Price price, int volToTrade) {
        Price topPrice = topOfBookPrice();
        
        if (topPrice == null) {
            return;
        }
        
        if (side == BookSide.BUY && topPrice.lessThan(price)) {
            return;
        }
        if (side == BookSide.SELL && topPrice.greaterThan(price)) {
            return;
        }
        
        ArrayList<Tradable> atPrice = bookEntries.get(topPrice);
        if (atPrice == null || atPrice.isEmpty()) {
            return;
        }
        
        int totalVolAtPrice = 0;
        for (Tradable t : atPrice) {
            totalVolAtPrice += t.getRemainingVolume();
        }
        
        if (volToTrade >= totalVolAtPrice) {
            for (Tradable t : new ArrayList<>(atPrice)) {
                int rv = t.getRemainingVolume();
                
                t.setRemainingVolume(0);
                
                t.setFilledVolume(t.getOriginalVolume());
                
                System.out.println("\tFULL FILL: (" + side + " " + rv + ") " + t.toString());
                
                UserManager.getInstance().updateTradable(t.getUser(), t.makeTradableDTO());
            }
            
            bookEntries.remove(topPrice);
            
        } else {
            int remainder = volToTrade;
            
            for (Tradable t : atPrice) {
                double ratio = (double) t.getRemainingVolume() / totalVolAtPrice;
                
                int toTrade = (int) Math.ceil(volToTrade * ratio);
                
                toTrade = Math.min(toTrade, remainder);
                
                if (toTrade > 0) {
                    t.setFilledVolume(t.getFilledVolume() + toTrade);
                    
                    t.setRemainingVolume(t.getRemainingVolume() - toTrade);
                    
                    System.out.println("\tPARTIAL FILL: (" + side + " " + toTrade + ") " + t.toString());
                    
                    remainder -= toTrade;
                    
                    UserManager.getInstance().updateTradable(t.getUser(), t.makeTradableDTO());
                }
            }
        }
    }

    @Override
    public String toString() {
        String result = "Side: " + side + "\n";
        if (bookEntries.isEmpty()) {
            result += "\t<Empty>\n";
        } else {
            for (Price p : bookEntries.keySet()) {
                result += "\t" + p.toString() + ":\n";
                for (Tradable t : bookEntries.get(p)) {
                    result += "\t\t" + t.toString() + "\n";
                }
            }
        }
        return result;
    }
}