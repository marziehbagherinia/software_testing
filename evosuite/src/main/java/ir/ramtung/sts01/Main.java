package ir.ramtung.sts01;

public class Main {
    public static void main(String[] args) {
        testLibrary(new ir.ramtung.impl1.Library());
//        testLibrary(new ir.ramtung.impl2.Library());
    }

    private static void testLibrary(ILibrary lib) {
        try {
            lib.addStudentMember("810199999", "Negar");
            lib.addStudentMember("810198888", "Naser");
            lib.addProfMember("Dr. Negargar");
            lib.addProfMember("Dr. Naseri");
            lib.addBook("Lean Startup", 1);
            lib.addMagazine("Today News, vol. 38, no.3", 1395, 3, 1);
            lib.addReference("Cambridge Dictionary", 3);
            lib.borrow("Negar", "Lean Startup");
            System.out.println(lib.availableTitles().size());
            lib.timePass(5);
            lib.extend("Negar", "Lean Startup");
            lib.timePass(50);
            lib.returnDocument("Negar", "Lean Startup");
            System.out.println(lib.getTotalPenalty("Negar"));
        } catch (LibraryException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
