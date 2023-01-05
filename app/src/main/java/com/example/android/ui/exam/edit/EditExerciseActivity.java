package com.example.android.ui.exam.edit;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.example.android.R;
import com.example.android.data.model.ParagraphAnswer;
import com.example.android.data.model.PossibleAnswer;
import com.example.android.databinding.ActivityEditExerciseBinding;
import com.example.model.exam.Choice;
import com.example.model.exam.Exercise;
import com.example.model.exam.FillBlanks;
import com.example.model.exam.FlashCard;
import com.example.model.exam.MultipleChoice;
import com.example.model.exam.MultipleTruthOrFalse;
import com.example.model.exam.SelectFromList;
import com.example.model.exam.TruthOrFalse;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Map;
import java.util.function.Function;

public class EditExerciseActivity extends AppCompatActivity {

	private final Map<String, Class<? extends Exercise>> exercises = Map.of(
			getString(R.string.flash_card), FlashCard.class,
			getString(R.string.fill_blanks), FillBlanks.class,
			getString(R.string.select_from_list), SelectFromList.class,
			getString(R.string.single_choice), Choice.class,
			getString(R.string.multiple_choice), MultipleChoice.class,
			getString(R.string.truth_or_false), TruthOrFalse.class,
			getString(R.string.multiple_truth_or_false), MultipleTruthOrFalse.class
	);

	private final Map<InputType, Runnable> spinnerHandler = Map.of(
			InputType.SIMPLE, this::initFlashCard,
			InputType.CHOICE, this::initChoiceAnswer,
			InputType.PARAGRAPH, this::initParagraphAnswer
	);


	private Spinner spinnerExerciseType;
	private EditText editTextQuestion;

	private Button buttonAdd;
	private Button buttonDone;

	private EditText editTextFlashCard;
	private ListView listViewAnswers;

	private ChoiceAnswerArrayAdapter choiceAdapter;
	private ParagraphAnswerArrayAdapter paragraphAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		var binding = ActivityEditExerciseBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		this.spinnerExerciseType = binding.spinnerPossibleExercises;
		this.editTextQuestion = binding.editTextQuestion;
		this.buttonAdd = binding.buttonAdd;
		this.buttonDone = binding.buttonDone;
		this.editTextFlashCard = binding.editTextFlashCard;
		this.listViewAnswers = binding.listViewAnswers;

		setupSpinner();
		setupDoneButton();
	}

	private void setupSpinner() {
		var exerciseNames = new ArrayList<>(exercises.keySet());
		var adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exerciseNames);
		spinnerExerciseType.setAdapter(adapter);
		spinnerExerciseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				var chosen = exercises.get(exerciseNames.get(position));
				EnumSet.allOf(InputType.class)
						.stream()
						.filter(inputType -> inputType.getClasses().contains(chosen))
						.findFirst()
						.map(spinnerHandler::get)
						.ifPresent(Runnable::run);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// Do nothing
			}
		});
	}

	private void initFlashCard() {
		editTextFlashCard.setVisibility(View.VISIBLE);
		listViewAnswers.setVisibility(View.GONE);
		buttonAdd.setVisibility(View.GONE);
	}

	private void initChoiceAnswer() {
		editTextFlashCard.setVisibility(View.GONE);
		listViewAnswers.setVisibility(View.VISIBLE);
		buttonAdd.setVisibility(View.VISIBLE);

		var answers = new ArrayList<PossibleAnswer>();
		answers.add(new PossibleAnswer());
		choiceAdapter = new ChoiceAnswerArrayAdapter(this, answers);
		listViewAnswers.setAdapter(choiceAdapter);

		buttonAdd.setOnClickListener(v -> choiceAdapter.add(new PossibleAnswer()));
	}

	private void initParagraphAnswer() {
		editTextFlashCard.setVisibility(View.GONE);
		listViewAnswers.setVisibility(View.VISIBLE);
		buttonAdd.setVisibility(View.VISIBLE);

		var answers = new ArrayList<ParagraphAnswer>();
		answers.add(new ParagraphAnswer());
		paragraphAdapter = new ParagraphAnswerArrayAdapter(this, answers);
		listViewAnswers.setAdapter(paragraphAdapter);

		buttonAdd.setOnClickListener(v -> paragraphAdapter.add(new ParagraphAnswer()));
	}

	private final Map<Class<? extends Exercise>, Function<String, Exercise>> exerciseMappers = Map.of(
			FlashCard.class, question -> {
				var answer = editTextFlashCard.getText().toString();
				return FlashCard.builder().question(question).answer(answer).build();
			},
			FillBlanks.class, question -> {
				return FillBlanks.builder().question(question).build();
			},
			SelectFromList.class, question -> {
				return SelectFromList.builder().question(question).build();
			},
			Choice.class, question -> {
				return Choice.builder().question(question).build();
			},
			MultipleChoice.class, question -> {
				return MultipleChoice.builder().question(question).build();
			},
			TruthOrFalse.class, question -> {
				return TruthOrFalse.builder().question(question).build();
			},
			MultipleTruthOrFalse.class, question -> {
				return MultipleTruthOrFalse.builder().question(question).build();
			}
	);

	private void setupDoneButton() {
		buttonDone.setOnClickListener(v -> {
			var chosen = exercises.get((String) spinnerExerciseType.getSelectedItem());
			var question = editTextQuestion.getText().toString();
			var mapper = exerciseMappers.get(chosen);
			if (mapper != null) {
				var exercise = mapper.apply(question);
			}
		});
	}
}