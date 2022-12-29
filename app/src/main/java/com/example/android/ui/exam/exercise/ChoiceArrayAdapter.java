package com.example.android.ui.exam.exercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.android.databinding.ViewItemChoiceAnswerBinding;
import com.example.model.exam.Choice;

public class ChoiceArrayAdapter extends ArrayAdapter<String> {

	private Choice choice;

	public ChoiceArrayAdapter(@NonNull Context context, Choice choice) {
		super(context, 0, choice.getPossibleAnswers());
		this.choice = choice;
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		if (convertView == null) {
			var binding = ViewItemChoiceAnswerBinding.inflate(LayoutInflater.from(getContext()), parent, false);

			var checkbox = binding.checkboxAnswer;
			var answer = choice.getPossibleAnswers().get(position);
			checkbox.setText(answer);

			return binding.getRoot();
		} else {
			return convertView;
		}
	}
}
