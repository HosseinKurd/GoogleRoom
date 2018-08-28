package com.hosseinkurd.googleroom.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.hosseinkurd.googleroom.helper.Helper;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String word;
    @ColumnInfo(name = "modify_date")
    private String modifyDate;

    public Word() {
        this.word = "";
        this.modifyDate = Helper.getInstance().getCurrentTime();
    }

    @Ignore
    public Word(@NonNull String word, @NonNull String modifyDate) {
        this.word = word;
        this.modifyDate = modifyDate;
    }

    @Ignore
    public Word(@NonNull String word) {
        this.word = word;
        this.modifyDate = Helper.getInstance().getCurrentTime();
    }

    @NonNull
    public String getWord() {
        return this.word;
    }

    public void setWord(@NonNull String word) {
        this.word = word;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
}