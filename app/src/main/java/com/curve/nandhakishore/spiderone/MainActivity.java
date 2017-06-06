package com.curve.nandhakishore.spiderone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends Activity {

    ArrayList<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        final EditText textAdd = (EditText) findViewById(R.id.editText_add);
        final EditText textRem = (EditText) findViewById(R.id.editText_rem);
        Button buttonAdd = (Button) findViewById(R.id.button_add);
        Button buttonRem = (Button) findViewById(R.id.button_rem);

        ListView list_todo = (ListView)findViewById(R.id.list_todo);
        final todoAdapter list_adapter= new todoAdapter(getApplicationContext(),items);
        list_todo.setAdapter(list_adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(textAdd.getText())) {
                    Toast.makeText(getApplicationContext(), "Enter some text in the field", Toast.LENGTH_SHORT).show();
                }
                else {
                    items.add(textAdd.getText().toString());
                    list_adapter.notifyDataSetChanged();
                    textAdd.setText(null);
                }
            }
        });

        buttonRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(textRem.getText())) {
                    Toast.makeText(getApplicationContext(), "Enter some text in the field", Toast.LENGTH_SHORT).show();
                }
                else if (!TextUtils.isDigitsOnly(textRem.getText())){
                    Toast.makeText(getApplicationContext(), "Enter valid position", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(textRem.getText().toString()) > items.size()) {
                    Toast.makeText(getApplicationContext(), "No element in requested position", Toast.LENGTH_SHORT).show();
                }
                else {
                    items.remove(Integer.parseInt(textRem.getText().toString())-1);
                    list_adapter.notifyDataSetChanged();
                    textRem.setText(null);
                }
            }
        });

        list_todo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent send_name = new Intent(getApplicationContext(), ResultActivity.class);
                final String displayName = (String) adapterView.getItemAtPosition(i);
                send_name.putExtra("KEY1", displayName);
                startActivity(send_name);
            }
        });
    }
}