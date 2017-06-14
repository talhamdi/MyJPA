package myjpa;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyJPA {

    public static void main(String[] args) throws Exception {

        EntityManagerFactory objFactory = Persistence.createEntityManagerFactory("MyJPAPU");
        Student student = new Student();
        StudentJpaController jpa = new StudentJpaController(objFactory);
        
        student.setId(2);
        student.setName("Yaser");
        student.setAge(25);
        student.setJoinDate(new Date(2014, 05, 05));      

        jpa.edit(student);

       List<Student> studentList = jpa.findStudentEntities();

        for (Student list : studentList) {
            System.out.println("Student ID: " + list.getId() + " Student Name: " + list.getName() + " Age: " + list.getAge() + " Join Date: " + list.getJoinDate() );
        }
    }

}


