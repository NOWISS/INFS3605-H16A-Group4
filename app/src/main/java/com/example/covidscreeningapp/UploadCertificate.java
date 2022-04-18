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
    private ImageView vax, res;
    private String FirstName, LastName, MobileNumber, Destination, CheckinTime, CheckoutTime, points;
    private ProgressDialog pd;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Employee");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private Uri imageUri, iamgeUri1;
    private static int latestId = 4;
    private static int PICK_IMAGE = 1;
    private String Green = "Green", Yellow = "Yellow", Red = "Red";
    private String color;
    private ImageView lefticon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_doc);

        lefticon = findViewById(R.id.back);
        // Make the return button
        lefticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(UploadCertificate.this,EmployeeCont.class);
                startActivity(intent1);
            }
        });

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        MobileNumber = bundle.getString("mobile");
        Destination = bundle.getString("destination");
        LastName = bundle.getString("lastname");
        FirstName = bundle.getString("firstname");
        CheckinTime = bundle.getString("checkin");
        CheckoutTime = bundle.getString("checkout");
        points = bundle.getString("points");


        upload = findViewById(R.id.btnup);
        vax = findViewById(R.id.upload_vax);
        res = findViewById(R.id.upload_result);
        pd = new ProgressDialog(UploadCertificate.this);
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
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2);
                Log.d(TAG, "Image selected ");
            }
        });

        if (points.equals("0")) {
            color = Green;
        } else if (points.equals("2")|| points.equals("1")) {
            color = Yellow;
        } else {
            color = Red;
        }



        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImage();

                // calculate points if the person can pass
                Intent intent1 = new Intent(UploadCertificate.this, Pass.class);
                Bundle extras = new Bundle();
                extras.putString("firstname", FirstName);
                extras.putString("lastname", LastName);
                extras.putString("location", Destination);
                extras.putString("color", color);
                Log.d(TAG, "onClick: Color" + color);
                intent1.putExtras(extras);
                startActivity(intent1);
            }
        });


    }

    public void UploadImage() {

        if (imageUri != null && iamgeUri1 != null) {
            pd.setTitle("Uploading...");
            pd.show();
            StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
            StorageReference fileRef1 = reference.child(System.currentTimeMillis() + "." + getFileExtension(iamgeUri1));
            fileRef1.putFile(iamgeUri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileRef1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri muri) {
                            fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            Employee emp = new Employee(FirstName, LastName, MobileNumber, Destination, uri.toString(), muri.toString(), CheckinTime, CheckoutTime, color);
                                            Log.d(TAG, "First Name" + FirstName + MobileNumber);
                                            generateId();
                                            Log.d(TAG, "ID:" + generateId());
                                            root.child(String.valueOf(latestId)).setValue(emp);
                                            pd.dismiss();
                                            Toast.makeText(UploadCertificate.this, "Your Certificates Are Uploaded Successfully!", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                }
                            });

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


        } else {

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
        }else {
            iamgeUri1 = data.getData();
            res.setImageURI(iamgeUri1);
        }
    }
    public static int generateId() {
        return latestId++;
    }
}
