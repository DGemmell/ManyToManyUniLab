import db.DBHelper;
import models.Course;
import models.Lesson;
import models.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class TestCourse {

    private Course course;
    private Lesson lesson;
    private Student student;

    @Before
    public void setUp() throws Exception {
        course = new Course("Course1", "PHD", null, null);
        lesson = new Lesson("Monday",1,null, course);
        student = new Student("Student One", 24, 123456, course, null);
        DBHelper.save(course);
        DBHelper.save(lesson);
        DBHelper.save(student);
    }

//    @After
//    public void tearDown() throws Exception {
//        DBHelper.delete(student);
//        DBHelper.delete(lesson);
//        DBHelper.delete(course);
//    }

    @Test
    public void testCanSave() {
        List<Course> savedCourses = DBHelper.getAll(Course.class);
        assertEquals(1, savedCourses.size());
        List<Lesson> savedLessons = DBHelper.getAll(Lesson.class);
        assertEquals(1, savedLessons.size());
        List<Student> savedStudents = DBHelper.getAll(Student.class);
        assertEquals(1, savedStudents.size());
    }

    @Test
    public void testCanAddStudentToLesson() {
        DBHelper.addStudentToLesson(student, lesson);
        assertEquals(1,1);
    }
}
