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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private Text requredFieldModify;

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
        femaleRadio.setToggleGroup(group);
        maleRadio.setToggleGroup(group);
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
        phoneNumberModify.setText(arr[8]);
        zipCodeModify.setText(arr[7]);
        idModify.setText(arr[10]);

        System.err.println("male o feemale" + arr[9]);
        if (arr[9].equalsIgnoreCase("M")) {
            maleRadioModify.setSelected(true);
            femaleRadioModify.setSelected(false);
        } else if (arr[9].equalsIgnoreCase("F")) {
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
        phoneDelValue.setText(arr[8]);
        zipCodeDelValue.setText(arr[7]);
        idDelete.setText(arr[10]);
        genderDelValue.setText(arr[9]);

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

            person.setFirstName(firstNameInput.getText());
            person.setLastName(lastNameInput.getText());
            person.setMiddleInitial(middleInitialInput.getText());
            person.setAddr1(addr1Input.getText());
            person.setAddr2(addr2Input.getText());
            person.setCity(cityInput.getText());
            person.setZipCode(zipCodeInput.getText());
            person.setPhoneNumber(phoneNumberInput.getText());
            person.setState(stateInput.getText());
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
        femaleRadio.setSelected(false);
        maleRadio.setSelected(false);

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

            requredFieldModify.setVisible(true);
        } else {
            requredFieldModify.setVisible(false);
            Person person = new Person();

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

            if (femaleRadioModify.isSelected()) {
                person.setGender("F");
            } else if (maleRadioModify.isSelected()) {
                person.setGender("M");
            }

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

    }

    /**
     *
     * This method gets called when the user clicks in reset button in modify
     * pane
     */
    @FXML
    private void resetModifyPaneAction() {

        System.out.println("You clicked reset modify");

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

    }

}
