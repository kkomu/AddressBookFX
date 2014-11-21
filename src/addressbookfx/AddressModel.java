/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbookfx;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ohjelmistokehitys
 */
public class AddressModel {
    List<UserData> userData;

    public AddressModel() {
        userData = new ArrayList<>();
    }
    
    public void addUserToArray(UserData user) {
        userData.add(user);
    }
    
    public List<UserData> returnUserArray() {
        return userData;
    }
    
}
