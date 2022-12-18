package com.example.android.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.model.Concept;
import com.example.model.Paragraph;

import java.util.List;

public class ConceptActivity extends AppCompatActivity {

	private final ConceptRecyclerViewAdapter conceptRecyclerViewAdapter = new ConceptRecyclerViewAdapter(this);
	private RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_concept);
		initViews();
	}

	private void initViews() {
		recyclerView = findViewById(R.id.concept_recycler_view);
		setUpRecyclerView();
	}

	private void setUpRecyclerView() {
		recyclerView.setHasFixedSize(true);
		recyclerView.setVisibility(View.GONE);

		RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(manager);
		getConcept();

		recyclerView.setAdapter(conceptRecyclerViewAdapter);
		recyclerView.setVisibility(View.VISIBLE);
	}

	private void getConcept() {
		var concept = Concept.builder()
				.keyPhrase("Sieć")
				.summary("zbiór komputerów i innych urządzeń połączonych z sobą kanałami komunikacyjnymi oraz oprogramowanie wykorzystywane w tej sieci. Umożliwia ona wzajemne przekazywanie informacji oraz udostępnianie zasobów własnych między podłączonymi do niej urządzeniami, zwanymi punktami sieci.")
				.paragraphs(List.of(
						Paragraph.builder()
								.header("Przeznaczenie")
								.description("Głównym przeznaczeniem sieci komputerowej – ideą, dla której została stworzona – jest ułatwienie komunikacji między ludźmi (będącymi faktycznymi użytkownikami sieci). Sieć umożliwia łatwy i szybki dostęp do publikowanych danych, jak również otwiera techniczną możliwość tworzenia i korzystania ze wspólnych zasobów informacji i zasobów danych. W sensie prawnym, i w pewnym przybliżeniu, użytkownicy sieci komputerowej są również jej beneficjentami.")
								.build(),
						Paragraph.builder()
								.header("Cechy użytkowe")
								.description("ułatwienie komunikacji między ludźmi. Korzystając z sieci, ludzie mogą komunikować się szybko i łatwo przy wykorzystaniu odpowiednich programów komputerowych i oferowanych w danej sieci usług sieciowych. W odniesieniu do sieci Internet należy wyróżnić dwa rodzaje programów i skojarzone z nimi usługi")
								.build()
				))
				.build();
		conceptRecyclerViewAdapter.setConcept(concept);

//		var apiClient = ApiClient.getInstance();
//		var settings = getSharedPreferences("Android", Context.MODE_PRIVATE);
//		var call = apiClient.getConceptById(settings.getInt("concept", 7));
//		call.enqueue(new Callback<>() {
//			@SuppressLint("NotifyDataSetChanged")
//			@Override
//			public void onResponse(@NonNull Call<Concept> call, @NonNull Response<Concept> response) {
//				if (response.isSuccessful() && response.body() != null) {
//					var concept = response.body();
//					concept.getParagraphs().sort(Comparator.comparingInt(Paragraph::getSequentialNumber));
//					conceptRecyclerViewAdapter.setConcept(concept);
//					runOnUiThread(conceptRecyclerViewAdapter::notifyDataSetChanged);
//				} else {
//					Log.e("Concept", "Response concept failure");
//				}
//			}
//
//			@Override
//			public void onFailure(@NonNull Call<Concept> call, @NonNull Throwable t) {
//				Log.e("Concept", "Load concept failure");
//			}
//		});
	}
}
