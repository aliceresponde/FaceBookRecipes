package com.example.alice.facebookrecipes.recipelist.ui.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alice.facebookrecipes.R;
import com.example.alice.facebookrecipes.db.entities.Recipe;
import com.example.alice.facebookrecipes.libs.base.ImageLoader;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.SendButton;
import com.facebook.share.widget.ShareButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alice on 7/4/16.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private List<Recipe> recipeList;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;

    public RecipesAdapter(OnItemClickListener onItemClickListener, ImageLoader imageLoader, List<Recipe> recipeList) {
        this.onItemClickListener = onItemClickListener;
        this.imageLoader = imageLoader;
        this.recipeList = recipeList;
    }

    public void setRecipes(List<Recipe> recipes) {
        Log.i("storage", "setRecipes" + recipes.size());
        this.recipeList = recipes;
        notifyDataSetChanged();
    }

    public void removeRecipe(Recipe recipe) {
        recipeList.remove(recipe);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_stored_recipes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe currentRecipe = recipeList.get(position);
        imageLoader.load(holder.imgRecipe, currentRecipe.getImageURL());
        holder.txtRecipeName.setText(currentRecipe.getTitle());
        holder.imgFav.setTag(currentRecipe.getFavorite());

        if (currentRecipe.getFavorite()){
            holder.imgFav.setImageResource(  android.R.drawable.btn_star_big_on  );
        }else{
            holder.imgFav.setImageResource(  android.R.drawable.btn_star_big_off  );
        }

        holder.setOnItemClickListener(currentRecipe, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgRecipe)
        ImageView imgRecipe;
        @BindView(R.id.txtRecipeName)
        TextView txtRecipeName;
        @BindView(R.id.imgFav)
        ImageView imgFav;
        @BindView(R.id.imgDelete)
        ImageView imgDelete;
        @BindView(R.id.fb_share)
        ShareButton fbShare;
        @BindView(R.id.fb_send)
        SendButton fbSend;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }


        public void setOnItemClickListener(final Recipe currentRecipe, final OnItemClickListener onItemClickListener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(currentRecipe);
                }
            });

            imgFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onFavClick(currentRecipe);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onDeleteClick(currentRecipe);
                }
            });

            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(currentRecipe.getSourceURL()))
                    .build();

            fbShare.setShareContent(content);
            fbSend.setShareContent(content);
        }
    }
}
