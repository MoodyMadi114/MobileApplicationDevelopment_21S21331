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

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    EditText E1,E2;
    dbHelper Madi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.log_in_21s21331);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        E1=findViewById(R.id.et1);
        E2=findViewById(R.id.et2);
        b1=findViewById(R.id.btn1);
        b2=findViewById(R.id.btn2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Register=new Intent(MainActivity.this,register_21s21331.class);
                startActivity(Register);
            }
        });

        Madi=new dbHelper(this);
        MohLogin();

    }
    public void MohLogin(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PN=E1.getText().toString();
                String Pass=E2.getText().toString();

                if(PN.equals("admin") && Pass.equals("admin")) {

                        Intent intent = new Intent(MainActivity.this, HomeScreen_21s21331.class);
                        startActivity(intent);
                    }

                else {
                    try {
                        String Madi1=Madi.MohLogin(PN);
                        if (Pass.equals(Madi1)){
                            Intent LI =new Intent(MainActivity.this, home_screen_user_21s21331.class);
                            E1.setText("");
                            E2.setText("");
                            startActivity(LI);
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Wrong Phone Number or Password", Toast.LENGTH_SHORT).show();
                        }

                    }
                    catch (Exception e){
                        Toast.makeText(MainActivity.this,"Unexpected Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }
}