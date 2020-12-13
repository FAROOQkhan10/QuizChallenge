package com.example.quizchallenge;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quizchallenge.databinding.RowLeaderboardBinding;

import java.util.ArrayList;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.leaderboardViewHolder> {
    Context context;
    ArrayList<User> users;
    public  LeaderboardAdapter(Context context , ArrayList<User> users)
    {
       this.context=context;
       this.users=users;
    }


    @NonNull
    @Override
    public leaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view = LayoutInflater.from(context).inflate(R.layout.row_leaderboard,parent,false);

        return new leaderboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull leaderboardViewHolder holder, int position) {
       User user = users.get(position);
       holder.binding.name.setText(user.getName());
       holder.binding.coins.setText(String.valueOf(user.getCoins()));
       holder.binding.index.setText(String.format("%d.",position+1));
        Glide.with(context)
                .load(user.getProfile())
                .into(holder.binding.imageView7);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class leaderboardViewHolder extends RecyclerView.ViewHolder{

        RowLeaderboardBinding binding;
        public leaderboardViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RowLeaderboardBinding.bind(itemView);

        }
    }

}
