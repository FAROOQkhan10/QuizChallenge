package com.example.quizchallenge;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.CategoryVewHolder> {
    Context context;
    ArrayList<categoryModel>categoryModels;
   public  categoryAdapter(Context context, ArrayList<categoryModel>categoryModels){
       this.context=context;
       this.categoryModels=categoryModels;
   }
    @NonNull
    @Override
    public CategoryVewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category,null);
        return new CategoryVewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVewHolder holder, int position) {
    categoryModel model= categoryModels.get(position);
    holder.textView.setText(model.getCategoryName());
        Glide.with(context)
                .load(model.getCategoryImage())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,QuizActivity.class);
                intent.putExtra("catId",model.getCategoryId());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class CategoryVewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView  textView;
        public CategoryVewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView  = itemView.findViewById(R.id.category);
        }
    }

}
