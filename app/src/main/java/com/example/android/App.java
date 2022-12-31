package com.example.android;

import android.app.Application;
import com.example.android.ui.settings.SettingsUtility;

public class App extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		SettingsUtility.updateTheme(this);
		SettingsUtility.updateLanguage(this);
	}
}
