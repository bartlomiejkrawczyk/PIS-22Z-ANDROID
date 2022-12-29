package com.example.android.ui.exam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.example.android.databinding.ActivityExamBinding;
import com.example.model.exam.Exercise;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

	private ActivityExamBinding binding;

	private ProgressBar timeProgressBar;
	private TextView time;
	private TextView examProgressTextView;
	private ProgressBar examProgressBar;
	private TextView backTextView;
	private TextView nextTextView;

	private ViewPager2 viewPager;

	private FragmentStateAdapter pagerAdapter;

	private ExercisesViewModel exercisesViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.binding = ActivityExamBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		this.timeProgressBar = binding.examTimeProgressBar;
		this.time = binding.examTimeTextView;
		this.examProgressTextView = binding.examQuestionTextView;
		this.examProgressBar = binding.examProgressBar;
		this.backTextView = binding.examBackTextView;
		this.nextTextView = binding.examNextTextView;
		this.viewPager = binding.examViewPager;

		viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
			@Override
			public void onPageSelected(int position) {
				exercisesViewModel.setExerciseNumber(position);
			}
		});

		this.viewPager.setPageTransformer(new ZoomOutPageTransformer());

		this.exercisesViewModel = new ViewModelProvider(this)
				.get(ExercisesViewModel.class);

		exercisesViewModel.getExercisesLiveData()
				.observe(this, this::setExercises);

		exercisesViewModel.getCurrentExerciseNumberLiveData()
				.observe(this, this::setCurrentQuestion);

		backTextView.setOnClickListener(v -> exercisesViewModel.previousExercise());
		nextTextView.setOnClickListener(v -> exercisesViewModel.nextExercise());

		setTimer(2 * 60_000L);
	}

	@SuppressLint("DefaultLocale")
	private void setCurrentQuestion(int currentQuestion) {
		examProgressTextView.setText(String.format("%d/%d", currentQuestion + 1, exercisesViewModel.getExercisesSize()));
		viewPager.setCurrentItem(currentQuestion);
	}

	@SuppressLint("DefaultLocale")
	private void setExercises(List<Exercise> exercises) {
		examProgressTextView.setText(String.format("%d/%d", exercisesViewModel.getCurrentExerciseNumber() + 1, exercises.size()));
		pagerAdapter = new ExerciseSlidePagerAdapter(this, exercises);
		viewPager.setAdapter(pagerAdapter);
		examProgressBar.setVisibility(View.GONE);
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