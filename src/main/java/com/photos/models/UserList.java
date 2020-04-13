package com.photos.models;

import com.photos.models.User;

import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;

public class UserList implements Serializable{

    static final long serialVersionUID = 1L;

    //master list of users.ser
    private static ArrayList<User> userList = new ArrayList<User>();
    public static final String dataFile = "src/main/resources/persist/serializedUsers.ser";

    //serialize list of users
    public static void writeToSerFile(ArrayList<User> users) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile));
        oos.writeObject(users);
    }

    //deserialize list of users - used to get the latest version
    public static ArrayList<User> deserialize(){
        ArrayList<User> users = new ArrayList<User>();
        try {
            FileInputStream fileIn = new FileInputStream(dataFile);
            ObjectInputStream ois = new ObjectInputStream(fileIn);
            users = (ArrayList<User>) ois.readObject();
            ois.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i){
            System.out.println("No users exist or class is not found");
            //i.printStackTrace();
            return users;
        }
        return users;
    }

    //this method adds new user to userList, as long as the user does not already exist
    //serializes class object whenever we add a user
    public void addUser(User user) throws IOException {
        for (User u : userList){
            if (u.getUsername().equals(user.getUsername())){
                System.out.println("Warning: username already exists!");
                return;
            }
        }
        userList.add(user);
        writeToSerFile(userList);
        System.out.println("Successfully added '" + user.getUsername() + "'...");
    }

    public void deleteUser(User user) throws IOException {
        for (User u : userList){
            if (u.getUsername().equals(user.getUsername())){
                userList.remove(user);
                System.out.println("The user: " + user.getUsername() + " has been deleted.");
                return;
            }
        }
        writeToSerFile(userList);
    }

    //get latest version of userList via deserialization from file
    public ArrayList<User> getUserList(){
        return this.userList;
    }

    //print the userList
    public void printUserList(){
        for (User u : userList){
            System.out.println(u.getUsername());
        }
    }

    public boolean userExists(String name){
        for (User u : userList){
            if (u.getUsername().equals(name)){
                return true;
            }
        }
        //System.out.println("THIS USER DOES NOT EXIST");
        return false;
    }

    public User getUser(String name){
        for (User u : userList){
            if (u.getUsername().equals(name)){
                return u;
            }
        }
        System.out.println("No user with the username '" + name+ "' was found!");
        return null;
    }

    public void setUpUsers() throws IOException {
        this.userList = deserialize();
        User stock = new User("stock", "stock");
        addUser(stock);
    }

}
