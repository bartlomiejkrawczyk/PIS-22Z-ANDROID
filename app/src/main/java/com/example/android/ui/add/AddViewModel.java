package com.example.android.ui.add;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddViewModel extends ViewModel {

	private final MutableLiveData<String> text;

	public AddViewModel() {
		text = new MutableLiveData<>();
		text.setValue("This is add fragment");
	}

	public LiveData<String> getText() {
		return text;
	}
}
