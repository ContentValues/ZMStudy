package xdroid.mwee.com.rxjavatest.design;

/**
 * Created by zhangmin on 2018/12/4.
 */

/**
 * MVC设计模式
 */
public class D1_3 {


    public static void main(String[] args) {

        Student student = new Student("ZHANGMIN","801");
        StudentView studentView = new StudentView();
        StudentController controller = new StudentController(studentView,student);
        controller.update();

        student.setName("李四");
        controller.update();


    }


    static class StudentController {

        private StudentView studentView;
        private Student student;

        public StudentView getStudentView() {
            return studentView;
        }

        public void setStudentView(StudentView studentView) {
            this.studentView = studentView;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        public StudentController(StudentView studentView, Student student) {
            this.studentView = studentView;
            this.student = student;
        }

        public void update() {
            studentView.printStudentDetails(student.getName(), student.getStudentRollNo());
        }
    }


    static class StudentView {
        public void printStudentDetails(String studentName, String studentRollNo) {
            System.out.println("Student: ");
            System.out.println("Name: " + studentName);
            System.out.println("Roll No: " + studentRollNo);
        }
    }


    static class Student {

        public Student(String name, String studentRollNo) {
            this.name = name;
            this.studentRollNo = studentRollNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStudentRollNo() {
            return studentRollNo;
        }

        public void setStudentRollNo(String studentRollNo) {
            this.studentRollNo = studentRollNo;
        }

        private String name;

        private String studentRollNo;

    }
}
