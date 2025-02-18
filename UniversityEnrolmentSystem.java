class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class Course {
    private String name;
    private int maxEnrollment;
    private int enrolledStudents;
    private String prerequisite;

    public Course(String name, int maxEnrollment, String prerequisite) {
        this.name = name;
        this.maxEnrollment = maxEnrollment;
        this.enrolledStudents = 0;
        this.prerequisite = prerequisite;
    }

    public void enrollStudent(StudentInfo student) throws CourseFullException, PrerequisiteNotMetException {
        if (enrolledStudents >= maxEnrollment) {
            throw new CourseFullException("Course is full.");
        }
        if (prerequisite != null && !student.hasCompleted(prerequisite)) {
            throw new PrerequisiteNotMetException("Complete " + prerequisite + " before enrolling in " + name);
        }
        enrolledStudents++;
        System.out.println("Enrolled in " + name);
    }
}

class StudentInfo {
    private String name;
    private String completedCourse;

    public StudentInfo(String name, String completedCourse) {
        this.name = name;
        this.completedCourse = completedCourse;
    }

    public boolean hasCompleted(String course) {
        return completedCourse != null && completedCourse.equals(course);
    }
}

public class Exp3hard {
    public static void main(String[] args) {
        Course advancedJava = new Course("Adv Java", 2, "Core Java");
        StudentInfo student = new StudentInfo("Ammar", null);

        try {
            advancedJava.enrollStudent(student);
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
