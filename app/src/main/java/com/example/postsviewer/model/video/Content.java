package com.example.postsviewer.model.video;

public class Content
{
    private String src;

    private String type;

    public String getSrc ()
    {
        return src;
    }

    public void setSrc (String src)
    {
        this.src = src;
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
        return "ClassPojo [src = "+src+", type = "+type+"]";
    }
}
