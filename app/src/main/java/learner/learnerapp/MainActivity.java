package learner.learnerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String id = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create reference for button
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        //create button listener
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the user input from
                EditText searchtext =(EditText)findViewById(R.id.searchTextView);
                String search;
                search = searchtext.getText().toString();
                //Create new activity of search results
                startActivity(new Intent(MainActivity.this, SearchResult.class).putExtra("<search>", search));
            }
        });

    }
    //create popup menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.popup_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.profile) {
            startActivity(new Intent(MainActivity.this, Profile.class).putExtra("<accountID>", id));
            return true;
        }
        else  if (id == R.id.offers) {
            startActivity(new Intent(MainActivity.this, Offers.class).putExtra("<accountID>", id));
            return true;
        }
        else  if (id == R.id.requests) {
            startActivity(new Intent(MainActivity.this, Request.class).putExtra("<accountID>", id));
            return true;
        }
        else return false;
    }
}
