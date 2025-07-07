package price;

import exceptions.InvalidPriceException;

import java.text.DecimalFormat;
import java.util.Objects;

public class Price implements Comparable<Price> {

    // final => immutable
    private final int cents;

    // constructor. using setPrice is mutable
    public Price(int cents) {
        this.cents = cents;
    }


    public boolean isNegative() {
        return cents < 0;
    }

    public Price add(Price p) throws InvalidPriceException {
        if (p == null) throw new InvalidPriceException("Null price");
        return new Price(this.cents + p.cents);
    }


    public Price subtract(Price p) throws InvalidPriceException {
        if (p == null) throw new InvalidPriceException("Null price");
        return new Price(this.cents - p.cents);
    }


    public Price multiply(int n) {
        return new Price(this.cents * n);
    }

    public boolean greaterOrEqual(Price p) throws InvalidPriceException {
        if (p == null) throw new InvalidPriceException("Null price");
        return this.cents >= p.cents;
    }

    public boolean lessOrEqual(Price p) throws InvalidPriceException {
        if (p == null) throw new InvalidPriceException("Null price");
        return this.cents <= p.cents;
    }

    public boolean greaterThan(Price p) throws InvalidPriceException {
        if (p == null) throw new InvalidPriceException("Null price");
        return this.cents > p.cents;
    }

    public boolean lessThan(Price p) throws InvalidPriceException {
        if (p == null) throw new InvalidPriceException("Null price");
        return this.cents < p.cents;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // literally same object

        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;
        return cents == price.cents;
    }

    @Override
    public int compareTo(Price p) {
        if (p == null) return -1;
        return this.cents - p.cents;
    }


    @Override
    public String toString() {
        // break this up into 3 steps
        // 1) create abs(cents) for formatting simplicity
        // 2) from abs(cents), get "dollar" amount and cent "remainder"
        // 3) get the negative "sign" if valid


        int absCents = Math.abs(cents);
        int dollars = absCents / 100;
        int remainder = absCents % 100;
        String sign = (cents < 0) ? "-" : "";

        // 4) combine 1,2,3...ALWAYS put negative AFTER the dollar sign
        return String.format("$%s%,d.%02d", sign, dollars, remainder);
    }


    @Override
    public int hashCode() {
        return Integer.hashCode(cents);
    }

}

