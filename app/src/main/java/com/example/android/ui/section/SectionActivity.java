package com.example.android.ui.section;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.android.databinding.ActivitySectionBinding;
import com.example.android.ui.ConceptActivity;
import com.example.android.ui.exam.ExamActivity;
import com.example.model.Concept;
import com.example.model.Section;
import java.util.List;
import java.util.stream.Collectors;

public class SectionActivity extends AppCompatActivity {

	private ActivitySectionBinding binding;
	private TextView conceptTextView;
	private ListView conceptListView;
	private ListView subSectionsListView;
	private Button testButton;
	private Button examButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.binding = ActivitySectionBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		this.conceptTextView = binding.sectionTextView;
		this.conceptListView = binding.conceptsView.conceptsListView;
		this.subSectionsListView = binding.subSectionsView.subSectionsListView;
		this.testButton = binding.sectionTestButton;
		this.examButton = binding.sectionExamButton;

		new ViewModelProvider(this)
				.get(SectionViewModel.class)
				.getSectionLiveData()
				.observe(this, this::setSection);

		new ViewModelProvider(this)
				.get(ConceptsViewModel.class)
				.getConceptsLiveData()
				.observe(this, this::setConcepts);

		new ViewModelProvider(this)
				.get(SubSectionsViewModel.class)
				.getSubSectionsLiveData()
				.observe(this, this::setSubSections);

		testButton.setOnClickListener(v -> {
			var intent = new Intent(this, ExamActivity.class);
			startActivity(intent);
		});
	}

	private void setSection(Section section) {
		conceptTextView.setText(section.getName());
	}

	private void setConcepts(List<Concept> concepts) {
		var arrayAdapter = new ArrayAdapter<>(
				this,
				android.R.layout.simple_list_item_1,
				concepts.stream().map(Concept::getKeyPhrase).collect(Collectors.toList())
		);
		conceptListView.setAdapter(arrayAdapter);
		setListViewHeightBasedOnChildren(conceptListView);
		conceptListView.setOnItemClickListener((parent, view, position, id) -> {
			var intent = new Intent(this, ConceptActivity.class);
			startActivity(intent);
		});
	}

	private void setSubSections(List<Section> sections) {
		var arrayAdapter = new ArrayAdapter<>(
				this,
				android.R.layout.simple_list_item_1,
				sections.stream().map(Section::getName).collect(Collectors.toList())
		);
		subSectionsListView.setAdapter(arrayAdapter);
		subSectionsListView.setOnItemClickListener((parent, view, position, id) -> {
			var intent = new Intent(this, SectionActivity.class);
			startActivity(intent);
		});
		setListViewHeightBasedOnChildren(subSectionsListView);
	}


	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.binding = null;
	}
}
