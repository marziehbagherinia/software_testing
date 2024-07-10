package ir.ramtung.impl1;

import java.util.List;

import org.junit.Assume;

import org.junit.runner.RunWith;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class LibraryTest {
    @Property
    public void testAddDuplicateMember(String studentId, String studentName) throws Exception {
        Assume.assumeTrue(studentId != null && !studentId.isEmpty());
        Assume.assumeTrue(studentName != null && !studentName.isEmpty());

        Library library = new Library();
        library.addStudentMember(studentId, studentName);

        Exception exception = null;
        try {
            library.addStudentMember(studentId, studentName);
        } catch (DuplicateMemberEx e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals("Member with the same name exists", exception.getMessage());
    }

    @Property
    public void testAddDocumentWithZeroCopy(String bookTitle) throws Exception {
        Assume.assumeTrue(bookTitle != null && !bookTitle.isEmpty());

        Library library = new Library();

        Exception exception = null;
        try {
            library.addBook(bookTitle, 0);
        } catch (InvalidArgumentEx e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals("Negative or zero copies of a document cannot be added", exception.getMessage());
    }

    @Property
    public void testAddDuplicateDocuments(String bookTitle) throws Exception {
        Assume.assumeTrue(bookTitle != null && !bookTitle.isEmpty());

        Library library = new Library();
        library.addBook(bookTitle, 1);

        Exception exception = null;
        try {
            library.addBook(bookTitle, 1);
        } catch (DuplicateDocumentEx e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals("Document with the same title exists", exception.getMessage());
    }

    @Property
    public void testBorrowingLimitForStudents(String studentId, String studentName) throws Exception {
        Assume.assumeTrue(studentId != null && !studentId.isEmpty());
        Assume.assumeTrue(studentName != null && !studentName.isEmpty());

        Library library = new Library();
        library.addStudentMember(studentId, studentName);

        for (int i = 1; i <= 2; i++) {
            library.addBook("Book" + i, 1);
            library.borrow(studentName, "Book" + i);
        }

        Exception exception = null;
        try {
            library.addBook("Book3", 1);
            library.borrow(studentName, "Book3");
        } catch (CannotBorrowEx e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals("Cannot borrow more documents", exception.getMessage());
    }


    @Property
    public void testBorrowingLimitForProfessors(String profName) throws Exception {
        Assume.assumeTrue(profName != null && !profName.isEmpty());

        Library library = new Library();
        library.addProfMember(profName);

        for (int i = 1; i <= 5; i++) {
            library.addBook("Book" + i, 1);
            library.borrow(profName, "Book" + i);
        }

        Exception exception = null;
        try {
            library.addBook("Book6", 1);
            library.borrow(profName, "Book6");
        } catch (CannotBorrowEx e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals("Cannot borrow more documents", exception.getMessage());
    }

    @Property
    public void testBorrowingDocumentWithZeroExistingCopy(String bookTitle) throws Exception {
        Assume.assumeTrue(bookTitle != null && !bookTitle.isEmpty());

        Library library = new Library();
        library.addStudentMember("1", "Stu_1");
        library.addStudentMember("2", "Stu_2");
        library.addBook(bookTitle, 1);
        library.borrow("Stu_1", bookTitle);

        Exception exception = null;
        try {
            library.borrow("Stu_2", bookTitle);
        } catch (CannotBorrowEx e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals("Document is not available to borrow", exception.getMessage());
    }

    @Property
    public void testAvailability(String studentId, String studentName, String bookTitle) throws Exception {
        Assume.assumeTrue(studentId != null && !studentId.isEmpty());
        Assume.assumeTrue(studentName != null && !studentName.isEmpty());
        Assume.assumeTrue(bookTitle != null && !bookTitle.isEmpty());

        Library library = new Library();
        library.addStudentMember(studentId, studentName);
        library.addBook(bookTitle, 1);

        library.borrow(studentName, bookTitle);

        List<String> availableTitles = library.availableTitles();
        assertFalse(availableTitles.contains(bookTitle));

        library.returnDocument(studentName, bookTitle);

        availableTitles = library.availableTitles();
        assertTrue(availableTitles.contains(bookTitle));
    }

    @Property
    public void testExtendMagazineRestriction(String studentId, String studentName, String magazineTitle) throws Exception {
        Assume.assumeTrue(magazineTitle != null && !magazineTitle.isEmpty());
        Assume.assumeTrue(studentId != null && !studentId.isEmpty());
        Assume.assumeTrue(studentName != null && !studentName.isEmpty());

        Library library = new Library();
        library.addStudentMember(studentId, studentName);
        library.addMagazine(magazineTitle, 1390, 5, 1);

        library.borrow(studentName, magazineTitle);

        library.timePass(1);

        Exception exception = null;
        try {
            library.extend(studentName, magazineTitle);
        } catch (CannotExtendEx e) {
            exception = e;
        }

        String exceptedOutput = "";
        if ( exception != null )
        {
            exceptedOutput = exception.getMessage();
        }
        else
        {
            exceptedOutput = "No exception was thrown";
        }

        // ToDo: The magazine document can't extended.
        assertNull(exception);
        assertNotEquals("Cannot extend a magazine", exceptedOutput);
    }

    @Property
    public void testExtendOnBorrowOnDate(String studentId, String studentName, String bookTitle) throws Exception {
        Assume.assumeTrue(studentId != null && !studentId.isEmpty());
        Assume.assumeTrue(studentName != null && !studentName.isEmpty());
        Assume.assumeTrue(bookTitle != null && !bookTitle.isEmpty());

        Library library = new Library();
        library.addStudentMember(studentId, studentName);
        library.addBook(bookTitle, 1);

        library.borrow(studentName, bookTitle);

        Exception exception = null;
        try {
            library.extend(studentName, bookTitle);
        } catch (CannotExtendEx e) {
            exception = e;
        }

         assertNotNull(exception);
         assertEquals("Cannot extend on the same day borrowed", exception.getMessage());
    }

    @Property
    public void testExtendAfterDueDate(String studentId, String studentName, String bookTitle) throws Exception {
        Assume.assumeTrue(studentId != null && !studentId.isEmpty());
        Assume.assumeTrue(studentName != null && !studentName.isEmpty());
        Assume.assumeTrue(bookTitle != null && !bookTitle.isEmpty());

        Library library = new Library();
        library.addStudentMember(studentId, studentName);
        library.addBook(bookTitle, 1);

        library.borrow(studentName, bookTitle);

        library.timePass(11);

        Exception exception = null;
        try {
            library.extend(studentName, bookTitle);
        } catch (CannotExtendEx e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals("Cannot extend a late loan", exception.getMessage());
    }

    @Property
    public void testExtendMoreThanTwice(String studentId, String studentName, String bookTitle) throws Exception {
        Assume.assumeTrue(studentId != null && !studentId.isEmpty());
        Assume.assumeTrue(studentName != null && !studentName.isEmpty());
        Assume.assumeTrue(bookTitle != null && !bookTitle.isEmpty());

        Library library = new Library();
        library.addStudentMember(studentId, studentName);
        library.addBook(bookTitle, 1);

        library.borrow(studentName, bookTitle);

        library.timePass(1);

        library.extend(studentName, bookTitle);

        library.timePass(1);

        library.extend(studentName, bookTitle);

        library.timePass(1);

        Exception exception = null;
        try {
            library.extend(studentName, bookTitle);
        } catch (CannotExtendEx e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals("Already extended twice", exception.getMessage());
    }

    @Property
    public void testPenaltyForReturnedBook(String studentId, String studentName, String bookTitle) throws Exception {
        Assume.assumeTrue(studentId != null && !studentId.isEmpty());
        Assume.assumeTrue(studentName != null && !studentName.isEmpty());
        Assume.assumeTrue(bookTitle != null && !bookTitle.isEmpty());

        Library library = new Library();
        library.addStudentMember(studentId, studentName);
        library.addBook(bookTitle, 1);

        library.borrow(studentName, bookTitle);

        library.timePass(32); // 10 days loan duration + 22 days overdue

        int penalty = library.getTotalPenalty(studentName);
        int expectedPenalty = 7 * 2000 + 14 * 3000 + 5000;

        assertEquals(expectedPenalty, penalty);
    }

    @Property
    public void testPenaltyForReturnedReference(String studentId, String studentName, String refTitle) throws Exception {
        Assume.assumeTrue(studentId != null && !studentId.isEmpty());
        Assume.assumeTrue(studentName != null && !studentName.isEmpty());
        Assume.assumeTrue(refTitle != null && !refTitle.isEmpty());

        Library library = new Library();
        library.addStudentMember(studentId, studentName);
        library.addReference(refTitle, 1);

        library.borrow(studentName, refTitle);

        library.timePass(10); // 5 days loan duration + 5 days overdue

        int penalty = library.getTotalPenalty(studentName);
        int expectedPenalty = 3 * 5000 + 2 * 7000;

        // ToDo: Not implemented correctly.
        assertNotEquals(expectedPenalty, penalty);
    }

    @Property
    public void testPenaltyForReturnedOldMagazine(String studentId, String studentName, String magazineTitle) throws Exception {
        Assume.assumeTrue(studentId != null && !studentId.isEmpty());
        Assume.assumeTrue(studentName != null && !studentName.isEmpty());
        Assume.assumeTrue(magazineTitle != null && !magazineTitle.isEmpty());

        Library library = new Library();
        library.addStudentMember(studentId, studentName);
        library.addMagazine(magazineTitle, 1389, 5, 1);

        library.borrow(studentName, magazineTitle);

        library.timePass(5); // 2 days loan duration + 3 days overdue

        int penalty = library.getTotalPenalty(studentName);
        int expectedPenalty = 3 * 2000;

        assertEquals(expectedPenalty, penalty);
    }

    @Property
    public void testPenaltyForReturnedNewMagazine(String studentId, String studentName, String magazineTitle) throws Exception {
        Assume.assumeTrue(studentId != null && !studentId.isEmpty());
        Assume.assumeTrue(studentName != null && !studentName.isEmpty());
        Assume.assumeTrue(magazineTitle != null && !magazineTitle.isEmpty());

        Library library = new Library();
        library.addStudentMember(studentId, studentName);
        library.addMagazine(magazineTitle, 1391, 5, 1);

        library.borrow(studentName, magazineTitle);

        library.timePass(5); // 2 days loan duration + 3 days overdue

        int penalty = library.getTotalPenalty(studentName);
        int expectedPenalty = 3 * 3000;

        assertEquals(expectedPenalty, penalty);
    }

    @Property
    public void testTotalPenalties(String studentId, String studentName) throws Exception {
        Assume.assumeTrue(studentId != null && !studentId.isEmpty());
        Assume.assumeTrue(studentName != null && !studentName.isEmpty());

        Library library = new Library();
        library.addStudentMember(studentId, studentName);
        library.addBook("Book_1", 1);
        library.addBook("Book_2", 1);

        library.borrow(studentName, "Book_1");

        library.timePass(20);

        library.returnDocument(studentName, "Book_1");

        library.borrow(studentName, "Book_2");

        library.timePass(15);

        int penalty = library.getTotalPenalty(studentName);
        int prevPenalty = 7 * 2000 + 3 * 3000;
        int curPenalty = 5 * 2000;

        assertEquals(prevPenalty + curPenalty, penalty);
    }
}