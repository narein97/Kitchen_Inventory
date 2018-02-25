package com.example.narein.kitchen_inventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class manipulation extends AppCompatActivity {
    EditText product,quantity,productid;
    Button add;database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manipulation);
        db=new database(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        product=(EditText)findViewById(R.id.Productentry);
        quantity=(EditText)findViewById(R.id.Quantityentry);
        productid=(EditText)findViewById(R.id.IDentry);
        add=(Button)findViewById(R.id.insert);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isinserted;
                //Log.d("Android:",product.getText().toString()+"  "+quantity.getText().toString()+"   "+productid.getText().toString());
                isinserted=db.insertData(product.getText().toString(),quantity.getText().toString(),productid.getText().toString());
                if(isinserted == true)
                    Toast.makeText(manipulation.this,"Product Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(manipulation.this,"Product not Inserted",Toast.LENGTH_LONG).show();
            }
        });
    }
}
