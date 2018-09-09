package learner.learnerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import android.content.Intent;
import android.view.View;
import android.widget.Button;



public class Profile extends AppCompatActivity {
    String id, z, firstName, lastName, email, pDescription;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileview);
        id = getIntent().getStringExtra("<accountID>");
        connectionClass = new ConnectionClass();

        // Establishes connection
        Connection con = connectionClass.CONN();
        String query = "SELECT FirstName,LastName, Email, ProfileDescription FROM dbo.UserProfile WHERE UserID = 1";

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(query);
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                    if (i == 1) {
                        TextView fn = (TextView) findViewById(R.id.profileFirstName);
                        fn.setText(columnValue);
                        firstName=columnValue;
                    }
                    if (i == 2) {
                        TextView ln = (TextView) findViewById(R.id.profileLastName);
                        ln.setText(columnValue);
                        lastName=columnValue;
                    }
                    if (i == 3) {
                        TextView em = (TextView) findViewById(R.id.profileEmail);
                        em.setText(columnValue);
                        email=columnValue;
                    }
                    if (i == 4) {
                        TextView de = (TextView) findViewById(R.id.profileDescription);
                        de.setText(columnValue);
                        pDescription=columnValue;
                    }
                }

            }
        }
            catch (Exception ex) {
                z = "Exceptions";
            }


        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        //Create quick Menu

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the user input from
                Intent update = new Intent (Profile.this, ProfileUpdate.class);
                Bundle extras = new Bundle();
                extras.putString("<AccountID>", id);
                extras.putString("<FirstName>", firstName);
                extras.putString("<LastName>", lastName);
                extras.putString("<Email>", email);
                extras.putString("<Description>", pDescription);
                update.putExtras(extras);
                startActivity(update);
            }
        });


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
            startActivity(new Intent(Profile.this, Profile.class).putExtra("<accountID>", id));
            return true;
        }
        else  if (id == R.id.offers) {
            startActivity(new Intent(Profile.this, Offers.class).putExtra("<accountID>", id));
            return true;
        }
        else  if (id == R.id.requests) {
            startActivity(new Intent(Profile.this, Request.class).putExtra("<accountID>", id));
            return true;
        }
        else return false;
    }

}





















        /*//connect to the Database
        String url = "jdbc:jtdc:sqlserver://10.161.118.219:3306/LearnerAppISQS4349001";
        try {
            //connects to the localhost on sql server
            Connection con = DriverManager.getConnection(url, "matia", "");
            //creates variable statement for First Name
            Statement statement = con.createStatement();
            ResultSet firstName = statement.executeQuery("SELECT FirstName FROM dbo.UserProfile WHERE UserID ="+ id);
            while (firstName.next()) {

                String result = firstName.getString(1);

                TextView FirstNameToChange = findViewById(R.id.profileFirstName);
                FirstNameToChange.setText(result);
            }
            firstName.close();
            statement.close();


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
            startActivity(new Intent(Profile.this, Profile.class).putExtra("<accountID>", id));
            return true;
        }
        else  if (id == R.id.offers) {
            startActivity(new Intent(Profile.this, Offers.class).putExtra("<accountID>", id));
            return true;
        }
        else  if (id == R.id.requests) {
            startActivity(new Intent(Profile.this, Request.class).putExtra("<accountID>", id));
            return true;
        }
        else return false;*/


