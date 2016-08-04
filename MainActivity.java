package com.example.ridzi.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private  static final int CAMERA_REQUEST=0;
    ImageView imageView;
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imageView);
        click=(Button)findViewById(R.id.button);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST);
            }
        });
    }

    protected  void onActivityResult(int requestCode,int resultData,Intent data)
    {
        if(requestCode==CAMERA_REQUEST)
        {
            Bitmap photo=(Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }
}
