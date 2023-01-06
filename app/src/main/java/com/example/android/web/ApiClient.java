package com.example.android.web;

import com.example.model.Concept;
import com.example.model.Definition;
import com.example.model.Section;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiClient {

	static ApiClient getInstance() {
		return new ApiClientBuilder().build();
	}

	@GET("ping/{name}")
	Call<String> testConnection(@Path(value = "name") String name);

	@GET("concept/definition/{id}")
	Call<Definition> getDefinitionById(@Path(value = "id") int id);

	@GET("concept/{id}")
	Call<Concept> getConceptById(@Path(value = "id") int id);

	@POST("concept/{sectionId}")
	Call<Concept> saveConcept(@Path(value = "sectionId") int sectionId, @Body Concept concept);

	@GET("concept/section/{sectionId}")
	Call<List<Concept>> getConceptsBySectionId(@Path(value = "sectionId") int sectionId);

	@GET("section/parent/{sectionId}")
	Call<List<Section>> getSectionsByParentId(@Path(value = "sectionId") int sectionId);

	@GET("section/")
	Call<List<Section>> getRootSections();

	@GET("section/id/{sectionId}")
	Call<Section> getSectionById(@Path(value = "sectionId") int sectionId);
}
