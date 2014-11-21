/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbookfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

/**
 *
 * @author Ohjelmistokehitys
 */
public class FXMLDocumentController implements Initializable {
    private AddressModel model = new AddressModel();
    
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
    private void handleCloseButton(ActionEvent e) {
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
    }
    
    
    @FXML
    private void handleBrowseTab(Event e) {
        
        if (tabBrowseAddress.isSelected()) {
            for(UserData u: model.returnUserArray() ) {
                
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
}
