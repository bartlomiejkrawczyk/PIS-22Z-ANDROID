package com.example.android.ui.exam.exercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import com.example.android.R;
import com.example.android.databinding.FragmentExerciseBinding;
import com.example.model.exam.Choice;
import com.example.model.exam.Exercise;

public class ExerciseFragment extends Fragment {

	private final Exercise exercise;
	private FragmentExerciseBinding binding;

	private TextView questionTextView;
	private FrameLayout imageFrameLayout;
	// TODO: Load images
	private ProgressBar imageProgressBar;
	private ImageView imageView;

	private FragmentContainerView fragmentContainerView;
	private Fragment fragment;

	public ExerciseFragment(Exercise exercise) {
		this.exercise = exercise;
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		binding = FragmentExerciseBinding.inflate(inflater, container, false);

		View root = binding.getRoot();
		questionTextView = binding.textViewQuestionCard;
		imageFrameLayout = binding.frameLayoutQuestionCard;
		imageView = binding.imageViewQuestionCard;
		imageProgressBar = binding.progressBarQuestionCard;

		fragmentContainerView = binding.fragmentContainerExercise;

		questionTextView.setText(exercise.getQuestion());
		imageFrameLayout.setVisibility(View.GONE);
		imageProgressBar.setVisibility(View.GONE);

		fragment = new ChoiceFragment((Choice) exercise);

		getChildFragmentManager().beginTransaction()
				.add(R.id.fragment_container_exercise, fragment)
				.commit();

		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}