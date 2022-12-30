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
import androidx.lifecycle.ViewModelProvider;
import com.example.android.R;
import com.example.android.databinding.FragmentExerciseBinding;
import com.example.android.ui.exam.ExercisesViewModel;
import com.example.model.exam.Exercise;

public class ExerciseFragment extends Fragment {

	private static final String ARG_EXERCISE = "exercise";
	private int position = 0;
	private Exercise exercise;
	private ExercisesViewModel viewModel;

	private FragmentExerciseBinding binding;

	private TextView questionTextView;
	private FrameLayout imageFrameLayout;
	// TODO: Load images
	private ProgressBar imageProgressBar;
	private ImageView imageView;

	private FragmentContainerView fragmentContainerView;
	private Fragment fragment;

	public ExerciseFragment() {
		// Required empty public constructor
	}

	public static ExerciseFragment newInstance(int position) {
		var fragment = new ExerciseFragment();
		var args = new Bundle();
		args.putInt(ARG_EXERCISE, position);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			position = getArguments().getInt(ARG_EXERCISE, 0);
			fragment = ChoiceFragment.newInstance(position);
		}
		if (getActivity() != null) {
			viewModel = new ViewModelProvider(getActivity()).get(ExercisesViewModel.class);
			exercise = viewModel.getExerciseAtPosition(position);
		}
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		binding = FragmentExerciseBinding.inflate(inflater, container, false);
		return binding.getRoot();
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		questionTextView = binding.textViewQuestionCard;
		imageFrameLayout = binding.frameLayoutQuestionCard;
		imageView = binding.imageViewQuestionCard;
		imageProgressBar = binding.progressBarQuestionCard;

		fragmentContainerView = binding.fragmentContainerExercise;

		questionTextView.setText(exercise.getQuestion());
		imageFrameLayout.setVisibility(View.GONE);
		imageProgressBar.setVisibility(View.GONE);

		getChildFragmentManager().beginTransaction()
				.add(R.id.fragment_container_exercise, fragment)
				.commit();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}