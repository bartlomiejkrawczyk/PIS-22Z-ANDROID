package com.example.android.ui.exam;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.android.web.ApiClient;
import com.example.model.exam.Choice;
import com.example.model.exam.Exercise;
import com.example.model.exam.FillBlanks;
import com.example.model.exam.FlashCard;
import com.example.model.exam.MultipleChoice;
import com.example.model.exam.MultipleTruthOrFalse;
import com.example.model.exam.SelectFromList;
import com.example.model.exam.TruthOrFalse;
import com.example.model.exam.answer.BlankAnswer;
import com.example.model.exam.answer.ChoiceAnswer;
import com.example.model.exam.answer.ListAnswer;
import java.util.List;
import java.util.Optional;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
		} else {
			stateLiveData.setValue(State.STUDY_ANSWERS);
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
		var apiClient = ApiClient.getInstance();
		var call = apiClient.getExercisesBySectionId(sectionId);
		call.enqueue(new Callback<>() {
			@Override
			public void onResponse(@NonNull Call<List<Exercise>> call, @NonNull Response<List<Exercise>> response) {
				Optional.ofNullable(response.body()).orElseGet(ExercisesViewModel::getFallbackExercises);
			}

			@Override
			public void onFailure(@NonNull Call<List<Exercise>> call, @NonNull Throwable t) {
				exercisesLiveData.setValue(getFallbackExercises());
			}
		});
	}

	private static List<Exercise> getFallbackExercises() {
		return List.of(
				Choice.builder()
						.question("Jaka jest twoja ulubiona litera?")
						.correctAnswer("A")
						.possibleAnswers(List.of("A", "B", "C"))
						.build(),
				MultipleChoice.builder()
						.question("Jakie są twoje ulubione litery?")
						.answers(List.of(
								ChoiceAnswer.builder().content("A").correct(true).build(),
								ChoiceAnswer.builder().content("B").correct(false).build(),
								ChoiceAnswer.builder().content("C").correct(true).build(),
								ChoiceAnswer.builder().content("D").correct(false).build()
						))
						.build(),
				TruthOrFalse.builder()
						.question("Czy mój ulubiony kolor to zielony?")
						.correct(true)
						.build(),
				MultipleTruthOrFalse.builder()
						.question("")
						.tasks(List.of(
								ChoiceAnswer.builder().content("Am I a god?").correct(true).build(),
								ChoiceAnswer.builder().content("Am I a genius?").correct(true).build(),
								ChoiceAnswer.builder().content("Am I a stupid?").correct(false).build()
						))
						.build(),
				FlashCard.builder()
						.question("Jaki skrót ma Transmission Control Protocol?")
						.answer("TCP")
						.build(),
				FillBlanks.builder()
						.question("Uzupełnij luki:")
						.answers(List.of(
								BlankAnswer.builder().start("Wczorajsze poranne ").answer("śniadanie").end(" bardzo mi smakowało.").build(),
								BlankAnswer.builder().start("Po południu jadłem bardzo dobry ").answer("obiad")
										.end(", a wieczorem zjem kolację.").build()
						))
						.build(),
				SelectFromList.builder()
						.question("Wybierz z listy dostępnych wartości:")
						.answers(List.of(
								ListAnswer.builder().start("Wczorajsze poranne ").possibleAnswers(List.of("śniadanie", "obiad"))
										.correctAnswer("śniadanie").end(" bardzo mi smakowało.").build(),
								ListAnswer.builder().start("Po południu jadłem bardzo dobry ").possibleAnswers(List.of("śniadanie", "obiad"))
										.correctAnswer("obiad").end(", a wieczorem zjem kolację.").build()
						))
						.build()
		);
	}
}
