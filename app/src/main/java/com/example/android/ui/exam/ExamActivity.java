package com.example.android.ui.exam;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.android.R;
import com.example.android.databinding.ActivityExamBinding;

public class ExamActivity extends AppCompatActivity {

	private ActivityExamBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exam);
	}
}