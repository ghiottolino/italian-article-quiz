package com.nicolatesser.italiangenderquiz;



import android.content.Intent;
import android.view.MenuItem;

public class SettingsActivity extends com.nicolatesser.androidquiztemplate.SettingsActivity {

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();

		if (itemId == android.R.id.home) {
			Intent homeIntent;
			homeIntent = new Intent(this, ItalianGenderQuizActivity.class);
			startActivity(homeIntent);
		} else if (itemId == R.id.menu_settings) {
			Intent settingsIntent;
			settingsIntent = new Intent(this, SettingsActivity.class);
			startActivity(settingsIntent);
		}
		return super.onOptionsItemSelected(item);
	}

}