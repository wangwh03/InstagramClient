package com.wewang.instagramclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wewang.instagramclient.models.InstagramPhoto;

import java.util.List;

/**
 * Created by wewang on 10/25/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {
    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto photo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }

        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        TextView likesCount = (TextView) convertView.findViewById(R.id.tvLikesCounts);

        tvUsername.setText(photo.getUsername());
        tvCaption.setText(photo.getCaption());
        ivPhoto.setImageResource(0);
        likesCount.setText(photo.getLikesCount()+"");

        Picasso.with(getContext()).load(photo.getImageUrl()).into(ivPhoto);
        return convertView;
    }
}
