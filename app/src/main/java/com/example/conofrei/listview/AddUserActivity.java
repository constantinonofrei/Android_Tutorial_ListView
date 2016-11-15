package com.example.conofrei.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.conofrei.listview.dto.SqlItem;
import com.example.conofrei.listview.service.DBHandler;

import java.util.List;

public class AddUserActivity extends AppCompatActivity {
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        dbHandler = new DBHandler(this, null, null, 1);
    }

    public void onClickAdd(View view) {
        Log.i("CTIN", "click me");
        TextView txtUser = (TextView)findViewById(R.id.txtUserName);
        TextView txtScore = (TextView)findViewById(R.id.txtScore);

        dbHandler.addProduct(new SqlItem(txtUser.getText().toString(), Integer.parseInt(txtScore.getText().toString()), 1));

    }

    public void onClickList(View view) {
        Log.i("CTIN", "click onClickList");
        List<SqlItem> dbaList = dbHandler.getSqlItems();
        String[] items = null;
        if(!dbaList.isEmpty()) {
            items=new String[dbaList.size()];
            int i=0;
            for(SqlItem sqlItem : dbaList) {
                items[i++] = sqlItem.getName()+ " " + sqlItem.getScore();
            }
        }
        ListAdapter la = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        ListView lv = (ListView)findViewById(R.id.listUser);
        lv.setAdapter(la);

    }
}
