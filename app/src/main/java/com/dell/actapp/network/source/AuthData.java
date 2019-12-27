package com.dell.actapp.network.source;

import com.dell.actapp.model.User;
import com.dell.actapp.utils.ShareReferenceUtils;
import com.google.gson.Gson;

public class AuthData implements AuthDataInterface {

    public volatile static AuthData instance;
    public static String KEY_USER = "key_user";

    public static AuthData getInstance(){
        if(instance == null){
            instance = new AuthData();
        }
        return instance;
    }
    @Override
    public void saveUser(User user) {
        Gson gson = new Gson();
        String data = gson.toJson(user);
        ShareReferenceUtils.setStringPreference(KEY_USER, data);
    }

    @Override
    public User getUser() {
        String data = ShareReferenceUtils.getStringPreference(KEY_USER);
        Gson gson = new Gson();
        return gson.fromJson(data, User.class);
    }
}
