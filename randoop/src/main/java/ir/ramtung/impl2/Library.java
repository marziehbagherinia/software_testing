package ir.ramtung.impl2;

import ir.ramtung.sts01.ILibrary;
import ir.ramtung.sts01.LibraryException;

import java.util.ArrayList;
import java.util.List;

abstract class Document {
    public Document(String docTitle, int docCopies) {
        title = docTitle;
        copies = docCopies;
    }

    public String getTitle() {
        return title;
    }

    public int getCopies() {
        return copies;
    }

    public int getDay() {
        return day;
    }

    public void barrowBook() {
        copies--;
    }

    public void returnBook() {
        copies++;
    }

    public abstract int calculatePenalty(int dayPenalty);

    protected String title;
    protected int copies;
    protected int day;
}

class Book extends Document {
    public Book(String bookTitle, int bookCopies) {
        super(bookTitle, bookCopies);
        day = 10;
    }

    public int calculatePenalty(int dayPenalty) {
        dayPenalty = dayPenalty * -1;
        if (dayPenalty <= 7)
            return 2000;
        if (dayPenalty <= 21)
            return 3000;
        return 2000;
    }

}

class Reference extends Document {
    Reference(String referenceTitle, int referenceCopies) {
        super(referenceTitle, referenceCopies);
        day = 5;
    }

    public int calculatePenalty(int dayPenalty) {
        dayPenalty = dayPenalty * -1;
        if (dayPenalty <= 3)
            return 5000;
        return 7000;
    }

}

class Magazine extends Document {
    public Magazine(String magTitle, int magYear, int magNumber, int magCopies) {
        super(magTitle, magCopies);
        year = magYear;
        number = magNumber;
        day = 2;
    }

    public int calculatePenalty(int dayPenalty) {
        if (year < 1390)
            return 2000;
        return 3000;
    }

    protected int year;
    protected int number;
}

abstract class Person {
    public Person(String personName) {

        name = personName;
        penalty = 0;
    }

    public void borrow(Document doc) {
        int day;
        docsBorrow.add(doc);
        day = doc.getDay();
        dayBorrow.add(day);
        canExtend.add(true);
    }

    public String getName() {
        return name;
    }

    public abstract boolean canBorrow();

    public void extend(String bookName) throws LibraryException {
        for (int i = 0; i < docsBorrow.size(); i++) {
            if (docsBorrow.get(i).getTitle().equals(bookName)) {
                if (canExtend.get(i)) {
                    dayBorrow.set(i, dayBorrow.get(i) + docsBorrow.get(i).getDay());
                    canExtend.set(i, false);
                } else {
                    throw new LibraryException("cant extend");
                }
            } else {
                throw new LibraryException("cant extend");
            }
        }
    }

    public void returnDoc(String bookName) {
        for (int i = 0; i < docsBorrow.size(); i++) {
            if (docsBorrow.get(i).getTitle().equals(bookName)) {
                docsBorrow.get(i).returnBook();
                docsBorrow.remove(i);
                dayBorrow.remove(i);
                canExtend.remove(i);
                break;
            }
        }
    }

    public int getPenalty() {
        return penalty;
    }

    public void timePass() {
        for (int i = 0; i < dayBorrow.size(); i++) {
            dayBorrow.set(i, dayBorrow.get(i) - 1);
            if (dayBorrow.get(i) < 0) {
                penalty = penalty + docsBorrow.get(i).calculatePenalty(dayBorrow.get(i));
            }
        }
    }

    protected String name;
    protected int penalty;
    protected List<Document> docsBorrow = new ArrayList<>();
    protected List<Integer> dayBorrow = new ArrayList<>();
    protected List<Boolean> canExtend = new ArrayList<>();
}

class Student extends Person {
    public Student(String studentId, String studentName) {
        super(studentName);
        id = studentId;
    }

    public boolean canBorrow() {
        return docsBorrow.size() < 2;
    }

    protected String id;
}

class Prof extends Person {

    public Prof(String profName) {
        super(profName);
    }

