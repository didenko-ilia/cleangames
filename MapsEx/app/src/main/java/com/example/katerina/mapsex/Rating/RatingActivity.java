package com.example.katerina.mapsex.Rating;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.PopupMenu;

import com.example.katerina.mapsex.Game.GamesActivity;
import com.example.katerina.mapsex.Map.DemoActivity;
import com.example.katerina.mapsex.R;
import com.example.katerina.mapsex.Repository;
import com.example.katerina.mapsex.Team.TeamsActivity;
import com.example.katerina.mapsex.datamodels.Game;
import com.example.katerina.mapsex.datamodels.Team;

import java.util.ArrayList;


public class RatingActivity extends ActionBarActivity {

    public RatingAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        setTitle("Rating:");
        final ListView listViewRating = (ListView) findViewById(R.id.listRating);
        final ArrayList<Team> exampleList = Repository.getRating(new Game());
        mAdapter = new RatingAdapter(this, exampleList);
        listViewRating.setAdapter(mAdapter);

        listViewRating.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RatingActivity.this, AdminGarbageTransferActivity.class);
                Team team = (Team) parent.getItemAtPosition(position);
                intent.putExtra("name",team.getName() );
                intent.putExtra("score",team.getTotal_scores());
                startActivity(intent);
            }
        });


        findViewById(R.id.button_popup)
                //������ �� ��������� �� ������:
                .setOnClickListener(new View.OnClickListener() {

                    //������������ ������� ������ Button:
                    @Override
                    public void onClick(View view) {
                        //�������� popup ����, ��������� ��� � ����� popup.xml � �����������
                        //��������� ������� �� ������� OnMenuItemClickListener:
                        PopupMenu popup_menu = new PopupMenu(RatingActivity.this, view);
                        popup_menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.game_menu:
                                        //Toast.makeText(this, "������ ����� 1", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RatingActivity.this, GamesActivity.class));
                                        return true;
                                    case R.id.teams_menu:
                                        //Toast.makeText(this, "������ ����� 2", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RatingActivity.this, TeamsActivity.class));
                                        return true;
                                    case R.id.map_menu:
                                        //Toast.makeText(this, "������ ����� 3", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RatingActivity.this, DemoActivity.class));
                                        return true;
                                    case R.id.rating_menu:
                                        startActivity(new Intent(RatingActivity.this, RatingActivity.class));
                                        return true;
                                }
                                return true;
                            }
                        });
                        popup_menu.inflate(R.menu.popup_menu);
                        popup_menu.show();
                    }
                });
    }

    //������������ ������� �� ������� popup ����, �������� �� id ������� ������, �������� � ����� popup.xml:
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.game_menu:
                //Toast.makeText(this, "������ ����� 1", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RatingActivity.this, GamesActivity.class));
                return true;
            case R.id.teams_menu:
                //Toast.makeText(this, "������ ����� 2", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RatingActivity.this, TeamsActivity.class));
                return true;
            case R.id.map_menu:
                //Toast.makeText(this, "������ ����� 3", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RatingActivity.this, DemoActivity.class));
                return true;
            case R.id.rating_menu:
                startActivity(new Intent(RatingActivity.this, RatingActivity.class));
                return true;
        }
        return true;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rating, menu);
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
