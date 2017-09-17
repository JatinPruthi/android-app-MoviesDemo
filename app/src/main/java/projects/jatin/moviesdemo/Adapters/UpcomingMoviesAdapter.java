package projects.jatin.moviesdemo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import projects.jatin.moviesdemo.CalendarEvent;
import projects.jatin.moviesdemo.Model.UpcomingMoviesModel;
import projects.jatin.moviesdemo.R;

/**
 * Created by Jateen on 01-09-2017.
 */

public class UpcomingMoviesAdapter extends RecyclerView.Adapter<UpcomingMoviesAdapter.MyViewHolder> {

    private List<UpcomingMoviesModel> list;
    Context context;
    int count=0;

    public UpcomingMoviesAdapter(List<UpcomingMoviesModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public UpcomingMoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_upcomingmovies,parent,false);
        return new UpcomingMoviesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UpcomingMoviesAdapter.MyViewHolder holder, int position) {

        final UpcomingMoviesModel upcomingMoviesModel=list.get(position);

        holder.title.setText("Title: "+upcomingMoviesModel.getTitle());
        holder.language.setText("Language: "+upcomingMoviesModel.getLanguage());
        holder.release_date.setText("Release Date: "+upcomingMoviesModel.getRelease_date());
        holder.overview.setText("Overview: "+upcomingMoviesModel.getOverview());

        Picasso.with(context).load(upcomingMoviesModel.getImage_url()).into(holder.picture);


        holder.cardlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count==1)
                {
                    holder.overview.setVisibility(View.GONE);
                    count=0;
                }

                if(count==0){
                    holder.overview.setVisibility(View.VISIBLE);
                    count=1;
                }
            }
        });

        holder.cardlayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i=new Intent(context, CalendarEvent.class);
                i.putExtra("movie",upcomingMoviesModel.getTitle());
                context.startActivity(i);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title,language,release_date,overview;
        ImageView picture;
        RelativeLayout cardlayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            cardlayout= (RelativeLayout) itemView.findViewById(R.id.card_movies);
            release_date=(TextView) itemView.findViewById(R.id.release_date);
            picture= (ImageView) itemView.findViewById(R.id.pic);
            language=(TextView)itemView.findViewById(R.id.language);
            title=(TextView)itemView.findViewById(R.id.movieTitle);
            overview= (TextView) itemView.findViewById(R.id.overview);

        }

    }
}

