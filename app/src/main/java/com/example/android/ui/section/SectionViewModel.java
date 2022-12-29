package com.example.android.ui.section;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.model.Section;
import java.util.List;

public class SectionViewModel extends ViewModel {

	private final MutableLiveData<Section> sectionLiveData = new MutableLiveData<>();

	public SectionViewModel() {
		populateSection();
	}

	public LiveData<Section> getSectionLiveData() {
		return sectionLiveData;
	}

	private void populateSection() {
		var section0 = Section.builder()
				.id(1)
				.name("Usługi sieciowe")
				.build();

		var section1 = Section.builder()
				.id(1)
				.name("Serwery DNS")
				.build();

		var section2 = Section.builder()
				.id(1)
				.name("Protokoły TCP i UDP")
				.build();

		var section3 = Section.builder()
				.id(1)
				.name("Sieci Komputerowe")
				.subSections(List.of(section0, section1, section2))
				.build();

		sectionLiveData.setValue(section3);
	}
}
