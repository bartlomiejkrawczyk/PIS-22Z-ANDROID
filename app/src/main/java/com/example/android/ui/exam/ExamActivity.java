package com.example.android.ui.exam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.android.R;
import com.example.android.databinding.ActivityExamBinding;
import com.example.model.exam.Choice;
import com.example.model.exam.Exercise;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

	private ActivityExamBinding binding;

	private ProgressBar timeProgressBar;
	private TextView time;
	private TextView examProgressTextView;
	private TextView backTextView;
	private TextView nextTextView;

	private TextView questionTextView;
	private ListView answerList;

	private ExercisesViewModel exercisesViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.binding = ActivityExamBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		this.timeProgressBar = binding.examTimeProgressBar;
		this.time = binding.examTimeTextView;
		this.examProgressTextView = binding.examQuestionTextView;
		this.backTextView = binding.examBackTextView;
		this.nextTextView = binding.examNextTextView;
		this.questionTextView = binding.page.textViewQuestionCard;
		this.answerList = binding.page.listViewQuestionCard;

		binding.examProgressBar.setVisibility(View.GONE);
		binding.page.imageViewQuestionCard.setImageDrawable(getDrawable(R.drawable.ic_settings));

		this.exercisesViewModel = new ViewModelProvider(this)
				.get(ExercisesViewModel.class);

		exercisesViewModel.getCurrentExerciseLiveData()
				.observe(this, this::setExercise);

		exercisesViewModel.getExercisesLiveData()
				.observe(this, this::setExercises);

		exercisesViewModel.getCurrentExerciseNumberLiveData()
				.observe(this, this::setCurrentQuestion);

		backTextView.setOnClickListener(v -> exercisesViewModel.previousExercise());
		nextTextView.setOnClickListener(v -> exercisesViewModel.nextExercise());

		setTimer(2 * 60_000L);
	}

	private void setExercise(Exercise exercise) {
		Choice choice = (Choice) exercise;
		questionTextView.setText(exercise.getQuestion());
		var arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, choice.getPossibleAnswers());
		answerList.setAdapter(arrayAdapter);
	}

	@SuppressLint("DefaultLocale")
	private void setCurrentQuestion(int currentQuestion) {
		this.examProgressTextView.setText(String.format("%d/%d", currentQuestion + 1, exercisesViewModel.getExercisesSize()));
	}

	@SuppressLint("DefaultLocale")
	private void setExercises(List<Exercise> exercises) {
		this.examProgressTextView.setText(String.format("%d/%d", exercisesViewModel.getCurrentExerciseNumber() + 1, exercises.size()));
	}

	private void setTimer(long examTime) {
		new CountDownTimer(examTime, 1000) {
			@SuppressLint("SimpleDateFormat")
			public void onTick(long millisUntilFinished) {
				time.setText(new SimpleDateFormat("mm:ss").format(new Date(millisUntilFinished)));
				timeProgressBar.setProgress((int) (millisUntilFinished * 100 / examTime), false);
			}

			@Override
			public void onFinish() {
				// Ignore
			}
		}.start();
	}
}