package com.photos.controllers;

import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;

public class UserList implements Serializable{

    static final long serialVersionUID = 1L;

    //master list of users.ser
    private static ArrayList<User> userList = new ArrayList<User>();


//    public static final String storeDir = "/Users";
//    public static final String storeFile = "users.ser";

    public static void writeToSerFile(UserList userApp) {
        try {
            String fileName = "serializedUsers.ser";
            File file = new File(fileName);
            file.setWritable(true);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true));
            //oos.writeObject((userList)); //adds most recently updated userList
            oos.writeObject(userApp);
            oos.close();
        } catch (IOException i){
            i.printStackTrace();
        }
        //deserialize();
    }

    public static UserList deserialize(){
        UserList test = new UserList();
        try {
            FileInputStream fileIn = new FileInputStream("serializedUsers.ser");
            ObjectInputStream ois = new ObjectInputStream(fileIn);
            test = (UserList) ois.readObject();
            ois.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i){
            System.out.println("No users exist or class is not found");
            i.printStackTrace();
            return test;
        }
        return test;
    }

    //add admin to the list of users.ser - initially the only user
    //** must be called **
    public void setAdmin() throws IOException {
        final User admin = new User("admin", "admin");
        for (User u : userList){ //check to see if admin was already added
            if (u.getUsername().equals("admin")){
                return;
            }
        }
        addUser(admin);
    }

    //this method adds new user to userList, as long as the user does not already exist
    public void addUser(User user) {
        for (User u : userList){
            if (u.getUsername().equals(user.getUsername())){
                System.out.println("Warning: username already exists!");
                return;
            }
        }
        userList.add(user);
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

}
