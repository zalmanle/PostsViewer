package com.example.postsviewer.adapters;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.postsviewer.R;
import com.example.postsviewer.model.Media_group;
import com.example.postsviewer.model.Media_item;
import com.example.postsviewer.model.Type;
import com.example.postsviewer.model.abstracts.Post;
import com.example.postsviewer.model.video.PostWithVideo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.LinkHolder> {


    private List<Post> items;
    private OnItemClickedListener onItemClickedListener;

    public PostAdapter(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
        this.items = new ArrayList<>();
    }


    public void initList(List<Post>posts){

        if(posts != null) {


            if (this.items.size() > 0) {


                DiffUtil.DiffResult diffResult = DiffUtil
                        .calculateDiff(
                                new PostsDiffCallback(posts,this.items));

                this.items.clear();
                this.notifyDataSetChanged();

                this.items.addAll(posts);
                this.notifyItemRangeInserted(0,posts.size());


                diffResult.dispatchUpdatesTo(this);

            } else {


                this.items.addAll(posts);
                notifyItemRangeInserted(0, posts.size());
            }
        }


    }

    @Override
    public int getItemViewType(int position) {

        Type type = items.get(position).getType();
        return type.getType();
    }

    @NonNull
    @Override
    public LinkHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {

        View itemView = null;
        if (type == Type.VIDEO) {
            itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.video_item, viewGroup, false);
            return new VideoHolder(itemView);
        }
        itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.link_item, viewGroup, false);

        return new LinkHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull LinkHolder linkHolder, int position) {

        final Post post = items.get(position);

        linkHolder.titleTV.setText(post.getTitle());
        linkHolder.summaryTV.setText(post.getSummary());

        Media_group[] media_group = post.getMedia_group();

        Media_item [] media_items = media_group[0].getMedia_item();

        String src = null;

        for(Media_item media_item : media_items){

            if(media_item.getKey().equals("image_base")){

                src = media_item.getSrc();
            }
        }
        if(TextUtils.isEmpty(src)){

            linkHolder.icon.setImageDrawable(
                    ContextCompat.getDrawable(linkHolder.icon.getContext(), R.drawable.no_image_128));
        }
        else {

            Picasso.get()
                    .load(src)
                    .into(linkHolder.icon);
        }

        linkHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onItemClickedListener.onItemClicked(post,post.getType().getType());
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class LinkHolder extends RecyclerView.ViewHolder{

        protected TextView titleTV;
        protected TextView summaryTV;
        protected ImageView icon;
        protected View container;

        public LinkHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.title);
            summaryTV = itemView.findViewById(R.id.summary);
            icon = itemView.findViewById(R.id.icon);
            container = itemView.findViewById(R.id.container);
        }
    }

    class VideoHolder extends LinkHolder{

        private FloatingActionButton fab;
        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            fab = (FloatingActionButton)itemView.findViewById(R.id.play_button);
        }
    }


    public interface OnItemClickedListener{

        void onItemClicked(Post post,int postType);
    }



}
