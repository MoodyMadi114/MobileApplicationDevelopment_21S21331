package com.example.mad_21s21331;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class HomeScreen_21s21331 extends AppCompatActivity {
    Button B2,B3,B4;
    Switch B5;
    EditText E1,E2;
    dbHelper2 Madi2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_screen_admin21s21331);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        B2=findViewById(R.id.btn2);
        B3=findViewById(R.id.btn3);
        B4=findViewById(R.id.btn4);
        B5=findViewById(R.id.swch1);
        E1=findViewById(R.id.et1);
        E2=findViewById(R.id.et2);
        Madi2=new dbHelper2(this);
        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent User=new Intent(HomeScreen_21s21331.this,home_screen_user_21s21331.class);
                startActivity(User);
            }
        });
        Madi2=new dbHelper2(this);
        MohViewAll();
    }
    public void MohViewAll(){
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor crsr=Madi2.MohViewAll();
                if(crsr.getCount()==0){
                    showMessage("Error","Nothing Found");
                    return;
                }
                StringBuffer Moh = new StringBuffer();
                while(crsr.moveToNext())
                {
                    Moh.append("Occasion : "+crsr.getString(0)+"\n");
                    Moh.append("Number of People : "+crsr.getString(1)+"\n");
                    Moh.append("Phone Number : "+crsr.getString(2)+"\n");
                    Moh.append("Cost Price  : "+crsr.getString(3)+"\n");
                    Moh.append("---------------------------------------"+"\n");
                }
                showMessage("Future Reservation",Moh.toString());
            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Pn=E1.getText().toString();
                String Oc=E2.getText().toString();
                boolean updatedlv=Madi2.UpdateOcc(Oc,Pn);
                if (updatedlv==true)
                {
                    Toast.makeText(HomeScreen_21s21331.this, "Occasion title updated", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(HomeScreen_21s21331.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PHN=E1.getText().toString();
                Integer delete=Madi2.MadiDeleteRecord(PHN);
                if(delete>0){
                    Toast.makeText(HomeScreen_21s21331.this,"Record Deleted",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(HomeScreen_21s21331.this,"Unexpected Error",Toast.LENGTH_SHORT).show();
                }
                E1.setText(null);
                E2.setText(null);
            }
        });
    }
    public void showMessage(String title,String mes){
        AlertDialog.Builder Madi20 = new AlertDialog.Builder(this);
        Madi20.setCancelable(true);
        Madi20.setTitle(title);
        Madi20.setMessage(mes);
        Madi20.show();
    }
}