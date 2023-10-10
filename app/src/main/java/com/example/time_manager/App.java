package com.example.time_manager;

import android.app.Application;

import androidx.room.Room;

import com.example.time_manager.data.api.ApiClient;
import com.example.time_manager.data.database.DataBase;
import com.example.time_manager.utils.Config;

import retrofit2.Retrofit;

public class App extends Application {

    public static App instance;

    private DataBase database;
    private Retrofit apiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, DataBase.class, "time_manager").fallbackToDestructiveMigration()
                .build();

    }

    public static App getInstance() {
        return instance;
    }

    public DataBase getDatabase() {
        return database;
    }

    public Retrofit getApiClient() {return apiClient;}
}