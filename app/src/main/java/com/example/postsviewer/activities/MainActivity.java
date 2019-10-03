package com.example.postsviewer.activities;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.postsviewer.R;
import com.example.postsviewer.adapters.PostAdapter;
import com.example.postsviewer.model.Type;
import com.example.postsviewer.model.abstracts.Post;
import com.example.postsviewer.model.link.Link;
import com.example.postsviewer.model.link.PostWithLink;
import com.example.postsviewer.model.video.Content;
import com.example.postsviewer.model.video.PostWithVideo;
import com.example.postsviewer.viewmodel.MainActivityViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity implements PostAdapter.OnItemClickedListener {

    private MainActivityViewModel viewModel;
    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private LinearLayoutManager layoutManager;
    private EditText filterET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecycler();

        initFilterField();

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        viewModel.getPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable List<Post> posts) {

                if((posts != null)&&(posts.size() > 0)) {

                    adapter.initList(posts);
                    viewModel.setFullList(posts);
                }
                else {

                    Log.i("TEST","empty list post");
                }
            }
        });

        viewModel.getFilteredPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable List<Post> posts) {

                if((posts != null)) {

                    adapter.initList(posts);
                }
                else {

                    Log.i("TEST","empty list filtered");
                }
            }
        });




    }

    private void initFilterField() {
        filterET = findViewById(R.id.filterET);
        filterET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                viewModel.filterContacts(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        filterET.clearFocus();
        recyclerView.requestFocus();
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.posts_recycler);
        adapter = new PostAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }


    @Override
    protected void onDestroy() {
        viewModel.dispose();
        super.onDestroy();
    }

    @Override
    public void onItemClicked(Post post, int postType) {


        switch (postType){

            case Type.VIDEO:

                if(post instanceof PostWithVideo){

                    PostWithVideo postWithVideo = (PostWithVideo)post;
                    Content content = postWithVideo.getContent();
                    String videoUrl = content.getSrc();

                    Intent videoPlayerIntent = new Intent(this,VideoPlayerActivity.class);
                    videoPlayerIntent.putExtra(VideoPlayerActivity.VIDEO_KEY,videoUrl);
                    startActivity(videoPlayerIntent);

                }
                break;

            case Type.LINK:

                if(post instanceof PostWithLink){

                    PostWithLink postWithLink = (PostWithLink)post;
                    Link link = postWithLink.getLink();
                    String linkUrl = link.getHref();

                    Intent webViewIntent = new Intent(this,WebViewActivity.class);
                    webViewIntent.putExtra(WebViewActivity.LINK_KEY,linkUrl);
                    startActivity(webViewIntent);
                }

                break;
        }
    }
}
