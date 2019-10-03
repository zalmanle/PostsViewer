package com.example.postsviewer.model.video;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;


public class VideoContainer {

    @SerializedName("entry")
    private PostWithVideo[]postWithVideos;

    public PostWithVideo[] getPostWithVideos() {
        return postWithVideos;
    }

    public List<PostWithVideo> getPostWithVideosList() {
        return Arrays.asList(postWithVideos);
    }

    public void setPostWithVideos(PostWithVideo[] postWithVideos) {
        this.postWithVideos = postWithVideos;
    }
}
