package com.example.citidemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder> {

    private Context context;
    private List<Announcement> announcementList;
    private SimpleDateFormat dateFormat;

    public AnnouncementAdapter(Context context, List<Announcement> announcementList) {
        this.context = context;
        this.announcementList = announcementList;
        this.dateFormat = new SimpleDateFormat("MMM dd, yyyy • hh:mm a", Locale.getDefault());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_announcement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Announcement announcement = announcementList.get(position);

        holder.tvTitle.setText(announcement.getTitle());
        holder.tvDescription.setText(announcement.getDescription());
        holder.tvCategory.setText(announcement.getCategory());
        holder.tvTimestamp.setText(dateFormat.format(new Date(announcement.getTimestamp())));

        // Show urgent badge if urgent
        if (announcement.isUrgent()) {
            holder.tvUrgentBadge.setVisibility(View.VISIBLE);
        } else {
            holder.tvUrgentBadge.setVisibility(View.GONE);
        }

        // Set category color
        int categoryColor = getCategoryColor(announcement.getCategory());
        holder.tvCategory.setTextColor(categoryColor);
    }

    @Override
    public int getItemCount() {
        return announcementList.size();
    }

    private int getCategoryColor(String category) {
        if (category == null) return context.getResources().getColor(android.R.color.darker_gray);

        switch (category.toLowerCase()) {
            case "water":
                return context.getResources().getColor(android.R.color.holo_blue_dark);
            case "electricity":
                return context.getResources().getColor(android.R.color.holo_orange_dark);
            case "roads":
                return context.getResources().getColor(android.R.color.holo_red_dark);
            case "waste":
                return context.getResources().getColor(android.R.color.holo_green_dark);
            case "general":
            default:
                return context.getResources().getColor(android.R.color.darker_gray);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        TextView tvTitle, tvDescription, tvCategory, tvTimestamp, tvUrgentBadge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (MaterialCardView) itemView;
            tvTitle = itemView.findViewById(R.id.tvAnnouncementTitle);
            tvDescription = itemView.findViewById(R.id.tvAnnouncementDescription);
            tvCategory = itemView.findViewById(R.id.tvAnnouncementCategory);
            tvTimestamp = itemView.findViewById(R.id.tvAnnouncementTimestamp);
            tvUrgentBadge = itemView.findViewById(R.id.tvUrgentBadge);
        }
    }
}