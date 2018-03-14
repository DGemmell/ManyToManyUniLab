package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lessons")
public class Lesson {

    private int id;
    private String title;
    private int classroom;
    private List<Student> students;
    private Course course;

    public Lesson() {
    }

    public Lesson(String title, int classroom, List<Student> students, Course course) {
        this.title = title;
        this.classroom = classroom;
        this.students = new ArrayList<Student>();
        this.course = course;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name ="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name ="classroom")
    public int getClassroom() {
        return classroom;
    }

    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }

    @ManyToMany(mappedBy = "lessons", fetch = FetchType.EAGER)
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @ManyToOne
    @JoinColumn(name = "course_id", nullable=false)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void addStudent(Student student){
        this.students.add(student);
    }
}
