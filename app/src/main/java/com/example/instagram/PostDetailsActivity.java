package com.example.instagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.instagram.model.Post;
import com.parse.ParseImageView;

import org.parceler.Parcels;

public class PostDetailsActivity extends AppCompatActivity {

    Post post;
    ImageView ivHome;
    ImageView ivPost;
    ImageView ivLogout;
    ParseImageView ivImage;
    TextView tvCaption;
    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        ivHome = findViewById(R.id.ivHome);
        ivPost = findViewById(R.id.ivPost);
        ivLogout = findViewById(R.id.ivLogout);
        ivImage = findViewById(R.id.ivImage);
        tvCaption = findViewById(R.id.tvCaption);
        tvTime = findViewById(R.id.tvTime);

        post = Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));
        tvCaption.setText(post.getDescription());
        ivImage.setParseFile(post.getImage());
        ivImage.setParseFile(post.getMedia());
        ivImage.loadInBackground();
        tvTime.setText(PostAdapter.getRelativeTimeAgo(post.getCreatedAt().toString()));

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(PostDetailsActivity.this, HomeActivity.class);
                startActivity(home);
            }
        });
        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(PostDetailsActivity.this, MainActivity.class);
                startActivity(logout);
            }
        });
        ivPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newPost = new Intent(PostDetailsActivity.this, PostActivity.class);
                startActivity(newPost);
            }
        });
    }
}
