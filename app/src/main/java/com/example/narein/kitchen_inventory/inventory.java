package com.example.narein.kitchen_inventory;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class inventory extends AppCompatActivity {

    database db;TableLayout table;Button add;
    String msg = "Android:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        //Log.d(msg,"onCreate::inventory");
        db=new database(this);
    }
    @Override
    protected void onStart(){
        super.onStart();
        //Log.d(msg,"onStart::inventory--start reading and output database content");
        if(!isEmpty()){
            TextView product,quantity;
            TableRow row;
            table=(TableLayout)findViewById(R.id.table);
            table.setColumnStretchable(0,true);
            table.setColumnStretchable(1,true);
            Cursor point = db.getAllData();
            point.moveToFirst();
            while(!point.isAfterLast()){
                row=new TableRow(this);
                product=new TextView(this);quantity=new TextView(this);
                final TextView temp=product;
                product.setText(point.getString(0));quantity.setText(point.getString(1));
                product.setTextSize(14);quantity.setTextSize(14);
                product.setGravity(Gravity.CENTER);quantity.setGravity(Gravity.CENTER);
                product.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder
                                .setMessage("Delete from inventory?")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Integer isdeleted=db.deleteData(temp.getText().toString());
                                        //Log.d("Android:",temp.getText().toString());
                                        if(isdeleted >0)
                                            Toast.makeText(inventory.this,"Product deleted",Toast.LENGTH_LONG).show();
                                        else
                                            Toast.makeText(inventory.this,"Product not deleted",Toast.LENGTH_LONG).show();
                                        Intent i=new Intent(inventory.this,inventory.class);
                                        startActivity(i);
                                        overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
                                    }
                                })
                                .setNegativeButton("No", null)						//Do nothing on no
                                .show();
                    }
                });
                row.addView(product);row.addView(quantity);
                table.addView(row);
                point.moveToNext();
            }point.close();
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        //Log.d(msg,"onResume::inventory--needed if any transition required");
        add=(Button)findViewById(R.id.addproduct);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(inventory.this,manipulation.class);
                startActivity(i);
                overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
            }
        });
    }
    @Override
    protected void onStop(){
        super.onStop();
        //Log.d(msg,"onStop::inventory--close connections to database if necessary");
        table=(TableLayout)findViewById(R.id.table);
        table.removeAllViews();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        this.finishAffinity();System.exit(0);
        //Log.d(msg,"onDestroy::inventory");
    }

    protected boolean isEmpty(){
        if(db.isempty()){
            TextView text = (TextView) findViewById(R.id.product);text.setText("");
            text = (TextView) findViewById(R.id.quantity);text.setText("");
            text = (TextView) findViewById(R.id.no_items);text.setText("No items in your inventory :(");
            return true;
        }
        else{
            TextView text = (TextView) findViewById(R.id.product);text.setText("Product");
            text = (TextView) findViewById(R.id.quantity);text.setText("Quantity");
            text = (TextView) findViewById(R.id.no_items);text.setText("");
            return false;
        }
    }

}
