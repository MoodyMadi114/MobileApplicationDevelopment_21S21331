package com.example.mad_21s21331;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class register_21s21331 extends AppCompatActivity {
    EditText E1,E2,E3;
    Button B1;
    dbHelper Madi1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register21s21331);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        E1=findViewById(R.id.et1);
        E2=findViewById(R.id.et2);
        E3=findViewById(R.id.Epswrd);
        B1=findViewById(R.id.btn1);
        Madi1=new dbHelper(this);
        NewRegistration();
    }
    public void NewRegistration(){
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FullName=E1.getText().toString();
                String PhNum=E2.getText().toString();
                String Pass=E3.getText().toString();
                boolean Madi=Madi1.NewRegistration(FullName,PhNum,Pass);
                if (Madi == true){
                    Toast.makeText(register_21s21331.this,"Registration complete ", Toast.LENGTH_SHORT).show();
                    Intent login=new Intent(register_21s21331.this,MainActivity.class);
                    startActivity(login);
                }
                else {
                    Toast.makeText(register_21s21331.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}