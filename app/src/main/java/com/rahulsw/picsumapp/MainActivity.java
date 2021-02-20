package com.rahulsw.picsumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

   private List<OutputImg> imgList = new ArrayList<>();

   GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridView);


        loadImages();
    }





    public void loadImages()
    {

        Call<List<OutputImg>> outputImage = RetrofitApi.getInterfaceApi().loadImages();
        outputImage.enqueue(new Callback<List<OutputImg>>() {
            @Override
            public void onResponse(Call<List<OutputImg>> call, Response<List<OutputImg>> response) {

                if (response.isSuccessful())
                {
                    String msg = "successful";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                    imgList = response.body();



                    DataAdapater dataAdapater = new DataAdapater(imgList,MainActivity.this);
                    gridView.setAdapter(dataAdapater);

                }else
                {

                    String msg = "failed something wrong";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<OutputImg>> call, Throwable t) {

                String msg = t.getMessage();
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();

            }
        });


    }


    public class DataAdapater extends BaseAdapter{

        private List<OutputImg> outputImgList;
        private Context context;
        private LayoutInflater layoutInflater;

        public DataAdapater(List<OutputImg> outputImgList, Context context) {
            this.outputImgList = outputImgList;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return outputImgList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null)
            {
                convertView = layoutInflater.inflate(R.layout.picsum_data_list,parent,false);
            }

            ImageView imageView = convertView.findViewById(R.id.imgs);
            TextView textView = convertView.findViewById(R.id.author);
            TextView textView1 = convertView.findViewById(R.id.idd);
            TextView textView2 = convertView.findViewById(R.id.urls);



            textView.setText(outputImgList.get(position).getAuthor());
            String iss= Integer.toString(outputImgList.get(position).getId());
            textView1.setText(iss);
            textView2.setText(outputImgList.get(position).getUrl());


            String url = "https://picsum.photos/300/300?image="+outputImgList.get(position).getId();
            Glide.with(context)
                    .load(url)
                    .override(300,300)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(imageView);



            return convertView;
        }
    }


}