package sg.edu.rp.c346.p05_ndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongsArrayAdapter extends ArrayAdapter<Song> {
    Context context;
    ArrayList<Song> songs;
    int resource;
    ImageView iv1, iv2, iv3, iv4, iv5;
    TextView tvYear, tvTitle, tvSinger;

    public SongsArrayAdapter(Context context, int resource, ArrayList<Song> songs) {
        super(context, resource, songs);
        this.context = context;
        this.songs = songs;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables

        iv1 = (ImageView) rowView.findViewById(R.id.imageView1Star);
        iv2 = (ImageView) rowView.findViewById(R.id.imageView2Star);
        iv3 = (ImageView) rowView.findViewById(R.id.imageView3Star);
        iv4 = (ImageView) rowView.findViewById(R.id.imageView4Star);
        iv5 = (ImageView) rowView.findViewById(R.id.imageView5Star);

        tvYear = (TextView) rowView.findViewById(R.id.tvYear);
        tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
        tvSinger = (TextView) rowView.findViewById(R.id.tvSinger);

        Song song = songs.get(position);

        if(song.getStars() >= 1){
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }if(song.getStars() >= 2){
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
        }if(song.getStars() >= 3){
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
        }if(song.getStars() >= 4){
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
        }if(song.getStars() >= 5){
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
        }

        tvYear.setText(song.getYear() + "");
        tvTitle.setText(song.getTitle());
        tvSinger.setText(song.getSingers());

        return rowView;
    }
}
