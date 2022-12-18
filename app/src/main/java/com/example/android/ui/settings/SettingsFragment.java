package com.example.android.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.example.android.R;
import com.example.android.ui.login.LoginActivity;
import java.util.Optional;

public class SettingsFragment extends PreferenceFragmentCompat {

	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
		setPreferencesFromResource(R.xml.root_preferences, rootKey);

		Optional<Preference> preference = Optional.ofNullable(findPreference("logout"));
		preference.ifPresent(p -> p.setOnPreferenceClickListener(pref -> {
			var intent = new Intent(getContext(), LoginActivity.class);
			startActivity(intent);
			return true;
		}));
	}
}
