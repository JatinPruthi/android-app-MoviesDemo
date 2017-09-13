package projects.jatin.moviesdemo.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import projects.jatin.moviesdemo.Adapters.UpcomingMoviesAdapter;
import projects.jatin.moviesdemo.Model.UpcomingMoviesModel;
import projects.jatin.moviesdemo.R;
import projects.jatin.moviesdemo.Utils.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingMovies extends Fragment {


    RecyclerView recycler;
    UpcomingMoviesAdapter upcomingMoviesAdapter;
    List<UpcomingMoviesModel> upcomingMoviesModels;
    UpcomingMoviesModel upcomingMoviesModel;
    public Context mContext;

    RequestQueue requestQueue;
    StringRequest stringRequest;


    public UpcomingMovies() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        this.mContext=context;
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recycler=(RecyclerView)view.findViewById(R.id.recyclerupcomingmovies);
        upcomingMoviesModels=new ArrayList<>();


        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        recycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false));
        getMovies();

        super.onActivityCreated(savedInstanceState);
    }

    private void getMovies() {


        String link="http://api.themoviedb.org/3/movie/upcoming?api_key=b758f3d7c7663123b6c693a2232eb117";
        final ProgressDialog loadingse = ProgressDialog.show(getActivity(),"Updating...","Please wait...",false,false);

        requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getContext(), ""+sharedPref.getUserid(), Toast.LENGTH_SHORT).show();
                        loadingse.dismiss();
                        try {
                            JSONObject jsonRootObject = new JSONObject(response);
//                           Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();

                            JSONArray jsonArray = jsonRootObject.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                upcomingMoviesModel=new UpcomingMoviesModel();

                                upcomingMoviesModel.setTitle(jsonObject.getString("title"));
                                upcomingMoviesModel.setLanguage(jsonObject.getString("original_language"));
                                upcomingMoviesModel.setOverview(jsonObject.getString("overview"));
                                upcomingMoviesModel.setRelease_date(jsonObject.getString("release_date"));
                                upcomingMoviesModel.setImage_url(jsonObject.getString("poster_path"));

                                upcomingMoviesModels.add(upcomingMoviesModel);


                            }

                            upcomingMoviesAdapter=new UpcomingMoviesAdapter(upcomingMoviesModels, mContext);
                            recycler.setAdapter(upcomingMoviesAdapter);


                        }

                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loadingse.dismiss();
                        Toast.makeText(getActivity(),"check your connection \nTry again",Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
//                map.put("api_key","b758f3d7c7663123b6c693a2232eb117");


                return  map;
            }
        };
        RetryPolicy retryPolicy=new DefaultRetryPolicy(30000,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming_movies, container, false);

    }



}
