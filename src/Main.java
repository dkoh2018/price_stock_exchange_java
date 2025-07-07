import price.Price;
import price.PriceFactory;

public class Main {

    public static void main(String[] args) {

        Price p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15;


        try {
            System.out.println("\nTest - PriceFactory.makePrice(String):");

            p1 = PriceFactory.makePrice("345");
            p2 = PriceFactory.makePrice("0000");
            p3 = PriceFactory.makePrice(".48");
            p4 = PriceFactory.makePrice("16.90");
            p5 = PriceFactory.makePrice("54.47");
            p6 = PriceFactory.makePrice("002.50");
            p7 = PriceFactory.makePrice("2,345.67");
            p8 = PriceFactory.makePrice("$-13.12");
            p9 = PriceFactory.makePrice("$-15");
            p10 = PriceFactory.makePrice("$-.35");
            p11 = PriceFactory.makePrice("2.15");
            p12 = PriceFactory.makePrice("$98.76");
            p13 = PriceFactory.makePrice("4");
            p14 = PriceFactory.makePrice("$5.");
            p15 = PriceFactory.makePrice("$9,876,543.21");

            System.out.println("\t1) Should say $345.00: " + p1 + " " + (p1.toString().equals("$345.00") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t2) Should say $0.00: " + p2 + " " + (p2.toString().equals("$0.00") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t3) Should say $0.48: " + p3 + " " + (p3.toString().equals("$0.48") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t4) Should say $16.90: " + p4 + " " + (p4.toString().equals("$16.90") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t5) Should say $54.47: " + p5 + " " + (p5.toString().equals("$54.47") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t6) Should say $2.50: " + p6 + " " + (p6.toString().equals("$2.50") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t7) Should say $2,345.67: " + p7 + " " + (p7.toString().equals("$2,345.67") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t8) Should say $-13.12: " + p8 + " " + (p8.toString().equals("$-13.12") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t9) Should say $-15.00: " + p9 + " " + (p9.toString().equals("$-15.00") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t10) Should say $-0.35: " + p10 + " " + (p10.toString().equals("$-0.35") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t11) Should say $2.15: " + p11 + " " + (p11.toString().equals("$2.15") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t12) Should say $98.76: " + p12 + " " + (p12.toString().equals("$98.76") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t13) Should say $4.00: " + p13 + " " + (p13.toString().equals("$4.00") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t14) Should say $5.00: " + p14 + " " + (p14.toString().equals("$5.00") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t15) Should say $9,876,543.21: " + p15 + " " + (p15.toString().equals("$9,876,543.21") ? "(PASS)" : "(FAIL)"));

            try {
                System.out.print("\t16) Should throw an InvalidPriceException (too many decimal places): ");
                PriceFactory.makePrice("34.5678");
                System.out.println("(FAIL)");
            } catch (Exception ipo) {
                System.out.println("\t\t" + ipo.getClass().getSimpleName() + ": " + ipo.getMessage() + " (PASS)");
            }

            try {
                System.out.print("\t17) Should throw an InvalidPriceException (too few decimal places): ");
                PriceFactory.makePrice("12.3");
                System.out.println("(FAIL)");
            } catch (Exception ipo) {
                System.out.println("\t\t" + ipo.getClass().getSimpleName() + ": " + ipo.getMessage() + " (PASS)");
            }

            try {
                System.out.print("\t18) Should throw an InvalidPriceException (non-numeric in the value): ");
                PriceFactory.makePrice("12.3A");
                System.out.println("(FAIL)");
            } catch (Exception ipo) {
                System.out.println("\t\t" + ipo.getClass().getSimpleName() + ": " + ipo.getMessage() + " (PASS)");
            }

            try {
                System.out.print("\t19) Should throw an InvalidPriceException (multiple decimal places): ");
                PriceFactory.makePrice("12.34.56");
                System.out.println("(FAIL)");
            } catch (Exception ipo) {
                System.out.println("\t\t" + ipo.getClass().getSimpleName() + ": " + ipo.getMessage() + " (PASS)");
            }

            try {
                System.out.print("\t20) Should throw an InvalidPriceException (empty string): ");
                PriceFactory.makePrice("");
                System.out.println("(FAIL)");
            } catch (Exception ipo) {
                System.out.println("\t\t" + ipo.getClass().getSimpleName() + ": " + ipo.getMessage() + " (PASS)");
            }

        } catch (Exception ipo) {
            System.out.println(ipo.getMessage());
            return;
        }

        System.out.println("--------------------------------------------------");
        System.out.println("\nTest - PriceFactory.makePrice(int):");

        // Prices for testing
        Price pa = PriceFactory.makePrice(100000);
        Price pb = PriceFactory.makePrice(1299);
        Price pc = PriceFactory.makePrice(85);
        Price pd = PriceFactory.makePrice(-7550);
        Price pe = PriceFactory.makePrice(0);
        Price pf = PriceFactory.makePrice(1);
        Price pg = PriceFactory.makePrice(Integer.MAX_VALUE);

        System.out.println("\t1) Should say $1,000.00: " + pa + " " + (pa.toString().equals("$1,000.00") ? "(PASS)" : "(FAIL)"));
        System.out.println("\t2) Should say $12.99: " + pb + " " + (pb.toString().equals("$12.99") ? "(PASS)" : "(FAIL)"));
        System.out.println("\t3) Should say $0.85: " + pc + " " + (pc.toString().equals("$0.85") ? "(PASS)" : "(FAIL)"));
        System.out.println("\t4) Should say $-75.50: " + pd + " " + (pd.toString().equals("$-75.50") ? "(PASS)" : "(FAIL)"));
        System.out.println("\t5) Should say $0.00: " + pe + " " + (pe.toString().equals("$0.00") ? "(PASS)" : "(FAIL)"));
        System.out.println("\t6) Should say $0.01: " + pf + " " + (pf.toString().equals("$0.01") ? "(PASS)" : "(FAIL)"));
        System.out.println("\t7) Should say $21,474,836.47: " + pg + " " + (pg.toString().equals("$21,474,836.47") ? "(PASS)" : "(FAIL)"));

        System.out.println("--------------------------------------------------");

        System.out.println("\nTest - isNegative:");
        System.out.println("\t1) Should say true: " + p8.isNegative() + ": " + (p8.isNegative() ? "(PASS)" : "(FAIL)"));
        System.out.println("\t2) Should say true: " + p9.isNegative() + ": " + (p9.isNegative() ? "(PASS)" : "(FAIL)"));
        System.out.println("\t3) Should say true: " + p10.isNegative() + ": " + (p10.isNegative() ? "(PASS)" : "(FAIL)"));
        System.out.println("\t4) Should say false: " + p11.isNegative() + ": " + (!p11.isNegative() ? "(PASS)" : "(FAIL)"));
        System.out.println("\t5) Should say false: " + p12.isNegative() + ": " + (!p12.isNegative() ? "(PASS)" : "(FAIL)"));
        System.out.println("\t6) Should say false: " + p13.isNegative() + ": " + (!p13.isNegative() ? "(PASS)" : "(FAIL)"));
        System.out.println("\t7) Should say false: " + pa.isNegative() + ": " + (!pa.isNegative() ? "(PASS)" : "(FAIL)"));
        System.out.println("\t8) Should say true: " + pd.isNegative() + ": " + (pd.isNegative() ? "(PASS)" : "(FAIL)"));
        System.out.println("\t9) Should say false: " + pg.isNegative() + ": " + (!pg.isNegative() ? "(PASS)" : "(FAIL)"));

        System.out.println("--------------------------------------------------");

        System.out.println("\nTest - add");
        try {
            System.out.println("\t1) Result: Should say $345.00: " + p1.add(p2) + ": " + (p1.add(p2).toString().equals("$345.00") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t2) Result: Should say $0.48: " + p2.add(p3) + ": " + (p2.add(p3).toString().equals("$0.48") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t3) Result: Should say $17.38: " + p3.add(p4) + ": " + (p3.add(p4).toString().equals("$17.38") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t4) Result: Should say $71.37: " + p4.add(p5) + ": " + (p4.add(p5).toString().equals("$71.37") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t5) Result: Should say $1,012.99: " + pa.add(pb) + ": " + (pa.add(pb).toString().equals("$1,012.99") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t6) Result: Should say $13.84: " + pb.add(pc) + ": " + (pb.add(pc).toString().equals("$13.84") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t7) Result: Should say $-74.65: " + pc.add(pd) + ": " + (pc.add(pd).toString().equals("$-74.65") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t8) Result: Should say $-75.50: " + pd.add(pe) + ": " + (pd.add(pe).toString().equals("$-75.50") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t9) Result: Should say $0.01: " + pe.add(pf) + ": " + (pe.add(pf).toString().equals("$0.01") ? "(PASS)" : "(FAIL)"));
            System.out.print("\t10) Should generate an exception with an message: ");
            p5.add(null);
            System.out.println(" (FAIL)");
        } catch (Exception ipo) {
            System.out.println(ipo.getMessage() + " (PASS)");
        }

        System.out.println("--------------------------------------------------");

        System.out.println("\nTest - subtract");
        try {
            System.out.println("\t1) Result: Should say $345.00: " + p1.subtract(p2) + ": " + (p1.subtract(p2).toString().equals("$345.00") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t2) Result: Should say $-0.48: " + p2.subtract(p3) + ": " + (p2.subtract(p3).toString().equals("$-0.48") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t3) Result: Should say $-16.42: " + p3.subtract(p4) + ": " + (p3.subtract(p4).toString().equals("$-16.42") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t4) Result: Should say $-37.57: " + p4.subtract(p5) + ": " + (p4.subtract(p5).toString().equals("$-37.57") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t5) Result: Should say $0.00: " + p6.subtract(p6) + ": " + (p6.subtract(p6).toString().equals("$0.00") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t6) Result: Should say $987.01: " + pa.subtract(pb) + ": " + (pa.subtract(pb).toString().equals("$987.01") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t7) Result: Should say $12.14: " + pb.subtract(pc) + ": " + (pb.subtract(pc).toString().equals("$12.14") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t8) Result: Should say $76.35: " + pc.subtract(pd) + ": " + (pc.subtract(pd).toString().equals("$76.35") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t9) Result: Should say $-75.50: " + pd.subtract(pe) + ": " + (pd.subtract(pe).toString().equals("$-75.50") ? "(PASS)" : "(FAIL)"));
            System.out.println("\t10) Result: Should say $0.00: " + pf.subtract(pf) + ": " + (pf.subtract(pf).toString().equals("$0.00") ? "(PASS)" : "(FAIL)"));
            System.out.print("\t11) Should generate an exception with a message: ");
            p4.subtract(null);
            System.out.println(" (FAIL)");
        } catch (Exception ipo) {
            System.out.println(ipo.getMessage() + " (PASS)");
        }

        System.out.println("--------------------------------------------------");

        System.out.println("\nTest - multiply");
        System.out.println("\t1) Should say $2,760.0: " + p1.multiply(8) + ": " + (p1.multiply(8).toString().equals("$2,760.00") ? "(PASS)" : "(FAIL)"));
        System.out.println("\t2) Should say $0.00: " + p2.multiply(3) + ": " + (p2.multiply(3).toString().equals("$0.00") ? "(PASS)" : "(FAIL)"));
        System.out.println("\t3) Should say $10.56: " + p3.multiply(22) + ": " + (p3.multiply(22).toString().equals("$10.56") ? "(PASS)" : "(FAIL)"));
        System.out.println("\t4) Should say $169.00: " + p4.multiply(10) + ": " + (p4.multiply(10).toString().equals("$169.00") ? "(PASS)" : "(FAIL)"));
        System.out.println("\t5) Should say $-163.41: " + p5.multiply(-3) + ": " + (p5.multiply(-3).toString().equals("$-163.41") ? "(PASS)" : "(FAIL)"));
        System.out.println("\t6) Should say $0.00: " + p6.multiply(0) + ": " + (p6.multiply(0).toString().equals("$0.00") ? "(PASS)" : "(FAIL)"));
        System.out.println("\t7) Should say $2,000.00: " + pa.multiply(2) + ": " + (pa.multiply(2).toString().equals("$2,000.00") ? "(PASS)" : "(FAIL)"));
        System.out.println("\t8) Should say $12.99: " + pb.multiply(1) + ": " + (pb.multiply(1).toString().equals("$12.99") ? "(PASS)" : "(FAIL)"));
        System.out.println("\t9) Should say $37.40: " + pc.multiply(44) + ": " + (pc.multiply(44).toString().equals("$37.40") ? "(PASS)" : "(FAIL)"));

        System.out.println("--------------------------------------------------");

        System.out.println("\nTest - greaterOrEqual");
        try {
            System.out.println("\t1) Should say true: " + p1.greaterOrEqual(p1) + ": " + (p1.greaterOrEqual(p1) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t2) Should say true: " + p1.greaterOrEqual(p2) + ": " + (p1.greaterOrEqual(p2) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t3) Should say false: " + p2.greaterOrEqual(p3) + ": " + (!p2.greaterOrEqual(p3) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t4) Should say false: " + p3.greaterOrEqual(p4) + ": " + (!p3.greaterOrEqual(p4) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t5) Should say false: " + p4.greaterOrEqual(p5) + ": " + (!p4.greaterOrEqual(p5) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t6) Should say true: " + p8.greaterOrEqual(p9) + ": " + (p8.greaterOrEqual(p9) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t7) Should say false: " + p9.greaterOrEqual(p10) + ": " + (!p9.greaterOrEqual(p10) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t8) Should say false: " + p10.greaterOrEqual(p11) + ": " + (!p10.greaterOrEqual(p11) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t9) Should say false: " + p11.greaterOrEqual(p12) + ": " + (!p11.greaterOrEqual(p12) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t10) Should say true: " + p12.greaterOrEqual(p13) + ": " + (p12.greaterOrEqual(p13) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t11) Should say true: " + pa.greaterOrEqual(pb) + ": " + (pa.greaterOrEqual(pb) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t12) Should say true: " + pb.greaterOrEqual(pc) + ": " + (pb.greaterOrEqual(pc) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t13) Should say true: " + pc.greaterOrEqual(pd) + ": " + (pc.greaterOrEqual(pd) ? "(PASS)" : "(FAIL)"));
            System.out.print("\t14) Should generate an exception with a message: ");
            pd.greaterOrEqual(null);
            System.out.println(" (FAIL)");
        } catch (Exception ipo) {
            System.out.println(ipo.getMessage() + " (PASS)");
        }

        System.out.println("--------------------------------------------------");

        System.out.println("\nTest - lessOrEqual");
        try {
            System.out.println("\t1) Should say true: " + p1.lessOrEqual(p1) + ": " + (p1.lessOrEqual(p1) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t2) Should say false: " + p1.lessOrEqual(p2) + ": " + (!p1.lessOrEqual(p2) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t3) Should say true: " + p2.lessOrEqual(p3) + ": " + (p2.lessOrEqual(p3) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t4) Should say true: " + p3.lessOrEqual(p4) + ": " + (p3.lessOrEqual(p4) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t5) Should say true: " + p4.lessOrEqual(p5) + ": " + (p4.lessOrEqual(p5) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t6) Should say false: " + p8.lessOrEqual(p9) + ": " + (!p8.lessOrEqual(p9) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t7) Should say true: " + p9.lessOrEqual(p10) + ": " + (p9.lessOrEqual(p10) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t8) Should say true: " + p10.lessOrEqual(p11) + ": " + (p10.lessOrEqual(p11) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t9) Should say true: " + p11.lessOrEqual(p12) + ": " + (p11.lessOrEqual(p12) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t10) Should say false: " + p12.lessOrEqual(p13) + ": " + (!p12.lessOrEqual(p13) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t11) Should say false: " + pa.lessOrEqual(pb) + ": " + (!pa.lessOrEqual(pb) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t12) Should say false: " + pb.lessOrEqual(pc) + ": " + (!pb.lessOrEqual(pc) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t13) Should say false: " + pc.lessOrEqual(pd) + ": " + (!pc.lessOrEqual(pd) ? "(PASS)" : "(FAIL)"));
            System.out.print("\t14) Should generate an exception with a message: ");
            pd.lessOrEqual(null);
            System.out.println(" (FAIL)");
        } catch (Exception ipo) {
            System.out.println(ipo.getMessage() + " (PASS)");
        }

        System.out.println("--------------------------------------------------");

        System.out.println("\nTest - greaterThan");
        try {
            System.out.println("\t1) Should say false: " + p1.greaterThan(p1) + ": " + (!p1.greaterThan(p1) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t2) Should say true: " + p1.greaterThan(p2) + ": " + (p1.greaterThan(p2) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t3) Should say false: " + p2.greaterThan(p3) + ": " + (!p2.greaterThan(p3) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t4) Should say false: " + p3.greaterThan(p4) + ": " + (!p3.greaterThan(p4) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t5) Should say false: " + p4.greaterThan(p5) + ": " + (!p4.greaterThan(p5) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t6) Should say true: " + p8.greaterThan(p9) + ": " + (p8.greaterThan(p9) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t7) Should say false: " + p9.greaterThan(p10) + ": " + (!p9.greaterThan(p10) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t8) Should say false: " + p10.greaterThan(p11) + ": " + (!p10.greaterThan(p11) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t9) Should say false: " + p11.greaterThan(p12) + ": " + (!p11.greaterThan(p12) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t10) Should say true: " + p12.greaterThan(p13) + ": " + (p12.greaterThan(p13) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t11) Should say true: " + pa.greaterThan(pb) + ": " + (pa.greaterThan(pb) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t12) Should say true: " + pb.greaterThan(pc) + ": " + (pb.greaterThan(pc) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t13) Should say true: " + pc.greaterThan(pd) + ": " + (pc.greaterThan(pd) ? "(PASS)" : "(FAIL)"));
            System.out.print("\t14) Should generate an exception with a message: ");
            pd.greaterThan(null);
            System.out.println(" (FAIL)");
        } catch (Exception ipo) {
            System.out.println(ipo.getMessage() + " (PASS)");
        }

        System.out.println("--------------------------------------------------");

        System.out.println("\nTest - lessThan");
        try {
            System.out.println("\t1) Should say false: " + p1.lessThan(p1) + ": " + (!p1.lessThan(p1) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t2) Should say false: " + p1.lessThan(p2) + ": " + (!p1.lessThan(p2) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t3) Should say true: " + p2.lessThan(p3) + ": " + (p2.lessThan(p3) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t4) Should say true: " + p3.lessThan(p4) + ": " + (p3.lessThan(p4) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t5) Should say true: " + p4.lessThan(p5) + ": " + (p4.lessThan(p5) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t6) Should say false: " + p8.lessThan(p9) + ": " + (!p8.lessThan(p9) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t7) Should say true: " + p9.lessThan(p10) + ": " + (p9.lessThan(p10) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t8) Should say true: " + p10.lessThan(p11) + ": " + (p10.lessThan(p11) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t9) Should say true: " + p11.lessThan(p12) + ": " + (p11.lessThan(p12) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t10) Should say false: " + p12.lessThan(p13) + ": " + (!p12.lessThan(p13) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t11) Should say false: " + pa.lessThan(pb) + ": " + (!pa.lessThan(pb) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t12) Should say false: " + pb.lessThan(pc) + ": " + (!pb.lessThan(pc) ? "(PASS)" : "(FAIL)"));
            System.out.println("\t13) Should say false: " + pc.lessThan(pd) + ": " + (!pc.lessThan(pd) ? "(PASS)" : "(FAIL)"));
            System.out.print("\t14) Should generate an exception with a message: ");
            pd.lessThan(null);
            System.out.println(" (FAIL)");
        } catch (Exception ipo) {
            System.out.println(ipo.getMessage() + " (PASS)");
        }

        System.out.println("--------------------------------------------------");

        System.out.println("\nTest - equals");
        System.out.println("\t1) Should say true: " + p1.equals(p1) + ": " + (p1.equals(p1) ? "(PASS)" : "(FAIL)"));
        System.out.println("\t2) Should say false: " + p1.equals(p2) + ": " + (!p1.equals(p2) ? "(PASS)" : "(FAIL)"));
        System.out.println("\t3) Should say false: " + p2.equals(p3) + ": " + (!p2.equals(p3) ? "(PASS)" : "(FAIL)"));
        System.out.println("\t4) Should say false: " + p3.equals(p4) + ": " + (!p3.equals(p4) ? "(PASS)" : "(FAIL)"));
        System.out.println("\t5) Should say false: " + p4.equals(p5) + ": " + (!p4.equals(p5) ? "(PASS)" : "(FAIL)"));
        System.out.println("\t6) Should say false: " + p8.equals(p9) + ": " + (!p8.equals(p9) ? "(PASS)" : "(FAIL)"));
        System.out.println("\t7) Should say false: " + p9.equals(p10) + ": " + (!p9.equals(p10) ? "(PASS)" : "(FAIL)"));
        System.out.println("\t8) Should say true: " + p10.equals(p10) + ": " + (p10.equals(p10) ? "(PASS)" : "(FAIL)"));
        System.out.println("\t9) Should say false: " + p11.equals(p12) + ": " + (!p11.equals(p12) ? "(PASS)" : "(FAIL)"));
        System.out.println("\t10) Should say false: " + p12.equals(p13) + ": " + (!p12.equals(p13) ? "(PASS)" : "(FAIL)"));
        System.out.println("\t11) Should say false: " + pa.equals(pb) + ": " + (!pa.equals(pb) ? "(PASS)" : "(FAIL)"));
        System.out.println("\t12) Should say false: " + pb.equals(pc) + ": " + (!pb.equals(pc) ? "(PASS)" : "(FAIL)"));
        System.out.println("\t13) Should say true: " + pc.equals(pc) + ": " + (pc.equals(pc) ? "(PASS)" : "(FAIL)"));

        System.out.println("--------------------------------------------------");

        System.out.println("\nTest - compareTo");
        System.out.println("\t1) Should generate zero " + p1.compareTo(p1) + ": " + (p1.compareTo(p1) == 0 ? "(PASS)" : "(FAIL)"));
        System.out.println("\t2) Should generate a positive number: " + p1.compareTo(p2) + ": " + (p1.compareTo(p2) > 0 ? "(PASS)" : "(FAIL)"));
        System.out.println("\t3) Should generate a negative number: " + p2.compareTo(p3) + ": " + (p2.compareTo(p3) < 0 ? "(PASS)" : "(FAIL)"));
        System.out.println("\t4) Should generate a negative number: " + p3.compareTo(p4) + ": " + (p3.compareTo(p4) < 0 ? "(PASS)" : "(FAIL)"));
        System.out.println("\t5) Should generate a negative number: " + p4.compareTo(p5) + ": " + (p4.compareTo(p5) < 0 ? "(PASS)" : "(FAIL)"));
        System.out.println("\t6) Should generate zero: " + p5.compareTo(p5) + ": " + (p5.compareTo(p5) == 0 ? "(PASS)" : "(FAIL)"));
        System.out.println("\t7) Should generate a negative number: " + p6.compareTo(null) + ": " + (p6.compareTo(null) < 0 ? "(PASS)" : "(FAIL)"));
        System.out.println("\t8) Should generate zero: " + pa.compareTo(pa) + ": " + (pa.compareTo(pa) == 0 ? "(PASS)" : "(FAIL)"));
        System.out.println("\t9) Should generate a positive number: " + pa.compareTo(pb) + ": " + (pa.compareTo(pb) > 0 ? "(PASS)" : "(FAIL)"));
        System.out.println("\t10) Should generate a positive number: " + pb.compareTo(pc) + ": " + (pb.compareTo(pc) > 0 ? "(PASS)" : "(FAIL)"));
        System.out.println("\t11) Should generate a positive number: " + pc.compareTo(pd) + ": " + (pc.compareTo(pd) > 0 ? "(PASS)" : "(FAIL)"));


        System.out.println("--------------------------------------------------");

        System.out.println("\nTest - hashCode");
        System.out.println("\t1) Should generate true: " + (p1.hashCode() == p1.hashCode()) + ": " + (p1.hashCode() == p1.hashCode() ? "(PASS)" : "(FAIL)"));
        System.out.println("\t2) Should generate false: " + (p1.hashCode() == p5.hashCode()) + ": " + (p1.hashCode() == p5.hashCode() ? "(FAIL)" : "(PASS)"));
        System.out.println("\t3) Should generate false: " + (p1.hashCode() == p2.hashCode()) + ": " + (p1.hashCode() == p2.hashCode() ? "(FAIL)" : "(PASS)"));
        System.out.println("\t4) Should generate false: " + (p1.hashCode() == p3.hashCode()) + ": " + (p1.hashCode() == p3.hashCode() ? "(FAIL)" : "(PASS)"));
        System.out.println("\t5) Should generate false: " + (p1.hashCode() == p4.hashCode()) + ": " + (p1.hashCode() == p4.hashCode() ? "(FAIL)" : "(PASS)"));
        System.out.println("\t6) Should generate false: " + (p2.hashCode() == p3.hashCode()) + ": " + (p2.hashCode() == p3.hashCode() ? "(FAIL)" : "(PASS)"));
        System.out.println("\t7) Should generate false: " + (p2.hashCode() == p4.hashCode()) + ": " + (p2.hashCode() == p4.hashCode() ? "(FAIL)" : "(PASS)"));
        System.out.println("\t8) Should generate false: " + (p3.hashCode() == p4.hashCode()) + ": " + (p3.hashCode() == p4.hashCode() ? "(FAIL)" : "(PASS)"));
        System.out.println("\t9) Should generate true: " + (pa.hashCode() == pa.hashCode()) + ": " + (pa.hashCode() == pa.hashCode() ? "(PASS)" : "(FAIL)"));
        System.out.println("\t10) Should generate false: " + (pa.hashCode() == pb.hashCode()) + ": " + (pa.hashCode() == pb.hashCode() ? "(FAIL)" : "(PASS)"));
        System.out.println("\t11) Should generate false: " + (pb.hashCode() == pc.hashCode()) + ": " + (pb.hashCode() == pc.hashCode() ? "(FAIL)" : "(PASS)"));
        System.out.println("\t12) Should generate true: " + (pc.hashCode() == pc.hashCode()) + ": " + (pc.hashCode() == pc.hashCode() ? "(PASS)" : "(FAIL)"));
        System.out.println("\t13) Should generate true: " + (pd.hashCode() == PriceFactory.makePrice(-7550).hashCode())
                + ": " + (pd.hashCode() == PriceFactory.makePrice(-7550).hashCode() ? "(PASS)" : "(FAIL)"));

    }
}
