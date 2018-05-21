package com.example.khoanguyen.demofirebase;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khoanguyen.demofirebase.R;
import com.example.khoanguyen.demofirebase.Contact;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Welcome on 8/27/2016.
 */
public class CustomAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private int resource;
    private List<Contact> arrContact;
    private Bitmap bitmap;

    public CustomAdapter(Context context, int resource, ArrayList<Contact> arrContact) {
        super(context, resource, arrContact);
        this.context = context;
        this.resource = resource;
        this.arrContact = arrContact;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_listview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvNumberPhone = (TextView) convertView.findViewById(R.id.tvPhoneNumber);
            viewHolder.ivAvatar = (ImageView) convertView.findViewById(R.id.ivAvatar);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Contact contact = arrContact.get(position);

        new GetImageFromUrl(viewHolder.ivAvatar).execute(contact.getAvatarUrl());
        viewHolder.tvName.setText(contact.getName());
        viewHolder.tvNumberPhone.setText(contact.getPhoneNumber());
        return convertView;
    }

    public class ViewHolder {
        TextView tvName, tvNumberPhone;
        ImageView ivAvatar;

    }

    public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap> {
        private ImageView imgV;

        public GetImageFromUrl(ImageView imgV){
            this.imgV = imgV;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            bitmap = null;
            try{
                InputStream str = new java.net.URL(urlDisplay).openStream();
                bitmap = BitmapFactory.decodeStream(str);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgV.setImageBitmap(bitmap);
        }
    }
}