package com.wewang.instagramclient;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.wewang.instagramclient.models.InstagramPhoto;

import java.text.NumberFormat;
import java.util.Date;
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

        ImageView ivUserProfileImage = (ImageView) convertView.findViewById(R.id.ivUserProfileImage);
        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        TextView likesCount = (TextView) convertView.findViewById(R.id.tvLikesCounts);
        TextView relativeTimestamp = (TextView) convertView.findViewById(R.id.tvRelativeTimestamp);

        ivUserProfileImage.setImageResource(0);
        tvUsername.setText(photo.getUsername());
        tvCaption.setText(photo.getCaption());
        ivPhoto.setImageResource(0);
        likesCount.setText(NumberFormat.getIntegerInstance().format(photo.getLikesCount()));
        relativeTimestamp.setText(DateUtils.getRelativeTimeSpanString(photo.getCreatedTime()*1000,
                new Date().getTime(), 0));

        Picasso.with(getContext())
                .load(photo.getImageUrl())
                .into(ivPhoto);

        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.GRAY)
                .borderWidthDp(1)
                .cornerRadiusDp(30)
                .oval(false)
                .build();

        Picasso.with(getContext())
                .load(photo.getUserProfilePicture())
                .fit()
                .transform(transformation)
                .into(ivUserProfileImage);

        return convertView;
    }
}
