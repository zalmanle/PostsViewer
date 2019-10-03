package com.example.postsviewer.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
//https://exoplayer.dev/hello-world.html
//https://android.jlelse.eu/android-exoplayer-starters-guide-6350433f256c
//https://stackoverflow.com/questions/53353248/exoplayer-player-video-mp4-e-stream-m3u8
import com.example.postsviewer.R;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.DefaultMediaSourceEventListener;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.IOException;

//https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8
//https://exoplayer.dev/
//https://stackoverflow.com/questions/53353248/exoplayer-player-video-mp4-e-stream-m3u8
//https://stackoverflow.com/questions/53353248/exoplayer-player-video-mp4-e-stream-m3u8
//2.10.5 available => my 2.9.6
public class VideoPlayerActivity extends AppCompatActivity implements Player.EventListener {

    public static final String VIDEO_KEY = "video";

    private SimpleExoPlayer player;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);


        Intent intent = getIntent();

        if(intent != null){

            try {

                url = intent.getStringExtra(VIDEO_KEY);

                final PlayerView playerView =(PlayerView) findViewById(R.id.post_video_view);
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                TrackSelection.Factory videoTrackSelectionFactory = new
                        AdaptiveTrackSelection.Factory(bandwidthMeter);
                TrackSelector trackSelector = new
                        DefaultTrackSelector(videoTrackSelectionFactory);

                player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);


                // HLS
                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "Posts Viewer"));

                // Produces Extractor instances for parsing the media data.
                ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
                MediaSource mediaSource = new HlsMediaSource(Uri.parse(url),
                        dataSourceFactory, null, new DefaultMediaSourceEventListener() {
                    @Override
                    public void onLoadError(int windowIndex, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException error, boolean wasCanceled) {

                        Log.i("TEST","error message onLoadError" +error.getMessage());
                    }
                });

                playerView.setPlayer(player);
                player.addListener(this);
                playerView.requestFocus();
                player.prepare(mediaSource);
                player.setPlayWhenReady(true);



            }
            catch (Exception e){


            }
        }





    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

        Log.i("TEST","error message" +error.getMessage());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(VIDEO_KEY, url);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        url = savedInstanceState.getString(VIDEO_KEY);
    }
}
