# Price Stock Exchange

A command-line stock exchange simulation built for **Object-Oriented Software Development**. It models orders, quotes, product books, users, and live “current market” updates.

## What I Learned & Implemented

### Object-Oriented Fundamentals

* **Encapsulation**: Domain objects keep state private; updates go through validated methods. Shared read-only views use **DTOs** to avoid leaking internals.
* **Immutability**: `Price` is an immutable value type (stored as **cents** `int` to avoid floating-point drift) and implements `Comparable`.
* **Interfaces & Polymorphism**: `Tradable` abstracts both **orders** and **quote sides**, letting the book match them uniformly.

### Design Patterns (with intent, not just names)

* **Factory**: `PriceFactory` normalizes creation (parsing `"$1,234.56"` → cents) and centralizes validation.
* **Flyweight**: `PriceFactory` returns **one shared instance per price value**, reducing memory churn across the book.
* **Singleton**: `UserManager` and `ProductManager` expose one source of truth for users and product books.
* **Façade**: Those managers provide a simple API while hiding coordination logic (routing tradables to the right book, user updates, etc.).
* **Observer (Publish/Subscribe)**: `CurrentMarketPublisher` notifies subscribed users when top-of-book changes; `User` implements `CurrentMarketObserver`.

### Data Structures & Algorithms

* **Order book**: `TreeMap<Price, List<Tradable>>` for each side

  * BUY: descending by price; SELL: ascending.
  * Efficient **top-of-book** lookups and price-level aggregation.
* **Lookups**: `HashMap` for users, products, and user-specific quote/order tracking.
* **Matching logic**:

  * `ProductBook.tryTrade()` computes tradable volume across sides.
  * `ProductBookSide.tradeOut(price, vol)` proportionally fills across price-level entries, updates filled/remaining/cancelled, and emits fills.

### Error Handling & Validation

* Purpose-built exceptions (e.g., **InvalidPriceException**, data-validation errors).
* Strict input rules for user IDs, symbols, volumes, and price formats.

### Testing Hooks

* A provided `Main` script exercises: adding orders/quotes, cancelling, trading, and current-market publications. Output is deterministic for quick verification.

## How to Run

**Prereqs**

* **JDK 17+** installed (`java -version` to confirm)

**Clone**

```bash
git clone <your-repo-url>
cd <repository-folder-name>
```

**Compile**
(Adjust the main class path if your `Main` has a package.)

```bash
javac -d out --source-path src src/Main.java
```

**Run**

```bash
java -cp out Main
```

You’ll see console output showing adds/cancels, fills, and current-market snapshots (e.g., `WMT $70.70x35 - $70.75x40 [$0.05]`).
