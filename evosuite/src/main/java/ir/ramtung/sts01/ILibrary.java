package ir.ramtung.sts01;

import java.util.List;

public interface ILibrary {
    void addStudentMember(String studentId, String studentName) throws LibraryException;

    void addProfMember(String profName) throws LibraryException;

    void addBook(String bookTitle, int copies) throws LibraryException;

    void addMagazine(String magazineTitle, int year, int number, int copies) throws LibraryException;

    void addReference(String referenceTitle, int copies) throws LibraryException;

    void borrow(String memberName, String documentTitle) throws LibraryException;

    List<String> availableTitles() throws LibraryException;

    void extend(String memberName, String documentTitle) throws LibraryException;

    void returnDocument(String memberName, String documentTitle) throws LibraryException;

    int getTotalPenalty(String memberName) throws LibraryException;

    void timePass(int days) throws LibraryException;
}
