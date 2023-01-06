package com.example.android.ui.section;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.android.web.ApiClient;
import com.example.model.Concept;
import java.util.List;
import java.util.Optional;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConceptsViewModel extends ViewModel {

	private final MutableLiveData<List<Concept>> conceptsLiveData = new MutableLiveData<>();

	public LiveData<List<Concept>> getConceptsLiveData() {
		return conceptsLiveData;
	}

	public void populateConcepts(int sectionId) {
		var apiClient = ApiClient.getInstance();
		var call = apiClient.getConceptsBySectionId(sectionId);
		call.enqueue(new Callback<>() {
			@Override
			public void onResponse(@NonNull Call<List<Concept>> call, @NonNull Response<List<Concept>> response) {
				var result = Optional.ofNullable(response.body()).orElseGet(ConceptsViewModel::getFallbackConcepts);
				conceptsLiveData.setValue(result);
			}

			@Override
			public void onFailure(@NonNull Call<List<Concept>> call, @NonNull Throwable t) {
				conceptsLiveData.setValue(getFallbackConcepts());
			}
		});
	}

	private static List<Concept> getFallbackConcepts() {
		var concept1 = Concept.builder()
				.keyPhrase("Sieć")
				.build();
		var concept2 = Concept.builder()
				.keyPhrase("Intersieć")
				.build();
		var concept3 = Concept.builder()
				.keyPhrase("Internet")
				.build();
		var concept4 = Concept.builder()
				.keyPhrase("Stos ISO/OSI")
				.build();
		return List.of(concept1, concept2, concept3, concept4);
	}
}
