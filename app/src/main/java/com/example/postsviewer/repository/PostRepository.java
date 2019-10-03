package com.example.postsviewer.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.postsviewer.model.Container;

import com.example.postsviewer.model.abstracts.Post;
import com.example.postsviewer.model.link.LinkContainer;
import com.example.postsviewer.model.link.PostWithLink;
import com.example.postsviewer.model.video.PostWithVideo;
import com.example.postsviewer.model.video.VideoContainer;
import com.example.postsviewer.networking.PostService;
import com.example.postsviewer.networking.Provider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class PostRepository {


    private MutableLiveData<List<Post>>postLiveData;



    public MutableLiveData<List<Post>>getPostLiveData(){
        postLiveData = new MutableLiveData<>();
        getDataFromApi();
        return postLiveData;
    }



    private void getDataFromApi() {

        PostService postService = Provider.getRetrofitInstance().create(PostService.class);

        Observable<VideoContainer> videoData = postService.getVideoData();

        Observable<LinkContainer> linkData = postService.getLinksData();

        Observable<Container> observable = Observable.zip(
                videoData, linkData, new BiFunction<VideoContainer, LinkContainer, Container>() {

                    @Override
                    public Container apply(VideoContainer videoContainer, LinkContainer linkContainer) throws Exception {

                        Container container = new Container();
                        container.setLinkContainer(linkContainer);
                        container.setVideoContainer(videoContainer);

                        return container;
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Consumer<Container>() {
            @Override
            public void accept(Container container) throws Exception {

                VideoContainer videoContainer = container.getVideoContainer();
                LinkContainer linkContainer = container.getLinkContainer();

                List<Post> posts = new ArrayList<>();

                List<PostWithVideo> postWithVideos = videoContainer.getPostWithVideosList();
                for (PostWithVideo postWithVideo : postWithVideos) {

                    posts.add(postWithVideo);
                }

                List<PostWithLink> postWithLinks = linkContainer.getPostsWithLinksList();
                for (PostWithLink postWithLink : postWithLinks) {

                    posts.add(postWithLink);
                }


                postLiveData.postValue(posts);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

                Log.i("TEST",throwable.getMessage());
            }
        });
    }







}
