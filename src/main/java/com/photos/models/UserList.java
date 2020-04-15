package com.photos.models;

import com.photos.models.User;

import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * UserList class: stores the master list of Users
 * @author Robert Cheng, Ray Sy
 */
public class UserList implements Serializable{

    static final long serialVersionUID = 1L;

    // master list of users.ser
    private List<User> userList = new ArrayList<User>();
    public final String dataFile = "src/main/resources/persist/serializedUsers.ser";

    // serialize list of users

    /**
     * serializes the userList
     * @throws IOException
     */
    public void serialize() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile));
        oos.writeObject(userList);
    }

    /**
     * deserialize list of users - used to get the latest version
     */
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

    /**
     * adds new user to userList, as long as the user does not already exist.
     * @param user User
     */
    public void addUser(User user) throws IOException {
        init();
        // username can't be empty
        if (user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
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

    /**
     * get a user by id
     * @param id String
     */
    public User getUser(String id) {
        init();
        List<User> filtered = userList.stream().filter(u -> u.getId().equals(id)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("User does not exist.");
        }
        return filtered.get(0);
    }

    /**
     * get a user by username
     * @param username String
     */
    public User getUserByUsername(String username) {
        init();
        List<User> filtered = userList.stream().filter(u -> u.getUsername().equals(username)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("User does not exist.");
        }
        return filtered.get(0);
    }

    /**
     * update user by id
     * @param id String
     * @param username String
     * @param password String
     * @throws IOException
     */
    public void editUser(String id, String username, String password) throws IOException {
        init();
        List<User> filtered = userList.stream().filter(u -> u.getId().equals(id)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("User does not exist.");
        }

        // make sure username is not empty
        if (username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        // make sure username is unique
        for (User user: userList) {
            if (user.getUsername().equals(username)) {
                throw new IllegalArgumentException("Username already taken.");
            }
        }

        User user = filtered.get(0);
        user.setUsername(username);
        user.setPassword(password);
        serialize();
    }

    // remove user by id

    /**
     * remove user by id
     * @param id String
     * @throws IOException
     */
    public void deleteUser(String id) throws IOException {
        init();
        List<User> filtered = userList.stream().filter(a -> a.getId().equals(id)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("The user to delete does not exist.");
        }
        userList = userList.stream().filter(p -> !p.getId().equals(id)).collect(Collectors.toList());
        serialize();
    }

    //get latest version of userList via deserialization from file

    /**
     * get latest version of userList via deserialization
     * @return List
     */
    public List<User> getUsers(){
        init();
        return userList;
    }

    /**
     * prints userList
     */
    public void printUserList(){
        init();
        for (User u : userList){
            System.out.println(u.toString());
        }
    }

    // check user existence by username

    /**
     * checks if a user with this username already exists
     * @param username String
     * @return boolean
     */
    public boolean userExistsByUsername(String username){
        init();
        for (User u : userList){
            if (u.getUsername().equals(username)){
                return true;
            }
        }
        //System.out.println("THIS USER DOES NOT EXIST");
        return false;
    }

    // appends initial stock user

    /**
     * sets up users: adds stock user, if not already added
     * @throws IOException
     */
    public void setUpUsers() throws IOException {
        init();
        if (!userExistsByUsername("stock")) {
            User stock = new User("stock", "stock");
            Album album = new Album("testAlbumStock", stock.getId());

            // add all photos in data directory
            File dataFolder = new File("data");
            FileFilter fileFilter = new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    String name = pathname.getName();
                    return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png") || name.endsWith(".gif");
                }
            };

            File[] stockPhotos = dataFolder.listFiles(fileFilter);

            for (File file: stockPhotos) {
                album.addPicture(new Picture(album.getId(), file));
            }

//            album.addPicture(new Picture(album.getId(), new File("data/desert_night.png")));
//            album.addPicture(new Picture(album.getId(), new File("data/arch_bridge.png")));

            stock.addAlbum(album);
            addUser(stock);
        }
    }

}
