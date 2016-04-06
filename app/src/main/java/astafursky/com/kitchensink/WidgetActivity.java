package astafursky.com.kitchensink;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;

/**
 * Created by aStafursky on 4/5/2016.
 */
public class WidgetActivity extends Activity {

    Spinner spinner;
    RatingBar ratingBar;
    Button clickMe;

    String radiobuttonResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_widgets);

        spinner = (Spinner)findViewById(R.id.spinner);

        addListenerOnRatingBar();
        populateSpinner();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public WidgetActivity(){

    }

    public void populateSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.schools_array, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }




    public void addListenerOnRatingBar(){
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(), String.valueOf(ratingBar.getRating() + " star rating."), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void onRadioButtonClicked(View v){
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()){
            case R.id.yes_radioButton:
                if (checked)
                    radiobuttonResponse = "Glad you liked the tutorial!";
                    Toast.makeText(this, "Glad you like the tutorial!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.no_radioButton:
                if (checked)
                    radiobuttonResponse = "Awh :( sorry";
                    Toast.makeText(this, "Awh :( sorry.", Toast.LENGTH_SHORT).show();
                break;
        }


    }

    public void startFinalActivity(View v){

        String spinnerResponse = spinner.getSelectedItem().toString();

        Intent i = new Intent (this, FinalActivity.class);
        i.putExtra("spinnerResponse", spinnerResponse);
        startActivity(i);
    }




}
