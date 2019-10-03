package com.example.postsviewer.model.link;

import com.example.postsviewer.model.abstracts.Post;

public class PostWithLink extends Post
{


    private Link link;

    public Link getLink ()
    {
        return link;
    }

    public void setLink (Link link)
    {
        this.link = link;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [summary = "+summary+", link = "+link+", id = "+id+", published = "+published+", type = "+type+", title = "+title+", updated = "+updated+", media_group = "+media_group+"]";
    }
}
