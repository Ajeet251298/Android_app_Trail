package com.example.social_event;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NgoActivity extends AppCompatActivity {

    private List<AllNgo> allNgos=new ArrayList<AllNgo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ngo );

        allNgos.add(new AllNgo( "Smile Foundation",R.drawable.smile ,"The main aim of this NGO is the rehabilitation of the underprivileged by providing them education and healthcare services, thereby converting them into productive assets. It is run by a group of corporate professionals.https://www.smilefoundationindia.org/\n","https://www.smilefoundationindia.org/"));
        allNgos.add(new AllNgo( "UDAAN",R.drawable.udaan,"The main aim of this NGO is to help the destitute, the main area of stress being women, children and senior citizens and also environmental welfare. One of their main projects is a cancer chemotherapy center.https://www.udaanwelfare.org\n","https://www.udaanwelfare.org\n" ));
        allNgos.add(new AllNgo( "SAMMAAN",R.drawable.samaan,"Originally established to link the poor to the mainstream through education, training and financial support, the current project of this NGO involves the rickshaw pullers to help them earn a better livelihood. This NGO also has notable contribution in areas like children education, health services and welfare of women.http://sammaan.org/\n" ,"http://sammaan.org/"));
        allNgos.add(new AllNgo( "PRATHAM",R.drawable.pratham,"The main aim of this NGO is to provide education to the children living in the huge slums of Mumbai and even providing education to those people who are unable to go to school. Their projects have increased enrollment of children in schools thus promising them a better tomorrow.http://www.pratham.org.in/\n","http://www.pratham.org.in/" ));
        allNgos.add(new AllNgo( "GOONJ",R.drawable.goonj ,"A recipient of the “NGO of the Year” award in 2007 at the India NGO Awards, this NGO aims at solving the clothing problems of the downtrodden. Goonj also provides relief during Rahat floods in West Bengal, Assam and Bihar.https://goonj.org/\n","https://goonj.org/"));
        allNgos.add(new AllNgo( "AKSHAYA",R.drawable.akshaya,"The sole aim of this NGO is to restore human dignity. Operating in Madurai, this NGO offers rehabilitation, healthy food and care to the street destitute.http://www.akshayatrust.org/about.php\n","http://www.akshayatrust.org/about.php\n" ));

        ArrayAdapter<AllNgo> adapter = new customAdapter();
        ListView mylist = (ListView)findViewById( R.id.list );
        mylist.setAdapter( adapter );

        mylist.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              AllNgo currngo = allNgos.get( i );
                Intent intent =new Intent( Intent.ACTION_VIEW);
                intent.setData( Uri.parse(currngo.getUrl()) );
                startActivity( intent );
            }
        } );
    }

    private class customAdapter extends ArrayAdapter<AllNgo>
    {
        public customAdapter() {
            super( NgoActivity.this,R.layout.my_layout_ngo ,allNgos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                convertView =getLayoutInflater().inflate( R.layout.my_layout_ngo,parent,false );
            }

            AllNgo currngo= allNgos.get( position );
            ImageView images = convertView.findViewById( R.id.list_image );
            TextView myTitle =convertView.findViewById( R.id.textview1 );
            TextView myDescription = convertView.findViewById( R.id.textview2 );

            images.setImageResource( currngo.getImageID() );
            myTitle.setText( currngo.getName() );
            myDescription.setText( currngo.getDes() );
            return convertView;
        }
    }
}
