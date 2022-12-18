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
		var concept = Concept.builder()
				.keyPhrase("Key Phrase")
				.summary("Summary")
				.build();
		// TODO: Download concepts form api
		conceptsLiveData.setValue(List.of(concept));
	}
}
