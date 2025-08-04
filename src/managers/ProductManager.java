package managers;

import book.BookSide;
import book.ProductBook;
import exceptions.DataValidationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import tradable.Quote;
import tradable.Tradable;
import tradable.TradableDTO;

public class ProductManager {
    private static final ProductManager instance = new ProductManager();
    private final HashMap<String, ProductBook> productBooks = new HashMap<>();
    
    private ProductManager() {}
    
    public static ProductManager getInstance() {
        return instance;
    }
    
    public void addProduct(String symbol) throws DataValidationException {
        if (symbol == null || !symbol.matches("[A-Z]{1,5}")) {
            throw new DataValidationException("Invalid product symbol");
        }
        ProductBook newBook = new ProductBook(symbol);
        productBooks.put(symbol, newBook);
    }
    
    public ProductBook getProductBook(String symbol) throws DataValidationException {
        ProductBook book = productBooks.get(symbol);
        if (book == null) {
            throw new DataValidationException("Product does not exist");
        }
        return book;
    }
    
    public String getRandomProduct() throws DataValidationException {
        if (productBooks.isEmpty()) {
            throw new DataValidationException("No products exist");
        }
        
        ArrayList<String> keys = new ArrayList<>(productBooks.keySet());
        Random random = new Random();
        int randomIndex = random.nextInt(keys.size());
        return keys.get(randomIndex);
    }
    
    public TradableDTO addTradable(Tradable o) throws DataValidationException {
        if (o == null) {
            throw new DataValidationException("Tradable cannot be null");
        }
        
        String productSymbol = o.getProduct();
        ProductBook book = getProductBook(productSymbol);
        TradableDTO dto = book.add(o);
        
        UserManager.getInstance().updateTradable(o.getUser(), new TradableDTO(o));
        
        return dto;
    }
    
    public TradableDTO[] addQuote(Quote q) throws DataValidationException {
        if (q == null) {
            throw new DataValidationException("Quote cannot be null");
        }
        
        String productSymbol = q.getProduct();
        ProductBook book = getProductBook(productSymbol);
        book.removeQuotesForUser(q.getUser());
        
        TradableDTO buyDTO = addTradable(q.getQuoteSide(BookSide.BUY));
        TradableDTO sellDTO = addTradable(q.getQuoteSide(BookSide.SELL));
        
        TradableDTO[] result = new TradableDTO[2];
        result[0] = buyDTO;
        result[1] = sellDTO;
        
        return result;
    }
    
    public TradableDTO cancel(TradableDTO o) throws DataValidationException {
        if (o == null) {
            throw new DataValidationException("TradableDTO cannot be null");
        }
        
        String productSymbol = o.product();
        ProductBook book = getProductBook(productSymbol);
        TradableDTO result = book.cancel(o.side(), o.tradableId());
        
        if (result != null) {
            return result;
        } else {
            System.out.println("Cancel failed");
            return null;
        }
    }
    
    public TradableDTO[] cancelQuote(String symbol, String user) throws DataValidationException {
        if (symbol == null) {
            throw new DataValidationException("Symbol cannot be null");
        }
        if (user == null) {
            throw new DataValidationException("User cannot be null");
        }
        
        ProductBook book = getProductBook(symbol);
        return book.removeQuotesForUser(user);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ProductBook book : productBooks.values()) {
            sb.append(book.toString());
        }
        return sb.toString();
    }
}
