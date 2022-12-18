package com.example.android.ui.section;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.model.Section;
import java.util.List;

public class SubSectionsViewModel extends ViewModel {

	private final int sectionId = 1;
	private final MutableLiveData<List<Section>> subSectionsLiveData = new MutableLiveData<>();

	public SubSectionsViewModel() {
		populateSubSections();
	}

	public MutableLiveData<List<Section>> getSubSectionsLiveData() {
		return subSectionsLiveData;
	}

	private void populateSubSections() {
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

		subSectionsLiveData.setValue(List.of(section1, section1, section2, section3));
	}
}
