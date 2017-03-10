package com.nettest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private static final String LIST = "LIST";
    private List<String> contactsList;
    private ListView mListViewContacts;

    public static Intent newIntent(Context context, ArrayList<String> conList) {
        Intent intent = new Intent(context, ContactsActivity.class);
        intent.putStringArrayListExtra(LIST, conList);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        contactsList = getIntent().getStringArrayListExtra(LIST);

        mListViewContacts = (ListView) findViewById(R.id.list_view_contacts);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactsList);
        mListViewContacts.setAdapter(arrayAdapter);


    }

}
