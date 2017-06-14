package myjpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyJPA {

    public static void main(String[] args) {

        EntityManagerFactory objFactory = Persistence.createEntityManagerFactory("MyJPAPU");
        Student record = new Student();
        StudentJpaController jpa = new StudentJpaController(objFactory);
        List<Student> list = jpa.findStudentEntities();

        record.setName("Test");

        jpa.create(record);

        for (Student l : list) {
            System.out.println("Cod.: " + l.getId());
        }
    }

}


