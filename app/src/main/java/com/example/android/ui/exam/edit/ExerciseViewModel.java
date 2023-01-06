package com.example.android.ui.exam.edit;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.android.web.ApiClient;
import com.example.model.exam.Exercise;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciseViewModel extends ViewModel {

	private final MutableLiveData<Boolean> sentLiveData = new MutableLiveData<>();

	public LiveData<Boolean> getSentLiveData() {
		return sentLiveData;
	}

	public void insertExercise(int sectionId, Exercise exercise) {
		var apiClient = ApiClient.getInstance();
		var call = apiClient.saveExercise(sectionId, exercise);
		call.enqueue(new Callback<>() {
			@Override
			public void onResponse(@NonNull Call<Exercise> call, @NonNull Response<Exercise> response) {
				sentLiveData.setValue(true);
			}

			@Override
			public void onFailure(@NonNull Call<Exercise> call, @NonNull Throwable t) {
				sentLiveData.setValue(true);
			}
		});
	}
}
