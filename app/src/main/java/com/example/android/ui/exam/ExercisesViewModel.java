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

	public ExercisesViewModel() {
		currentExerciseNumberLiveData.setValue(0);
		populateExercises();
	}

	public LiveData<List<Exercise>> getExercisesLiveData() {
		return exercisesLiveData;
	}

	public int getExercisesSize() {
		return Optional.ofNullable(exercisesLiveData.getValue()).map(List::size).orElse(0);
	}

	public LiveData<Integer> getCurrentExerciseNumberLiveData() {
		return currentExerciseNumberLiveData;
	}

	public int getCurrentExerciseNumber() {
		return Optional.ofNullable(currentExerciseNumberLiveData.getValue())
				.orElse(0);
	}

	public void nextExercise() {
		int value = getCurrentExerciseNumber() + 1;
		if (value < getExercisesSize()) {
			currentExerciseNumberLiveData.setValue(value);
		}
	}

	public void previousExercise() {
		int value = getCurrentExerciseNumber() - 1;
		if (value >= 0) {
			currentExerciseNumberLiveData.setValue(value);
		}
	}

	public void setExerciseNumber(int position) {
		currentExerciseNumberLiveData.setValue(position);
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
	}
}
