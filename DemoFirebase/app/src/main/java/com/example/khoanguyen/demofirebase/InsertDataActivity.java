package com.example.khoanguyen.demofirebase;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.*;
import com.google.firebase.storage.*;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public class InsertDataActivity extends AppCompatActivity{
    Button btnInsert;
    ImageView ivAvatar;
    EditText etName;
    EditText etPhone;
    int REQUEST_CODE_IMAGE = 1;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseStorage mStorage = FirebaseStorage.getInstance();
    StorageReference storageRef = mStorage.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertdata);

        MapView();
        AddListnerComponent();


    }

    private void MapView(){
        btnInsert = findViewById(R.id.btnInsert);
        ivAvatar = findViewById(R.id.ivAvatar);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
    }

    private void AddListnerComponent(){
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = etName.getText().toString().trim();
                final String phone = etPhone.getText().toString().trim();

                if(name.equals("") || phone.equals("")){
                    Toast.makeText(InsertDataActivity.this,"Phone And Name not empty!",Toast.LENGTH_SHORT).show();
                    System.out.println("Click button Vao IF");
                }else{

                    StorageReference mountainsRef = storageRef.child("images/"+phone+".jpg");
                    System.out.println("AAA" + mountainsRef);
                    ivAvatar.setDrawingCacheEnabled(true);
                    ivAvatar.buildDrawingCache();
                    Bitmap bitmap = ivAvatar.getDrawingCache();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] data = baos.toByteArray();

                    UploadTask uploadTask = mountainsRef.putBytes(data);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(InsertDataActivity.this,"Upload Failed!",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            System.out.println("AAA"+ downloadUrl);

                            Contact ct = new Contact(name,phone,downloadUrl.toString());

                            mDatabase.child("Contacts").push().setValue(ct);

                            Toast.makeText(InsertDataActivity.this,"Successfull!",Toast.LENGTH_SHORT).show();
                            etName.setText("", TextView.BufferType.EDITABLE);
                            etPhone.setText("", TextView.BufferType.EDITABLE);
                            ivAvatar.setImageResource(R.drawable.no_image_icon);
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ivAvatar.setImageBitmap(bitmap);
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
