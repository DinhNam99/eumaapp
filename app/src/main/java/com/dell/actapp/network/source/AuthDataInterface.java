package com.dell.actapp.network.source;


import com.dell.actapp.model.User;

public interface AuthDataInterface {

    void saveUser(User user);
    User getUser();
}
