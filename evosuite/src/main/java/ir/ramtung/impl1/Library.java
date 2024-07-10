package ir.ramtung.impl1;

import ir.ramtung.sts01.LibraryException;

import java.util.*;

class DuplicateMemberEx extends LibraryException {
    DuplicateMemberEx(String message) {
        super(message);
    }
}

class DuplicateDocumentEx extends LibraryException {
    DuplicateDocumentEx(String message) {
        super(message);
    }
}

class InvalidArgumentEx extends LibraryException {
    InvalidArgumentEx(String message) {
        super(message);
    }
}

class CannotExtendEx extends LibraryException {
    CannotExtendEx(String message) {
        super(message);
    }
}

class CannotBorrowEx extends LibraryException {
    CannotBorrowEx(String message) {
        super(message);
    }
}

abstract class Document {
    Document(String title) throws InvalidArgumentEx {
        if (Objects.equals(title, ""))
            throw new InvalidArgumentEx("Documents with empty title are not allowed");
        this.title = title;
    }

    abstract int loanDuration();

    abstract int penaltyFor(int days);

    boolean isTitled(String documentTitle) {
        return Objects.equals(title, documentTitle);
    }

    String getTitle() {
        return title;
    }

    protected String title;
}

class Book extends Document {

    Book(String title) throws InvalidArgumentEx {
        super(title);
    }

    int loanDuration() {
        return 10;
    }

    int penaltyFor(int days) {
        if (days <= 7)
            return days * 2000;
        else if (days <= 21)
            return 7 * 2000 + (days - 7) * 3000;
        else
            return 7 * 2000 + (21 - 7) * 3000 + (days - 21) * 5000;
    }
}

class Reference extends Document {

    Reference(String title) throws InvalidArgumentEx {
        super(title);
    }

    int loanDuration() {
        return 5;
    }

    int penaltyFor(int days) {
        if (days <= 3)
            return 3 * 5000 + days * 7000;
        else
            return (21 - 7) * 3000 + (days - 21) * 5000;
    }
}

class Magazine extends Document {

    Magazine(String title, int yr, int num) throws InvalidArgumentEx {
        super(title);
        if (yr <= 0)
            throw new InvalidArgumentEx("Magazine with zero or negative publication year");
        if (num <= 0)
            throw new InvalidArgumentEx("Magazine with zero or negative number");
        year = yr;
        number = num;
    }

    int loanDuration() {
        return 2;
    }

    int penaltyFor(int days) {
        if (year < 1390)
            return days * 2000;
        else
            return days * 3000;
    }


    protected int year;
    protected int number;
}

abstract class Member {

    Member(String name) throws InvalidArgumentEx {

        if (Objects.equals(name, ""))
            throw new InvalidArgumentEx("Empty member name not allowed");
        this.name = name;
        prevPenalty = 0;
    }

    abstract int allowedToBorrow();

    boolean isNamed(String memberName) {
        return Objects.equals(name, memberName);
    }

    boolean sameAs(Member member) {
        return member.isNamed(name);
    }

    void penalize(int penalty) {
        prevPenalty += penalty;
    }

    int getPrevPenalty() {
        return prevPenalty;
    }


    protected String name;
    protected int prevPenalty;
}

class Student extends Member {

    Student(String id, String name) throws InvalidArgumentEx {
        super(name);
        if (Objects.equals(id, ""))
            throw new InvalidArgumentEx("Empty student ID is not allowed");
        this.id = id;
    }

    int allowedToBorrow() {
        return 2;
    }


    protected String id;
}

class Professor extends Member {

    Professor(String name) throws InvalidArgumentEx {
        super(name);
    }

    int allowedToBorrow() {
        return 5;
    }
}

class Loan {

    Loan(Member mem, Document doc, int date) throws InvalidArgumentEx {
        if (mem == null || doc == null)
            throw new InvalidArgumentEx("Invalid argument to loan constructor");
        member = mem;
        document = doc;
        timesExtended = 0;
        borrowedOn = date;
        dueDate = date + document.loanDuration();
    }

    boolean isFor(Document doc) {
        return document == doc;
    }

    boolean isBy(Member mem) {
        return mem == member;
    }

    void extend(int now) throws CannotExtendEx {
        if (borrowedOn == now)
            throw new CannotExtendEx("Cannot extend on the same day borrowed");
        if (now > dueDate)
            throw new CannotExtendEx("Cannot extend a late loan");
        if (timesExtended >= 2)
            throw new CannotExtendEx("Already extended twice");
        dueDate += document.loanDuration();
        timesExtended++;
    }

    void return_(int now) {
        if (now > dueDate)
            member.penalize(getPenalty(now));
    }

    int getPenalty(int now) {
        if (now <= dueDate)
            return 0;
        return document.penaltyFor(now - dueDate);
    }


    private Document document;
    private Member member;
    private int borrowedOn;
    private int dueDate;
    private int timesExtended;
}

public class Library implements ir.ramtung.sts01.ILibrary {

