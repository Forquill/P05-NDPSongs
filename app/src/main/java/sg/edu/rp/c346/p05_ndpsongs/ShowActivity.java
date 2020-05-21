package sg.edu.rp.c346.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    ArrayAdapter aa;
    ListView lvShow;
    ArrayList<Song> song;
    Button btn5Star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        this.setTitle("P05-NDPSongs ~ Show Song");

        lvShow = (ListView) findViewById(R.id.lvShow);
        btn5Star = (Button) findViewById(R.id.btnShowFive);

        DBHelper db = new DBHelper(ShowActivity.this);
        final ArrayList<Song> data = db.getAllSongs();
        db.close();

        song = new ArrayList<Song>();

        for (int i = 0; i < data.size(); i++){
            song.add(data.get(i));
        }

        ArrayAdapter aa = new SongsArrayAdapter(ShowActivity.this,R.layout.row,song);
        lvShow.setAdapter(aa);

        btn5Star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                song.clear();
                for (int i = 0; i < data.size(); i++){
                    if (data.get(i).getStars() == 5){
                        song.add(data.get(i));
                        ArrayAdapter aa = new SongsArrayAdapter(ShowActivity.this,R.layout.row,song);
                        lvShow.setAdapter(aa);
                    }
                }
            }
        });

        lvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long identity) {
                Song data = song.get(position);
                Intent i = new Intent(ShowActivity.this, ModifyActivity.class);
                i.putExtra("data",data);
                startActivityForResult(i,9);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 9) {
                song.clear();
                DBHelper dbh = new DBHelper(ShowActivity.this);
                song.addAll(dbh.getAllSongs());
                dbh.close();
                ArrayAdapter aa = new SongsArrayAdapter(ShowActivity.this,R.layout.row,song);
                lvShow.setAdapter(aa);
            }
        }
    }

}
