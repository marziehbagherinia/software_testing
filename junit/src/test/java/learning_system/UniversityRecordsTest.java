package learning_system;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;

public class UniversityRecordsTest {

    @Test
    public void testNoPrerequisites() {
        UniversityRecords.Course course = new UniversityRecords.Course(1, List.of());

        List<UniversityRecords.Record> records = List.of();

        assertTrue(UniversityRecords.hasPassedPre(records, course));
    }

    @Test
    public void testPrerequisitePassed() {
        UniversityRecords.Course course = new UniversityRecords.Course(2, List.of(101));

        List<UniversityRecords.Record> records = List.of(
                new UniversityRecords.Record(1, 101, 11.5, false)
        );

        assertTrue(UniversityRecords.hasPassedPre(records, course));
    }

    @Test
    public void testMehmanPrerequisitePassed() {
        UniversityRecords.Course course = new UniversityRecords.Course(3, List.of(102));

        List<UniversityRecords.Record> records = List.of(
                new UniversityRecords.Record(1, 102, 13.5, true)
        );

        assertTrue(UniversityRecords.hasPassedPre(records, course));
    }

    @Test
    public void testAllPrerequisitesPassed() {
        UniversityRecords.Course course = new UniversityRecords.Course(4, Arrays.asList(103, 104));

        List<UniversityRecords.Record> records = Arrays.asList(
                new UniversityRecords.Record(1, 103, 12.0, false),
                new UniversityRecords.Record(1, 104, 10.5, false)
        );

        assertTrue(UniversityRecords.hasPassedPre(records, course));
    }

    @Test
    public void testAllMehmanPrerequisitesPassed() {
        UniversityRecords.Course course = new UniversityRecords.Course(5, Arrays.asList(105, 106));

        List<UniversityRecords.Record> records = Arrays.asList(
                new UniversityRecords.Record(1, 105, 12.0, true),
                new UniversityRecords.Record(1, 106, 13.5, true)
        );

        assertTrue(UniversityRecords.hasPassedPre(records, course));
    }

    @Test
    public void testAllMixedPrerequisitesPassed() {
        UniversityRecords.Course course = new UniversityRecords.Course(6, Arrays.asList(107, 108));

        List<UniversityRecords.Record> records = Arrays.asList(
                new UniversityRecords.Record(1, 107, 11.0, false),
                new UniversityRecords.Record(1, 108, 14, true)
        );

        assertTrue(UniversityRecords.hasPassedPre(records, course));
    }

    @Test
    public void testPrerequisiteNotPassed() {
        UniversityRecords.Course course = new UniversityRecords.Course(7, List.of(109));

        List<UniversityRecords.Record> records = List.of(
                new UniversityRecords.Record(1, 109, 9.0, false)
        );

        assertFalse(UniversityRecords.hasPassedPre(records, course));
    }

    @Test
    public void testMehmanPrerequisiteNotPassed() {
        UniversityRecords.Course course = new UniversityRecords.Course(8, List.of(110));

        List<UniversityRecords.Record> records = List.of(
                new UniversityRecords.Record(1, 110, 11.0, true)
        );

        assertFalse(UniversityRecords.hasPassedPre(records, course));
    }

    @Test
    public void testAllPrerequisitesNotPassed() {
        UniversityRecords.Course course = new UniversityRecords.Course(9, Arrays.asList(111, 112));

        List<UniversityRecords.Record> records = Arrays.asList(
                new UniversityRecords.Record(1, 111, 9.0, false),
                new UniversityRecords.Record(1, 112, 9.5, false)
        );

        assertFalse(UniversityRecords.hasPassedPre(records, course));
    }

    @Test
    public void testAllMehmanPrerequisitesNotPassed() {
        UniversityRecords.Course course = new UniversityRecords.Course(10, Arrays.asList(113, 114));

        List<UniversityRecords.Record> records = Arrays.asList(
                new UniversityRecords.Record(1, 113, 9.0, true),
                new UniversityRecords.Record(1, 114, 11.5, true)
        );

        assertFalse(UniversityRecords.hasPassedPre(records, course));
    }

    @Test
    public void testAllMixedPrerequisitesNotPassed() {
        UniversityRecords.Course course = new UniversityRecords.Course(11, Arrays.asList(115, 116));

        List<UniversityRecords.Record> records = Arrays.asList(
                new UniversityRecords.Record(1, 115, 9.0, false),
                new UniversityRecords.Record(1, 116, 11.5, true)
        );

        assertFalse(UniversityRecords.hasPassedPre(records, course));
    }

    @Test
    public void testNotMehmanPrerequisitePassedAndMehmanPrerequisiteNotPassed() {
        UniversityRecords.Course course = new UniversityRecords.Course(12, Arrays.asList(117, 118));

        List<UniversityRecords.Record> records = Arrays.asList(
                new UniversityRecords.Record(1, 117, 10.5, false),
                new UniversityRecords.Record(1, 118, 11.5, true)
        );

        assertFalse(UniversityRecords.hasPassedPre(records, course));
    }

    @Test
    public void testNotMehmanPrerequisiteNotPassedAndMehmanPrerequisitePassed() {
        UniversityRecords.Course course = new UniversityRecords.Course(13, Arrays.asList(119, 120));

        List<UniversityRecords.Record> records = Arrays.asList(
                new UniversityRecords.Record(1, 119, 9.5, false),
                new UniversityRecords.Record(1, 120, 12.5, true)
        );

        assertFalse(UniversityRecords.hasPassedPre(records, course));
    }

    @Test
    public void testBoundaryGrade() {
        UniversityRecords.Course course = new UniversityRecords.Course(14, List.of(121));

        List<UniversityRecords.Record> records = List.of(
                new UniversityRecords.Record(1, 121, 10.0, false)  // Exactly on the boundary
        );

        assertTrue(UniversityRecords.hasPassedPre(records, course));
    }

    @Test
    public void testMehmanStudentBoundaryGrade() {
        UniversityRecords.Course course = new UniversityRecords.Course(15, List.of(122));

        List<UniversityRecords.Record> records = List.of(
                new UniversityRecords.Record(1, 122, 12.0, true)  // Exactly on the boundary for mehman
        );

        assertTrue(UniversityRecords.hasPassedPre(records, course));
    }
}
