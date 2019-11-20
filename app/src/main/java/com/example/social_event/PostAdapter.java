package com.example.social_event;

import android.content.Context;
import android.gesture.GestureLibraries;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public Context mcontext;
    public List<Post> mpost;

    private FirebaseUser firebaseUser;

    public PostAdapter(Context mcontext, List<Post> mpost) {
        this.mcontext = mcontext;
        this.mpost = mpost;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( mcontext ).inflate( R.layout.post_item, parent, false );

        return new PostAdapter.ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Post post =mpost.get(i);

        Glide.with( mcontext ).load(post.getPostimage()).into(viewHolder.post_image);
        if(post.getDescription().equals(""))
        {
            viewHolder.description.setVisibility( View.GONE );
        }
        else
        {
            viewHolder.description.setVisibility( View.VISIBLE );
            viewHolder.description.setText( post.getDescription());
        }
        publisherInfo( viewHolder.image_profile, viewHolder.username,viewHolder.publisher,post.getPublisher());
    }

    @Override
    public int getItemCount() {

        return mpost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image_profile, post_image, like, comment, save;
        public TextView username, likes, publisher, description, comments;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            image_profile = itemView.findViewById( R.id.image_profile );
            post_image = itemView.findViewById( R.id.post_image );
            like = itemView.findViewById( R.id.like );
            comment = itemView.findViewById( R.id.comment );
            save = itemView.findViewById( R.id.save );

            username = itemView.findViewById( R.id.username );
            likes = itemView.findViewById( R.id.likes );
            publisher = itemView.findViewById( R.id.publisher );
            description = itemView.findViewById( R.id.description );
            comment = itemView.findViewById( R.id.comments );
        }
    }

    private void publisherInfo(final ImageView image_profile, final TextView username, final TextView publisher, String userid) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference( "Users" ).child( userid );

        reference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             User user = dataSnapshot.getValue(User.class);
             Glide.with(mcontext).load(user.getImageurl()).into(image_profile);
             username.setText(user.getUsername());
             publisher.setText( user.getUsername() );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

    }
}

