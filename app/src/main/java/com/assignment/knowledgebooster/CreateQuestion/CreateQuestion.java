package com.assignment.knowledgebooster.CreateQuestion;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.knowledgebooster.FirebaseClass.Pictionary;
import com.assignment.knowledgebooster.FirebaseClass.Questions;
import com.assignment.knowledgebooster.FirebaseClass.Scramble;
import com.assignment.knowledgebooster.FirebaseClass.Selection;
import com.assignment.knowledgebooster.PermissionUtils;
import com.assignment.knowledgebooster.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class CreateQuestion extends AppCompatActivity implements View.OnClickListener{
    Questions questions;
    SharedPreferences mPrefs;
    AlertDialog.Builder builder;
    Button btnSelection, btnScramble, btnPictionary, btnQuestionCategory;
    CreateQuestionSelection createQuestionSelection;
    CreateQuestionScramble createQuestionScramble;
    CreateQuestionPictionary createQuestionPictionary;
    Activity activity;
    byte[] imageData;
    public FragmentManager fm;

    public static final int CAMERA_PERMISSIONS_REQUEST = 2;
    public static final int CAMERA_IMAGE_REQUEST = 3;
    public static final String FILE_NAME = "temp.jpg";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String TAG = CreateQuestion.class.getSimpleName();
    private static final int GALLERY_PERMISSIONS_REQUEST = 0;
    private static final int GALLERY_IMAGE_REQUEST = 1;
    private static final int MAX_DIMENSION = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        btnQuestionCategory = findViewById(R.id.btnQuestionCategory);
        fm = getSupportFragmentManager();

        activity = this;

        createQuestionSelection =  new CreateQuestionSelection();
        createQuestionScramble =  new CreateQuestionScramble();

        createQuestionPictionary =  new CreateQuestionPictionary();

        retrieveQuestion();
        setNightMode();
    }

    protected void setNightMode(){
        mPrefs = this.getSharedPreferences("myPreference",0);
        if(mPrefs.getBoolean("NightMode", true) == true){
            LinearLayout container;
            LinearLayout createQuestionLinearLayout1, questionTypeLinearLayout;
            ImageButton btnRevert;
            TextView txtViewMyQuestionHeader, txtMust;

            container = findViewById(R.id.container);
            createQuestionLinearLayout1 = findViewById(R.id.createQuestionLinearLayout1);
            questionTypeLinearLayout = findViewById(R.id.questionTypeLinearLayout);
            btnRevert = findViewById(R.id.btnRevert);
            txtViewMyQuestionHeader = findViewById(R.id.txtViewMyQuestionHeader);
            txtMust = findViewById(R.id.txtMust);

            container.setBackgroundColor(Color.BLACK);
            createQuestionLinearLayout1.setBackgroundColor(Color.BLACK);
            questionTypeLinearLayout.setBackgroundColor(Color.BLACK);
            btnRevert.setBackgroundColor(Color.BLACK);
            txtViewMyQuestionHeader.setBackgroundColor(Color.BLACK);
            txtViewMyQuestionHeader.setTextColor(Color.WHITE);
            txtMust.setBackgroundColor(Color.BLACK);
            txtMust.setTextColor(Color.WHITE);
        }
    }

    public void btnQuestionCategoryOnClick(View view) {
        builder = new AlertDialog.Builder(this);

        //Declare main layout
        LinearLayout mainLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLayout.setOrientation(LinearLayout.HORIZONTAL);
        mainLayout.setLayoutParams(params);

        //Declare buttons for type
        btnSelection = new Button(this);
        btnScramble = new Button(this);
        btnPictionary = new Button(this);

        btnSelection.setId(Ids.ID_ONE)      ; btnSelection.setText("Selection")      ;btnSelection.setBackgroundColor(Color.TRANSPARENT);
        btnScramble.setId(Ids.ID_TWO)       ; btnScramble.setText("Scramble")        ;btnScramble.setBackgroundColor(Color.TRANSPARENT);
        btnPictionary.setId(Ids.ID_THREE)   ; btnPictionary.setText("Pictionary")   ;btnPictionary.setBackgroundColor(Color.TRANSPARENT);
        mainLayout.addView(btnSelection);
        mainLayout.addView(btnScramble);
        mainLayout.addView(btnPictionary);

        btnSelection.setOnClickListener(this);
        btnScramble.setOnClickListener(this);
        btnPictionary.setOnClickListener(this);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if( ((ColorDrawable)btnSelection.getBackground()).getColor() == Color.BLUE){
                    btnQuestionCategory.setText("Selection");
                    fm.beginTransaction().replace(R.id.frameLayoutCreateQuestion, createQuestionSelection).commit();
                }
                else if(((ColorDrawable)btnScramble.getBackground()).getColor() == Color.BLUE){
                    btnQuestionCategory.setText("Scramble");
                    fm.beginTransaction().replace(R.id.frameLayoutCreateQuestion, createQuestionScramble).commit();
                }
                else if(((ColorDrawable)btnPictionary.getBackground()).getColor() == Color.BLUE){
                    btnQuestionCategory.setText("Pictionary");
                    fm.beginTransaction().replace(R.id.frameLayoutCreateQuestion, createQuestionPictionary).commit();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //Add layouts to main layout
        builder.setView(mainLayout);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 1:
                btnSelection.setBackgroundColor(Color.BLUE);
                btnScramble.setBackgroundColor(Color.WHITE);
                btnPictionary.setBackgroundColor(Color.WHITE);
                break;
            case 2:
                btnSelection.setBackgroundColor(Color.WHITE);
                btnScramble.setBackgroundColor(Color.BLUE);
                btnPictionary.setBackgroundColor(Color.WHITE);
                break;
            case 3:
                btnSelection.setBackgroundColor(Color.WHITE);
                btnScramble.setBackgroundColor(Color.WHITE);
                btnPictionary.setBackgroundColor(Color.BLUE);
                break;
        }
    }
    public class Ids{
        public static final int ID_ONE = 1; //btnMCQ
        public static final int ID_TWO = 2; //btnCrossword
        public static final int ID_THREE = 3; //btnNews
    }

    public void retrieveQuestion() {
        DatabaseReference databaseQuestion = FirebaseDatabase.getInstance().getReference();
        databaseQuestion.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questions = dataSnapshot.child("questions").getValue(Questions.class);
                while (questions.getPictionaries().remove(null));
                while (questions.getScrambles().remove(null));
                while (questions.getSelections().remove(null));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void submitQuestionSelection(View v){
        EditText txtQuestion = createQuestionSelection.txtQuestion;
        EditText editTextAnswer = createQuestionSelection.editTextAnswer;
        EditText editTextFake1 = createQuestionSelection.editTextFake1;
        EditText editTextFake2 = createQuestionSelection.editTextFake2;
        EditText editTextFake3 = createQuestionSelection.editTextFake3;

        if(txtQuestion.getText().toString().equals("") || editTextAnswer.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Please Enter Both Question and Answer Before Proceed",Toast.LENGTH_SHORT);
        }
        else{
            int length = questions.getSelections().size();
            String fake1= editTextFake1.getText().toString()
                    , fake2=editTextFake2.getText().toString()
                    , fake3=editTextFake3.getText().toString();

            Selection selection = new Selection(""+(length+1),txtQuestion.getText().toString(),editTextAnswer.getText().toString(),fake1,fake2,fake3,"",0,0);
            selection.setAuthor(FirebaseAuth.getInstance().getCurrentUser().getUid());

            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("questions").child("selections");

            myRef.child(selection.getQuestionId()).setValue(selection, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null){
                        Toast.makeText(getApplicationContext(), "Question added successfully!", Toast.LENGTH_LONG).show();
                        redirectQuestion();
                    }else{
                        Toast.makeText(getApplicationContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    public void submitQuestionScramble(View v){
        EditText txtQuestion = createQuestionScramble.editTextQuestion;

        if(txtQuestion.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Please Enter Both Question and Answer Before Proceed",Toast.LENGTH_SHORT);
        }
        else{
            int length = questions.getScrambles().size();
            Scramble selection = new Scramble(""+(length+1),txtQuestion.getText().toString(),FirebaseAuth.getInstance().getCurrentUser().getUid(),0,0);

            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("questions").child("scrambles");

            myRef.child(selection.getQuestionId()).setValue(selection, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null){
                        Toast.makeText(getApplicationContext(), "Question added successfully!", Toast.LENGTH_LONG).show();
                        redirectQuestion();
                    }else{
                        Toast.makeText(getApplicationContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    public void submitQuestionPictionary(View v){
        EditText txtQuestion = createQuestionPictionary.editTextQuestion;

        if(txtQuestion.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Please Enter Both Question and Answer Before Proceed",Toast.LENGTH_SHORT);
        }
        else{
            int length = questions.getPictionaries().size();
            Pictionary pictionary = new Pictionary(""+(length+1),txtQuestion.getText().toString(),FirebaseAuth.getInstance().getCurrentUser().getUid(),0,0);

            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("questions").child("pictionaries");
            StorageReference filepath = FirebaseStorage.getInstance().getReference().child("Question").child("Pictionary").child(pictionary.getPicUrl()+".jpg");

            myRef.child(pictionary.getQuestionId()).setValue(pictionary, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null){
                        Toast.makeText(getApplicationContext(), "Question added successfully!", Toast.LENGTH_LONG).show();
                        redirectQuestion();
                    }else{
                        Toast.makeText(getApplicationContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            filepath.putBytes(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(activity, "Item added successfully!", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Failed to add item.", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void redirectQuestion() {
        Intent intent = new Intent(this, MyQuestion.class);
        this.startActivity(intent);
    }

    public void buildRetrievePicture(View v){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        builder
                .setMessage("Select your IC picture")
                .setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startGalleryChooser();
                    }
                })
                .setNegativeButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startCamera();
                    }
                });
        builder.create().show();
    }
    public void startGalleryChooser() {
        if (PermissionUtils.requestPermission(activity, GALLERY_PERMISSIONS_REQUEST, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select a photo"), GALLERY_IMAGE_REQUEST);
        }
    }
    public void startCamera() {
        if (PermissionUtils.requestPermission(this, CAMERA_PERMISSIONS_REQUEST, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri photoUri = FileProvider.getUriForFile(activity, getApplicationContext().getPackageName() + ".provider", getCameraFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
        }
    }
    public File getCameraFile() {
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }
    public void setImage(Uri uri) {
        if (uri != null) {
            try {
                // scale the image to save on bandwidth
                Bitmap bitmap =
                        scaleBitmapDown(
                                MediaStore.Images.Media.getBitmap(getContentResolver(), uri),
                                MAX_DIMENSION);

                //callCloudVision(bitmap);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                imageData = baos.toByteArray();
                createQuestionPictionary.imageViewPic.setImageBitmap(bitmap);

            } catch (IOException e) {
                Log.d(TAG, "Image picking failed because " + e.getMessage());
                Toast.makeText(this, "Image picker error", Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d(TAG, "Image picker gave us a null image.");
            Toast.makeText(this, "Image picker error", Toast.LENGTH_LONG).show();
        }
    }

    private Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            setImage(data.getData());
        } else if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Uri photoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", getCameraFile());
            setImage(photoUri);
        }
    }
}
