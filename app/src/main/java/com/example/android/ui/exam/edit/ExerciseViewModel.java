package com.example.android.ui.exam.edit;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.model.exam.Exercise;

public class ExerciseViewModel extends ViewModel {

	private final MutableLiveData<Boolean> sentLiveData = new MutableLiveData<>();

	public LiveData<Boolean> getSentLiveData() {
		return sentLiveData;
	}

	public void insertExercise(Exercise exercise) {
		// TODO: send exercise to database
		Log.i("EXE", exercise.toString());
		sentLiveData.setValue(true);
	}
}
