package com.photos.controllers;

import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;

public class UserList implements Serializable{

    static final long serialVersionUID = 1L;

    //master list of users.ser
    private static ArrayList<User> userList = new ArrayList<User>();
    public static final String dataFile = "serializedUsers.ser";

    //serialize list of users
    public static void writeToSerFile(ArrayList<User> users) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile));
        oos.writeObject(users);
    }

    //deserialize list of users - used to get the latest version
    public static ArrayList<User> deserialize(){
        ArrayList<User> users = new ArrayList<User>();
        try {
            FileInputStream fileIn = new FileInputStream("serializedUsers.ser");
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
        return false;
    }

    public void setUpUsers(){
        this.userList = deserialize();
    }

}
