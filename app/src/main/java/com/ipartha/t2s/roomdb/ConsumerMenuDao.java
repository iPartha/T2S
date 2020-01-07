package com.ipartha.t2s.roomdb;

import androidx.room.Insert;
import androidx.room.Query;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ConsumerMenuDao {
    @Insert
    void insertAll(@NotNull List var1);

    @Query("SELECT * FROM consumer_menu")
    @NotNull
    List getAll();
}
