package com.example.android.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.android.databinding.FragmentMenuBinding;
import com.example.model.Section;
import java.util.List;

public class MenuFragment extends Fragment {

	private FragmentMenuBinding binding;
	private SectionRecyclerViewAdapter recyclerViewAdapter;

	private final Observer<List<Section>> sectionListUpdateObserver = sections -> recyclerViewAdapter.setSections(sections);

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		MenuViewModel menuViewModel =
				new ViewModelProvider(this).get(MenuViewModel.class);

		binding = FragmentMenuBinding.inflate(inflater, container, false);
		View root = binding.getRoot();

		var recyclerView = binding.menuRecyclerView;
		recyclerViewAdapter = new SectionRecyclerViewAdapter(this.getContext());

		recyclerView.setHasFixedSize(true);

		var manager = new LinearLayoutManager(this.getContext());
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
