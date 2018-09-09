package learner.learnerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.sql.Connection;
import java.sql.Statement;


public class ProfileUpdate extends AppCompatActivity {
    String id, pFirstName, pLastName, pEmail, pDescription;
    String newFirstName, newLastName, newEmail, newDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileupdateview);

        //Gets the data from the extras

        id = getIntent().getStringExtra("<accountID>");
        pFirstName = getIntent().getStringExtra("<FirstName>");
        pLastName = getIntent().getStringExtra("<LastName>");
        pEmail = getIntent().getStringExtra("<Email>");
        pDescription = getIntent().getStringExtra("<pDescription>");

        //Create references to the xml objects

        final EditText editFName =(EditText) findViewById(R.id.upFirstNamePlain);
        final EditText editLName =(EditText) findViewById(R.id.upLastNamePlain);
        final EditText editEmail =(EditText) findViewById(R.id.upEmail);
        final EditText editDescription =(EditText) findViewById(R.id.upDescriptionMulti);

        // place previous original data in the text inputs

        editFName.setText(pFirstName);
        editLName.setText(pLastName);
        editEmail.setText(pEmail);
        editDescription.setText(pDescription);

        Button save = (Button) findViewById(R.id.btnSave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the user input from

                newFirstName = editFName.getText().toString();
                newLastName = editLName.getText().toString();
                newEmail = editEmail.getText().toString();
                newDescription = editDescription.getText().toString();

                //Establish connection to SQL Server
                ConnectionClass connectionClass = new ConnectionClass();

                Connection con = connectionClass.CONN();
                String query = "UPDATE UserProfile SET FirstName='" + newFirstName + "' , LastName ='" + newLastName + "Email ='" + newEmail + "' , ProfileDescription ='" + newDescription +"' WHERE UserProfikeID ="+id;
                try{
                    //executes the query
                    Statement stmt = con.createStatement();
                    stmt.executeQuery(query);
                }
                catch (Exception ex) {
                   String z = "Exceptions";
                }

                startActivity(new Intent(ProfileUpdate.this, Profile.class).putExtra("<AccountID>", id));
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
            startActivity(new Intent(ProfileUpdate.this, Profile.class).putExtra("<accountID>", id));
            return true;
        }
        else  if (id == R.id.offers) {
            startActivity(new Intent(ProfileUpdate.this, Offers.class).putExtra("<accountID>", id));
            return true;
        }
        else  if (id == R.id.requests) {
            startActivity(new Intent(ProfileUpdate.this, Request.class).putExtra("<accountID>", id));
            return true;
        }
        else return false;
    }
}
