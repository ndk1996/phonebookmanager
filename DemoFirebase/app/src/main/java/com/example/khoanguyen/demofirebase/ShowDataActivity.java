package com.example.khoanguyen.demofirebase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import com.google.firebase.database.*;

/**
 * Created by khoanguyen on 4/14/18.
 */

public class ShowDataActivity extends AppCompatActivity {
    private ListView lvContact;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_showdata);

            lvContact = (ListView) findViewById(R.id.lv_contact);
            final ArrayList<Contact> arrContact = new ArrayList<>();
            final CustomAdapter customAdaper = new CustomAdapter(this,R.layout.row_listview,arrContact);
            lvContact.setAdapter(customAdaper);

            mDatabase.child("Contacts").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Contact ct = dataSnapshot.getValue(Contact.class);
                    arrContact.add(ct);
                    customAdaper.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
}
