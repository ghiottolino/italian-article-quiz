package com.nicolatesser.italiangenderquiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
		// List<Question> questions = new Vector<Question>();
		// List<Answer> answers1 = new Vector<Answer>();
		// List<Answer> answers2 = new Vector<Answer>();
		// List<Answer> answers3 = new Vector<Answer>();
		// List<Answer> answers4 = new Vector<Answer>();
		//
		// Answer answer1false = new Answer("ita answer 1", false);
		// Answer answer2false = new Answer("ita answer 2", false);
		// Answer answer3false = new Answer("ita answer 3", false);
		// Answer answer4false = new Answer("ita answer 4", false);
		// Answer answer1true = new Answer("ita answer 1", true);
		// Answer answer2true = new Answer("ita answer 2", true);
		// Answer answer3true = new Answer("ita answer 3", true);
		// Answer answer4true = new Answer("ita answer 4", true);
		//
		// answers1.add(answer1true);
		// answers1.add(answer2false);
		// answers1.add(answer3false);
		//
		// answers2.add(answer1false);
		// answers2.add(answer2true);
		// answers2.add(answer3false);
		// answers2.add(answer4false);
		//
		// answers3.add(answer1false);
		// answers3.add(answer2true);
		// answers3.add(answer3true);
		//
		// answers4.add(answer1false);
		// answers4.add(answer2true);
		// answers4.add(answer3false);
		// answers4.add(answer4true);
		//
		// Question question1 = new Question("ita question 1", answers1);
		// Question question2 = new Question("ita question 2", answers2);
		// Question question3 = new Question("ita question 3", answers3);
		// Question question4 = new Question("ita question 4", answers4);
		//
		// questions.add(question1);
		// questions.add(question2);
		// questions.add(question3);
		// questions.add(question4);
		List<Question> questions = new LinkedList<Question>();
		try {
			questions = loadQuestions("ita_words_v_0_1.txt");
		} catch (IOException e) {
			throw new RuntimeException("Could not load questions");
		}

		setSettingPrefixName(SETTING_PREF_KEY);
		Integer record = getIntFieldInPreferences(RECORD_PREF_KEY);
		Game game = new Game(questions, record);
		setGame(game);
		loadSession();
	}

	private List<Question> loadQuestions(String fileName) throws IOException {
		List<Question> questions = new Vector<Question>();
		InputStream in = this.getClass().getResourceAsStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;

		while ((strLine = br.readLine()) != null) {
			String[] split;

			// TODO : make it better
			split = strLine.split(" ", 2);

			if (split.length == 2) {
				String word = split[1];
				String article = split[0];

				List<Answer> answers = new Vector<Answer>();
				Answer answer1 = new Answer("il", false);
				Answer answer2 = new Answer("la", false);
				Answer answer3 = new Answer("lo", false);
				Answer answer4 = new Answer("l'", false);
				// Answer answer5 = new Answer("gli", false);

				if (article.equalsIgnoreCase("il")) {
					answer1 = new Answer("il", true);
				} else if (article.equalsIgnoreCase("la")) {
					answer2 = new Answer("la", true);
				} else if (article.equalsIgnoreCase("lo")) {
					answer3 = new Answer("lo", true);
				} else if (article.equalsIgnoreCase("l'")) {
					answer4 = new Answer("l'", true);
				} else {
					 continue;
				}
				answers.add(answer1);
				answers.add(answer2);
				answers.add(answer3);
				answers.add(answer4);
				//answers.add(answer5);

				Question question = new Question(word, answers);
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
			startActivityForResult(myIntent, 0);
		}

		return super.onOptionsItemSelected(item);
	}
}
