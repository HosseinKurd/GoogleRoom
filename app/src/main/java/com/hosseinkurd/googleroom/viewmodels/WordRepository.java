package com.hosseinkurd.googleroom.viewmodels;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.hosseinkurd.googleroom.model.Word;

import java.util.List;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Word>> getAllWords() {
        mAllWords = mWordDao.getAlphabetizedWords();
        return mAllWords;
    }

    LiveData<List<Word>> getAllWordsDesc() {
        mAllWords = mWordDao.getAlphabetizedWordsDesc();
        return mAllWords;
    }

    LiveData<List<Word>> getAllWordsByDate() {
        mAllWords = mWordDao.getAlphabetizedWordsByDate();
        return mAllWords;
    }

    LiveData<List<Word>> getAllWordsByDateDesc() {
        mAllWords = mWordDao.getAlphabetizedWordsByDateDesc();
        return mAllWords;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}