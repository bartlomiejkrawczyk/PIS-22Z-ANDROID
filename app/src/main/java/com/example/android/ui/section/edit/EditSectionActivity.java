package com.example.android.ui.section.edit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.android.databinding.ActivityEditSectionBinding;
import com.example.android.ui.ConceptActivity;
import com.example.android.ui.exam.edit.EditExerciseActivity;
import com.example.android.ui.section.SectionActivity;

public class EditSectionActivity extends AppCompatActivity {

	private Button buttonAddConcept;
	private Button buttonAddSubSection;
	private Button buttonAddExercise;

	private int parentSectionId;
	private int sectionId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		var binding = ActivityEditSectionBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		var previousIntent = getIntent();
		parentSectionId = previousIntent.getIntExtra(SectionActivity.ARG_PARENT_SECTION_ID, 0);
		sectionId = previousIntent.getIntExtra(SectionActivity.ARG_SECTION_ID, 0);

		this.buttonAddConcept = binding.buttonAddConcept;
		this.buttonAddSubSection = binding.buttonAddSubSection;
		this.buttonAddExercise = binding.buttonAddExercise;

		setupListeners();
	}

	private void setupListeners() {
		buttonAddConcept.setOnClickListener(v -> {
			var intent = new Intent(this, ConceptActivity.class);
			intent.putExtra(SectionActivity.ARG_SECTION_ID, sectionId);
			// TODO: open in edit view
			startActivity(intent);
		});

		buttonAddSubSection.setOnClickListener(v -> {
			var intent = new Intent(this, EditSectionActivity.class);
			intent.putExtra(SectionActivity.ARG_PARENT_SECTION_ID, sectionId);
			intent.putExtra(SectionActivity.ARG_SECTION_ID, 0);
			// TODO: open in edit view
			startActivity(intent);
		});

		buttonAddExercise.setOnClickListener(v -> {
			var intent = new Intent(this, EditExerciseActivity.class);
			intent.putExtra(SectionActivity.ARG_SECTION_ID, sectionId);
			startActivity(intent);
		});
	}
}