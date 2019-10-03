package com.example.postsviewer.model;

public class Type
{
    public static final int VIDEO = 123;
    public static final int LINK = 456;

    private String value;

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    public int getType(){

        return value.equals("video")? VIDEO : LINK;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [value = "+value+"]";
    }
}
