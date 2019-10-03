package com.example.postsviewer.model;

import com.example.postsviewer.model.link.LinkContainer;
import com.example.postsviewer.model.video.VideoContainer;

public class Container {

    private VideoContainer videoContainer;

    private LinkContainer linkContainer;

    public VideoContainer getVideoContainer() {
        return videoContainer;
    }

    public void setVideoContainer(VideoContainer videoContainer) {
        this.videoContainer = videoContainer;
    }

    public LinkContainer getLinkContainer() {
        return linkContainer;
    }

    public void setLinkContainer(LinkContainer linkContainer) {
        this.linkContainer = linkContainer;
    }
}
