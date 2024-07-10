package dramaplays.model;

import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceTest {

    @Test
    public void testConstructor() {
        List<Performance> performances = new ArrayList<>();
        performances.add(new Performance("1", 100));
        performances.add(new Performance("2", 150));
        Invoice invoice = new Invoice("Customer", performances);

        assertEquals("Customer", invoice.customer);
        assertEquals(2, invoice.performances.size());
    }

    @Test
    public void testGetCustomer() {
        List<Performance> performances = new ArrayList<>();
        Invoice invoice = new Invoice("Customer", performances);
        assertEquals("Customer", invoice.customer);
    }

    @Test
    public void testGetPerformances() {
        List<Performance> performances = new ArrayList<>();
        performances.add(new Performance("1", 100));
        performances.add(new Performance("2", 150));
        Invoice invoice = new Invoice("Customer", performances);

        assertEquals(2, invoice.performances.size());
    }

    @Test
    public void testSetCustomer() {
        List<Performance> performances = new ArrayList<>();
        Invoice invoice = new Invoice("Customer", performances);
        invoice.customer = "New Customer";
        assertEquals("New Customer", invoice.customer);
    }

    @Test
    public void testSetPerformances() {
        List<Performance> performances = new ArrayList<>();
        Invoice invoice = new Invoice("Customer", performances);

        List<Performance> newPerformances = new ArrayList<>();
        newPerformances.add(new Performance("3", 200));
        invoice.performances = newPerformances;

        assertEquals(1, invoice.performances.size());
    }

    @Test
    public void testEquals() {
        List<Performance> performances1 = new ArrayList<>();
        performances1.add(new Performance("1", 100));
        Invoice invoice1 = new Invoice("Customer", performances1);

        List<Performance> performances2 = new ArrayList<>();
        performances2.add(new Performance("1", 100));
        Invoice invoice2 = new Invoice("Customer", performances2);

        assertNotEquals(invoice1, invoice2); // Since invoice1 and invoice2 are two different instances of the Play class, their references are not equal.
    }

    @Test
    public void testNotEquals() {
        List<Performance> performances1 = new ArrayList<>();
        performances1.add(new Performance("1", 100));
        Invoice invoice1 = new Invoice("Customer1", performances1);

        List<Performance> performances2 = new ArrayList<>();
        performances2.add(new Performance("1", 100));
        Invoice invoice2 = new Invoice("Customer2", performances2);

        assertNotEquals(invoice1, invoice2);
    }
}
