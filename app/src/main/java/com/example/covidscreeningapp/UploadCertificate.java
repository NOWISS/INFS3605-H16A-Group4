package com.example.covidscreeningapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadCertificate extends AppCompatActivity {

    private Button upload;
    private ImageView vax,res;
    private String FirstName, LastName,MobileNumber,Destination,CheckinTime,CheckoutTime;
    private ProgressDialog pd;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Employee");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private Uri imageUri;
    private static int latestId = 0;
    private static final int PICK_IMAGE = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_doc);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        MobileNumber = bundle.getString("mobile");
        Destination = bundle.getString("destination");
        LastName = bundle.getString("lastname");
        FirstName = bundle.getString("firstname");
        CheckinTime = bundle.getString("checkin");
        CheckoutTime = bundle.getString("checkout");


        upload = findViewById(R.id.btnup);
        vax = findViewById(R.id.upload_vax);
        res = findViewById(R.id.upload_result);
        pd =  new ProgressDialog(UploadCertificate.this);
        vax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                Log.d(TAG, "Image selected ");
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImage();
            }
        });
    }

    public void UploadImage() {

        if (imageUri != null) {
            pd.setTitle("Uploading...");
            pd.show();
            StorageReference fileRef = reference.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));
            fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Employee emp = new Employee(FirstName,LastName,MobileNumber,Destination,String.valueOf(uri),null,CheckinTime,CheckoutTime);
                            Log.d(TAG, "First Name"+FirstName+MobileNumber);
                            generateId();
                            Log.d(TAG, "ID:"+generateId());
                            root.child(String.valueOf(latestId)).setValue(emp);
                            Toast.makeText(UploadCertificate.this, "Your information is Uploaded Successfully!", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(UploadCertificate.this, "Something went wrong when uploading!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {

            Toast.makeText(UploadCertificate.this, "Vaccination or PCR/RAT result is empty", Toast.LENGTH_LONG).show();

        }
    }


    private String getFileExtension(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE){
            imageUri = data.getData();
            vax.setImageURI(imageUri);
        }
    }
    public static int generateId() {
        return latestId++;
    }
}
