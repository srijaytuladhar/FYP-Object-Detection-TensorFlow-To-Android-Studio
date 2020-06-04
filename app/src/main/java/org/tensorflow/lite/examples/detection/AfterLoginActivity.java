package org.tensorflow.lite.examples.detection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.unity3d.player.UnityPlayerActivity;

import java.util.List;

public class AfterLoginActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name, email, id;
    Button signout, btn_object_detection, btn_AR;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.textName);
        email = findViewById(R.id.textEmail);
        id = findViewById(R.id.textID);
        signout = findViewById(R.id.button_sign_out);
        btn_object_detection = findViewById(R.id.btn_object_detection);
        btn_AR = findViewById(R.id.btn_AR);


        btn_object_detection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterLoginActivity.this, DetectorActivity.class);
                startActivity(intent);
            }
        });

        btn_AR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(AfterLoginActivity.this, UnityPlayerActivity.class);
                //startActivity(intent);

                // for Google Maps
                // Build the intent
                /*Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

                // Verify it resolves
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
                boolean isIntentSafe = activities.size() > 0;

                // Start an activity if it's safe
                if (isIntentSafe) {
                    startActivity(mapIntent);
                }*/

                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.DefaultCompany.AR_FYP");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                } else {
                    Toast.makeText(AfterLoginActivity.this, "There is no AR package installed....", Toast.LENGTH_LONG).show();
                }

            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    // ...
                    case R.id.button_sign_out:
                        signOut();
                        break;
                    // ...
                }

            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            name.setText(personName);
            email.setText(personEmail);
            id.setText(personId);
            //imageView.setImageURI(personPhoto);
            //Picasso.with(this).load(personPhoto).into(imageView);
            //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);

            //Glide.with(this).load("https://i.imgur.com/DvpvklR.png").into(imageView);

            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);

        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                        //Intent intent = new Intent(AfterLoginActivity.this, HomeActivity.class);
                        //startActivity(intent);
                        Toast.makeText(AfterLoginActivity.this, "Signed Out Successfully....", Toast.LENGTH_LONG).show();

                    }
                });
    }

    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
}
