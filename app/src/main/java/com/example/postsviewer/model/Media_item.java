package com.example.postsviewer.model;

public class Media_item
{
    private String src;

    private String scale;

    private String type;

    private String key;

    public String getSrc ()
    {
        return src;
    }

    public void setSrc (String src)
    {
        this.src = src;
    }

    public String getScale ()
    {
        return scale;
    }

    public void setScale (String scale)
    {
        this.scale = scale;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key)
    {
        this.key = key;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [src = "+src+", scale = "+scale+", type = "+type+", key = "+key+"]";
    }
}
