package br.ifsc.edu.firebasemateus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastrarPessoasActivity extends AppCompatActivity {

    EditText edNome,edCpf,edSexo;

    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pessoas);

        edNome = findViewById(R.id.editTextNome);
        edCpf = findViewById(R.id.editTextCpf);
        edSexo = findViewById(R.id.editTextSexo);

        mRef = FirebaseDatabase.getInstance().getReference();
    }

    public void cadastrarPessoa(View view){
        Pessoa pessoa = new Pessoa(edNome.getText().toString(),edCpf.getText().toString(),edSexo.getText().toString());
        mRef.push().setValue(pessoa).addOnCompleteListener(CadastrarPessoasActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Cadastrado com Sucesso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Falha ao cadastrar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
