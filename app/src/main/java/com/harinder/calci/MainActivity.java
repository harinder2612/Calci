package com.harinder.calci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    DatabaseReference myref= FirebaseDatabase.getInstance().getReferenceFromUrl("https://calci-b1a59.firebaseio.com/");
    Button b,b1,b2,b3,b4,b5,b6,b7,b8,b9;
    ArrayList<String> musers=new ArrayList<>();
    static int val,i=0;
    static int calflag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1= (TextView) findViewById(R.id.textView);
        b= (Button) findViewById(R.id.zero);
        b2= (Button) findViewById(R.id.two);
        b3= (Button) findViewById(R.id.three);
        b4= (Button) findViewById(R.id.four);
        b5= (Button) findViewById(R.id.five);
        b6= (Button) findViewById(R.id.six);
        b7= (Button) findViewById(R.id.seven);
        b8= (Button) findViewById(R.id.eight);
        b9= (Button) findViewById(R.id.nine);
        b1= (Button) findViewById(R.id.one);

        myref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value=dataSnapshot.getValue(String.class);
                musers.add(value);
                i=musers.size();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void save(View v)
    {

        i++;
        DatabaseReference chi = myref.child(""+i);
        chi.setValue(t1.getText().toString());
        Toast.makeText(this, "Result saved successfully", Toast.LENGTH_SHORT).show();

    }
    public void zero(View v)
    {
        t1.setText((t1.getText()+""+b.getText()));
    }
    public void one(View v)
    {
        t1.setText((t1.getText()+""+b1.getText()));
    }
    public void two(View v)
    {
        t1.setText((t1.getText()+""+b2.getText()));
    }
    public void three(View v)
    {
        t1.setText((t1.getText()+""+b3.getText()));
    }
    public void four(View v)
    {
        t1.setText((t1.getText()+""+b4.getText()));
    }
    public void five(View v)
    {
        t1.setText(t1.getText()+""+b5.getText());
    }
    public void six(View v)
    {
        t1.setText(t1.getText()+""+b6.getText());
    }
    public void seven(View v)
    {
        t1.setText(t1.getText()+""+b7.getText());
    }
    public void eight(View v)
    {
        t1.setText(t1.getText()+""+b8.getText());
    }
    public void nine(View v)
    {
        t1.setText(t1.getText()+""+b9.getText());
    }
    public void plus(View v)
    {   calflag=1;
        val=Integer.parseInt((String) t1.getText());
        t1.setHint(t1.getText()+"+");
        t1.setText("");
    }
    public void minus(View v)
    {   calflag=2;
        val=Integer.parseInt((String) t1.getText());
        t1.setHint(t1.getText()+"-");
        t1.setText("");
    }
    public void mul(View v)
    {   calflag=3;
        val=Integer.parseInt((String) t1.getText());
        t1.setHint(t1.getText()+"*");
        t1.setText("");
    }
    public void divide(View v)
    {   calflag=4;
        val=Integer.parseInt((String) t1.getText());
        t1.setHint(t1.getText()+"/");
        t1.setText("");
    }
    public void refresh(View v)
    {
        t1.setHint("0");
        t1.setText("");
    }
    public void eql(View v)
    {int x;
        switch(calflag)
        {
            case 1: x=Integer.parseInt(t1.getText().toString())+val;
                t1.setText(""+x);
                break;
            case 2: x=val-Integer.parseInt(t1.getText().toString());
                t1.setText(""+x);
                break;
            case 3: x=Integer.parseInt(t1.getText().toString())*val;
                t1.setText(""+x);
                break;
            case 4: double y=(double) val/(double)Integer.parseInt(t1.getText().toString());
                t1.setText(""+y);
                break;
        }
        val=0;
        calflag=0;
    }
    public void history(View v)
    {
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}
