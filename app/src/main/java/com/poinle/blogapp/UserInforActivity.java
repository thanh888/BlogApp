package com.poinle.blogapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserInforActivity extends AppCompatActivity {

    private TextInputLayout nameLayout, lastnameLayout;
    private TextInputEditText editName, editLastname;
    private TextView txtSelectPhoto;
    CircleImageView circleImageView;
    Button btnContinue;
    private static final int GALLERY_AND_PROFILE = 1;
    private Bitmap bitmap = null;
    private SharedPreferences sharedPreferences;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_infor);

        init();
    }

    private void init() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        nameLayout = findViewById(R.id.layoutNameUserInfor);
        lastnameLayout = findViewById(R.id.layoutLastnameUserInfor);
        editName = findViewById(R.id.editNameUserInfor);
        editLastname = findViewById(R.id.editLastnameUserInfor);
        txtSelectPhoto = findViewById(R.id.txtSelectPhoto);
        circleImageView = findViewById(R.id.image_user);
        btnContinue = findViewById(R.id.btnContinue);

        txtSelectPhoto.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY_AND_PROFILE);
//            someActivityResultLauncher.launch(intent);
        });
        btnContinue.setOnClickListener(view -> {
            if (validate()) {
                saveUserInfor();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_AND_PROFILE && resultCode == RESULT_OK) {
            Uri imgUri = data.getData();
            circleImageView.setImageURI(imgUri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public boolean validate() {

        if (editName.getText().toString().isEmpty()) {
            lastnameLayout.setErrorEnabled(true);
            lastnameLayout.setError("Lastname is require");
            return false;
        }
        if (editName.getText().toString().isEmpty()) {
            nameLayout.setErrorEnabled(true);
            nameLayout.setError("Name is require");
            return false;
        }
        return true;
    }

    private void saveUserInfor() {
        dialog.setMessage("saving");
        dialog.show();
        String name = editName.getText().toString().trim();
        String lastname = editLastname.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.SAVE_USER_INFOR, response -> {

            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("photo", object.getString("photo"));
                    editor.apply();
                    startActivity(new Intent(UserInforActivity.this, HomeActivity.class));
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();

        }, error -> {
            error.printStackTrace();
            dialog.dismiss();

        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = sharedPreferences.getString("token", "");

                HashMap<String, String> map = new HashMap<>();
                map.put("Authorization", "Bearer" + token);
                return map;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("name", name);
                map.put("lastname", lastname);
                map.put("photo", bitmapToString(bitmap));
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(UserInforActivity.this);
        requestQueue.add(stringRequest);
    }

    private String bitmapToString(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] array = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(array, Base64.DEFAULT);
        }
        return "";
    }
}