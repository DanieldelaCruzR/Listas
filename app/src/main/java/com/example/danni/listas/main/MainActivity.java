package com.example.danni.listas.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.danni.listas.R;
import com.example.danni.listas.lista.ListaActivity;
import com.example.danni.listas.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button bCreate,bRead,bUpdate, bDelete;
    EditText eId, eName, ePhone, eEmail;
    int cont=0;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);

        bCreate= (Button)findViewById(R.id.bCreate);
        bRead= (Button)findViewById(R.id.bRead);
        bUpdate= (Button)findViewById(R.id.bUpdate);
        bDelete= (Button)findViewById(R.id.bDelete);

        eId=(EditText)findViewById(R.id.eId);
        eEmail=(EditText)findViewById(R.id.eEmail);
        eName=(EditText)findViewById(R.id.eName);
        ePhone=(EditText)findViewById(R.id.ePhone);



    }
    public void onClick(View view){
        int id= view.getId();

        final String uid = eId.getText().toString();
        String name = eName.getText().toString();
        String email = eEmail.getText().toString();
        String phone = ePhone.getText().toString();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usuarios").child("user"+cont);

        switch (id){
            case R.id.bCreate:
                user = new User("user"+cont, name,email,phone);
                myRef.setValue(user);
                cont++;
                break;
            case R.id.bRead:
                myRef = database.getReference("usuarios");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child("user"+uid).exists()){
                            user = dataSnapshot.child("user"+uid).getValue(User.class);
                            eName.setText(user.getName());
                            eEmail.setText(user.getEmail());
                            ePhone.setText(user.getPhone());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                break;
            case R.id.bUpdate:
                myRef = database.getReference("usuarios").child("user"+uid);

                Map<String, Object> newData=new HashMap<>();
                newData.put("name",name);
                newData.put("email",email);
                newData.put("phone",phone);
                myRef.updateChildren(newData);

                break;
            case R.id.bDelete:
                break;
            case R.id.bLista:
                Intent intent = new Intent(MainActivity.this, ListaActivity.class);
                startActivity(intent);
                break;
        }
    }
}
