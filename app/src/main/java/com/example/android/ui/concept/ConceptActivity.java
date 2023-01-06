package com.example.android.ui.concept;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.databinding.ActivityConceptBinding;
import com.example.model.Concept;

public class ConceptActivity extends AppCompatActivity {

	public static final String ARG_CONCEPT_ID = "concept_id";

	private final ConceptRecyclerViewAdapter conceptRecyclerViewAdapter = new ConceptRecyclerViewAdapter(this);
	private RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		var binding = ActivityConceptBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		this.recyclerView = binding.conceptRecyclerView;

		setUpRecyclerView();
		setupViewModel();
	}

	private void setUpRecyclerView() {
		recyclerView.setHasFixedSize(true);
		recyclerView.setVisibility(View.GONE);

		RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(manager);

		recyclerView.setAdapter(conceptRecyclerViewAdapter);
		recyclerView.setVisibility(View.VISIBLE);
	}

	private void setupViewModel() {
		var viewModel = new ViewModelProvider(this).get(ConceptViewModel.class);

		viewModel.getConceptLiveData().observe(this, this::setConcept);

		var intent = getIntent();
		var conceptId = intent.getIntExtra(ARG_CONCEPT_ID, 0);

		viewModel.populateConcept(conceptId);
	}

	private void setConcept(Concept concept) {
		conceptRecyclerViewAdapter.setConcept(concept);
	}
}
