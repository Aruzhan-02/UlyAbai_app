package com.example.ulyabai.exam;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ulyabai.R;
import com.example.ulyabai.homepage.HomeAdapter;
import com.example.ulyabai.homepage.SingleNewsPage;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    public Context context;
    public List<CategoryModel> categoryList;

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView categoryTitle;

        public CategoryViewHolder(View view){
            super(view);

            categoryTitle = view.findViewById(R.id.categoryTitle);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String categories = categoryTitle.getText().toString();
            Intent intent = new Intent(context, CategoryExamsPage.class);
            intent.putExtra("category_title",categories);
            context.startActivity(intent);
        }
    }

    public CategoryAdapter(Context context, List<CategoryModel> categoryList){
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories, parent, false);

        return new CategoryAdapter.CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryModel item = categoryList.get(position);
        holder.categoryTitle.setText(item.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

}
