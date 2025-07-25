package tradable;

import book.BookSide;
import price.Price;

public interface Tradable {
    String getId();
    String getProduct();
    Price getPrice();
    int getOriginalVolume();
    int getRemainingVolume();
    int getCancelledVolume();
    int getFilledVolume();
    void setCancelledVolume(int newVol);
    void setRemainingVolume(int newVol);
    void setFilledVolume(int newVol);
    String getUser();
    BookSide getSide();
    TradableDTO makeTradableDTO();
}