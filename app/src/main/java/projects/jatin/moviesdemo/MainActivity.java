package projects.jatin.moviesdemo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import projects.jatin.moviesdemo.Fragments.NowPlaying;
import projects.jatin.moviesdemo.Fragments.TopRatedMovies;
import projects.jatin.moviesdemo.Fragments.UpcomingMovies;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.base_fragment, new UpcomingMovies()).commit();

        BottomNavigationView bottomNavigationView= (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.upcoming:
                        Toast.makeText(MainActivity.this, "upcoming", Toast.LENGTH_SHORT).show();
                        FragmentManager fm= getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.base_fragment, new UpcomingMovies()).commit();
                        break;

                    case R.id.now_playing:
                        Toast.makeText(MainActivity.this, "Now Playing", Toast.LENGTH_SHORT).show();
                        fm = getSupportFragmentManager();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.base_fragment, new NowPlaying()).commit();
                        break;

                    case R.id.top_rated:
                        fm = getSupportFragmentManager();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.base_fragment,new TopRatedMovies()).commit();
                        Toast.makeText(MainActivity.this, "Top Rated", Toast.LENGTH_SHORT).show();
                        break;

                }
           return true;
            }
        });
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                /*switch (item.getItemId())
                {
                    case R.id.upcoming:
                        Toast.makeText(MainActivity.this, "upcoming", Toast.LENGTH_SHORT).show();
                        FragmentManager fm= getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.base_fragment, new UpcomingMovies()).commit();
                        break;

                    case R.id.now_playing:
                        Toast.makeText(MainActivity.this, "Now Playing", Toast.LENGTH_SHORT).show();
                        fm = getSupportFragmentManager();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.base_fragment, new NowPlaying()).commit();
                        break;

                    case R.id.top_rated:
                        fm = getSupportFragmentManager();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.base_fragment,new TopRatedMovies()).commit();
                        Toast.makeText(MainActivity.this, "Top Rated", Toast.LENGTH_SHORT).show();
                        break;

                }*/
            }
        });
    }
}
