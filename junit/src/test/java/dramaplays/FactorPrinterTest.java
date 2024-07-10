package dramaplays;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import dramaplays.model.Play;
import dramaplays.model.Invoice;
import org.junit.jupiter.api.Test;
import dramaplays.model.Performance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorPrinterTest {

    @Test
    public void testPrintWithTragedy() {
        // Initialize plays
        Map<String, Play> plays = new HashMap<>();
        plays.put("hamlet", new Play("Hamlet", "tragedy"));
        plays.put("comedy_of_errors", new Play("The Comedy of Errors", "comedy"));

        // Initialize performances
        List<Performance> performances = List.of(
                new Performance("hamlet", 55),
                new Performance("comedy_of_errors", 35)
        );

        // Initialize invoice
        Invoice invoice = new Invoice("Alice", performances);

        // Initialize factorPrinter
        FactorPrinter factorPrinter = new FactorPrinter();
        String result = factorPrinter.print(invoice, plays);

        // Verify the result
        String expected = "Factor for Alice\n" +
                "  Hamlet: $650.00 (55 seats)\n" +
                "  The Comedy of Errors: $580.00 (35 seats)\n" +
                "Amount owed is $1,230.00\n" +
                "You earned 37 credits\n";

        assertEquals(expected, result);
    }

    @Test
    public void testPrintWithComedy() {
        Map<String, Play> plays = new HashMap<>();
        plays.put("as_you_like_it", new Play("As You Like It", "comedy"));

        List<Performance> performances = List.of(
                new Performance("as_you_like_it", 10),
                new Performance("as_you_like_it", 30)
        );

        Invoice invoice = new Invoice("Bob", performances);

        FactorPrinter factorPrinter = new FactorPrinter();
        String result = factorPrinter.print(invoice, plays);

        // Verify the result
        String expected = "Factor for Bob\n" +
                "  As You Like It: $330.00 (10 seats)\n" +
                "  As You Like It: $540.00 (30 seats)\n" +
                "Amount owed is $870.00\n" +
                "You earned 8 credits\n";

        assertEquals(expected, result);
    }

    @Test
    public void testPrintWithUnknownType() {
        Map<String, Play> plays = new HashMap<>();
        plays.put("unknown_play",  new Play("Unknown Play", "unknown"));

        List<Performance> performances = List.of(new Performance("unknown_play", 20));

        Invoice invoice = new Invoice("Charlie", performances);

        FactorPrinter factorPrinter = new FactorPrinter();

        // Verify if an error is thrown when encountering an unknown type
        assertThrows(Error.class, () -> factorPrinter.print(invoice, plays));
    }

    @Test
    public void testPrintWithZeroAudience() {
        Map<String, Play> plays = new HashMap<>();
        plays.put("midsummer_nights_dream", new Play("A Midsummer Night's Dream", "comedy"));

        List<Performance> performances = List.of(new Performance("midsummer_nights_dream", 0));

        Invoice invoice = new Invoice("David", performances);

        FactorPrinter factorPrinter = new FactorPrinter();
        String result = factorPrinter.print(invoice, plays);

        // Verify the result
        String expected = "Factor for David\n" +
                "  A Midsummer Night's Dream: $300.00 (0 seats)\n" +
                "Amount owed is $300.00\n" +
                "You earned 0 credits\n";

        assertEquals(expected, result);
    }

    @Test
    void testPrintEmptyPlays() {
        // Initialize EMPTY plays
        Map<String, Play> plays = new HashMap<>();

        List<Performance> performances = List.of(new Performance("midsummer_nights_dream", 0));

        Invoice invoice = new Invoice("CustomerName", performances);

        FactorPrinter factorPrinter = new FactorPrinter();

        assertThrows(NullPointerException.class, () -> factorPrinter.print(invoice, plays));
    }

    @Test
    void testPrintEmptyPerformances() {
        Map<String, Play> plays = new HashMap<>();
        plays.put("midsummer_nights_dream", new Play("A Midsummer Night's Dream", "comedy"));

        // Initialize EMPTY performances
        List<Performance> performances = List.of();

        Invoice invoice = new Invoice("CustomerName", performances);

        FactorPrinter factorPrinter = new FactorPrinter();
        String result = factorPrinter.print(invoice, plays);

        // Verify the result
        String expected = "Factor for CustomerName\n" +
                "Amount owed is $0.00\n" +
                "You earned 0 credits\n";

        assertEquals(expected, result);
    }
}
