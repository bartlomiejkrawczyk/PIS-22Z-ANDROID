package com.example.android.ui.exam.exercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.android.databinding.FragmentChoiceBinding;
import com.example.model.exam.Choice;

public class ChoiceFragment extends Fragment {

	private final Choice choice;

	private FragmentChoiceBinding binding;

	private ListView answerList;

	public ChoiceFragment(Choice choice) {
		this.choice = choice;
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		binding = FragmentChoiceBinding.inflate(inflater, container, false);

		answerList = binding.listViewChoice;

		var arrayAdapter = new ChoiceArrayAdapter(this.getContext(), choice);
		answerList.setAdapter(arrayAdapter);

		return binding.getRoot();
	}


}