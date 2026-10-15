package com.example.appocho;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView tvEntrada;
    Button btEnviar;
    EditText edEnvio;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("prueba");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvEntrada = findViewById(R.id.tvtvLlegada);
        btEnviar = findViewById(R.id.btEnviar);
        edEnvio = findViewById(R.id.edEnvio);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = edEnvio.getText().toString();
                if (!mensaje.isEmpty()) {
                    ref.setValue(mensaje);
                } else {
                    edEnvio.setError("Ingrese un texto");
                }
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String resultado = snapshot.getValue(String.class);
                tvEntrada.setText(resultado);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}