package br.ifsc.edu.firebasemateus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<Pessoa> pessoas = new ArrayList<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        if (mUser != null) {
            Log.d("FirebaseAuth", mUser.getEmail());
        } else {
            Log.d("FirebaseAuth", "Usuário não autenticado");
            finish();
        }

        findViewById(R.id.buttonCadastrarPessoa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CadastrarPessoasActivity.class);
                startActivity(i);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Pessoa p;
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            p = postSnapshot.getValue(Pessoa.class);
                            Log.d("postSnapshot",postSnapshot.toString());
                            p.setId(postSnapshot.getKey());
                            pessoas.add(p);
                        }

                        recyclerView = findViewById(R.id.recyclerView);
                        recyclerView.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);

                        PessoaAdapter pessoaAdapter = new PessoaAdapter(
                                getApplicationContext(),
                                R.layout.item_pessoa_list,
                                pessoas);

                        recyclerView.setAdapter(pessoaAdapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                }
        );


    }

    public void logOut(View view) {
        mAuth.signOut();
        finish();
    }

}
