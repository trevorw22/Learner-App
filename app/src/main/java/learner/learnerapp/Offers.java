package learner.learnerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Offers extends AppCompatActivity {
    String id = "1", z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offersview);
        //id= getIntent().getStringExtra("<accountID>");
        List<Map<String, String>> offerlist  = new ArrayList<Map<String, String>>();
        Map<String, String> datanum = new HashMap<String, String>();
        ListView ol;


        //Establish connection

        ConnectionClass connectionClass = new ConnectionClass();
        Connection con = connectionClass.CONN();
        String query = "SELECT Title FROM TutorOffer INNER JOIN Tutor OM Tutor.TutorID = TutorOffer.TutorID WHERE Tutor.UserID = 1";
        try {
            //executes the query
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //gets the results
            ArrayList data1 = new ArrayList();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                    if (i == 1) {
                        datanum.put("A", rs.getString(i));
                    }
                    offerlist.add(datanum);
                }
            }
        }
        catch (Exception ex) {
                z = "Exceptions";
            }
        //Create reference to the ListView
        ol = (ListView) findViewById(R.id.offersList);
        String[] from = { "A", "B"};
        int[] views = { R.id.offerListTitle,R.id.offerListRate };
        // Populate List View With the results
        final SimpleAdapter ADA = new SimpleAdapter(Offers.this,
                offerlist,R.layout.offer_list_view, from, views);
        ol.setAdapter(ADA);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.profile) {
            startActivity(new Intent(Offers.this, Profile.class).putExtra("<accountID>", id));
            return true;
        }
        else  if (id == R.id.offers) {
            startActivity(new Intent(Offers.this, Offers.class).putExtra("<accountID>", id));
            return true;
        }
        else  if (id == R.id.requests) {
            startActivity(new Intent(Offers.this, Request.class).putExtra("<accountID>", id));
            return true;
        }
        else return false;
    }
}
