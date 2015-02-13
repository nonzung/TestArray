package com.example.sah.testarray;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditActivity extends ActionBarActivity {
    EditText et1,et2,et3,et4;
    Button btn,btnDel;
    int posEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Bundle extras = getIntent().getExtras();
        et1 = (EditText)findViewById(R.id.tv1);
        et2 = (EditText)findViewById(R.id.tv2);
        et3 = (EditText)findViewById(R.id.tv3);
        et4 = (EditText)findViewById(R.id.tv4);
        btn = (Button)findViewById(R.id.btnTest);
        btnDel = (Button)findViewById(R.id.btnDel);
        posEdit = extras.getInt("posEdit");
        et1.setText(extras.getString("Name"));
        et2.setText(extras.getString("Id"));
        et3.setText(extras.getString("Phone"));
        et4.setText(extras.getString("Sex"));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.collections.get(posEdit).put("Name",et1.getText().toString());
                MainActivity.collections.get(posEdit).put("Id",et2.getText().toString());
                MainActivity.collections.get(posEdit).put("Phone",et3.getText().toString());
                MainActivity.collections.get(posEdit).put("Sex",et4.getText().toString());
                Intent in = new Intent();
                in.putExtra("PosEdit", -1);
                setResult(RESULT_OK, in);
                finish();
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("PosEdit", posEdit);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
