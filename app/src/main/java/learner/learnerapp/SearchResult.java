package learner.learnerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResult extends AppCompatActivity {

    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultsview);
        s= getIntent().getStringExtra("<search>");
        setSearch();
        Button btnSearch = (Button)findViewById(R.id.btnSearch);

        ConnectionClass connectionClass;
        connectionClass = new ConnectionClass();
        Connection con = connectionClass.CONN();
        ListView results = (ListView)findViewById(R.id.resultList);
        SimpleAdapter ADAhere;
        String query = "SELECT SubjectName FROM TutorOffer t INNER JOIN SubjectType s ON t.SubjectID = s.SubjectID WHERE SubjectName = "+ '\'' + s + '\'';

        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            List<Map<String, String>> titles = null;
            titles = new ArrayList<Map<String, String>>();
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(query);
            int columnsNumber = rsmd.getColumnCount();
            Map<String, String> datanum = new HashMap<String, String>();
            //this is the new while(rs.next) attempt
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                    if (i == 1) {
                        TextView fn = (TextView) findViewById(R.id.profileFirstName);
                        datanum.put("A", rs.getString(i));
                        titles.add(datanum);
                    }

                }

            }
            String[] fromwhere = { "A" };
            int[] viewswhere = { R.id.lblresultname };
            ADAhere = new SimpleAdapter(SearchResult.this, titles, R.layout.listtemplate, fromwhere, viewswhere);
            results.setAdapter(ADAhere);

           /* while(rs.next()){

                Map<String, String> datanum = new HashMap<String, String>();
                datanum.put("A", rs.getString("Title"));
                titles.add(datanum);
            }
            String[] fromwhere = { "A" };
            int[] viewswhere = { R.id.lblresultname };
            ADAhere = new SimpleAdapter(SearchResult.this, titles, R.layout.listtemplate, fromwhere, viewswhere);
            results.setAdapter(ADAhere);*/


        }
        catch(SQLException e){
            Toast.makeText(SearchResult.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }

    }
    public void setSearch(){
        EditText newSearch = (EditText)findViewById(R.id.searchPlain);
        newSearch.setText(s);
    }
}

