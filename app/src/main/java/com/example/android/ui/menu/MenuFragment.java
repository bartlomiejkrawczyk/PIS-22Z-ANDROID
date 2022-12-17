package com.example.android.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.databinding.FragmentMenuBinding;
import com.example.model.Section;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {

	private FragmentMenuBinding binding;
	private RecyclerView recyclerView;
	private SectionRecyclerViewAdapter recyclerViewAdapter;

	Observer<List<Section>> sectionListUpdateObserver = new Observer<>() {
		@Override
		public void onChanged(List<Section> sections) {
			recyclerViewAdapter.setSections(sections);
		}
	};

	public View onCreateView(@NonNull LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		MenuViewModel menuViewModel =
				new ViewModelProvider(this).get(MenuViewModel.class);

		binding = FragmentMenuBinding.inflate(inflater, container, false);
		View root = binding.getRoot();

		recyclerView = root.findViewById(R.id.menu_recycler_view);
		recyclerViewAdapter = new SectionRecyclerViewAdapter();

		recyclerView.setHasFixedSize(true);

		RecyclerView.LayoutManager manager = new LinearLayoutManager(this.getContext());
		recyclerView.setLayoutManager(manager);

		recyclerView.setAdapter(recyclerViewAdapter);
		menuViewModel.getSectionsLiveData().observe(getViewLifecycleOwner(), sectionListUpdateObserver);
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}