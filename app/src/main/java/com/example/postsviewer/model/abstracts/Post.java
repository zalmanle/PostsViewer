package com.example.postsviewer.model.abstracts;

import com.example.postsviewer.model.Media_group;
import com.example.postsviewer.model.Type;

public abstract class Post implements IFilterable{

    protected String summary;

    protected String id;

    protected String published;

    protected Type type;

    protected String title;

    protected String updated;

    protected Media_group[] media_group;

    public String getSummary ()
    {
        return summary;
    }

    public void setSummary (String summary)
    {
        this.summary = summary;
    }



    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPublished ()
    {
        return published;
    }

    public void setPublished (String published)
    {
        this.published = published;
    }

    public Type getType ()
    {
        return type;
    }

    public void setType (Type type)
    {
        this.type = type;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getUpdated ()
    {
        return updated;
    }

    public void setUpdated (String updated)
    {
        this.updated = updated;
    }


    public Media_group[] getMedia_group ()
    {
        return media_group;
    }

    public void setMedia_group (Media_group[] media_group)
    {
        this.media_group = media_group;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [summary = "+summary+", id = "+id+", published = "+published+", type = "+type+", title = "+title+", updated = "+updated+", media_group = "+media_group+"]";
    }

    @Override
    public boolean apply(String pattern) {
        return title.startsWith(pattern);
    }
}
