package ir.ramtung.impl2;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test01() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test01");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addReference("", 100);
        // The following exception was thrown during execution in test generation
        try {
            library0.addReference("", (int) 'a');
            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: the reference has already added");
        } catch (ir.ramtung.sts01.LibraryException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test02() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test02");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addBook("", 100);
        java.lang.Class<?> wildcardClass7 = library0.getClass();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertNotNull(wildcardClass7);
    }

    @Test
    public void test03() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test03");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addProfMember("hi!");
        // The following exception was thrown during execution in test generation
        try {
            library0.addMagazine("", (int) 'a', (int) (short) 0, 100);
            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: magazine`s number is incorrect");
        } catch (ir.ramtung.sts01.LibraryException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test04() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test04");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.returnDocument("", "hi!");
//        try {
//            library0.returnDocument("", "hi!");
//            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: The document is not in member's loan");
//        } catch (Exception e) {
//            // Expected exception.
//        }
        java.lang.Class<?> wildcardClass5 = library0.getClass();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(wildcardClass5);
    }

    @Test
    public void test05() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test05");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addBook("", 100);
        int int8 = library0.getTotalPenalty("hi!");
        library0.borrow("", "hi!");
//        try {
//            library0.borrow("", "hi!");
//            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: this member doesnt exist");
//        } catch (Exception e) {
//            // Expected exception.
//        }
        try {
            library0.addMagazine("", 1, 0, (int) (byte) -1);
            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: this book doesnt exist");
        } catch (ir.ramtung.sts01.LibraryException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test06() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test06");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addBook("", 100);
        int int8 = library0.getTotalPenalty("hi!");
        library0.borrow("", "hi!");
//        try {
//            library0.borrow("", "hi!");
//            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: this book doesnt exist");
//        } catch (Exception e) {
//            // Expected exception.
//        }
        try {
            library0.addBook("", (int) (short) 0);
            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: the book has already added");
        } catch (ir.ramtung.sts01.LibraryException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test07() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test07");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addProfMember("hi!");
        library0.addProfMember("");
        // The following exception was thrown during execution in test generation
        try {
            library0.addMagazine("", 0, (int) '4', (int) (short) -1);
            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: magazine`s year is incorrect");
        } catch (ir.ramtung.sts01.LibraryException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test08() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test08");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addBook("", 100);
        int int8 = library0.getTotalPenalty("hi!");
        library0.borrow("", "");
        library0.addProfMember("");
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test09() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test09");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("", "hi!");
        // The following exception was thrown during execution in test generation
        try {
            library0.addStudentMember("hi!", "");
            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: student name is empty");
        } catch (ir.ramtung.sts01.LibraryException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test10() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test10");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addReference("hi!", (int) (short) 0);
//        try {
//            library0.addReference("hi!", (int) (short) 0);
//            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: Negative or zero copies of a document cannot be added");
//        } catch (Exception e) {
//             Expected exception.
//        }
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test11() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test11");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.extend("", "hi!");
//        try {
//            library0.extend("", "hi!");
//            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: The document is not in member's loan");
//        } catch (ir.ramtung.sts01.LibraryException e) {
//             Expected exception.
//        }
        library0.addBook("", (int) 'a');
        java.lang.Class<?> wildcardClass8 = library0.getClass();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(wildcardClass8);
    }

    @Test
    public void test12() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test12");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.addProfMember("hi!");
        library0.borrow("", "");
        int int11 = library0.getTotalPenalty("hi!");
        // The following exception was thrown during execution in test generation
        try {
            library0.addStudentMember("", "");
            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: student id is empty");
        } catch (ir.ramtung.sts01.LibraryException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
    }

    @Test
    public void test13() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test13");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.addReference("hi!", (int) (byte) 10);
        java.lang.Class<?> wildcardClass8 = library0.getClass();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(wildcardClass8);
    }

    @Test
    public void test14() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test14");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("", "hi!");
        library0.addReference("hi!", 10);
        java.util.List<java.lang.String> strList8 = library0.availableTitles();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList8);
    }

    @Test
    public void test15() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test15");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addProfMember("hi!");
        library0.addBook("hi!", (int) (byte) 100);
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test16() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test16");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addProfMember("hi!");
        library0.addReference("hi!", 0);
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test17() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test17");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.addProfMember("hi!");
        library0.addProfMember("");
        java.util.List<java.lang.String> strList9 = library0.availableTitles();
        library0.extend("hi!", "hi!");
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList9);
    }

    @Test
    public void test18() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test18");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("", "hi!");
        library0.returnDocument("", "");
        library0.borrow("hi!", "");
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test19() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test19");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.timePass(100);
        library0.addMagazine("", (int) ' ', (int) (byte) 1, 0);
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test20() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test20");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addBook("", 100);
        int int8 = library0.getTotalPenalty("hi!");
        library0.borrow("", "");
        library0.extend("hi!", "");
        library0.extend("hi!", "hi!");
        java.lang.Class<?> wildcardClass18 = library0.getClass();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertNotNull(wildcardClass18);
    }

    @Test
    public void test21() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test21");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addBook("", 100);
        library0.borrow("", "");
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test22() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test22");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.addProfMember("hi!");
        library0.borrow("", "");
        library0.extend("", "");
        library0.returnDocument("", "hi!");
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test23() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test23");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addProfMember("hi!");
        library0.addProfMember("");
        // The following exception was thrown during execution in test generation
        try {
            library0.addProfMember("hi!");
            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: the member has already added");
        } catch (ir.ramtung.sts01.LibraryException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test24() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test24");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addBook("", 100);
        int int8 = library0.getTotalPenalty("hi!");
        library0.borrow("", "hi!");
        library0.extend("", "");
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test25() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test25");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.extend("", "hi!");
        library0.addBook("", (int) 'a');
        library0.borrow("hi!", "");
        library0.returnDocument("hi!", "");
        java.util.List<java.lang.String> strList14 = library0.availableTitles();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList14);
    }

    @Test
    public void test26() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test26");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.extend("", "hi!");
        library0.addBook("", (int) 'a');
        library0.addReference("hi!", (int) (byte) 10);
        // The following exception was thrown during execution in test generation
        try {
            library0.addMagazine("", (int) (byte) 10, 0, (int) (short) 10);
            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: the magazine has already added");
        } catch (ir.ramtung.sts01.LibraryException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test27() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test27");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("", "hi!");
        library0.addReference("hi!", 10);
        library0.addProfMember("");
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test28() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test28");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.extend("", "hi!");
        library0.addBook("", (int) 'a');
        library0.addReference("hi!", (int) (byte) 10);
        // The following exception was thrown during execution in test generation
        try {
            library0.addBook("hi!", (int) '#');
            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: the book has already added");
        } catch (ir.ramtung.sts01.LibraryException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test29() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test29");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.timePass(100);
        library0.returnDocument("", "");
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test30() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test30");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("", "hi!");
        library0.addReference("hi!", 0);
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test31() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test31");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.addProfMember("hi!");
        library0.timePass((int) '#');
        library0.returnDocument("hi!", "hi!");
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test32() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test32");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("", "hi!");
        library0.returnDocument("", "");
        // The following exception was thrown during execution in test generation
        try {
            library0.addStudentMember("", "hi!");
            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: student id is empty");
        } catch (ir.ramtung.sts01.LibraryException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test33() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test33");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.returnDocument("", "hi!");
        library0.extend("hi!", "");
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test34() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test34");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.extend("", "hi!");
        library0.addBook("", (int) 'a');
        library0.borrow("hi!", "");
        library0.returnDocument("hi!", "");
        library0.addReference("hi!", (int) (byte) 1);
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test35() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test35");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.timePass(100);
        library0.timePass(1);
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test36() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test36");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.addProfMember("hi!");
        library0.addProfMember("");
        java.util.List<java.lang.String> strList9 = library0.availableTitles();
        library0.addMagazine("", 10, (int) (byte) 1, 1);
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList9);
    }

    @Test
    public void test37() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test37");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.extend("", "hi!");
        library0.addBook("", (int) 'a');
        library0.borrow("hi!", "");
        library0.returnDocument("hi!", "");
        // The following exception was thrown during execution in test generation
        try {
            library0.addMagazine("", (int) 'a', (int) '4', (int) (short) -1);
            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: the magazine has already added");
        } catch (ir.ramtung.sts01.LibraryException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test38() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test38");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.addProfMember("hi!");
        library0.borrow("", "");
        library0.extend("", "");
        library0.addReference("hi!", (int) (byte) 100);
        // The following exception was thrown during execution in test generation
        try {
            library0.addBook("hi!", (int) (byte) 100);
            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: the book has already added");
        } catch (ir.ramtung.sts01.LibraryException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test39() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test39");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.addProfMember("hi!");
        library0.borrow("", "");
        library0.extend("", "");
        java.lang.Class<?> wildcardClass13 = library0.getClass();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(wildcardClass13);
    }

    @Test
    public void test40() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test40");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.timePass(100);
        library0.addReference("", (int) (short) -1);
        library0.timePass(100);
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test41() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test41");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addReference("hi!", 1);
        library0.addBook("", (int) '#');
        library0.returnDocument("", "hi!");
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test42() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test42");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addBook("", 100);
        int int8 = library0.getTotalPenalty("hi!");
        library0.borrow("", "");
        library0.extend("hi!", "");
        library0.extend("hi!", "hi!");
        library0.addReference("hi!", 10);
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test43() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test43");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.extend("", "hi!");
        library0.addBook("", (int) 'a');
        library0.returnDocument("hi!", "hi!");
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test44() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test44");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addBook("", 100);
        int int8 = library0.getTotalPenalty("hi!");
        library0.borrow("", "");
        library0.extend("hi!", "");
        library0.timePass(10);
        java.lang.Class<?> wildcardClass17 = library0.getClass();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertNotNull(wildcardClass17);
    }

    @Test
    public void test45() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test45");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.addProfMember("hi!");
        library0.addProfMember("");
        // The following exception was thrown during execution in test generation
        try {
            library0.addStudentMember("hi!", "");
            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: student name is empty");
        } catch (ir.ramtung.sts01.LibraryException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test46() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test46");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        int int3 = library0.getTotalPenalty("");
        library0.addBook("", 100);
        library0.addReference("hi!", (int) (short) 0);
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test47() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test47");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.addProfMember("hi!");
        library0.addBook("", (int) (byte) 0);
        library0.addBook("hi!", (int) (short) 0);
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test48() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test48");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        // The following exception was thrown during execution in test generation
        try {
            library0.addMagazine("", (int) (short) 0, (int) (byte) 1, 0);
            org.junit.Assert.fail("Expected exception of type ir.ramtung.sts01.LibraryException; message: magazine`s year is incorrect");
        } catch (ir.ramtung.sts01.LibraryException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test49() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test49");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.addProfMember("hi!");
        library0.extend("hi!", "hi!");
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test50() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test50");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.addReference("hi!", (int) (byte) 10);
        library0.addProfMember("");
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test51() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test51");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.addProfMember("hi!");
        library0.addProfMember("");
        java.util.List<java.lang.String> strList9 = library0.availableTitles();
        library0.extend("", "");
        library0.addMagazine("", (int) (byte) 1, (int) '#', (int) (short) -1);
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList9);
    }

    @Test
    public void test52() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test52");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.addProfMember("hi!");
        library0.borrow("", "");
        library0.extend("", "");
        library0.addReference("hi!", (int) (byte) 100);
        java.util.List<java.lang.String> strList16 = library0.availableTitles();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList16);
    }

    @Test
    public void test53() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test53");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.extend("", "");
        library0.borrow("", "hi!");
        library0.addProfMember("");
        java.util.List<java.lang.String> strList10 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList10);
    }

    @Test
    public void test54() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test54");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.extend("", "");
        library0.borrow("", "hi!");
        library0.borrow("hi!", "hi!");
        library0.extend("", "hi!");
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test55() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test55");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("", "hi!");
        library0.returnDocument("", "");
        library0.addMagazine("hi!", 10, (int) 'a', (-1));
        library0.extend("hi!", "");
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test56() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test56");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("", "hi!");
        library0.returnDocument("", "");
        library0.addMagazine("hi!", 10, (int) 'a', (-1));
        library0.addProfMember("");
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test57() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ir.ramtung.impl2.RegressionTest0.test57");
        ir.ramtung.impl2.Library library0 = new ir.ramtung.impl2.Library();
        java.util.List<java.lang.String> strList1 = library0.availableTitles();
        library0.borrow("hi!", "hi!");
        library0.addProfMember("hi!");
        library0.borrow("", "");
        // The following exception was thrown during execution in test generation
        try {
            library0.borrow("hi!", "");
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
    }
}

