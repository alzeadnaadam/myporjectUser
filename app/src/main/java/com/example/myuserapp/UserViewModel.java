package com.example.myuserapp;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository repository;
    private List<User> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public List<User> getAllUsers() {
        return allUsers;
    }
}
