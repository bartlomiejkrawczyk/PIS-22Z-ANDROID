package com.example.android.ui.menu;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.model.Section;

import java.util.ArrayList;
import java.util.List;

public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private List<Section> sections;

	public SectionRecyclerViewAdapter() {
		this.sections = new ArrayList<>();
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_section, parent, false);
		return new SectionViewHolder(rootView);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		Section section = sections.get(position);
		SectionViewHolder viewHolder = (SectionViewHolder) holder;
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

		public SectionViewHolder(@NonNull View itemView) {
			super(itemView);
		}
	}
}
