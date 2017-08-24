package editattendancetime;

import static com.oracle.jrockit.jfr.ContentType.Timestamp;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
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
    @FXML
    private TableColumn<Test3, Date> LogDateColumn;
    @FXML
    private TableColumn<Test3, String> timeColumn;
    @FXML
    private TableColumn<Test3, Character> InOutColumn;
    @FXML
    private TextField textID;
    @FXML
    private TextField textEmpID;
    @FXML
    private DatePicker DatePick;
    
    
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       refreshTableView();
        
    }    
    
    public void refreshTableView(){
        EntityManagerFactory objFactory = Persistence.createEntityManagerFactory("EditAttendanceTimePU");      
        Test3JpaController jpa = new Test3JpaController(objFactory);
        
        List<Test3> test3List = jpa.findTest3Entities();

        ObservableList<Test3> Test3obsList = FXCollections.observableArrayList(test3List);
        
        idColumn.setCellValueFactory( new PropertyValueFactory("Id") );
        empidColumn.setCellValueFactory( new PropertyValueFactory("Empid") );
       // LogDateColumn.setCellValueFactory( new PropertyValueFactory("Logdate") );
       
       LogDateColumn.setCellValueFactory(new PropertyValueFactory("Logdate"));
       
      
      

       
       DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

       LogDateColumn.setCellFactory(column -> {
    return new TableCell<Test3, Date>() {
        @Override
        protected void updateItem(Date item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
                setStyle("");
            } else {
                // Format date.
                setText(myDateFormatter.format(item.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));

                // Style all dates in March with a different color.
                LocalDate itemLocaldate = item.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (itemLocaldate.getMonth() == Month.OCTOBER) {
                    setTextFill(Color.CHOCOLATE);
                    setStyle("-fx-background-color: yellow");
                } else {
                    setTextFill(Color.BLACK);
                    setStyle("");
                }
            }
        }
    };
});

       

       


        
     
        
        
        
        timeColumn.setCellValueFactory( new PropertyValueFactory("Time") );
        InOutColumn.setCellValueFactory( new PropertyValueFactory("Inout") );

        tableView.setItems(Test3obsList);
        
        tableView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Test3>() {
            
            
           @Override
            public void onChanged(ListChangeListener.Change<? extends Test3> c) {
               
                
                for (Test3 p : c.getList()) {
                    System.out.println("ID:" + p.getId()  + "  " + "EmpID:" + p.getEmpid() + "  " 
                            + "LogDate:" + p.getLogdate() + "  " + "Time:" + p.getTime() + "  " + "InOut:" + p.getInout() );
                    
                     textID.setText(p.getId().toString());
                     textEmpID.setText( p.getEmpid());
                    //DatePick.setValue(p.getLogdate());
                    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                  //  LocalDate date = p.getLogdate();
                //  Date input = p.getLogdate();
        LocalDate date = p.getLogdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      
        
        
        
                    DatePick.setValue(date);
                  

                 




                    
                }
            }
        });
        
    }
    
}
