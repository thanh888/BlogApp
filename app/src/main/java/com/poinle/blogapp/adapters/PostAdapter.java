package com.poinle.blogapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poinle.blogapp.Constant;
import com.poinle.blogapp.R;
import com.poinle.blogapp.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder>{

    private Context context;
    private ArrayList<Post> posts;

    public PostAdapter(Context context, ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Post post = posts.get(position);
        Picasso.get().load(Constant.URL+"storage/profiles/"+ post.getUser().getPhoto()).into(holder.imgProfile);
        Picasso.get().load(Constant.URL+"storage/posts/"+ post.getPhoto()).into(holder.imgPost);
        holder.txtName.setText(post.getUser().getUsername());
        holder.txtComments.setText("View all"+ post.getComment());
        holder.txtLikes.setText(post.getLike()+"Likes");
        holder.txtDate.setText(post.getDate());
        holder.txtDesc.setText(post.getDesc());


    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{

        private TextView txtName, txtDate,txtDesc, txtLikes, txtComments;
        private CircleImageView imgProfile;
        ImageView imgPost;
        private ImageButton btnPostOption, btnPostLikes, btnPostComment;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtPostName);
            txtDate = itemView.findViewById(R.id.txtPostDate);
            txtDesc = itemView.findViewById(R.id.txtPostDesc);
            txtLikes = itemView.findViewById(R.id.txtPostLike);
            txtComments = itemView.findViewById(R.id.txtPostComment);
            imgProfile = itemView.findViewById(R.id.imgPostProfile);
            imgPost = itemView.findViewById(R.id.imagePostPhoto);
            btnPostOption = itemView.findViewById(R.id.btnPostOption);
            btnPostLikes = itemView.findViewById(R.id.btnPostLike);
            btnPostComment = itemView.findViewById(R.id.btnPostComment);


        }
    }
}
