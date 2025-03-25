package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entites.User;
import org.example.util.UserServiceUtil;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    //because we don't want ki jab koi user login ho jaaye aur use baad me methods bulane pade to service bole bhai zara dobara bhejna kon ho tum, mai bhul gaya tumhe
    //that's why we are storing it here
    private User user;

    private List<User> userList;

    private static final String USERS_PATH = "app/src/main/java/org.example/localDb/users.json";


    //now in our model and in the local db files that we have made one is userId in camel case and user_id in snake case
    //so to map this we need to have an object mapping and we need to use a library for this also
    //and aslo object se json me daalne ke liye serialize karna padega and vice-versa de-serialize karna padega
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<User> loadUsers() throws IOException{
        File users = new File(USERS_PATH); //I have plain text in this users
        return objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }

    //default constructor
    public UserBookingService() throws IOException{
       loadUsers();
    }

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        File users = new File(USERS_PATH); //I have plain text in this users
        loadUsers();
    }

    public Boolean loginUser(){
        Optional<User> foundUser=userList.stream().filter(user1 -> {
            return user1.getName().equalsIgnoreCase( user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),user1.getHashedPassword());
        }).findFirst(); //let's say humari list me 2 bande hai jinke same name aur password hai to jo pehele number be padha hua hai vo use dedega
          return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException{
        File usersFile=new File(USERS_PATH);
        objectMapper.writeValue(usersFile,userList); //here serialization is taking place
    }

    public void fetchBookings(){
        user.printTickets();
    }

}