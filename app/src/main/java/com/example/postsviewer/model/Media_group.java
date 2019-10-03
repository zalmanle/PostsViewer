package com.example.postsviewer.model;

public class Media_group
{
    private Media_item[] media_item;

    private String type;

    public Media_item[] getMedia_item ()
    {
        return media_item;
    }

    public void setMedia_item (Media_item[] media_item)
    {
        this.media_item = media_item;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [media_item = "+media_item+", type = "+type+"]";
    }
}
