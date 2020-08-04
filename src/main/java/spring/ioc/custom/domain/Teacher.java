package spring.ioc.custom.domain;

public class Teacher {
    private String name;
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher() {
    }

    public Teacher(String name, Student student) {
        this.name = name;
        this.student = student;
    }
}
