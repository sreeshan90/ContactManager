import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

/**
 *
 * @author sreesha.n
 */
/**
 *
 * This class is the front controller which handles all the input and output to
 * and from the JavaFX window
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Pane addPane;
    @FXML
    private Pane modifyPane;
    @FXML
    private Pane deletePane;
    @FXML
    private Label welcomeNote;
    @FXML
    private TextField zipCodeInput;
    @FXML
    private TextField stateInput;
    @FXML
    private TextField addr1Input;
    @FXML
    private TextField addr2Input;
    @FXML
    private TextField cityInput;
    @FXML
    private TextField phoneNumberInput;
    @FXML
    private TextField lastNameInput;
    @FXML
    private TextField middleInitialInput;
    @FXML
    private TextField firstNameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField countryInput ;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn firstNameTable;
    @FXML
    private TableColumn lNameTable;
    @FXML
    private TableColumn phoneNumTable;
    @FXML
    private TableColumn idTable;
    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Label successMessage;

    @FXML
    private Label successMessageModify;

    @FXML
    private Label successMessageDelete;

    @FXML
    private Button deleteConfirmButton;
    @FXML
    private TextField zipCodeModify;
    @FXML
    private TextField stateModify;
    @FXML
    private TextField addr1Modify;
    @FXML
    private TextField addr2Modify;
    @FXML
    private TextField cityModify;
    @FXML
    private TextField phoneNumberModify;
    @FXML
    private TextField lastNameModify;
    @FXML
    private TextField middleInitialModify;
    @FXML
    private TextField firstNameModify;
     @FXML
    private TextField emailModify;
      @FXML
    private TextField countryModify;
    @FXML
    private TextField idDelete;
    @FXML
    private TextField idModify;
    @FXML
    private RadioButton femaleRadioModify;
    @FXML
    private RadioButton maleRadioModify;
    @FXML
    private Button saveButton;
    @FXML
    private Label fNameDelValue;
    @FXML
    private Label initialDelValue;
    @FXML
    private Label lNameDelValue;
    @FXML
    private Label genderDelValue;
    @FXML
    private Label phoneDelValue;
    @FXML
    private Label addr1DelValue;
    @FXML
    private Label addr2DelValue;
    @FXML
    private Label cityDelValue;
    @FXML
    private Label countryDelValue;
    @FXML
    private Label emailDelValue;
   
    @FXML
    private Label stateDelValue;
    @FXML
    private Label zipCodeDelValue;
    @FXML
    private Label nameErrorInlineMessage;
    @FXML
    private Label nameErrorInlineMessageModify;
    @FXML
    private Text requiredFielAdd;
    @FXML
    private Text requiredFieldModify;

    private Pattern pattern;
    private Matcher matcher;
 
	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        
    private Person p = null;
    private int mode = 0; //1 - add; 2-modify; 3-delete

    private FadeTransition fadeIn = new FadeTransition(Duration.millis(6000));
    private final ObservableList<Person> data = FXCollections.observableArrayList();
    //male female radio buttons
    final ToggleGroup group = new ToggleGroup();

    /**
     *
     * This method is called when the user clicks on the "Add Row" button
     */
    @FXML
    private void addAction(ActionEvent event) {
        System.out.println("You clicked add");
        successMessageDelete.setVisible(false);
        nameErrorInlineMessage.setVisible(false);
        resetAction(new ActionEvent());
        addPane.setVisible(true);
        modifyPane.setVisible(false);
        deletePane.setVisible(false);
        welcomeNote.setVisible(false);
     
        //set mode as 1(add)
        mode = 1;

    }

    /**
     *
     * This method is called when the user clicks on the "Modify Row" button
     */
    @FXML
    private void modifyAction(ActionEvent event) throws FileNotFoundException {

        System.out.println("You clicked modify!");
        successMessageDelete.setVisible(false);
        nameErrorInlineMessage.setVisible(false);
        mode = 2;
        femaleRadioModify.setToggleGroup(group);
        maleRadioModify.setToggleGroup(group);
        addPane.setVisible(false);
        modifyPane.setVisible(true);
        deletePane.setVisible(false);
        welcomeNote.setVisible(false);
        System.out.println(getIDofSelectedRow().getId());
        String idToFetch = getIDofSelectedRow().getId();
        String line = Service.fetchRecord(idToFetch);
        String[] arr = line.split("!");

        saveButton.setDisable(false);
        firstNameModify.setText(arr[0]);
        lastNameModify.setText(arr[1]);
        middleInitialModify.setText(arr[2]);
        addr1Modify.setText(arr[3]);
        addr2Modify.setText(arr[4]);
        cityModify.setText(arr[5]);
        stateModify.setText(arr[6]);
        phoneNumberModify.setText(arr[10]);
        countryModify.setText(arr[8]);
        emailModify.setText(arr[9]);
        zipCodeModify.setText(arr[7]);
        idModify.setText(arr[12]);
         p = new Person();
                  
            p.setFirstName(firstNameModify.getText());
            p.setMiddleInitial(middleInitialModify.getText());
            p.setLastName(lastNameModify.getText());
            p.setAddr1(addr1Modify.getText());
            p.setAddr2(addr2Modify.getText());
            p.setCity(cityModify.getText());
            p.setZipCode(zipCodeModify.getText());
            p.setPhoneNumber(phoneNumberModify.getText());
            p.setState(stateModify.getText());
            p.setId(idModify.getText());
            p.setIndexTable(getIDofSelectedRow().getIndexTable());
            p.setCountry(countryModify.getText());
            p.setEmail(emailModify.getText());
            
            
        System.err.println("male o feemale" + arr[9]);
        
        if (arr[11].equalsIgnoreCase("M")) {
            maleRadioModify.setSelected(true);
            femaleRadioModify.setSelected(false);
        } else if (arr[11].equalsIgnoreCase("F")) {
            femaleRadioModify.setSelected(true);
            maleRadioModify.setSelected(false);
        }

     
    }

    /**
     *
     * This method is called when the user clicks on the "Delete Row" button
     */
    @FXML
    private void deleteAction(ActionEvent event) throws FileNotFoundException {
        System.out.println("You clicked delete!");
        successMessageDelete.setVisible(false);
        nameErrorInlineMessage.setVisible(false);
        mode = 3;
        addPane.setVisible(false);
        modifyPane.setVisible(false);
        deletePane.setVisible(true);
        welcomeNote.setVisible(false);

        String idToFetch = getIDofSelectedRow().getId();
        String line = Service.fetchRecord(idToFetch);
        String[] arr = line.split("!");

        deleteConfirmButton.setDisable(false);
        fNameDelValue.setText(arr[0]);
        lNameDelValue.setText(arr[1]);
        initialDelValue.setText(arr[2]);
        addr1DelValue.setText(arr[3]);
        addr2DelValue.setText(arr[4]);
        cityDelValue.setText(arr[5]);
        stateDelValue.setText(arr[6]);
        phoneDelValue.setText(arr[10]);
        zipCodeDelValue.setText(arr[7]);
        idDelete.setText(arr[12]);
        genderDelValue.setText(arr[11]);
        countryDelValue.setText(arr[8]);
        emailDelValue.setText(arr[9]);

    }

    /**
     *
     * This method is called when the user clicks on the "Submit" button in the
     * adding pane
     */
    @FXML
    private void submitAction(ActionEvent e) throws FileNotFoundException, IOException {

        nameErrorInlineMessage.setVisible(false);
        //validation logic
        if (firstNameInput.getText().isEmpty() || lastNameInput.getText().isEmpty() || addr1Input.getText().isEmpty()
                || cityInput.getText().isEmpty() || zipCodeInput.getText().isEmpty() || phoneNumberInput.getText().isEmpty() || stateInput.getText().isEmpty()
                || (femaleRadio.isSelected() == false && maleRadio.isSelected() == false)) {
            requiredFielAdd.setVisible(true);
            

        } else {
            requiredFielAdd.setVisible(false);
            //create a new person object to serialize
            Person person = new Person();

            if(firstNameInput.getText().contains("!")){
                firstNameInput.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setFirstName(firstNameInput.getText());
                  firstNameInput.setStyle("-fx-border-color: default");
            }
            if(lastNameInput.getText().contains("!")){
                lastNameInput.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setLastName(lastNameInput.getText());
                  lastNameInput.setStyle("-fx-border-color: default");
            }
            if(middleInitialInput.getText().contains("!")){
                middleInitialInput.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setMiddleInitial(middleInitialInput.getText());
                  middleInitialInput.setStyle("-fx-border-color: default");
            }
              if(addr1Input.getText().contains("!")){
                addr1Input.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setAddr1(addr1Input.getText());
                  addr1Input.setStyle("-fx-border-color: default");
            }
                
                 if(addr2Input.getText().contains("!")){
                addr2Input.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setAddr2(addr2Input.getText());
                  addr2Input.setStyle("-fx-border-color: default");
            }
                  
            if(cityInput.getText().contains("!")){
                cityInput.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setCity(cityInput.getText());
                  cityInput.setStyle("-fx-border-color: default");
            }
            
            if(zipCodeInput.getText().contains("!")){
                zipCodeInput.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setZipCode(zipCodeInput.getText());
                  zipCodeInput.setStyle("-fx-border-color: default");
            }
            
          
            if(validate(emailInput.getText())){
               person.setEmail(emailInput.getText());  
               emailInput.setStyle("-fx-border-color: default");
            }
            else{
               emailInput.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            
            if(validatePhoneNumber(phoneNumberInput.getText())){
                 person.setPhoneNumber(phoneNumberInput.getText());
                  phoneNumberInput.setStyle("-fx-border-color: default");
                
            }
            else{
               phoneNumberInput.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            
             if(stateInput.getText().contains("!")){
                stateInput.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setState(stateInput.getText());
                  stateInput.setStyle("-fx-border-color: default");
            }
             
              if(countryInput.getText().contains("!")){
                countryInput.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setCountry(countryInput.getText());
                  countryInput.setStyle("-fx-border-color: default");
            }
           
           
            
            if (femaleRadio.isSelected()) {
                person.setGender("F");
            } else if (maleRadio.isSelected()) {
                person.setGender("M");
            }
            //assign id to row
            Long id = new Random().nextLong();
            if (id < 0) {
                id = id * -1;
            }
            person.setId(id.toString());

            // check if the input names already exits in the file
            if (!Service.checkNames(firstNameInput.getText(), middleInitialInput.getText(), lastNameInput.getText())) {

                firstNameInput.setStyle("-fx-border-color: default  ");
                middleInitialInput.setStyle("-fx-border-color: default  ");
                lastNameInput.setStyle("-fx-border-color: default  ");
                
                nameErrorInlineMessage.setVisible(false);
                // persist the record to the file
                if (Service.persist(person) == 1) {

                    successMessage.setVisible(true);
                    successMessageDelete.setAlignment(Pos.CENTER);
                    fadeIn.setNode(successMessage);
                    fadeIn.setFromValue(1.0);
                    fadeIn.setToValue(0.0);
                    fadeIn.setCycleCount(1);
                    fadeIn.setAutoReverse(true);
                    fadeIn.playFromStart();

                    firstNameTable.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
                    lNameTable.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
                    phoneNumTable.setCellValueFactory(new PropertyValueFactory<Person, String>("phoneNumber"));
                    idTable.setCellValueFactory(new PropertyValueFactory<Person, String>("id"));
                    //update table view
                    data.add(person);
                    tableView.setItems(data);

                } // error handling when writing to file fails
                else if (Service.persist(person) == 0) {
                    successMessage.setVisible(true);
                    successMessage.setText("Error!");
                    successMessage.setTextFill(Paint.valueOf("Red"));
                    successMessage.setAlignment(Pos.CENTER);
                    fadeIn.setNode(successMessage);
                    fadeIn.setFromValue(1.0);
                    fadeIn.setToValue(0.0);
                    fadeIn.setCycleCount(1);
                    fadeIn.setAutoReverse(true);
                    fadeIn.playFromStart();
                }
                modifyButton.setDisable(false);
                deleteButton.setDisable(false);
            } // if the name alreasy exits in the file
            else {
                firstNameInput.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
                middleInitialInput.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
                lastNameInput.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               
                nameErrorInlineMessage.setVisible(true);
                modifyButton.setDisable(true);
                deleteButton.setDisable(true);

            }
            // if the table is empty, disable modify and delete buttons
            if (data.isEmpty()) {
                modifyButton.setDisable(true);
                deleteButton.setDisable(true);
                System.err.println("data empty");

            }

            tableView.getSelectionModel().selectLast();
        }

    }

    /**
     *
     * This method is called when the user clicks on the "Confirm" button in the
     * delete pane
     */
    @FXML
    private void deleteActionConfirm(ActionEvent e) throws FileNotFoundException {

        //get the id of the row to be deleted
        String idToFetch = getIDofSelectedRow().getId();
        //call to service method to fetch the record corresponding to the id 
        String line = Service.fetchRecord(idToFetch);

        //found
        if (Service.deleteLine(line) == 0) {

            successMessageDelete.setVisible(true);
            successMessageDelete.setText("Deleted Successfully");
            successMessageDelete.setAlignment(Pos.CENTER);

        } //not found
        else if (Service.deleteLine(line) == 1) {
            successMessageDelete.setVisible(true);
            successMessageDelete.setText("Error!");
            successMessageDelete.setTextFill(Paint.valueOf("Red"));
            successMessageDelete.setAlignment(Pos.CENTER);

        }

        successMessageDelete.setVisible(true);
        data.remove(getIDofSelectedRow());
        deleteConfirmButton.setDisable(true);
        tableView.getSelectionModel().clearAndSelect(-1);
        // if the table is empty, disable modify and delete buttons
        if (data.isEmpty()) {
            modifyButton.setDisable(true);
            deleteButton.setDisable(true);

        }
    }

    /**
     *
     * This method is called when the user clicks on the "Reset" button in the
     * add pane
     */

    @FXML
    private void resetAction(ActionEvent event) {
        System.out.println("You clicked reset");
        nameErrorInlineMessage.setVisible(false);
        firstNameInput.setText("");
        lastNameInput.setText("");
        middleInitialInput.setText("");
        addr1Input.setText("");
        addr2Input.setText("");
        cityInput.setText("");
        stateInput.setText("");
        zipCodeInput.setText("");
        phoneNumberInput.setText("");
        emailInput.setText("");
        countryInput.setText("");
        femaleRadio.setSelected(false);
        maleRadio.setSelected(false);
        firstNameInput.setStyle("-fx-border-color: default  ");
        middleInitialInput.setStyle("-fx-border-color: default  ");
        lastNameInput.setStyle("-fx-border-color: default  ");
                

    }

    /**
     *
     * This method is called when the user clicks on any row of the table
     */
    @FXML
    private void selectTableActionResponse() throws FileNotFoundException {

        if (!data.isEmpty()) {
            modifyButton.setDisable(false);
            deleteButton.setDisable(false);
        }
        // call the methods to perform actions according to the mode
        if (mode == 1) {
            addAction(null);
        } else if (mode == 2) {
            modifyAction(null);
        } else if (mode == 3) {
            deleteAction(null);
        }

    }

    /**
     *
     * Method to get the person object for the id selected in the table
     */
    private Person getIDofSelectedRow() {

        Person p = null;
        successMessageModify.setVisible(false);

        for (int i = 0; i < tableView.getSelectionModel().getSelectedItems().size(); i++) {
            p = (Person) tableView.getSelectionModel().getSelectedItems().get(i);
            modifyButton.setDisable(false);
            deleteButton.setDisable(false);
        }

        Integer indexTable = tableView.getFocusModel().getFocusedIndex();
        String index = indexTable.toString();
        p.setIndexTable(index);

        return p;
    }

    /**
     *
     * This method gets called when the user clicks in save button in modify
     * pane
     */
    @FXML
    public void saveAction() throws IOException {

        //service call to search for the id in the file and get the line
        nameErrorInlineMessageModify.setVisible(false);

        //validation logic
        if (firstNameModify.getText().isEmpty() || lastNameModify.getText().isEmpty() || addr1Modify.getText().isEmpty()
                || cityModify.getText().isEmpty() || zipCodeModify.getText().isEmpty() || phoneNumberModify.getText().isEmpty() || stateModify.getText().isEmpty()
                || (femaleRadioModify.isSelected() == false && maleRadioModify.isSelected() == false)) {

            requiredFieldModify.setVisible(true);
        } else {
            requiredFieldModify.setVisible(false);
            Person person = new Person();
            
            
            
            if(firstNameModify.getText().contains("!")){
                firstNameModify.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setFirstName(firstNameModify.getText());
                  firstNameModify.setStyle("-fx-border-color: default");
            }
            if(lastNameModify.getText().contains("!")){
                lastNameModify.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setLastName(lastNameModify.getText());
                  lastNameModify.setStyle("-fx-border-color: default");
            }
            if(middleInitialModify.getText().contains("!")){
                middleInitialModify.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setMiddleInitial(middleInitialModify.getText());
                  middleInitialModify.setStyle("-fx-border-color: default");
            }
              if(addr1Modify.getText().contains("!")){
                addr1Modify.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setAddr1(addr1Modify.getText());
                  addr1Modify
                          .setStyle("-fx-border-color: default");
            }
                
                 if(addr2Modify.getText().contains("!")){
                addr2Modify.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setAddr2(addr2Modify.getText());
                  addr2Modify.setStyle("-fx-border-color: default");
            }
                  
            if(cityModify.getText().contains("!")){
                cityModify.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setCity(cityModify.getText());
                  cityModify.setStyle("-fx-border-color: default");
            }
            
            if(zipCodeModify.getText().contains("!")){
                zipCodeModify.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setZipCode(zipCodeModify.getText());
                  zipCodeModify.setStyle("-fx-border-color: default");
            }
            
          
            if(validate(emailModify.getText())){
               person.setEmail(emailModify.getText());  
               emailModify.setStyle("-fx-border-color: default");
            }
            else{
               emailModify.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            
            if(validatePhoneNumber(phoneNumberModify.getText())){
                 person.setPhoneNumber(phoneNumberModify.getText());
                  phoneNumberModify.setStyle("-fx-border-color: default");
                
            }
            else{
               phoneNumberModify.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            
             if(stateModify.getText().contains("!")){
                stateModify.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setState(stateModify.getText());
                  stateModify.setStyle("-fx-border-color: default");
            }
             
              if(countryModify.getText().contains("!")){
                countryModify.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               return;
            }
            else{
                  person.setCountry(countryModify.getText());
                  countryModify.setStyle("-fx-border-color: default");
            }
           
            
            
            
            person.setFirstName(firstNameModify.getText());
            person.setLastName(lastNameModify.getText());
            person.setMiddleInitial(middleInitialModify.getText());
            person.setAddr1(addr1Modify.getText());
            person.setAddr2(addr2Modify.getText());
            person.setCity(cityModify.getText());
            person.setZipCode(zipCodeModify.getText());
            person.setPhoneNumber(phoneNumberModify.getText());
            person.setState(stateModify.getText());
            person.setId(idModify.getText());
            person.setIndexTable(getIDofSelectedRow().getIndexTable());
            person.setEmail(emailModify.getText());
            person.setCountry(countryModify.getText());
            

            if (femaleRadioModify.isSelected()) {
                person.setGender("F");
            } else if (maleRadioModify.isSelected()) {
                person.setGender("M");
            }

            //check if any one of names is changed to different content.
            
               if( firstNameModify.getText().equalsIgnoreCase(p.getFirstName()) && middleInitialModify.getText().equalsIgnoreCase( p.getMiddleInitial()) && lastNameModify.getText().equalsIgnoreCase( p.getLastName()) ){
                
                System.out.println("not changed");
                
                firstNameModify.setStyle("-fx-border-color: default  ");
                middleInitialModify.setStyle("-fx-border-color: default ");
                lastNameModify.setStyle("-fx-border-color: default  ");
                
                nameErrorInlineMessageModify.setVisible(false);
            
               //remove existing line and add new line
                String line = Service.fetchRecord(idModify.getText());

                Service.deleteLine(line);
                if (Service.persist(person) == 1) {

                    successMessageModify.setVisible(true);
                    successMessageDelete.setAlignment(Pos.CENTER);
                    successMessageModify.setText("Saved Successfully");

                } else if (Service.persist(person) == 0) {
                    successMessageModify.setVisible(true);
                    successMessageModify.setText("Error!");
                    successMessageModify.setTextFill(Paint.valueOf("Red"));
                    successMessageModify.setAlignment(Pos.CENTER);

                }
                data.remove(getIDofSelectedRow());
                data.add(person);
                successMessageModify.setVisible(true);
                nameErrorInlineMessageModify.setVisible(false);
                tableView.getSelectionModel().selectLast();
            }
            else{
                 System.out.println("changed");
                 p.setFirstName(firstNameModify.getText());
                 p.setMiddleInitial(middleInitialModify.getText());
                 p.setLastName(lastNameModify.getText());
                 
                 // if the name alreasy exits in the file
                if (!Service.checkNames(firstNameModify.getText(), middleInitialModify.getText(), lastNameModify.getText())) {

                firstNameModify.setStyle("-fx-border-color: default  ");
                middleInitialModify.setStyle("-fx-border-color: default  ");
                lastNameModify.setStyle("-fx-border-color: default  ");
                
                nameErrorInlineMessage.setVisible(false);
            
               //remove existing line and add new line
                String line = Service.fetchRecord(idModify.getText());

                Service.deleteLine(line);
                if (Service.persist(person) == 1) {

                    successMessageModify.setVisible(true);
                    successMessageDelete.setAlignment(Pos.CENTER);
                    successMessageModify.setText("Saved Successfully");

                } else if (Service.persist(person) == 0) {
                    successMessageModify.setVisible(true);
                    successMessageModify.setText("Error!");
                    successMessageModify.setTextFill(Paint.valueOf("Red"));
                    successMessageModify.setAlignment(Pos.CENTER);

                }
                data.remove(getIDofSelectedRow());
                data.add(person);
                successMessageModify.setVisible(true);
                nameErrorInlineMessageModify.setVisible(false);
                tableView.getSelectionModel().selectLast();
            } 
            else {
                    
                    
                firstNameModify.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
                middleInitialModify.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
                lastNameModify.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;-fx-border-radius: 3px;");
               
                nameErrorInlineMessageModify.setVisible(true);
       
            }
            }
            
                  
        }
    }
    /**
     *
     * This method gets called when the user clicks in reset button in modify
     * pane
     */
    @FXML
    private void resetModifyPaneAction() {

        System.out.println("You clicked reset modify");

        firstNameModify.setStyle("-fx-border-color: default  ");
                middleInitialModify.setStyle("-fx-border-color: default ");
                lastNameModify.setStyle("-fx-border-color: default  ");
                
                nameErrorInlineMessageModify.setVisible(false);
                
       
        firstNameModify.setText("");
        lastNameModify.setText("");
        middleInitialModify.setText("");
        addr1Modify.setText("");
        addr2Modify.setText("");
        cityModify.setText("");
        stateModify.setText("");
        zipCodeModify.setText("");
        phoneNumberModify.setText("");
        femaleRadioModify.setSelected(false);
        maleRadioModify.setSelected(false);
        emailModify.setText("");
        countryModify.setText("");
        successMessageModify.setVisible(false);
       
    }

    /**
     *
     * This method gets called when the user clicks in cancel button in delete
     * pane
     */
    @FXML
    private void cancelAction() {

        deletePane.setVisible(false);
        welcomeNote.setVisible(true);
        mode = 0;

    }

  
    // this method is called to initialize a controller after its root element has been completely processed.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        femaleRadio.setToggleGroup(group);
        maleRadio.setToggleGroup(group);
        
        Service.addTextLimiter(firstNameInput,20);
        Service.addTextLimiter(lastNameInput,20);
        Service.addTextLimiter(middleInitialInput,1);
        Service.addTextLimiter(addr1Input,35);
        Service.addTextLimiter(addr2Input,35);
        Service.addTextLimiter(cityInput,25);
        Service.addTextLimiter(stateInput,2);
        Service.addTextLimiter(zipCodeInput,35);
        Service.addTextLimiter(phoneNumberInput,35);
        Service.addTextLimiter(countryInput,30);
        Service.addTextLimiter(emailInput,100);
        
        
        Service.addTextLimiter(firstNameModify,20);
        Service.addTextLimiter(lastNameModify,20);
        Service.addTextLimiter(middleInitialModify,1);
        Service.addTextLimiter(addr1Modify,35);
        Service.addTextLimiter(addr2Modify,35);
        Service.addTextLimiter(cityModify,25);
        Service.addTextLimiter(stateModify,2);
        Service.addTextLimiter(zipCodeModify,35);
        Service.addTextLimiter(phoneNumberModify,35);
        Service.addTextLimiter(countryModify,30);
        Service.addTextLimiter(emailModify,100);
        
        pattern = Pattern.compile(EMAIL_PATTERN);
        
    }

    private static boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
        //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
        //validating phone number with extension length from 3 to 5
        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
        //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
        //return false if nothing matches the input
        else return false;
         
    }
    
    	public boolean validate(final String hex) {
 
		matcher = pattern.matcher(hex);
		return matcher.matches();
 
	}
        
}
