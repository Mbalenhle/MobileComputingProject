package com.example.simplefragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.HttpUrl;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GamesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GamesFragment extends Fragment {
    ListView gamesList;
    View view;
    ImageView addGames;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GamesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GamesFragment newInstance(String param1, String param2) {
        GamesFragment fragment = new GamesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_games, container, false);

        String localhost = getString(R.string.ipaddress);

        ArrayList<Games> myGames = new ArrayList<>();

        //request data from database
        AsyncNetwork getGames = new AsyncNetwork();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(localhost+"getGames.php").newBuilder();
        String url = urlBuilder.build().toString();
        getGames.execute(url);

        while( getGames.Result.equals("Waiting")){
        }
        try{
            JSONObject jsonObject =  new JSONObject(getGames.Result);

            if(jsonObject.getString("success").equals("1")){

                JSONArray my_games = new JSONArray(jsonObject.getJSONArray("games").toString());
                for(int i = 0; i < my_games.length(); i++){
                    JSONObject currentGame = my_games.getJSONObject(i);
                    Games firstGame = new Games();
                    String team_a_name = currentGame.getString("team_a_name");
                    firstGame.setTeamA(team_a_name);

                    String team_b_name = currentGame.getString("team_b_name");
                    firstGame.setTeamB(team_b_name);
                    int team_a_score = currentGame.getInt("team_a_score");
                    firstGame.setTeamAScore(team_a_score);
                    int team_b_score = currentGame.getInt("team_b_score");
                    firstGame.setTeamBScore(team_b_score);
                    myGames.add(firstGame);

                }

            }else{
                Toast.makeText(getContext(), "error occured", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e ){
            e.printStackTrace();
        }

        MyListGamesAdapter adapter=new MyListGamesAdapter(getContext(), myGames);
        gamesList= (ListView) view.findViewById(R.id.games_listView);
        gamesList.setAdapter(adapter);

        addGames = (ImageView) view.findViewById(R.id.imageCreateGame);
        addGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateGame.class);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}