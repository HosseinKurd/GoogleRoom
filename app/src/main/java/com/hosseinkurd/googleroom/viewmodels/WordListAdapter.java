package com.hosseinkurd.googleroom.viewmodels;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hosseinkurd.googleroom.R;
import com.hosseinkurd.googleroom.model.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {
        private TextView wordItemView, dateItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.txt_word);
            dateItemView = itemView.findViewById(R.id.txt_date);
        }

        void onBind(int position) {
            Word current = mWords.get(position);
            wordItemView.setText(current.getWord());
            dateItemView.setText(current.getModifyDate());
        }
    }

    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words

    public WordListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        holder.onBind(position);
    }

    public void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }
}