package com.photos.models;

import com.photos.models.User;

import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class UserList implements Serializable{

    static final long serialVersionUID = 1L;

    // master list of users.ser
    private List<User> userList = new ArrayList<User>();
    public final String dataFile = "src/main/resources/persist/serializedUsers.ser";

    // serialize list of users
    public void serialize() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile));
        oos.writeObject(userList);
    }

    // deserialize list of users - used to get the latest version
    private void init() {
        try {
            FileInputStream fileIn = new FileInputStream(dataFile);
            ObjectInputStream ois = new ObjectInputStream(fileIn);
            userList = (ArrayList<User>) ois.readObject();
            ois.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i){
            System.out.println("No users exist or class is not found");
            userList = new ArrayList<>();
        }
    }

    //this method adds new user to userList, as long as the user does not already exist
    //serializes class object whenever we add a user
    public void addUser(User user) throws IOException {
        init();
        // enforce username uniqueness
        for (User u : userList){
            if (u.getUsername().equals(user.getUsername())){
                throw new IllegalArgumentException("Username already taken.");
            }
        }
        userList.add(user);
        serialize();
        System.out.println("Successfully added '" + user.getUsername() + "'...");
    }

    // get a user by id
    public User getUser(String id) {
        init();
        List<User> filtered = userList.stream().filter(u -> u.getId().equals(id)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("User does not exist.");
        }
        return filtered.get(0);
    }

    // get a user by username
    public User getUserByUsername(String username) {
        init();
        List<User> filtered = userList.stream().filter(u -> u.getUsername().equals(username)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("User does not exist.");
        }
        return filtered.get(0);
    }

    // remove user by id
    public void deleteUser(User id) throws IOException {
        init();
        List<User> filtered = userList.stream().filter(a -> a.getId().equals(id)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("The user to delete does not exist.");
        }
        userList = userList.stream().filter(p -> !p.getId().equals(id)).collect(Collectors.toList());
        serialize();
    }

    //get latest version of userList via deserialization from file
    public List<User> getUsers(){
        init();
        return userList;
    }

    public void printUserList(){
        for (User u : userList){
            System.out.println(u.toString());
        }
    }

    // check user existence by username
    public boolean userExistsByUsername(String username){
        for (User u : userList){
            if (u.getUsername().equals(username)){
                return true;
            }
        }
        //System.out.println("THIS USER DOES NOT EXIST");
        return false;
    }

    // appends initial stock user
    public void setUpUsers() throws IOException {
        init();
        User stock = new User("stock", "stock");
        Album stockA = new Album("testAlbumStock", stock.getUsername());
        stock.addAlbum(stockA);
        addUser(stock);
    }

}
