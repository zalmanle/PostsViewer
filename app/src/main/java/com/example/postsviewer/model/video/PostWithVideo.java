package com.example.postsviewer.model.video;

import com.example.postsviewer.model.abstracts.Post;

public class PostWithVideo extends Post
{

    private Content content;


    public Content getContent ()
    {
        return content;
    }

    public void setContent (Content content)
    {
        this.content = content;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [summary = "+summary+", id = "+id+", type = "+type+", title = "+title+", updated = "+updated+", content = "+content+", media_group = "+media_group+"]";
    }
}
