package com.ourblog.android;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {
    public static final String FRIENDREQUEST = "friendrequest";
    private Button login;
    private Button signUp;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String account;
    private String password;
    private FirebaseUser currentUser;
    private DatabaseReference mListDb;
    FirebaseDatabase database;
    String friendsID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_main);
        Article article = new Article();

//        FirebaseUser user = mAuth.getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        account = "chloe.thhsdddddu@gmail.com";
        password = "123456";
        database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final String title = article.getTitle();
        final String content = article.getContent();
        Button testingBtn = findViewById(R.id.testing_button);
        Button signupBtn = findViewById(R.id.sign_button);
        Button addFriendBtn = findViewById(R.id.addFriend_button);
        Button checkFriendRequest = findViewById(R.id.check_Friend_Request);
        Button confirmFriendRequest = findViewById(R.id.confirm_friend_request);
        Button rejectFriendRequest = findViewById(R.id.reject_friend_request);
        mListDb = FirebaseDatabase.getInstance().getReference().child("data");
        Log.d("Chloe", "firebase database list: " + mListDb);
        String userId = currentUser.getUid();
        final TextView friendRequest = findViewById(R.id.friendRequest);
        final TextView friendList = findViewById(R.id.frinedList);


//    private void initView() {
//        EditText accountEdit, passwordEdit;
//        TextInputLayout accoutLayout, passwordLayout;
//        Button signUpBtn;
//        accountEdit = (EditText) findViewById(R.id.account_edit);
//        passwordEdit = (EditText) findViewById(R.id.password_edit);
//        accoutLayout = (TextInputLayout) findViewById(R.id.account_layout);
//        passwordLayout = (TextInputLayout) findViewById(R.id.password_layout);
//        passwordLayout.setErrorEnabled(true);
//        accoutLayout.setErrorEnabled(true);
//        signUpBtn = (Button) findViewById(R.id.sign_button);
//        }
        testingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewUser(title, content);
                Toast.makeText(SignUpActivity.this, "Added into firebase successfully", Toast.LENGTH_SHORT).show();
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.createUserWithEmailAndPassword(account, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "success", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            };
                        });
            }
        });

        //Get user uid
        mListDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot objSnapshot: snapshot.getChildren()) {
                    Object obj = objSnapshot.getKey();
                    String email = objSnapshot.child("email").getValue().toString();
                    Log.d("Chloe", "Id: " + obj);
                    Log.d("Chloe", "email: " + email);
                    if (email.equals("testing123@gmail.com")){
                        friendsID = objSnapshot.getKey();
                        Log.d("Chloe", "emailID:" + friendsID);
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.e("Read failed", firebaseError.getMessage());
            }
        });

        addFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("data").child(friendsID).child(FRIENDREQUEST).push().setValue("chloe@gmail.com");
                Log.d("Chloe","Request sent successfully!");
                Toast.makeText(SignUpActivity.this, "Request sent successfully", Toast.LENGTH_SHORT).show();
            }
        });

        checkFriendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((mDatabase.child("data").child(friendsID).child(FRIENDREQUEST) != null)){
                    friendRequest.setText("chloe.thhsu@gmail.com");
                }
            }
        });

        confirmFriendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("data").child(friendsID).child("friendlist").push().setValue("chloe@gmail.com");
                mDatabase.child("data").child(friendsID).child(FRIENDREQUEST).removeValue();
                friendRequest.setText("");
                friendList.setText("chloe.thhsu@gmail.com");
                Toast.makeText(SignUpActivity.this, "Added friend sent successfully", Toast.LENGTH_SHORT).show();

            }
        });

        rejectFriendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("data").child(friendsID).child(FRIENDREQUEST).removeValue();
                friendRequest.setText("");
                Toast.makeText(SignUpActivity.this, "You've rejected an invitation!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void writeNewUser(String title, String content) {
        String userId = currentUser.getUid();
        Log.d("Chloe", "getProvide Id: " + userId);
        mDatabase.child("data").child(userId).child("email").setValue("chloe.thhsu@gmail.com");
        mDatabase.child("data").child(userId).child("friendlist").child("user").setValue("friend id");
        mDatabase.child("data").child(userId).child("friendlist").child("email").setValue("xxxx@hhhhh");
        mDatabase.child("data").child(userId).child(FRIENDREQUEST).child("email").setValue("dddd@kkkk");
        mDatabase.child("data").child(userId).child("articles").child("articles1").child("title").setValue("旅遊好好玩");
        mDatabase.child("data").child(userId).child("articles").child("articles1").child("content").setValue("chloe content testing");
        mDatabase.child("data").child(userId).child("articles").child("articles1").child("tag").setValue("佛媽");
        mDatabase.child("data").child(userId).child("articles").child("articles2").child("title").setValue("chloe content testing");
        mDatabase.child("data").child(userId).child("articles").child("articles2").child("content").setValue("chloe content testing");
        mDatabase.child("data").child(userId).child("articles").child("articles2").child("tag").setValue("chloe content testing");

    }


}

