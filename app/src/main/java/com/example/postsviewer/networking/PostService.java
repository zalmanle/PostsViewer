package com.example.postsviewer.networking;

import com.example.postsviewer.model.link.LinkContainer;
import com.example.postsviewer.model.video.VideoContainer;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PostService {

    @GET("applicaster-employees/israel_t\n" +
            "eam/Elad/assignment/link_json.json")
    Observable<LinkContainer> getLinksData();

    @GET("applicaster-employees/israel_t\n"+
                "eam/Elad/assignment/video_json.json")
    Observable<VideoContainer>getVideoData();
}
