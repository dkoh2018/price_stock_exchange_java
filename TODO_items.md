1.  **`CurrentMarketSide` Class**
    * **Purpose**: Holds the price and volume for one side of the market (buy or sell).
    * **Private Data**:
        * `Price price`: The top-of-book price. This cannot be changed after creation.
        * `int volume`: The top-of-book volume. This cannot be changed after creation.
    * **Constructor**: Must accept and set the `price` and `volume`.
    * **Method**:
        * `public String toString()`: Must return a string in the format `$98.10x105`.

2.  **`CurrentMarketObserver` Interface**
    * **Purpose**: An interface for any class that needs to receive current market updates.
    * **Method to Define**:
        * `void updateCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide)`

3.  **`CurrentMarketTracker` Class**
    * **Design**: This must be a **Singleton**.
    * **Purpose**: Receives market data from `ProductBook` and passes it to the `CurrentMarketPublisher`.
    * **Method**:
        * `public void updateMarket(String symbol, Price buyPrice, int buyVolume, Price sellPrice, int sellVolume)`: This method must:
            1.  Calculate the market width (the difference between `sellPrice` and `buyPrice`). If either price is null, the width is 0.
            2.  Create a `CurrentMarketSide` object for the buy side.
            3.  Create a `CurrentMarketSide` object for the sell side.
            4.  Print the current market details to the console in a specific format.
            5.  Call the `acceptCurrentMarket` method on the `CurrentMarketPublisher`.

4.  **`CurrentMarketPublisher` Class**
    * **Design**: This must be a **Singleton** and use the **Observer pattern**.
    * **Purpose**: Manages a list of subscribers and sends them market updates.
    * **Private Data**:
        * `HashMap<String, ArrayList<CurrentMarketObserver>> filters`: The key is the stock symbol, and the value is a list of observers subscribed to that stock.
    * **Public Methods**:
        * `void subscribeCurrentMarket(String symbol, CurrentMarketObserver cmo)`: Adds an observer to the subscription list for a given stock symbol.
        * `void unSubscribeCurrentMarket(String symbol, CurrentMarketObserver cmo)`: Removes an observer from the subscription list for a given stock symbol.
        * `void acceptCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide)`: Notifies all subscribed observers for a given symbol by calling their `updateCurrentMarket` method.

---

### Existing Classes to Update

You need to modify three existing classes.

1.  **`ProductBook` Class**
    * **New Private Method**:
        * `void updateMarket()`: This method gets the top-of-book price and volume for both buy and sell sides and calls the `CurrentMarketTracker`'s `updateMarket` method.
    * **Updated `add` Method**:
        * Call your new `updateMarket()` method immediately after the existing call to `tryTrade()`.
    * **Updated `cancel` Method**:
        * Call your new `updateMarket()` method immediately after the order is cancelled.
    * **Updated `removeQuotesForUser` Method**:
        * Call your new `updateMarket()` method just before the method returns.

2.  **`User` Class**
    * **Declaration Change**: The class must implement the `CurrentMarketObserver` interface.
    * **New Private Data**:
        * `HashMap<String, CurrentMarketSide[]> currentMarkets`: This will store the market data for the user.
    * **New Public Method (from interface)**:
        * `public void updateCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide)`: This method takes the market data, creates a two-element array of `CurrentMarketSide` objects, and stores it in the `currentMarkets` HashMap using the stock symbol as the key.
    * **New Public Method**:
        * `public String getCurrentMarkets()`: This method creates and returns a string that summarizes all the market data stored in the `currentMarkets` HashMap, with one line per stock.

3.  **`UserManager` Class**
    * **New Public Method**:
        * `public User getUser(String userId)`: This method returns the `User` object that matches the provided `userId`. It must throw a custom exception if the user does not exist.