    public boolean canBorrow() {
        return docsBorrow.size() < 5;
    }
}

public class Library implements ILibrary {
    public void addStudentMember(String studentId, String studentName) throws LibraryException {
            if (studentId.equals(""))
                throw new LibraryException("student id is empty");

            if (studentName.equals(""))
                throw new LibraryException("student name is empty");
            for (Person person : persons) {
                if (person.getName().equals(studentName))
                    throw new LibraryException("the member has already added");
            }
            Student student = new Student(studentId, studentName);
            persons.add(student);
    }

    public void addProfMember(String profName) throws LibraryException {
            if (profName.equals(" "))
                throw new LibraryException("prof name is empty");
            for (Person person : persons) {
                if (person.getName().equals(profName))
                    throw new LibraryException("the member has already added");
            }
            Prof prof = new Prof(profName);
            persons.add(prof);
    }

    public void addBook(String bookTitle, int copies) throws LibraryException {
            if (bookTitle.equals(" "))
                throw new LibraryException("book name is empty");
            for (Document doc : docs) {
                if (bookTitle.equals(doc.getTitle()))
                    throw new LibraryException("the book has already added");
            }
            Book book = new Book(bookTitle, copies);
            docs.add(book);
    }

    public void addMagazine(String magazineTitle, int year, int number, int copies) throws LibraryException {
            if (magazineTitle.equals(" "))
                throw new LibraryException("magazine name is empty");
            for (Document doc : docs) {
                if (magazineTitle.equals(doc.getTitle()))
                    throw new LibraryException("the magazine has already added");
            }
            if (year <= 0)
                throw new LibraryException("magazine`s year is incorrect");
            if (number <= 0)
                throw new LibraryException("magazine`s number is incorrect");
            Magazine magazine = new Magazine(magazineTitle, year, number, copies);
            docs.add(magazine);
    }

    public void addReference(String referenceTitle, int copies) throws LibraryException {
            if (referenceTitle.equals(" "))
                throw new LibraryException("reference name is empty");
            for (Document doc : docs) {
                if (referenceTitle.equals(doc.getTitle()))
                    throw new LibraryException("the reference has already added");
            }
            Reference reference = new Reference(referenceTitle, copies);
            docs.add(reference);
    }

    public void borrow(String memberName, String documentTitle) throws LibraryException {
        int iBook = 0;
            for (int i = 0; i < docs.size(); i++) {
                if (docs.get(i).getTitle().equals(documentTitle)) {
                    if (docs.get(i).getCopies() <= 0)
                        throw new LibraryException("this book doesnt exist");
                    iBook = i;
                    break;
                }
            }
            for (Person person : persons) {
                if (person.getName().equals(memberName)) {
                    if (!person.canBorrow())
                        throw new LibraryException("the member cant borrow any more");
                    else {
                        person.borrow(docs.get(iBook));
                        docs.get(iBook).barrowBook();
                    }
                }
            }
    }

    public void extend(String memberName, String documentTitle) throws LibraryException {
            for (Person person : persons) {
                if (person.getName().equals(memberName)) {
                    person.extend(documentTitle);
                }
            }
    }

    public void returnDocument(String memberName, String documentTitle) {
        for (Person person : persons) {
            if (person.getName().equals(memberName))
                person.returnDoc(documentTitle);
        }
    }

    public int getTotalPenalty(String memberName) {
        for (Person person : persons) {
            if (person.getName().equals(memberName))
                return person.getPenalty();
        }
        return 0;
    }

    public List<String> availableTitles() {
        List<String> available_title = new ArrayList<>();
        for (Document doc : docs) {
            if (doc.getCopies() > 0)
                available_title.add(doc.getTitle());
        }
        return available_title;
    }

    public void timePass(int days) throws LibraryException {
            if (days < 0)
                throw new LibraryException("days cant be negative");
            for (int i = 0; i < days; i++) {
                for (Person person : persons) {
                    person.timePass();
                }
            }
    }

    private final List<Person> persons = new ArrayList<>();
    private final List<Document> docs = new ArrayList<>();
}

