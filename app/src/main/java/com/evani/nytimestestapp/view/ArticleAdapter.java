package com.evani.nytimestestapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.evani.nytimestestapp.data.model.Article;
import com.evani.nytimestestapp.databinding.NewsDataBinding;
import com.evani.nytimestestapp.presenter.NewsClickHandler;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Article> articleList;
    private LayoutInflater layoutInflater;
    private ImageView imageView;

    public ArticleAdapter(Context mContext, List<Article> articleList) {
        this.mContext = mContext;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }

        NewsDataBinding dataBinding = NewsDataBinding.inflate(layoutInflater, viewGroup, false);
        return new ViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Article article = articleList.get(i);
        viewHolder.bind(article);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final NewsDataBinding newsDataBinding;

        ViewHolder(NewsDataBinding dataBinding) {
            super(dataBinding.getRoot());
            this.newsDataBinding = dataBinding;
            newsDataBinding.setHandler(new NewsClickHandler(mContext));

        }

        void bind(Article article) {
            this.newsDataBinding.setNews(article);
        }
    }

    public ImageView getImageView() {
        return imageView;
    }


}
