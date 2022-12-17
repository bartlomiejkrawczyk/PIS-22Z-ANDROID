package com.example.android.ui.menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.R;
import com.example.model.Section;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private List<Section> sections;
	private final Context context;

	public SectionRecyclerViewAdapter(Context context) {
		this.sections = new ArrayList<>();
		this.context = context;
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		var rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_section, parent, false);
		return new SectionViewHolder(rootView);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		var section = sections.get(position);
		var viewHolder = (SectionViewHolder) holder;

		viewHolder.sectionLinearLayout.setOnClickListener(v -> {
			viewHolder.onClick();
			notifyItemChanged(position);
		});

		viewHolder.sectionName.setText(section.getName());

		if (viewHolder.selected) {
			viewHolder.sectionLinearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.section_acvite_background));
			var subSections = section.getSubSections()
					.stream()
					.map(Section::getName)
					.collect(Collectors.toList());

			ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, subSections);
			viewHolder.subSections.setAdapter(arrayAdapter);
		}
	}

	@Override
	public int getItemCount() {
		return sections.size();
	}

	@SuppressLint("NotifyDataSetChanged")
	public void setSections(List<Section> sections) {
		this.sections.clear();
		this.sections = sections;
		notifyDataSetChanged();
	}

	static class SectionViewHolder extends RecyclerView.ViewHolder {
		boolean selected;
		TextView sectionName;
		ListView subSections;
		LinearLayout sectionLinearLayout;

		public SectionViewHolder(@NonNull View itemView) {
			super(itemView);
			this.sectionName = itemView.findViewById(R.id.section_name);
			this.subSections = itemView.findViewById(R.id.sub_section_list_view);
			this.sectionLinearLayout = itemView.findViewById(R.id.section_linear_layout);
			this.selected = false;
		}

		void onClick() {
			selected = !selected;
		}
	}
}
