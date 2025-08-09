package price;


import exceptions.InvalidPriceException;
import java.util.HashMap;

public abstract class PriceFactory {
    private static final HashMap<Integer, Price> priceCache = new HashMap<>();

    public static Price makePrice(int value) {
        if (priceCache.containsKey(value)) {
            return priceCache.get(value);
        }
        Price newPrice = new Price(value);
        priceCache.put(value, newPrice);
        return newPrice;
    }

    public static Price makePrice(String stringValueIn) throws InvalidPriceException {
        if (stringValueIn == null || stringValueIn.trim().isEmpty()) {
            throw new InvalidPriceException("Input string is empty");
        }

        // remove commas first on the value
        String s = stringValueIn.trim().replace(",", "");
        boolean negative = false;

        // handle the negative sign before/after $
        if (s.startsWith("$-")) {
            negative = true;
            s = s.substring(2);
        } else if (s.startsWith("-$")) {
            negative = true;
            s = s.substring(2);
        } else if (s.startsWith("$")) {
            s = s.substring(1);
        } else if (s.startsWith("-")) {
            negative = true;
            s = s.substring(1);
        }



        if (s.isEmpty()) {
            throw new InvalidPriceException("Input string is empty");
        }


        // now handle digits with only one decimal allowed
        int decimalCount = 0;
        for (char c : s.toCharArray()) {
            if (c == '.') {
                decimalCount++;
            } else if (!Character.isDigit(c)) {
                throw new InvalidPriceException("Invalid character in price");
            }
        }

        // if more than one decimal place, invalid
        if (decimalCount > 1) {
            throw new InvalidPriceException("Multiple decimal points");
        }


        // now that we figured out the negative, parse the string into the valid dollar and cents
        // once finished, make usre to add negative if valid
        int value;
        if (s.contains(".")) {
            int index = s.indexOf(".");
            String dollars = s.substring(0, index);
            String cents = s.substring(index + 1);

            // accept ".cc" by defaulting dollars to 0
            // accept "$d*." by defaulting cents to 0
            if (dollars.isEmpty()) dollars = "0";
            if (cents.isEmpty()) cents = "00";

            // cents must have length() == 2 no matter what
            if (cents.length() == 1) throw new InvalidPriceException("Cents must be two digits");
            if (cents.length() > 2) throw new InvalidPriceException("Cents must be two digits");

            // ("\\d*") => checks if string is ONLY digits or is empty (zero or more digits)
            // ("\\d+") => checks if string is ONLY digits and is NOT empty (one or more digits)
            if (!dollars.matches("\\d*") || !cents.matches("\\d+")) {
                throw new InvalidPriceException("Non-numeric characters in price string");
            }

            value = Integer.parseInt(dollars) * 100 + Integer.parseInt(cents);

        } else {
            if (!s.matches("\\d+")) {
                throw new InvalidPriceException("Non-numeric characters in price string");
            }

            value = Integer.parseInt(s) * 100;
        }

        if (negative) {
            value = -value;
        }

        if (priceCache.containsKey(value)) {
            return priceCache.get(value);
        }
        Price newPrice = new Price(value);
        priceCache.put(value, newPrice);
        return newPrice;


    }

}

