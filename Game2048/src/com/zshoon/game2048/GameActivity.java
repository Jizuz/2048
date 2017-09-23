package com.zshoon.game2048;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class GameActivity extends Activity {
	
	public int score = 0;
	
	private TextView showScore;

	private static GameActivity gameActivity = null;

	public GameActivity() {
		gameActivity = this;
	}
	
	public static GameActivity getGameActivity() {
		return gameActivity;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		showScore = (TextView) findViewById(R.id.showScore);
	}
	
	public void clearGame() {
		score = 0;
		showScore();
	}
	
	public void showScore() {
		showScore.setText(score + "");
	}
	
	public void addScore(int s) {
		score += s;
		showScore();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
