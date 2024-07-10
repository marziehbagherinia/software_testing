package learning_system;

import java.util.List;

public class UniversityRecords {

    static class Course {
        int id;
        List<Integer> pre;  // IDs of prerequisite courses

        public Course(int id, List<Integer> pre) {
            this.id = id;
            this.pre = pre;
        }
    }

    static class Record {
        int termId;
        int courseId;
        double grade;
        boolean isMehman;  // True if the student was a guest

        // Adding a constructor to initialize the fields
        public Record(int termId, int courseId, double grade, boolean isMehman) {
            this.termId = termId;
            this.courseId = courseId;
            this.grade = grade;
            this.isMehman = isMehman;
        }
    }

    public static boolean hasPassedPre( List<Record> rec, Course course ) {
        for (int i = 0; i < course.pre.size(); i++) {
            boolean prePassed = false;
            for (Record record : rec) {
                if (record.courseId == course.pre.get(i)) {
                    if (record.grade >= 10 && (!record.isMehman || record.grade >= 12)) {
                        prePassed = true;
                        break;
                    }
                }
            }
            if (!prePassed) {
                return false;
            }
        }
        return true;
    }
}
