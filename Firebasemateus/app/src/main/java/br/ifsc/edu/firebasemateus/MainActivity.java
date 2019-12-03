package br.ifsc.edu.firebasemateus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText edLogin, edSenha;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        edLogin = findViewById(R.id.editTextUsuario);
        edSenha = findViewById(R.id.editTextSenha);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


//        databaseReference.child("Pessoas").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Pessoa p = dataSnapshot.getValue(Pessoa.class);
//                Log.d("DatabasePessoa", p.nome);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


    }

    public void autenticar(View view) {
        mAuth.signInWithEmailAndPassword(
                edLogin.getText().toString(),
                edSenha.getText().toString()
        ).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //you can verify here if your Sing Up was completed successfully
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Login Completo com Sucesso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Falha no Login", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void cadastrar(View view) {
        mAuth.createUserWithEmailAndPassword(
                edLogin.getText().toString(),
                edSenha.getText().toString()
        ).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //you can verify here if your Sing Up was completed successfully
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Cadastro Concluido com Sucesso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Falha ao Criar o Cadastro", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void resetSenha(View view){
        if(!edLogin.getText().toString().trim().equals("")){
            mAuth.sendPasswordResetEmail(edLogin.getText().toString());
        }
    }

}
