package com.hosseinkurd.googleroom.presenter;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.hosseinkurd.googleroom.model.Word;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
    }

    public WordViewModel sortByNameAsc() {
        mAllWords = mRepository.getAllWords();
        return this;
    }

    public WordViewModel sortByNameDesc() {
        mAllWords = mRepository.getAllWordsDesc();
        return this;
    }

    public WordViewModel sortByDateAsc() {
        mAllWords = mRepository.getAllWordsByDate();
        return this;
    }

    public WordViewModel sortByDateDesc() {
        mAllWords = mRepository.getAllWordsByDateDesc();
        return this;
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }
}