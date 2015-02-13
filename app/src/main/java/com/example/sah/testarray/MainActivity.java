package com.example.sah.testarray;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class MainActivity extends ActionBarActivity {
    public static ArrayList<HashMap<String, String>> collection,collections;

    Button btnSt,btnRt;
    EditText tv1,tv2,tv3,tv4;
    ListViewAdapter adapter;
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnSt = (Button)findViewById(R.id.btnTest);
        btnRt = (Button)findViewById(R.id.btnShow);
        tv1 = (EditText)findViewById(R.id.tv1);
        tv2 = (EditText)findViewById(R.id.tv2);
        tv3 = (EditText)findViewById(R.id.tv3);
        tv4 = (EditText)findViewById(R.id.tv4);
        listview = (ListView)findViewById(R.id.listview);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(MainActivity.this,EditActivity.class);
                in.putExtra("posEdit",i);
                in.putExtra("Name", collections.get(i).get("Name"));
                in.putExtra("Id",collections.get(i).get("Id"));
                in.putExtra("Phone",collections.get(i).get("Phone"));
                in.putExtra("Sex",collections.get(i).get("Sex"));
                startActivityForResult(in,202);
            }
        });
        SharedPreferences pref = getApplicationContext().getSharedPreferences("TestHashMap", 0);
        String storedCollection = pref.getString("Secret", null);
        collections = new ArrayList<HashMap<String, String>>();
        try {
            JSONArray array = new JSONArray(storedCollection);
            HashMap<String, String> item = null;
            for(int i =0; i<array.length(); i++){
                String obj = array.get(i).toString();
                JSONObject ary = new JSONObject(obj);
                Iterator<String> it = ary.keys();
                item = new HashMap<String, String>();
                while(it.hasNext()){
                    String key = it.next();
                    item.put(key, (String)ary.get(key));
                }
                collections.add(item);
            }

            //tv1.setText(collections.get(0).get("Name"));
            //tv2.setText(collections.get(0).get("Id"));
            // tv3.setText(collections.get(0).get("Phone"));
            //tv4.setText(collections.get(0).get("Sex"));


            adapter = new ListViewAdapter(MainActivity.this,collections);
            listview.setAdapter(adapter);
        } catch (JSONException e) {
            Log.e("Restore", "while parsing", e);
        }
        btnSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("TestHashMap", 0);
                SharedPreferences.Editor prefEditor = pref.edit();
                collection = new ArrayList<HashMap<String, String>>();
                String storedCollection = pref.getString("Secret", null);
                //ArrayList<HashMap<String, String>> collections = new ArrayList<HashMap<String, String>>();
                try {
                    JSONArray array = new JSONArray(storedCollection);
                    HashMap<String, String> item = null;
                    for(int i =0; i<array.length(); i++){
                        String obj = array.get(i).toString();
                        JSONObject ary = new JSONObject(obj);
                        Iterator<String> it = ary.keys();
                        item = new HashMap<String, String>();
                        while(it.hasNext()){
                            String key = it.next();
                            item.put(key, (String)ary.get(key));
                        }
                        collection.add(item);
                    }


                } catch (JSONException e) {
                    Log.e("Restore", "while parsing", e);
                }
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("Name",tv1.getText().toString());
                map.put("Id",tv2.getText().toString());
                map.put("Phone",tv3.getText().toString());
                map.put("Sex",tv4.getText().toString());

                collection.add(map);

                JSONArray result= new JSONArray(collection);
                prefEditor.putString("Secret", result.toString());
                prefEditor.commit();

                tv1.setText("");
                tv2.setText("");
                tv3.setText("");
                tv4.setText("");

            }
        });
        btnRt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences pref = getApplicationContext().getSharedPreferences("TestHashMap", 0);
                String storedCollection = pref.getString("Secret", null);
                collections = new ArrayList<HashMap<String, String>>();
                try {
                    JSONArray array = new JSONArray(storedCollection);
                    HashMap<String, String> item = null;
                    for(int i =0; i<array.length(); i++){
                        String obj = array.get(i).toString();
                        JSONObject ary = new JSONObject(obj);
                        Iterator<String> it = ary.keys();
                        item = new HashMap<String, String>();
                        while(it.hasNext()){
                            String key = it.next();
                            item.put(key, (String)ary.get(key));
                        }
                        collections.add(item);
                    }

                    //tv1.setText(collections.get(0).get("Name"));
                    //tv2.setText(collections.get(0).get("Id"));
                   // tv3.setText(collections.get(0).get("Phone"));
                    //tv4.setText(collections.get(0).get("Sex"));


                    adapter = new ListViewAdapter(MainActivity.this,collections);
                    listview.setAdapter(adapter);
                } catch (JSONException e) {
                    Log.e("Restore", "while parsing", e);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(MainActivity.this, ""+resultCode, Toast.LENGTH_SHORT).show();
        if(resultCode == RESULT_OK){
            int pos = data.getExtras().getInt("PosEdit");

            if(pos >= 0){
                collections.remove(pos);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("TestHashMap", 0);
                SharedPreferences.Editor prefEditor = pref.edit();
                JSONArray result= new JSONArray(collections);
                prefEditor.putString("Secret", result.toString());
                prefEditor.commit();
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Data has been Deleted.", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(this, "Saving OK", Toast.LENGTH_SHORT).show();
                SharedPreferences pref = getApplicationContext().getSharedPreferences("TestHashMap", 0);
                SharedPreferences.Editor prefEditor = pref.edit();
                JSONArray result= new JSONArray(collections);
                prefEditor.putString("Secret", result.toString());
                prefEditor.commit();
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
