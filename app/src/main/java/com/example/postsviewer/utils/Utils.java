package com.example.postsviewer.utils;

import com.example.postsviewer.model.abstracts.Post;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Post> getCopyList(List<Post>oldList){

        if (oldList != null) {
            List<Post>newList = new ArrayList<>(oldList.size());
            for(int i = 0;i < oldList.size();i++){

                newList.add(oldList.get(i));
            }
            return newList;
        }
        else {

            return new ArrayList<>(0);
        }
    }
}
