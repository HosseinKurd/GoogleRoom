package com.hosseinkurd.googleroom.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.hosseinkurd.googleroom.model.Word;

import java.util.List;

@Dao
public interface WordDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAlphabetizedWords();
    @Query("SELECT * from word_table ORDER BY word DESC")
    LiveData<List<Word>> getAlphabetizedWordsDesc();
    
    @Query("SELECT * from word_table ORDER BY modify_date ASC")
    LiveData<List<Word>> getAlphabetizedWordsByDate();
    @Query("SELECT * from word_table ORDER BY modify_date DESC")
    LiveData<List<Word>> getAlphabetizedWordsByDateDesc();

    // We do not need a conflict strategy, because the word is our primary key, and you cannot
    // add two items with the same primary key to the database. If the table has more than one
    // column, you can use @Insert(onConflict = OnConflictStrategy.REPLACE) to update a row.
    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();
}