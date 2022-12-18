package com.example.android.ui.section;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.model.Concept;
import java.util.List;

public class ConceptsViewModel extends ViewModel {


	// TODO: Create factory and pass sectionId value using factory to constructor
	private final int sectionId = 1;
	private final MutableLiveData<List<Concept>> conceptsLiveData = new MutableLiveData<>();

	public ConceptsViewModel() {
		populateConcepts();
	}

	public MutableLiveData<List<Concept>> getConceptsLiveData() {
		return conceptsLiveData;
	}

	private void populateConcepts() {
		var concept1 = Concept.builder()
				.keyPhrase("Sieć")
				.summary("Summary")
				.build();
		var concept2 = Concept.builder()
				.keyPhrase("Intersieć")
				.summary("Summary")
				.build();
		var concept3 = Concept.builder()
				.keyPhrase("Internet")
				.summary("Summary")
				.build();
		var concept4 = Concept.builder()
				.keyPhrase("Stos ISO/OSI")
				.summary("Summary")
				.build();
		// TODO: Download concepts form api
		conceptsLiveData.setValue(List.of(concept1, concept2, concept3, concept4));
	}
}
