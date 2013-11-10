package com.weddingpics.model;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weddingpics.R;
import com.weddingpics.service.LoadImageService;



public class AlbumPictureListAdapter extends BaseAdapter {
	
	 private static List<Picture> pictures;
	 
	 private LayoutInflater mInflater;

	 public AlbumPictureListAdapter(Context context, List<Picture> results) {
		 pictures = results;
	  mInflater = LayoutInflater.from(context);
	 }

	 public int getCount() {
	  return pictures.size();
	 }

	 public Object getItem(int position) {
	  return pictures.get(position);
	 }

	 public long getItemId(int position) {
	  return position;
	 }

	 public View getView(int position, View convertView, ViewGroup parent) {
	  ViewHolder holder;
	  if (convertView == null) {
		   convertView = mInflater.inflate(R.layout.album_photo_list, null);
		   holder = new ViewHolder();
		   holder.user_name = (TextView) convertView.findViewById(R.id.user_name);
		   holder.picture_date = (TextView) convertView.findViewById(R.id.picture_date);
		   holder.cover_image = (ImageView) convertView.findViewById(R.id.cover_image);
		   holder.picture_id = (TextView) convertView.findViewById(R.id.picture_id);
		   convertView.setTag(holder);
	  } else {
		  holder = (ViewHolder) convertView.getTag();
	  }
	  
	  
	  holder.user_name.setText(pictures.get(position).getUser().getFullName());
	  SimpleDateFormat  dateFormat = new SimpleDateFormat(" MMM dd yyy,hh:ss a");
	  holder.picture_date.setText(dateFormat.format(pictures.get(position).getPictureDate()));
	  Bitmap bitmap = null;
	  try {
			bitmap = LoadImageService.loadBitmap(pictures.get(position).getUrl());
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("AlbumPictureListAdapter", "Error occured while  Load Image.", e);
		}
	  if (bitmap != null) {
	  holder.cover_image.setImageBitmap(bitmap);
	  }
	  holder.picture_id.setVisibility(View.GONE);
	  holder.picture_id.setText(pictures.get(position).getPictureId().toString());
		
	  return convertView;
	 }

	 static class ViewHolder {
	  TextView	id;
	  TextView user_name;
	  TextView picture_date;
	  ImageView cover_image;
	  TextView picture_id;
	}
	 
	}