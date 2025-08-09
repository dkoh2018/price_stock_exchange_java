

import static book.BookSide.BUY;
import static book.BookSide.SELL;
import managers.ProductManager;
import managers.UserManager;
import price.Price;
import price.PriceFactory;
import tradable.Order;
import tradable.Quote;
import tradable.TradableDTO;

public class Main {
    
    public static void main(String[] args) {

        try {
            System.out.println("1) Create 2 product books - WMT and TGT");

            ProductManager.getInstance().addProduct("WMT");
            ProductManager.getInstance().addProduct("TGT");
            System.out.println();

            //////////////////////////////////////////////////////////////
            System.out.println("2) Add 5 users to UserManager - \"ANA\", \"BOB\", \"COD\", \"DIG\", \"EST\", \"FUN\"");
            UserManager.getInstance().init(
                    new String[]{"ANA", "BOB", "COD", "DIG", "EST", "FUN"});
            System.out.println();

            //////////////////////////////////////////////////////////////
            System.out.println("2a) Print User Tradables:");
            System.out.println("User Tradables:\n" + UserManager.getInstance().toString());

            //////////////////////////////////////////////////////////////
            System.out.println("3) Print WMT and TGT books");
            System.out.println(ProductManager.getInstance().getProductBook("WMT"));
            System.out.println(ProductManager.getInstance().getProductBook("TGT"));
            System.out.println();

            //////////////////////////////////////////////////////////////
            System.out.println("4) Trade test: Single BUY and single SELL order (WMT) - fully trade");
            ProductManager.getInstance().getProductBook("WMT")
                    .add(new Order("ANA", "WMT", PriceFactory.makePrice("$134.00"), 88, BUY));
            ProductManager.getInstance().getProductBook("WMT")
                    .add(new Order("BOB", "WMT", PriceFactory.makePrice("$134.00"), 88, SELL));
            System.out.println(ProductManager.getInstance().getProductBook("WMT"));
            System.out.println();

            //////////////////////////////////////////////////////////////
            System.out.println("5) User Tradables:");
            System.out.println("User Tradables:\n" + UserManager.getInstance().toString());

            //////////////////////////////////////////////////////////////
            System.out.println("6) Trade test: Single BUY and single SELL order (TGT) - full/partial trade & cancel");

            ProductManager.getInstance().getProductBook("TGT")
                    .add(new Order("ANA", "TGT", PriceFactory.makePrice("$134.00"), 33, BUY));

            TradableDTO dto1 = ProductManager.getInstance().getProductBook("TGT")
                    .add(new Order("DIG", "TGT", PriceFactory.makePrice("$134.00"), 66, SELL));

            assert dto1 != null;
            ProductManager.getInstance().getProductBook("TGT").cancel(SELL, dto1.tradableId());
            System.out.println(ProductManager.getInstance().getProductBook("TGT"));
            System.out.println();

            //////////////////////////////////////////////////////////////
            System.out.println("7) User Tradables:");
            System.out.println("User Tradables:\n" + UserManager.getInstance().toString());

            //////////////////////////////////////////////////////////////
            System.out.println("8) Trade test: Multiple BUY and single SELL order (WMT) - fully trade");

            ProductManager.getInstance().getProductBook("WMT")
                    .add(new Order("ANA", "WMT", PriceFactory.makePrice("$134.50"), 60, BUY));
            ProductManager.getInstance().getProductBook("WMT")
                    .add(new Order("BOB", "WMT", PriceFactory.makePrice("$134.50"), 70, BUY));
            ProductManager.getInstance().getProductBook("WMT")
                    .add(new Order("DIG", "WMT", PriceFactory.makePrice("$134.50"), 80, BUY));
            ProductManager.getInstance().getProductBook("WMT")
                    .add(new Order("EST", "WMT", PriceFactory.makePrice("$134.50"), 210, SELL));

            System.out.println(ProductManager.getInstance().getProductBook("WMT"));
            System.out.println();

            //////////////////////////////////////////////////////////////
            System.out.println("9) User Tradables:");
            System.out.println("User Tradables:\n" + UserManager.getInstance().toString());

            //////////////////////////////////////////////////////////////
            System.out.println("10) Trade test: Multiple BUY and single SELL order (TGT) - full/partial trade & cancel");
            dto1 = ProductManager.getInstance().getProductBook("TGT")
                    .add(new Order("BOB", "TGT", PriceFactory.makePrice("$134.65"), 60, BUY));

            TradableDTO dto2 = ProductManager.getInstance().getProductBook("TGT")
                    .add(new Order("COD", "TGT", PriceFactory.makePrice("$134.65"), 70, BUY));

            TradableDTO dto3 = ProductManager.getInstance().getProductBook("TGT")
                    .add(new Order("DIG", "TGT", PriceFactory.makePrice("$134.65"), 80, BUY));

            ProductManager.getInstance().getProductBook("TGT")
                    .add(new Order("EST", "TGT", PriceFactory.makePrice("$134.65"), 100, SELL));

            assert dto1 != null;
            assert dto2 != null;
            assert dto3 != null;
            ProductManager.getInstance().getProductBook("TGT").cancel(BUY, dto1.tradableId());
            ProductManager.getInstance().getProductBook("TGT").cancel(BUY, dto2.tradableId());
            ProductManager.getInstance().getProductBook("TGT").cancel(BUY, dto3.tradableId());
            System.out.println(ProductManager.getInstance().getProductBook("TGT"));
            System.out.println();


            //////////////////////////////////////////////////////////////
            System.out.println("11) User Tradables:");
            System.out.println("User Tradables:\n" + UserManager.getInstance().toString());

            //////////////////////////////////////////////////////////////
            System.out.println("12) Trade test: Multiple orders and quotes on BUY side traded with 1 SELL order (WMT)");

            ProductManager.getInstance().getProductBook("WMT")
                    .add(new Quote("WMT", PriceFactory.makePrice("$133.00"), 40,
                            PriceFactory.makePrice("$133.20"), 75, "ANA"));
            ProductManager.getInstance().getProductBook("WMT")
                    .add(new Quote("WMT", PriceFactory.makePrice("$133.00"), 60,
                            PriceFactory.makePrice("$133.20"), 100, "BOB"));

            dto1 = ProductManager.getInstance().getProductBook("WMT")
                    .add(new Order("COD", "WMT", PriceFactory.makePrice("$133.00"), 15, BUY));
            dto2 = ProductManager.getInstance().getProductBook("WMT")
                    .add(new Order("DIG", "WMT", PriceFactory.makePrice("$133.00"), 180, BUY));
            dto3 = ProductManager.getInstance().getProductBook("WMT")
                    .add(new Order("EST", "WMT", PriceFactory.makePrice("$133.00"), 65, BUY));

            assert dto1 != null;
            assert dto2 != null;
            assert dto3 != null;
            System.out.println(ProductManager.getInstance().getProductBook("WMT"));
            ProductManager.getInstance().getProductBook("WMT")
                    .add(new Order("FUN", "WMT", PriceFactory.makePrice("$133.00"), 200, SELL));
            System.out.println(ProductManager.getInstance().getProductBook("WMT"));
            System.out.println();

            //////////////////////////////////////////////////////////////
            System.out.println("13) Cancel all WMT quotes and orders");
            ProductManager.getInstance().getProductBook("WMT").removeQuotesForUser("ANA");
            ProductManager.getInstance().getProductBook("WMT").removeQuotesForUser("BOB");
            ProductManager.getInstance().getProductBook("WMT").cancel(BUY, dto1.tradableId());
            ProductManager.getInstance().getProductBook("WMT").cancel(BUY, dto2.tradableId());
            ProductManager.getInstance().getProductBook("WMT").cancel(BUY, dto3.tradableId());
            System.out.println();
            System.out.println(ProductManager.getInstance().getProductBook("WMT"));
            System.out.println();

            //////////////////////////////////////////////////////////////
            System.out.println("14) User Tradables:");
            System.out.println("User Tradables:\n" + UserManager.getInstance().toString());

            //////////////////////////////////////////////////////////////
            System.out.println("15) Trade test: Multiple orders and quotes on BUY side traded with 1 SELL order (TGT)");
            ProductManager.getInstance().getProductBook("TGT")
                    .add(new Quote("TGT", PriceFactory.makePrice("$133.00"), 40,
                            PriceFactory.makePrice("$133.20"), 75, "ANA"));
            ProductManager.getInstance().getProductBook("TGT")
                    .add(new Quote("TGT", PriceFactory.makePrice("$133.00"), 60,
                            PriceFactory.makePrice("$133.20"), 100, "BOB"));

            dto1 = ProductManager.getInstance().getProductBook("TGT")
                    .add(new Order("COD", "TGT", PriceFactory.makePrice("$131.00"), 15, BUY));
            dto2 = ProductManager.getInstance().getProductBook("TGT")
                    .add(new Order("DIG", "TGT", PriceFactory.makePrice("$131.00"), 180, BUY));
            dto3 = ProductManager.getInstance().getProductBook("TGT")
                    .add(new Order("EST", "TGT", PriceFactory.makePrice("$131.00"), 65, BUY));
            assert dto1 != null;
            assert dto2 != null;
            assert dto3 != null;
            System.out.println(ProductManager.getInstance().getProductBook("TGT"));
            ProductManager.getInstance().getProductBook("TGT")
                    .add(new Order("FUN", "TGT", PriceFactory.makePrice("$131.00"), 200, SELL));
            System.out.println(ProductManager.getInstance().getProductBook("TGT"));
            System.out.println();
            System.out.println("User Tradables:\n" + UserManager.getInstance().toString());

            //////////////////////////////////////////////////////////////
            System.out.println("16) Cancel all TGT quotes and orders");
            ProductManager.getInstance().getProductBook("TGT").removeQuotesForUser("ANA");
            ProductManager.getInstance().getProductBook("TGT").removeQuotesForUser("BOB");
            ProductManager.getInstance().getProductBook("TGT").cancel(BUY, dto1.tradableId());
            ProductManager.getInstance().getProductBook("TGT").cancel(BUY, dto2.tradableId());
            ProductManager.getInstance().getProductBook("TGT").cancel(BUY, dto3.tradableId());
            System.out.println();
            System.out.println(ProductManager.getInstance().getProductBook("TGT"));
            System.out.println();

            //////////////////////////////////////////////////////////////
            System.out.println("17) User Tradables:");
            System.out.println("User Tradables:\n" + UserManager.getInstance().toString());
            //////////////////////////////////////////////////////////////

            System.out.println("18) Flyweight Price Tests");

            Price p1 = PriceFactory.makePrice("$134.00");
            Price p2 = PriceFactory.makePrice("$134.00");
            Price p3 = PriceFactory.makePrice(13400);
            Price p4 = PriceFactory.makePrice("$134.01");

            System.out.println("\tp1 and p2 same object (true): " + (p1 == p2));
            System.out.println("\tp1 and p3 same object (true): " + (p1 == p3));
            System.out.println("\tp2 and p3 same object (true): " + (p2 == p3));
            System.out.println("\tp1 and p4 same object (false): " + (p1 == p4));


            System.out.println("\nTESTS COMPLETE");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
