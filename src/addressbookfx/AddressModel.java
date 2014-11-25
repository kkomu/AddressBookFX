/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbookfx;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Ohjelmistokehitys
 */
public class AddressModel {
    private List<UserData> userData;
    
    public AddressModel() {
        userData = new ArrayList<>();
        readUserDataFromFile();
    }
    
    public void addUserToArray(UserData user) {
        userData.add(user);
    }
    
    public void sortUserDataArray() {
        Collections.sort(userData);
    }
    
    public void deleteUserFromArray(String userName) {
        int index=0;
        UserData found = null;
        Iterator<UserData> iter = userData.iterator();
        
         while(iter.hasNext()) {
            UserData u = iter.next();
            // Concurrent-something exception if not breaking out from the loop
            // (iterating and deleting from array simultaneously
            if ( userName.equalsIgnoreCase(u.getFirstName()+" "+u.getLastName() )) {
                //synchronized(userData) {
                    userData.remove(index);
                    break;
                //}
            }
            index++;
        }
    }
    
    public List<UserData> returnUserArray() {
        return userData;
    }
    
    public UserData getSingleUser(String userName) {
        UserData found = null;
        Iterator<UserData> iter = userData.iterator();
        
        while(iter.hasNext()) {
            UserData u = iter.next();
            if ( userName.equalsIgnoreCase(u.getFirstName()+" "+u.getLastName() )) {
                found = u;
                break;
            }
        }
        return found;
    }

    public void saveUserDataToFile() {
        FileOutputStream outFile = null;
        ObjectOutputStream outObj = null;

        Iterator<UserData> iter = userData.iterator();
        
        try {
             outFile = new FileOutputStream("userdata.dat");
             outObj = new ObjectOutputStream(outFile);
             while(iter.hasNext()) {
                 outObj.writeObject(iter.next());
             }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (outObj != null) {
                try {
                    outObj.close();
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
                
            if (outFile != null) {
                try {
                    outFile.close();
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    
    private void readUserDataFromFile() {
        FileInputStream inFile = null;
        ObjectInputStream inObj = null;
        Object o;
        
        try {
            inFile = new FileInputStream("userdata.dat");
            inObj = new ObjectInputStream(inFile);
            while ((o = inObj.readObject()) != null) {
                UserData u = (UserData)o;
                userData.add(u);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (inObj != null) {
                try {
                    inObj.close();
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
                
            if (inFile != null) {
                try {
                    inFile.close();
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
