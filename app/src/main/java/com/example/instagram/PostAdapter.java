package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.instagram.model.Post;
import com.example.instagram.model.User;
import com.parse.ParseImageView;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    ArrayList<Post> posts;
    ArrayList<User> users;
    public Context context;
    public String USER_KEY = "post";

    public PostAdapter(ArrayList<Post> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View postView = inflater.inflate(R.layout.item_post, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Post post = posts.get(i);
        //User user = users.get(i);
        viewHolder.ivImage.setParseFile(post.getImage());
        //viewHolder.tvUser.setText(user.getUsername());
        viewHolder.tvCaption.setText(post.getDescription());
        viewHolder.ivImage.setParseFile(post.getMedia());
        viewHolder.ivImage.loadInBackground();
        viewHolder.tvTime.setText(getRelativeTimeAgo(post.getCreatedAt().toString()));
    }

    public static String getRelativeTimeAgo(String rawJsonDate) {
        String postFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(postFormat, Locale.ENGLISH);
        sf.setLenient(true);
        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return relativeDate;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ParseImageView ivImage;
        public ImageView ivSend;
        public ImageView ivLike;
        public ImageView ivComment;
        public ImageView ivSave;
        public TextView tvCaption;
        public TextView tvUser;
        public TextView tvTime;

        public ViewHolder(View postView) {
            super(postView);
            ivImage = itemView.findViewById(R.id.ivImage);
            ivSend = itemView.findViewById(R.id.ivSend);
            ivLike = itemView.findViewById(R.id.ivLike);
            ivComment = itemView.findViewById(R.id.ivComment);
            ivSave = itemView.findViewById(R.id.ivSave);
            tvCaption = itemView.findViewById(R.id.tvCaption);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvTime = itemView.findViewById(R.id.tvTime);
            ivImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Post post = posts.get(position);
                Intent intent = new Intent(context, PostDetailsActivity.class);
                intent.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));
                context.startActivities(new Intent[]{intent});
            }
        }
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }
}
