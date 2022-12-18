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

		subSectionsLiveData.setValue(List.of(section0, section1, section2));
	}
}
