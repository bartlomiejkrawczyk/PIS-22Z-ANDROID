package com.example.android.ui.menu;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.model.Section;
import java.util.List;

public class MenuViewModel extends ViewModel {

	private final MutableLiveData<List<Section>> sectionsLiveData;
	private List<Section> sections;

	public MenuViewModel() {
		sectionsLiveData = new MutableLiveData<>();

		init();
	}

	public MutableLiveData<List<Section>> getSectionsLiveData() {
		return sectionsLiveData;
	}

	public void init() {
		populateList();
		sectionsLiveData.setValue(sections);
	}

	public void populateList() {
		var section1 = Section.builder()
				.id(1)
				.name("SIECI KOMPUTEROWE")
				.build();

		Section section2 = Section.builder()
				.id(1)
				.name("SYSTEMY OPERACYJNE")
				.subSections(List.of(section1, section1))
				.build();

		var section3 = Section.builder()
				.id(1)
				.name("SIECI KOMPUTEROWE")
				.subSections(List.of(section1, section2, section1))
				.build();

		sections = List.of(section1, section1, section2, section3);
	}
}
