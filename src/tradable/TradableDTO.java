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
}