package samplefragment.com.sqlitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper myDb;
    EditText editName, editSurname, editMarks, editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    Button btnviewUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editName = (EditText) findViewById(R.id.editText_name);
        editSurname = (EditText) findViewById(R.id.editText_surname);
        editMarks = (EditText) findViewById(R.id.editText_Marks);
        editTextId = (EditText) findViewById(R.id.editText_id);
        btnAddData = (Button) findViewById(R.id.button_add);
        btnviewAll = (Button) findViewById(R.id.button_viewAll);
        btnviewUpdate = (Button) findViewById(R.id.button_update);
        btnDelete = (Button) findViewById(R.id.button_delete);
        btnAddData.setOnClickListener(this);
        btnviewAll.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnviewUpdate.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add:
                addData();
                break;
            case R.id.button_viewAll:
                viewAllData();
                break;
            case R.id.button_update:
                break;
            case R.id.button_delete:
                break;
        }
    }


    private void viewAllData(){
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("Name :"+ res.getString(1)+"\n");
            buffer.append("Surname :"+ res.getString(2)+"\n");
            buffer.append("Marks :"+ res.getString(3)+"\n\n");
        }

        // Show all data
        showMessage("Data",buffer.toString());
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    private void addData(){
        myDb.insertData(editName.getText().toString(),editSurname.getText().toString(),editMarks.getText().toString());
        editName.getText().clear();
        editSurname.getText().clear();
        editMarks.getText().clear();
    }

    public void addBirthday(View view) {
        // Add a new birthday record
        ContentValues values = new ContentValues();

        values.put(BirthProvider.NAME,
                editName.getText().toString());

        values.put(BirthProvider.BIRTHDAY,
                editSurname.getText().toString());

        Uri uri = getContentResolver().insert(
                BirthProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                "Javacodegeeks: " + uri.toString() + " inserted!", Toast.LENGTH_LONG).show();
    }

}
