package com.example.katerina.mapsex.Game;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.katerina.mapsex.Map.DemoActivity;
import com.example.katerina.mapsex.R;
import com.example.katerina.mapsex.Registration.UserProvider_temp;
import com.example.katerina.mapsex.Repository;
import com.example.katerina.mapsex.Team.TeamsActivity;
import com.example.katerina.mapsex.datamodels.Game;
import com.example.katerina.mapsex.datamodels.Team;
import com.example.katerina.mapsex.datamodels.User;

import java.util.ArrayList;


public class GamesActivity extends ActionBarActivity {

    public GamesAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        setTitle("Игры");

        final ListView listViewGames = (ListView) findViewById(R.id.listGames);
        // listViewTeams.getSelectedItem()
        ArrayList<Game> exampleList = Repository.getGames();
        mAdapter = new GamesAdapter(this,exampleList);
        listViewGames.setAdapter(mAdapter);
        listViewGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Game game = (Game) parent.getItemAtPosition(position);
                GameProvider provider= GameProvider.Initialize(game,true);
                UserProvider_temp uprovider = UserProvider_temp.Initialize(new User());
                User user = uprovider.getUser();
                Team team = user.getTeam();
                Intent intent;
                if (team == null) {
                    intent = new Intent(GamesActivity.this, TeamsActivity.class);
                }else{
                    intent = new Intent(GamesActivity.this, DemoActivity.class);
                }
                //delete next line
                intent.putExtra("Name", game.name);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_games, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
