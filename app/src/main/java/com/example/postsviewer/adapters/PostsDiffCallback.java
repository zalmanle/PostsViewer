package com.example.postsviewer.adapters;


import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.postsviewer.model.abstracts.Post;

import java.util.List;

public class PostsDiffCallback extends DiffUtil.Callback {


    private List<Post> newPostsList;
    private List<Post> oldPostsList;

    public PostsDiffCallback(List<Post> newPostsList, List<Post> oldPostsList) {
        this.newPostsList = newPostsList;
        this.oldPostsList = oldPostsList;
    }

    @Override
    public int getOldListSize() {
        return this.oldPostsList.size();
    }

    @Override
    public int getNewListSize() {
        return this.newPostsList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return this.oldPostsList.get(oldItemPosition).getId().equals(
                 this.newPostsList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return this.oldPostsList.get(oldItemPosition)
                .equals(this.newPostsList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
