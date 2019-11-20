package com.example.social_event;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //own variable
    ImageView imageView;
    Uri filepath;
    final int PICK_IMAGE_REQUEST=71;
   // for firebase storage
    FirebaseStorage storage;
    StorageReference storageReference;

    ListView listView;
    String mTitle[] = {"Valmiki Ambedkar Awas Yojana","Swarna Jayanti Shahari Rozgar Yojana ","Antyodaya Anna Yojana ","Pradhan Mantri Gram Sadak Yojana","Prime Minister’s Rozgar Yojana ","Pradhan Mantri Gramodaya Yojana","Indira Awaas Yojana","Sampoorna Grameen Rozgar Yojana","Swaranjayanti Gram Swarozgar Yojana","Jawahar Rozgar Yojana","Integrated Rural Development Programme","Pradhan Mantri Gram Sadak Yojana","Rajiv Gandhi Grameen Vidutikaran Yojana","Rashtriya Swastha Bima Yojana"};
    String mDescription[]={" The VAMBAY launched in December 2001 facilitates the construction and upgradation of dwelling units for the slum dwellers and provides a healthy and enabling urban environment through community toilets under Nirmal Bharat Abhiyan, a component of the scheme. The Central Government provides a subsidy of 50 per cent, the balance 50 per cent being arranged by the State Government.","The Urban Self Employment Programme and the Urban Wage Employment Programme are the two special components of the SJSRY, which, in December 1997, substituted for various extant programmers implemented for urban poverty alleviation. SJSRY is funded on 75:25 basis between the Centre and the States.","AAY launched in December 2000 provides food grains at a highly subsidized rate of Rs.2.00 per kg for wheat and Rs.3.00 per kg for rice to the poor families under the Targeted Public Distribution System (TPDS). The scale of issue, which was initially 25 kg per family per month, was increased to 35 kg per family per month from April 1, 2002. The scheme initially for one crore families was expanded in June 2003 by adding another 50 lakh BPL Families.","The PMGSY, launched in December 2000 as a 100 per cent centrally Sponsored Scheme, aims at providing rural connectivity to unconnected habitations with population of 500 persons or more in the rural areas by the end of the Tenth Plan period. Augmenting and modernising rural roads has been included as an item of the NCMP. The programme is funded mainly from the accruals of diesel cess in the Central Road Fund. In addition, support of the multi-lateral funding agencies and the domestic financial institutions are being obtained to meet the financial requirements of the programme.","PMRY started in 1993 with the objective of making available self-employment opportunities to the educated unemployed youth by assisting them in setting up any economically viable activity. While the REGP is implemented in the rural areas and small towns (population up to 20,000) for setting up village industries without any cap on income, educational qualification or age of the beneficiary, PMRY is meant for educated unemployed youth with family income of up to Rs.40, 000 per annum, in both urban and rural areas, for engaging in any economically viable activity.","PMGY launched in 2000-01 envisages allocation of Additional Central Assistance (ACA) to the States and UTs for selected basic services such as primary health, primary education, rural shelter, rural drinking water, nutrition and rural electrification.","The Indira Awaas Yojana (IAY) operationalised from 1999-2000 is the major scheme for construction of houses for the poor, free of cost. The Ministry of Rural Development (MORD) provides equity support to the Housing and Urban Development Corporation (HUDCO) for this purpose.","SGRY, launched in 2001, aims at providing additional wage employment in all rural areas and thereby food security and improve nutritional levels. The SGRY is open to all rural poor who are in need of wage employment and desire to do manual and unskilled work around the village/habitat. The programme is implemented through the Panchayati Raj Institutions (PRIs).","SGSY, launched in April 1999, aims at bringing the assisted poor families (Swarozgaris) above the poverty line by organizing them into Self Help Groups (SHGs) through a mix of Bank credit and Government subsidy.","JRY was launched as Centrally Sponsored Scheme on 1st April, 1989 by merging National Rural Employment Programme (NREP) and Rural Landless Employment Guarantee Programme (RLEGP). Its main objective was generation of additional gainful employment for the unemployed and under-employed people in rural areas through the creation of rural economic infrastructure, community and social assets with the aim of improving the quality of life of the rural poor.","The Integrated Rural Development Programme (IRDP) was started in 1980-81 in all blocks of the country and continued as a major self-employment scheme till April 1, 1999. Then, it was restructured as the Swarnjayanti Gram Swarozgar Yojana (SGSY) which aimed at self-employment of the rural poor. The objective will be achieved through acquisition of productive assets or appropriate skills that would generate an additional income on a sustained basis to enable them to cross poverty line.","The primary objective of the PMGSY is to provide Connectivity, by way of an All-weather Road (with necessary culverts and cross-drainage structures, which is operable throughout the year), to the eligible unconnected Habitations in the rural areas, in such a way that all Unconnected Habitations with a population of 1000 persons and above are covered in three years (2000-2003) and all Unconnected Habitations with a population of 500 persons and above by the end of the Tenth Plan Period (2007). In respect of the Hill States (North-East, Sikkim, Himachal Pradesh, Jammu & Kashmir, Uttaranchal) and the Desert Areas (as identified in the Desert Development Programme) as well as the Tribal (Schedule V) areas, the objective would be to connect Habitations with a population of 250 persons and above.","Under RGGVY, electricity distribution infrastructure is envisaged to establish Rural Electricity Distribution Backbone (REDB) with at least a 33/11KV sub-station in a block, Village Electrification Infrastructure (VEI) with at least a Distribution Transformer in a village or hamlet, and standalone grids with generation where grid supply is not feasible.","RSBY has been launched by Ministry of Labour and Employment, Government of India to provide health insurance coverage for Below Poverty Line (BPL) families. The objective of RSBY is to provide protection to BPL households from financial liabilities arising out of health shocks that involve hospitalization. Beneficiaries under RSBY are entitled to hospitalization coverage up to Rs. 30,000/- for most of the diseases that require hospitalization. Government has even fixed the package rates for the hospitals for a large number of interventions. Pre-existing conditions are covered from day one and there is no age limit. Coverage extends to five members of the family which includes the head of household, spouse and up to three dependents. Beneficiaries need to pay only Rs. 30/- as registration fee while Central and State Government pays the premium to the insurer selected by the State Government on the basis of a competitive bidding."};
    int images[]={R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4,R.drawable.b5,R.drawable.b6,R.drawable.b7,R.drawable.b8,R.drawable.b9,R.drawable.b10,R.drawable.b11,R.drawable.b12,R.drawable.b13,R.drawable.b14};
    String url[]={"https://www.gktoday.in/gk/valmiki-ambedkar-awas-yojana-vambay_29/amp/","https://en.wikipedia.org/wiki/Swarna_Jayanti_Shahari_Rozgar_Yojana","https://dfpd.gov.in/aay_C.htm","http://omms.nic.in/","https://dcmsme.gov.in/schemes/pmry.html","https://www.indiastat.com/social-and-welfare-schemes-data/27/rural-schemes/247/pradhan-mantri-gramodaya-yojana-pmgy/18052/stats.aspx","https://pmayg.nic.in/netiay/about-us.aspx","https://archive.india.gov.in/sectors/rural/index.php?id=13","https://archive.india.gov.in/sectors/rural/index.php?id=15","https://data.gov.in/keywords/jawahar-rozgar-yojana-jry","https://www.bankbazaar.com/saving-schemes/integrated-rural-development-program.html","http://omms.nic.in/","https://www.india.gov.in/rajiv-gandhi-grameen-vidyutikaran-yojana","http://www.rsby.gov.in/how_works.html"};
    //own variable

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> postLists;
    private FirebaseAuth auth;
    private List<String> followingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        auth = FirebaseAuth.getInstance();
        //my own code for content profile
        listView = findViewById( R.id.listview );
         MyAdapter adapter =new MyAdapter( this,mTitle,mDescription,images);
         listView.setAdapter( adapter );
         listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                 Intent intent =new Intent( Intent.ACTION_VIEW);
                 intent.setData( Uri.parse(url[i]) );
                 startActivity( intent );
             }
         } );
        //code ends here

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        //own init
        storage = FirebaseStorage.getInstance();
        storageReference  = storage.getReference();
        //own init
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //own code profile photo update

        View headView = navigationView.getHeaderView(0);
         imageView = headView.findViewById(R.id.imageView);

         Bitmap bitmp = BitmapFactory.decodeResource(getResources(),R.id.imageView);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),bitmp);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                chooseImage();
             uploadImage();
                //startActivity( new Intent( getApplicationContext(),PostActivity.class ) );

            }
        });

        TextView textView = headView.findViewById(R.id.textview);
        Bundle bn=getIntent().getExtras();
        String email= bn.getString("email");
        textView.setText(String.valueOf(email));
        //own code

    }

    class MyAdapter extends ArrayAdapter<String>
    {
        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter (Context c,String title[],String description[],int imgs[])
        {
         super(c,R.layout.row,R.id.textview1,title);
         this.context = c;
         this.rTitle =title;
         this.rDescription =description;
         this.rImgs =imgs;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            View row =layoutInflater.inflate( R.layout.row,parent,false );
            ImageView images = row.findViewById( R.id.list_image );
            TextView myTitle =row.findViewById( R.id.textview1 );
            TextView myDescription = row.findViewById( R.id.textview2 );

            //now set our resource on views
            images.setImageResource(rImgs[position]);
            myTitle.setText( rTitle[position] );
            myDescription.setText( rDescription[position] );


            return row;
        }
    }
    /*
    private  void checkFollowing()
    {
        followingList = new ArrayList<>(  );
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Follow")
                .child( FirebaseAuth.getInstance().getCurrentUser().getUid() )
                .child( "following" );

        reference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                followingList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    followingList.add(snapshot.getKey());
                }
                readPosts();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

    }
    private void readPosts()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");
        reference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              postLists.clear();

              for(DataSnapshot snapshot:dataSnapshot.getChildren())
              {
                 Post post =snapshot.getValue(Post.class);
                 for(String id:followingList)
                 {
                     if(post.getPublisher().equals( id ))
                     {
                         postLists.add( post );
                     }
                 }
              }
              postAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }
    */
    //own code start here
    private void uploadImage() {
        if(filepath!=null)
        {
            Toast.makeText(getApplicationContext(),"strat me hai",Toast.LENGTH_SHORT).show();
            final ProgressDialog progressDialog = new ProgressDialog(this);
//            progressDialog.setTitle("Uploading...");
//            progressDialog.show();

            StorageReference ref=FirebaseStorage.getInstance().getReference("image/"+ auth.getCurrentUser().getUid()+".jpg");
            Log.d("Fleoath","####"+filepath);
            ref.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getApplicationContext(),"Uploaded",Toast.LENGTH_SHORT).show();
                            //progressDialog.dismiss();


                        }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Failed "+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }


    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode ==RESULT_OK
             && data !=null && data.getData()!=null)
        {
            filepath = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                imageView.setImageBitmap(bitmap);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

    }
    //own code end here


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        //
        //
        // The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }
    // main work
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment=null;
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            //fragment = new homeFragment();
            Intent intent = new Intent(getApplicationContext(),PostActivity.class );
            startActivity( intent );

        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(getApplicationContext(),galleryIntent.class );
            startActivity( intent );

        }
        else if (id == R.id.nav_ngos) {
            Intent intent = new Intent(getApplicationContext(),NgoActivity.class );
            startActivity( intent );

        } else if (id == R.id.nav_developers) {
            Intent intent = new Intent(getApplicationContext(),DeveloperActivity.class );
            startActivity( intent );

        } else if (id == R.id.nav_share) {

        }

        if(fragment !=null)
        {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.screen_area,fragment);
            fragmentTransaction.commit();



        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}




























