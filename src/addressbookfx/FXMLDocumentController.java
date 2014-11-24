/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbookfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Ohjelmistokehitys
 */
public class FXMLDocumentController implements Initializable {
    private AddressModel model = new AddressModel();
    
    @FXML
    Button btnSave;
    
    @FXML
    Button btnDelete;
    
    @FXML
    Button btnOrganize;
    
    @FXML
    TextField txtFirstName;
    
    @FXML
    TextField txtLastName;
    
    @FXML
    TextField txtAddress;
    
    @FXML
    TextField txtPhone;
    
    @FXML
    TextField txtEmail;
    
    @FXML
    Tab tabBrowseAddress;
    
    @FXML
    ListView lstFullName;
    
    @FXML
    TextArea txtInfoArea;
    
    @FXML
    private void handleOrganizeButton(ActionEvent e) {
        model.arrangeUserDataArray();
        updateListView();
        txtInfoArea.clear();
    }
    
    @FXML
    private void handleCloseButton(ActionEvent e) {
        model.saveUserDataToFile();
        Platform.exit();
    }
    
    @FXML
    private void handleSaveButton(ActionEvent e) {
        UserData user = new UserData();
        user.setFirstName(txtFirstName.getText());
        user.setLastName(txtLastName.getText());
        user.setAddress(txtAddress.getText());
        user.setPhone(txtPhone.getText());
        user.setEmail(txtEmail.getText());
        model.addUserToArray(user);
        txtFirstName.clear();
        txtLastName.clear();
        txtAddress.clear();
        txtPhone.clear();
        txtEmail.clear();
    }
    
    @FXML
    private void handleDeleteButton(ActionEvent e) {
        String userName = (String) lstFullName.getSelectionModel().getSelectedItem();
        model.deleteUserFromArray(userName);
        updateListView();
        txtInfoArea.clear();
    }
    
    @FXML
    private void handleBrowseTab(Event e) {

        if (tabBrowseAddress.isSelected()) {
            updateListView();
            txtInfoArea.clear();
            btnSave.setVisible(false);
            btnDelete.setVisible(true);
            btnOrganize.setVisible(true);
        }
        else {
            btnOrganize.setVisible(false);
            btnSave.setVisible(true);
            btnDelete.setVisible(false);
        }
    }
    
    private void updateListView() {
        ObservableList<String> items = FXCollections.observableArrayList();
        for(UserData u: model.returnUserArray() ) {
            items.add(u.getFirstName()+" "+u.getLastName());
        }
        lstFullName.setItems(items);
    }
    
       
    @FXML
    protected void handleListView(Event e) {
        String userName = (String) lstFullName.getSelectionModel().getSelectedItem();
        UserData user = model.getSingleUser(userName);
        
        txtInfoArea.setText("First name: "+user.getFirstName()+"\n");
        txtInfoArea.appendText("Last name: "+user.getLastName()+"\n");
        txtInfoArea.appendText("Address: "+user.getAddress()+"\n");
        txtInfoArea.appendText("Phone: "+user.getPhone()+"\n");
        txtInfoArea.appendText("Email: "+user.getEmail()+"\n");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
