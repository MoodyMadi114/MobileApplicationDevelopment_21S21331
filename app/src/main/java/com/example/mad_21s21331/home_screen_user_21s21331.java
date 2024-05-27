package com.example.mad_21s21331;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class home_screen_user_21s21331 extends AppCompatActivity {
    Button B1,B2;
    EditText E1,E2, E3;
    dbHelper2 Madi3;
    TextView TV1;
    PriceCalculation PC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_screen_user21s21331);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        B1=findViewById(R.id.button);
        B2=findViewById(R.id.button2);
        E1=findViewById(R.id.et1);
        E2=findViewById(R.id.et2);
        E3=findViewById(R.id.editTextText);
        TV1=findViewById(R.id.tv3);
        Madi3=new dbHelper2(this);
        PC= new PriceCalculation();
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Moh=E2.getText().toString();
                Double DMoh=Double.parseDouble(Moh);
                Double FinalPrice=PC.MadiPrice(DMoh);
                TV1.setText(""+FinalPrice);
                TV1.setVisibility(View.VISIBLE);
            }
        });
        NewReservation();
    }
    public void NewReservation(){
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String OCC=E1.getText().toString();
                String NP=E2.getText().toString();
                String PHN=E3.getText().toString();
                String FP=TV1.getText().toString();
                boolean AddRecord= Madi3.NewReservation(OCC,NP,PHN,FP);
                if(AddRecord==true){
                    Toast.makeText(home_screen_user_21s21331.this,"Reservation Added Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(home_screen_user_21s21331.this,"Unexpected Error", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}