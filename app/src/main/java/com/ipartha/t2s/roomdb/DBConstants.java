package com.ipartha.t2s.roomdb;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.DefaultConstructorMarker;

public final class DBConstants {
    @NotNull
    private static final String DB_NAME = "t2s_db";
    @NotNull
    public static final String CONSUMER_MENU_TABLE_NAME = "consumer_menu";
    @NotNull
    public static final String CONSUMER_MENU_PRICE = "price";
    @NotNull
    public static final String CONSUMER_MENU_NAME = "name";
    public static final DBConstants.Companion Companion = new DBConstants.Companion((DefaultConstructorMarker)null);

    public static final class Companion {
        @NotNull
        public final String getDB_NAME() {
            return DBConstants.DB_NAME;
        }

        private Companion() {
        }

        // $FF: synthetic method
        public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

