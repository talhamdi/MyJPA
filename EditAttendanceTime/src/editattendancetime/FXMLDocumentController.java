package editattendancetime;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TableView<Test3> tableView;
    @FXML
    private TableColumn<Test3, Integer> idColumn;
    @FXML
    private TableColumn<Test3, String> empidColumn;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        EntityManagerFactory objFactory = Persistence.createEntityManagerFactory("EditAttendanceTimePU");      
        Test3JpaController jpa = new Test3JpaController(objFactory);
        
        List<Test3> test3List = jpa.findTest3Entities();

        ObservableList<Test3> Test3obsList = FXCollections.observableArrayList(test3List);
        
        idColumn.setCellValueFactory( new PropertyValueFactory("id") );
        empidColumn.setCellValueFactory( new PropertyValueFactory("empid") );

        tableView.setItems(Test3obsList);
    }    
    
}
