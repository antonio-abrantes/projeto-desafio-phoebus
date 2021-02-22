package com.aadev.aecomics.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aadev.aecomics.R;
import com.aadev.aecomics.models.Comic;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterComic extends RecyclerView.Adapter<AdapterComic.MyViewHolder> {

    private List<Comic> comics = new ArrayList<>();
    private Context context;

    public AdapterComic(List<Comic> comics, Context context) {
        this.comics = comics;
        this.context = context;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView title;
        TextView price;
        TextView rare;
        TextView description;
        ImageView thumbnail;

        public MyViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            thumbnail = itemView.findViewById(R.id.imageComic);
            price = itemView.findViewById(R.id.textViewPrice);
            rare = itemView.findViewById(R.id.textViewRare);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_comic, parent, false);
        return new AdapterComic.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Comic comic = comics.get(position);
        holder.title.setText(comic.title);
        holder.price.setText(String.format("US$ %.2f", Double.parseDouble(comic.prices.get(0).price)));

        if(comic.rare){
            holder.rare.setText("RARIDADE");
            holder.rare.setVisibility(View.VISIBLE);
        }

        if(!comic.images.isEmpty()){
            String url = comic.images.get(0).path + "." + comic.images.get(0).extension;
            Picasso.get().load(url).into(holder.thumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

}
