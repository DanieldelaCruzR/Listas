package com.example.danni.listas.lista;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danni.listas.R;
import com.example.danni.listas.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {
    //final String[] nombres=new String[]{"Parra","Daniel","Camila"};
    /*private User[] user = new User[]{
            new User("User0", "Parra", "para@mail.com","1234567"),
            new User("User1", "Medina", "medina@mail.com","987654"),
            new User("User2", "Martinez", "martinez@mail.com","34567"),
            new User("User3", "Ruiz", "ruiz@mail.com","567899")
    };*/
    private ListView listView;
    private ArrayList<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usuarios");

        setContentView(R.layout.lista_activity_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//Como poner el boton atras en la barra
        ActionBar ab =getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


     //   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nombres);

        listView= (ListView)findViewById(R.id.list);

        users = new ArrayList<User>();
        final Adapter adapter =new Adapter(this,users);
        listView.setAdapter(adapter);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                    users.add(userSnapshot.getValue(User.class));
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListaActivity.this, String.valueOf(i),Toast.LENGTH_SHORT).show();
            }
        });



    }

    class Adapter extends ArrayAdapter<User>{
        public Adapter(Context context, ArrayList<User> user) {
            super(context, R.layout.lista_item,user);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater= LayoutInflater.from(getContext());
            View item =inflater.inflate(R.layout.lista_item,null);

            User user = getItem(position);

            TextView tUid=(TextView)item.findViewById(R.id.tID);
            tUid.setText(user.getUid());

            TextView tName=(TextView)item.findViewById(R.id.tName);
            tName.setText(user.getName());

            TextView tEmail=(TextView)item.findViewById(R.id.tCorreo);
            tEmail.setText(user.getEmail());

            TextView tPhone=(TextView)item.findViewById(R.id.tPhone);
            tPhone.setText(user.getPhone());

            return item;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
  /*
    Datos sencillos
    class Adapter extends ArrayAdapter<User>{
        public Adapter(Context context, User[] user) {
            super(context, R.layout.lista_item,user);
        }



        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater= LayoutInflater.from(getContext());
            View item =inflater.inflate(R.layout.lista_item,null);

            TextView tUid=(TextView)item.findViewById(R.id.tID);
            tUid.setText(user[position].getUid());

            TextView tName=(TextView)item.findViewById(R.id.tName);
            tName.setText(user[position].getName());

            TextView tEmail=(TextView)item.findViewById(R.id.tCorreo);
            tEmail.setText(user[position].getEmail());

            TextView tPhone=(TextView)item.findViewById(R.id.tPhone);
            tPhone.setText(user[position].getPhone());

            return item;
        }
    }*/