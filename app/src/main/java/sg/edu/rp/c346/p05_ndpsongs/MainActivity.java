package sg.edu.rp.c346.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnAdd , btnShow;
    RadioGroup rgStars;
    EditText etTitle, etSinger, etYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("P05-NDPSongs ~ Insert Song");

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnShow = (Button) findViewById(R.id.btnShow);
        rgStars = (RadioGroup) findViewById(R.id.rgStars);
        etTitle = (EditText) findViewById(R.id.etSong);
        etSinger = (EditText) findViewById(R.id.etSinger);
        etYear = (EditText) findViewById(R.id.etYear);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);
                String newSong = etTitle.getText().toString();
                String newSinger = etSinger.getText().toString();
                int newYear = Integer.parseInt(etYear.getText().toString());
                int newStars = Integer.parseInt(((RadioButton) rgStars.getChildAt(rgStars.indexOfChild(rgStars.findViewById(rgStars.getCheckedRadioButtonId())))).getText().toString());
                db.insertSong(newSong,newSinger,newYear,newStars);
                db.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ShowActivity.class);
                startActivity(i);
            }
        });
    }
}
