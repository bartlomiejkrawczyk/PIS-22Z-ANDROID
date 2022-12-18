package com.example.android.ui.menu;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.model.Section;
import java.util.List;

public class MenuViewModel extends ViewModel {

	private final MutableLiveData<List<Section>> sectionsLiveData = new MutableLiveData<>();

	public MenuViewModel() {
		populateSections();
	}

	public MutableLiveData<List<Section>> getSectionsLiveData() {
		return sectionsLiveData;
	}

	public void populateSections() {
		var section0 = Section.builder()
				.id(-1)
				.name("Usługi sieciowe")
				.build();

		var section1 = Section.builder()
				.id(-1)
				.name("Serwery DNS")
				.build();

		var section2 = Section.builder()
				.id(-1)
				.name("Protokoły TCP i UDP")
				.build();

		var section3 = Section.builder()
				.id(-1)
				.name("Sieci Komputerowe")
				.subSections(List.of(section0, section1, section2))
				.build();

		var section4 = Section.builder()
				.id(-1)
				.name("Aproksymacja")
				.build();

		var section5 = Section.builder()
				.id(-1)
				.name("Metody Numeryczne")
				.subSections(List.of(section4))
				.build();

		var section6 = Section.builder()
				.id(-1)
				.name("Systemy Operacyjne")
				.build();

		var section7 = Section.builder()
				.id(-1)
				.name("Bezpieczeństwo")
				.build();

		var section8 = Section.builder()
				.id(-1)
				.name("Bazy Danych")
				.build();


		sectionsLiveData.setValue(List.of(section3, section5, section6, section7, section8));
	}
}
