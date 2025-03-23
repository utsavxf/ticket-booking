package org.example.service;

import org.example.entites.User;

public class UserBookingService {

    //because we don't want ki jab koi user login ho jaaye aur use baad me methods bulane pade to service bole bhai zara dobara bhejna kon ho tum, mai bhul gaya tumhe
    //that's why we are storing it here
    private User user;

    public UserBookingService(User user1){
      this.user=user1;
    }

}
