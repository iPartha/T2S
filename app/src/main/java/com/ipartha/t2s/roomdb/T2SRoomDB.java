package com.ipartha.t2s.roomdb;

import androidx.room.RoomDatabase;

import org.jetbrains.annotations.NotNull;

public abstract class T2SRoomDB extends RoomDatabase {
    @NotNull
    public abstract ConsumerMenuDao consumerMenuDao();
}