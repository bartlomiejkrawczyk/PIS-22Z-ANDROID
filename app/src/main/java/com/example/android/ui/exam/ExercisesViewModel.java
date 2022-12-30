package com.example.android.ui.exam;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.model.exam.Exercise;
import com.example.model.exam.MultipleTruthOrFalse;
import com.example.model.exam.answer.ChoiceAnswer;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExercisesViewModel extends ViewModel {

	private final MutableLiveData<Integer> currentExerciseNumberLiveData = new MutableLiveData<>();
	private final MutableLiveData<List<Exercise>> exercisesLiveData = new MutableLiveData<>();
	private final MutableLiveData<State> stateLiveData = new MutableLiveData<>();

	public ExercisesViewModel() {
		currentExerciseNumberLiveData.setValue(0);
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

	public LiveData<State> getStateLiveData() {
		return stateLiveData;
	}

	public State getState() {
		return stateLiveData.getValue();
	}

	public void setState(State state) {
		stateLiveData.setValue(state);
	}

	public Exercise getExerciseAtPosition(int position) {
		return Optional.ofNullable(exercisesLiveData.getValue())
				.map(exercises -> exercises.get(position))
				.orElse(null);
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

	public void populateExercises(int sectionId) {
		var result = IntStream.range(0, 100).boxed()
				.map(i -> (Exercise)
//						Choice.builder()
//						.question("Jaka jest twoja ulubiona litera?")
//						.correctAnswer("A")
//						.possibleAnswers(List.of("A", "B", "C"))
//						.build()
//								MultipleChoice.builder()
//										.question("Jakie są towoje ulubione litery?")
//										.answers(List.of(
//												ChoiceAnswer.builder().content("A").correct(true).build(),
//												ChoiceAnswer.builder().content("B").correct(false).build(),
//												ChoiceAnswer.builder().content("C").correct(true).build(),
//												ChoiceAnswer.builder().content("D").correct(false).build()
//										))
//										.build()
//								TruthOrFalse.builder()
//										.question("Czy mój ulubiony kolor to zielony?")
//										.correct(true)
//										.build()
								MultipleTruthOrFalse.builder()
										.question("")
										.tasks(List.of(
												ChoiceAnswer.builder().content("Am I a god?").correct(true).build(),
												ChoiceAnswer.builder().content("Am I a genius?").correct(true).build(),
												ChoiceAnswer.builder().content("Am I a stupid?").correct(false).build()
										))
										.build()
				)
				.collect(Collectors.toList());

		exercisesLiveData.setValue(result);
	}
}
