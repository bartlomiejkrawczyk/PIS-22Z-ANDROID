package com.example.android.ui.exam;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.model.exam.Choice;
import com.example.model.exam.Exercise;
import java.util.List;
import java.util.Optional;

public class ExercisesViewModel extends ViewModel {

	private final int sectionId = 1;
	private final MutableLiveData<Integer> currentExerciseNumberLiveData = new MutableLiveData<>();
	private final MutableLiveData<List<Exercise>> exercisesLiveData = new MutableLiveData<>();
	private final MutableLiveData<Exercise> currentExerciseLiveData = new MutableLiveData<>();

	public ExercisesViewModel() {
		currentExerciseNumberLiveData.setValue(1);
		populateExercises();
	}

	public LiveData<List<Exercise>> getExercisesLiveData() {
		return exercisesLiveData;
	}

	public int getExercisesSize() {
		return Optional.ofNullable(exercisesLiveData.getValue()).map(List::size).orElse(0);
	}

	public LiveData<Exercise> getCurrentExerciseLiveData() {
		return currentExerciseLiveData;
	}

	public LiveData<Integer> getCurrentExerciseNumberLiveData() {
		return currentExerciseNumberLiveData;
	}

	private int currentExercise() {
		return Optional.ofNullable(currentExerciseNumberLiveData.getValue()).orElse(1);
	}

	public void nextExercise() {
		int value = currentExercise() + 1;
		if (value < getExercisesSize() + 1) {
			currentExerciseNumberLiveData.setValue(value);
			updateCurrentExercise();
		}
	}

	public void previousExercise() {
		int value = currentExercise() - 1;
		if (value >= 1) {
			currentExerciseNumberLiveData.setValue(value);
			updateCurrentExercise();
		}
	}

	private void updateCurrentExercise() {
		Optional.ofNullable(exercisesLiveData.getValue())
				.map(exercises -> exercises.get(currentExercise() - 1))
				.ifPresent(currentExerciseLiveData::setValue);
	}

	private void populateExercises() {
		var exercise1 = Choice.builder()
				.question("Jaka jest twoja ulubiona litera?")
				.correctAnswer("A")
				.possibleAnswers(List.of("A", "B", "C"))
				.build();

		var exercise2 = Choice.builder()
				.question("Jaki jest twój ulubiony kolor?")
				.correctAnswer("czerwony")
				.possibleAnswers(List.of("czerwony", "zielony", "niebieski", "różowy"))
				.build();

		exercisesLiveData.setValue(List.of(exercise1, exercise2));
		currentExerciseLiveData.setValue(exercise1);
	}
}
