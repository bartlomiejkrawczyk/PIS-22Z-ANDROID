package com.example.android.ui.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.model.Section;

import java.util.ArrayList;
import java.util.List;

public class MenuViewModel extends ViewModel {

	private MutableLiveData<List<Section>> sectionsLiveData;
	private List<Section> sections;

	public MenuViewModel() {
		sectionsLiveData = new MutableLiveData<>();

		// call your Rest API in init method
		init();
	}

	public MutableLiveData<List<Section>> getSectionsLiveData(){
		return sectionsLiveData;
	}

	public void init(){
		populateList();
		sectionsLiveData.setValue(sections);
	}

	public void populateList(){

		Section section = Section.builder()
				.name("XDDDD")
				.build();

		sections = new ArrayList<>();
		sections.add(section);
		sections.add(section);
	}
}