    @Override
    public void addStudentMember(String studentId, String studentName) throws InvalidArgumentEx, DuplicateMemberEx {
        addMember(new Student(studentId, studentName));
    }

    @Override
    public void addProfMember(String profName) throws InvalidArgumentEx, DuplicateMemberEx {
        addMember(new Professor(profName));
    }

    @Override
    public void addBook(String bookTitle, int copies) throws InvalidArgumentEx, DuplicateDocumentEx {
        addDocument(new Book(bookTitle), copies);
    }

    @Override
    public void addMagazine(String magazineTitle, int year, int number, int copies) throws InvalidArgumentEx, DuplicateDocumentEx {
        addDocument(new Magazine(magazineTitle, year, number), copies);
    }

    @Override
    public void addReference(String referenceTitle, int copies) throws InvalidArgumentEx, DuplicateDocumentEx {
        addDocument(new Reference(referenceTitle), copies);
    }

    @Override
    public void borrow(String memberName, String documentTitle) throws CannotBorrowEx, InvalidArgumentEx {
        Document document = findDocument(documentTitle);
        if (document == null)
            throw new InvalidArgumentEx("Cannot find document to borrow");
        Member member = findMember(memberName);
        if (member == null)
            throw new InvalidArgumentEx("Cannot find member to borrow");
        if (isAvailable(document)) {
            if (canBorrowMore(member)) {
                loans.add(new Loan(member, document, now));
                documents.put(document, documents.get(document) - 1);
            } else {
                throw new CannotBorrowEx("Cannot borrow more documents");
            }
        } else {
            throw new CannotBorrowEx("Document is not available to borrow");
        }
    }

    @Override
    public List<String> availableTitles() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<Document, Integer> elem : documents.entrySet()) {
            if (elem.getValue() > 0)
                result.add(elem.getKey().getTitle());
        }
        return result;

    }

    @Override
    public void extend(String memberName, String documentTitle) throws InvalidArgumentEx, CannotExtendEx {
        Loan loan = findLoan(memberName, documentTitle);
        if (loan == null)
            throw new InvalidArgumentEx("The document is not in member's loan");
        loan.extend(now);
    }

    @Override
    public void returnDocument(String memberName, String documentTitle) throws InvalidArgumentEx {
        Loan loan = findLoan(memberName, documentTitle);
        if (loan == null)
            throw new InvalidArgumentEx("The document is not in member's loan");
        loan.return_(now);
        Document document = findDocument(documentTitle);
        documents.put(document, documents.get(document) + 1);
        deleteLoan(loan);
    }

    @Override
    public int getTotalPenalty(String memberName) throws InvalidArgumentEx {
        Member member = findMember(memberName);
        if (member == null)
            throw new InvalidArgumentEx("Cannot find member");
        return member.getPrevPenalty() + currentPenalty(member);
    }

    @Override
    public void timePass(int days) throws InvalidArgumentEx {
        if (days < 0)
            throw new InvalidArgumentEx("Cannot go back in time");
        now += days;
    }


    private List<Member> members  =  new ArrayList<>();
    private Map<Document, Integer> documents = new HashMap<>();
    private List<Loan> loans = new ArrayList<>();
    private int now = 0;

    private int currentPenalty(Member member) {
        int curPenalty = 0;
        for (Loan loan : loans) {
            if (loan.isBy(member))
                curPenalty += loan.getPenalty(now);
        }
        return curPenalty;
    }

    private void deleteLoan(Loan loan) {
        loans.remove(loan);
    }

    private Loan findLoan(String memberName, String documentName) {
        for (Loan loan : loans)
            if (loan.isBy(findMember(memberName)) && loan.isFor(findDocument(documentName))) {
                return loan;
            }
        return null;
    }

    private Member findMember(String memberName) {
        for (Member member : members)
            if (member.isNamed(memberName))
                return member;
        return null;
    }

    private Document findDocument(String documentTitle) {
        for (Map.Entry<Document, Integer> element : documents.entrySet())
            if (element.getKey().isTitled(documentTitle))
                return element.getKey();
        return null;
    }

    private boolean canBorrowMore(Member member) {
        int allowedToBorrow = member.allowedToBorrow();
        for (Loan loan : loans) {
            if (loan.isBy(member))
                allowedToBorrow--;
            if (allowedToBorrow == 0)
                return false;
        }
        return allowedToBorrow > 0;
    }

    private boolean isAvailable(Document document) {
        return documents.get(document) > 0;
    }

    private void addDocument(Document document, int copies) throws DuplicateDocumentEx, InvalidArgumentEx {
        if (copies <= 0)
            throw new InvalidArgumentEx("Negative or zero copies of a document cannot be added");
        if (documents.containsKey(document) || findDocument(document.getTitle()) != null)
            throw new DuplicateDocumentEx("Document with the same title exists");
        documents.put(document, copies);
    }

    private void addMember(Member member) throws DuplicateMemberEx {
        for (Member mem : members)
            if (mem.sameAs(member))
                throw new DuplicateMemberEx("Member with the same name exists");
        members.add(member);
    }
}


