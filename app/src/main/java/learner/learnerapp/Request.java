package learner.learnerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request extends AppCompatActivity {
    String id, z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requestview);
        id = getIntent().getStringExtra("<accountID>");

        List<Map<String, String>> requestlist  = new ArrayList<Map<String, String>>();
        ListView rl;
        Map<String, String> datanum = new HashMap<String, String>();


        //Establish connection

        ConnectionClass connectionClass = new ConnectionClass();
        Connection con = connectionClass.CONN();
        String query = "Select Title, RequestDate FROM TutoringRequest tr INNER JOIN Student st ON st.StudentID = tr.StudentID INNER JOIN TutorOffer tuto ON tuto.OfferID = tr.OfferID WHERE UserID = 1"
                ;
        try{
            //executes the query
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //gets the results
            //ArrayList data1 = new ArrayList();
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
                        else if (i == 2){
                            datanum.put("B", rs.getString(i));
                        }

                    }


                }

            requestlist.add(datanum);
            System.out.println(requestlist);

              /*  Map<String, String> datanum = new HashMap<String, String>();
                datanum.put("A", rs.getString("Title"));
                datanum.put("B", rs.getString("RequestDate"));
                requestlist.add(datanum);*/



        }
        catch (Exception ex) {
            z = "Exceptions";
        }
        //Create reference to the ListView
        rl = (ListView) findViewById(R.id.requestList);
        String[] from = { "A", "B"};
        int[] views = { R.id.requestListTitle,R.id.requestListDate };
        // Populate List View With the results
        final SimpleAdapter ADA = new SimpleAdapter(Request.this,
                requestlist,R.layout.requests_list_view, from, views);
        rl.setAdapter(ADA);
    }
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
            startActivity(new Intent(Request.this, Profile.class).putExtra("<accountID>", id));
            return true;
        }
        else  if (id == R.id.offers) {
            startActivity(new Intent(Request.this, Offers.class).putExtra("<accountID>", id));
            return true;
        }
        else  if (id == R.id.requests) {
            startActivity(new Intent(Request.this, Request.class).putExtra("<accountID>", id));
            return true;
        }
        else return false;
    }
}
