package com.example.simplefragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.HttpUrl;


public class TeamsFragment extends Fragment {

    String localhost = "";
    ListView teamsList;
    View view;

    public TeamsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { // write your code in here
        view = inflater.inflate(R.layout.fragment_teams, container, false);
        localhost = getString(R.string.getTeamsUrl);
        //request data from database
        AsyncNetwork getTeams = new AsyncNetwork();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(localhost).newBuilder();
        String url = urlBuilder.build().toString();
        getTeams.execute(url);

        while( getTeams.Result.equals("Waiting")){
        }
        try{
            JSONObject jsonObject =  new JSONObject(getTeams.Result);

            if(jsonObject.getString("success").equals("1")){
                ArrayList<Teams> teams = new ArrayList<>();

                JSONArray myteams = new JSONArray(jsonObject.getJSONArray("teams").toString());
                for(int i = 0; i < myteams.length(); i++){
                    JSONObject currentTeam = myteams.getJSONObject(i);
                    Teams firstTeam = new Teams();
                    String team_name = currentTeam.getString("team_name");
                    firstTeam.setTeamName(team_name);
                    int gamesPlayed = currentTeam.getInt("games_played");
                    firstTeam.setTotalGamesPlayed(gamesPlayed);
                    int gamesWon = currentTeam.getInt("games_won");
                    firstTeam.setGamesWonByTeam(gamesWon);
                    teams.add(firstTeam);

                }

                MyListTeamsAdapter adapter=new MyListTeamsAdapter(getContext(), teams);
                teamsList= (ListView) view.findViewById(R.id.teams_listView);
                teamsList.setAdapter(adapter);

            }else{
                Toast.makeText(getContext(), "error occured", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e ){
            e.printStackTrace();
        }



        // Inflate the layout for this fragment
        return view;

    }
}