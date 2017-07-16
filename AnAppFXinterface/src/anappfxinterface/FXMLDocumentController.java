/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anappfxinterface;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author t.aljehani
 */
public class FXMLDocumentController implements Initializable {

    ObservableList<String> hourseList = FXCollections.observableArrayList("00", "01", "02");
    ObservableList<String> minutesList = FXCollections.observableArrayList("00", "01", "02");

    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField textField1;
    @FXML
    private ComboBox combHours;
    @FXML
    private ComboBox combMinutes;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Student, String> getName2;
    @FXML
    private TableColumn<Student, Date> birthdayColumn;

    DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    @FXML
    private TableView<Student> tableView2;
    @FXML
    private TableColumn<Student, String> getName3;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        /*
        //EntityManager manager = objFactory.createEntityManager();
        EntityManagerFactory objFactory = Persistence.createEntityManagerFactory("AnAppFXinterfacePU");
        //EntityManager manager = objFactory.createEntityManager();
        // Student student = new Student();
        StudentJpaController jpa = new StudentJpaController(objFactory);
         Student student = new Student();
         //List<Student> studentList = jpa.findStudentEntities();
         
         
         student.setId(14);
         student.setName("Masri");
        try {
            // jpa.create(student);
            jpa.edit(student);
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        //tableView.getS
     //   Person person = taview.getSelectionModel().getSelectedItem();
        Student student = (Student) tableView.getSelectionModel().getSelectedItem();
        System.out.println(student.getName());    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //combHours.setValue("Hourse");
        
        combHours.setItems(hourseList);

        //combMinutes.setValue("Minutes");
        combMinutes.setItems(minutesList);

        ////////////////////
        EntityManagerFactory objFactory = Persistence.createEntityManagerFactory("AnAppFXinterfacePU");
        //EntityManager manager = objFactory.createEntityManager();
        // Student student = new Student();
        StudentJpaController jpa = new StudentJpaController(objFactory);
        List<Student> studentList = jpa.findStudentEntities();

        ObservableList<Student> proList;

        proList = FXCollections.observableArrayList(studentList);

        tableView.setItems(proList);
        tableView2.setItems(proList);
        tableView.getColumns().addAll(FXMLDocumentController.getId(),
                FXMLDocumentController.getName(),
                FXMLDocumentController.getAge(),
                FXMLDocumentController.getJoinDate());

        for (Student l : studentList) {
            System.out.println("Name.: " + l.getName());
        }

        getName2.setCellValueFactory( new PropertyValueFactory("name") );
        getName3.setCellValueFactory( new PropertyValueFactory("name") );
        
        tableView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Student>() {
            
            
           @Override
            public void onChanged(ListChangeListener.Change<? extends Student> c) {
               
                
                for (Student p : c.getList()) {
                    System.out.println(p.getId().toString() + "  " + p.getName() );
                }
            }
        });
        // TODO
    }

    public static TableColumn<Student, Integer> getId() {
        TableColumn<Student, Integer> id = new TableColumn<>("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.setMinWidth(50);
        id.setText("id");
        return id;
    }

    public static TableColumn<Student, String> getName() {
        TableColumn<Student, String> name = new TableColumn<>("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setMinWidth(100);
        name.setText("name");
        return name;
    }

    public static TableColumn<Student, Integer> getAge() {
        TableColumn<Student, Integer> age = new TableColumn<>("age");
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        age.setMinWidth(100);
        age.setText("age");
        return age;
    }

    public static TableColumn<Student, Date > getJoinDate() {
        TableColumn<Student, Date> joindate = new TableColumn<>("JoinDate");
        joindate.setCellValueFactory(new PropertyValueFactory<>("JoinDate"));
        joindate.setMinWidth(150);
        joindate.setText("JoinDate");
        return joindate;
    }

    @FXML
    private void combHoursOnAction(ActionEvent event) {
        //  System.out.println(combHours.getValue());
        if (combHours.getValue() != null && combMinutes.getValue() != null) {
            textField1.setText((String) combHours.getValue() + ":" + (String) combMinutes.getValue());
        }
    }

    @FXML
    private void combMinutesOnAction(ActionEvent event) {
        if (combHours.getValue() != null && combMinutes.getValue() != null) {
            textField1.setText((String) combHours.getValue() + ":" + (String) combMinutes.getValue());
        }
    }

    @FXML
    private void handleTabelVieAction(SortEvent event) {
        Student student = (Student) tableView.getSelectionModel().getSelectedItem();
        System.out.println(student.getName());   
    }
    
     
    
    
    //////////
    
    
    
    //////////
    

}
