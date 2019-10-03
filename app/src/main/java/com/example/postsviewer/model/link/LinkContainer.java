package com.example.postsviewer.model.link;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class LinkContainer {

    @SerializedName("entry")
    private PostWithLink[]postsWithLinks;

    public PostWithLink[] getPostsWithLinks() {
        return postsWithLinks;
    }

    public List<PostWithLink> getPostsWithLinksList() {
        return Arrays.asList(postsWithLinks);
    }

    public void setPostsWithLinks(PostWithLink[] postsWithLinks) {
        this.postsWithLinks = postsWithLinks;
    }
}
