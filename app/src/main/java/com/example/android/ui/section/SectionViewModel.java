package com.example.android.ui.section;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.model.Section;
import java.util.List;

public class SectionViewModel extends ViewModel {

	private final MutableLiveData<Section> sectionLiveData = new MutableLiveData<>();

	public SectionViewModel() {
		populateSection();
	}

	public MutableLiveData<Section> getSectionLiveData() {
		return sectionLiveData;
	}

	private void populateSection() {
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

		sectionLiveData.setValue(section3);
	}
}
