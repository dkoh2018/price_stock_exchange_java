package tradable;

import book.BookSide;
import price.Price;

public record TradableDTO(
        String user,
        String product,
        Price price,
        int originalVolume,
        int remainingVolume,
        int cancelledVolume,
        int filledVolume,
        BookSide side,
        String tradableId) {
    public TradableDTO(Tradable t) {
        this(
                t.getUser(),
                t.getProduct(),
                t.getPrice(),
                t.getOriginalVolume(),
                t.getRemainingVolume(),
                t.getCancelledVolume(),
                t.getFilledVolume(),
                t.getSide(),
                t.getId()
        );
    }
    
    public String getId() {
        return tradableId;
    }
    
    @Override
    public String toString() {
        return String.format("Product: %s, Price: %s, OriginalVolume: %d, RemainingVolume: %d, CancelledVolume: %d, FilledVolume: %d, User: %s, Side: %s, Id: %s",
                product,
                price,
                originalVolume,
                remainingVolume,
                cancelledVolume,
                filledVolume,
                user,
                side,
                tradableId
        );
    }
}