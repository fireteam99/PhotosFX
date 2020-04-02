package com.photos.controllers;

import java.io.*;
import java.util.ArrayList;

public class UserList implements Serializable{

    //master list of users.ser
    private static ArrayList<User> userList = new ArrayList<User>();

//    public static final String storeDir = "/Users";
//    public static final String storeFile = "users.ser";

    public static void writeToSerFile(User user) throws IOException{
        try {
            String fileName = "serializedUsers.ser";
            File file = new File(fileName);
            file.setWritable(true);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true));
            oos.writeObject((userList)); //adds most recently updated userList
            oos.close();
        } catch (IOException i){
            i.printStackTrace();
        }
        deserialize();
    }

    public static ArrayList<User> deserialize(){
        ArrayList<User> test = null;
        try {
            FileInputStream fileIn = new FileInputStream("serializedUsers.ser");
            ObjectInputStream ois = new ObjectInputStream(fileIn);
            test = (ArrayList<User>) ois.readObject();
            ois.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i){
            System.out.println("No users exist or class is not found");
            i.printStackTrace();
            return test;
        }
        for (User u : test){
            System.out.println("List of users...");
            System.out.println("username: " + u.getUsername());
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
        //System.out.println("Admin user added to list.");
    }

    //this method adds new user to userList, as long as the user does not already exist
    public void addUser(User user) throws IOException {
        for (User u : userList){
            if (u.getUsername().equals(user.getUsername())){
                System.out.println("Warning: user already exists!");
                return;
            }
        }
        userList.add(user);
        writeToSerFile(user);
    }

    public ArrayList<User> getUserList(){
        //get from serialized data
        return this.userList;
    }

}
