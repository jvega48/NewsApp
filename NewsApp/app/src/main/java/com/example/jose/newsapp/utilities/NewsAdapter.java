package com.example.jose.newsapp.utilities;
import android.support.v7.widget.RecyclerView;
import com.example.jose.newsapp.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ItemHolder> {
    private ArrayList<NewsItem> data;
    private ItemClickListener listener;

    public ArrayList<NewsItem> getData() {
        return data;
    }

    public NewsAdapter(ArrayList<NewsItem> data, ItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }
    public interface ItemClickListener{
        void onItemClick(int clickedItemIndex);
    }
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ItemHolder(view);
    }
    public int getItemCount() {
        if(data == null) return 0;

        return data.size();
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bind(position);
    }
    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, author, description, urlTOImage, url, publishedAt;
        //urlTOImage (Change to make image view ) re-change for normal
        //ImageView urlTOImage;
        ItemHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title_article);
            author = (TextView)view.findViewById(R.id.author);
            description = (TextView) view.findViewById(R.id.description);
            //url = (TextView) view.findViewById(R.id.url);
            //urlTOImage =(TextView) view.findViewById(R.id.urlToImage);
            //urlTOImage =(ImageView) view.findViewById(R.id.urlToImage);
            publishedAt = (TextView) view.findViewById(R.id.publishedAt);
            view.setOnClickListener(this);
        }
        public void bind(int position){
            NewsItem item = data.get(position);
            title.setText(item.getTitle());
            author.setText(item.getAuthor());
            description.setText(item.getDescription());
            //url.setText(item.getUrl());
            //urlTOImage.setText(item.getUrlTOImage());
            //urlTOImage.setImageBitmap(item.getUrlTOImage());
            publishedAt.setText(item.getPublushedAt());
        }

        @Override
        public void onClick(View view){
            int posit = getAdapterPosition();
            listener.onItemClick(posit);
        }
    }
}
