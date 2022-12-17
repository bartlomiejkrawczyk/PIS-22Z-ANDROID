package com.example.android.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.android.R;
import com.example.android.databinding.ActivityMainMenuBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenuActivity extends AppCompatActivity {

	public ActivityMainMenuBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		BottomNavigationView navView = findViewById(R.id.nav_view);
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
				R.id.navigation_menu, R.id.navigation_tests, R.id.navigation_add, R.id.navigation_settings)
				.build();
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_menu);
		NavigationUI.setupWithNavController(binding.navView, navController);
	}

}
