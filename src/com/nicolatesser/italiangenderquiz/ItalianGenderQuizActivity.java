package com.nicolatesser.italiangenderquiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nicolatesser.androidquiztemplate.QuizActivity;
import com.nicolatesser.androidquiztemplate.R;
import com.nicolatesser.androidquiztemplate.quiz.Answer;
import com.nicolatesser.androidquiztemplate.quiz.Game;
import com.nicolatesser.androidquiztemplate.quiz.Question;

public class ItalianGenderQuizActivity extends QuizActivity {

	public static final String SETTING_PREF_KEY = "ITALIAN_GENDER_QUIZ";

	@Override
	public void initGame() {
		List<Question> questions = new LinkedList<Question>();
		try {
			questions = loadQuestions("ita_words_v_0_2.txt");
		} catch (IOException e) {
			throw new RuntimeException("Could not load questions");
		}

		Integer record = getIntFieldInPreferences(RECORD_PREF_KEY);
		Game game = new Game(questions, record);
		Game.setInstance(game);
		loadSession();
		loadSettings();
	}

	private List<Question> loadQuestions(String fileName) throws IOException {
		List<Question> questions = new Vector<Question>();
		InputStream in = this.getClass().getResourceAsStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;

		while ((strLine = br.readLine()) != null) {
			
			String[]columns = strLine.split("\\|");
			String en = columns[0].trim();
			String it = columns[1].trim();
			String category = columns[2].trim();
				
			//replace apostrophs with apostrophs + spaces
			it = it.replace('\'', ' ');
			String[] split;
			// TODO : make it better
			split = it.split(" ", 2);

			if (split.length == 2) {
				String word = split[1];
				String article = split[0];

				List<Answer> answers = new Vector<Answer>();
				Answer answer1 = new Answer("il", false);
				Answer answer2 = new Answer("la", false);
				Answer answer3 = new Answer("lo", false);
				Answer answer4 = new Answer("l'", false);

				if (article.equalsIgnoreCase("il")) {
					answer1 = new Answer("il", true);
				} else if (article.equalsIgnoreCase("la")) {
					answer2 = new Answer("la", true);
				} else if (article.equalsIgnoreCase("lo")) {
					answer3 = new Answer("lo", true);
				} else if (article.equalsIgnoreCase("l")) {
					answer4 = new Answer("l'", true);
				} else {
					 continue;
				}
				answers.add(answer1);
				answers.add(answer2);
				answers.add(answer3);
				answers.add(answer4);

				Question question = new Question(word, answers, Arrays.asList(category));
				questions.add(question);
			}
		}

		return questions;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();

		if (itemId == android.R.id.home) {
			Intent myIntent = new Intent(this, ItalianGenderQuizActivity.class);
			startActivity(myIntent);
		}
		else if (itemId == R.id.menu_settings) {
			Intent settingsIntent; 
			settingsIntent = new Intent(this,SettingsActivity.class); 
			startActivity(settingsIntent); 
		}

		return super.onOptionsItemSelected(item);
	}
}
