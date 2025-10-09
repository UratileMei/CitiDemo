package com.example.citidemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.citidemo.activities.IssueDetailActivity;
import com.google.android.material.card.MaterialCardView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.ViewHolder> {

    private Context context;
    private List<Issue> issueList;
    private SimpleDateFormat dateFormat;

    public IssueAdapter(Context context, List<Issue> issueList) {
        this.context = context;
        this.issueList = issueList;
        this.dateFormat = new SimpleDateFormat("MMM dd, yyyy • hh:mm a", Locale.getDefault());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_issue, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Issue issue = issueList.get(position);

        holder.tvTitle.setText(issue.getTitle());
        holder.tvCategory.setText(issue.getCategory());
        holder.tvLocation.setText(issue.getLocation());
        holder.tvTimestamp.setText(dateFormat.format(new Date(issue.getTimestamp())));
        holder.tvStatus.setText(issue.getStatus());
        holder.tvPriority.setText(issue.getPriority());

        // Set status color and background
        setStatusAppearance(holder.tvStatus, issue.getStatus());
        setPriorityAppearance(holder.tvPriority, issue.getPriority());

        // Show/hide image indicator
        if (issue.getImageUrl() != null && !issue.getImageUrl().isEmpty()) {
            holder.ivImageIndicator.setVisibility(View.VISIBLE);
        } else {
            holder.ivImageIndicator.setVisibility(View.GONE);
        }

        // Click listener to open detail view
        holder.cardView.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(context, IssueDetailActivity.class);
            intent.putExtra("issueId", issue.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return issueList.size();
    }

    private void setStatusAppearance(TextView textView, String status) {
        int colorRes;
        int bgRes;

        switch (status) {
            case "Pending":
                colorRes = R.color.status_pending_text;
                bgRes = R.drawable.status_pending_bg;
                break;
            case "In Progress":
                colorRes = R.color.status_in_progress_text;
                bgRes = R.drawable.status_in_progress_bg;
                break;
            case "Resolved":
                colorRes = R.color.status_resolved_text;
                bgRes = R.drawable.status_resolved_bg;
                break;
            case "Rejected":
                colorRes = R.color.status_rejected_text;
                bgRes = R.drawable.status_rejected_bg;
                break;
            default:
                colorRes = android.R.color.darker_gray;
                bgRes = R.drawable.status_pending_bg;
        }

        textView.setTextColor(ContextCompat.getColor(context, colorRes));
        textView.setBackgroundResource(bgRes);
    }

    private void setPriorityAppearance(TextView textView, String priority) {
        int colorRes;

        switch (priority) {
            case "Low":
                colorRes = R.color.priority_low;
                break;
            case "High":
                colorRes = R.color.priority_high;
                break;
            default: // Medium
                colorRes = R.color.priority_medium;
        }

        textView.setTextColor(ContextCompat.getColor(context, colorRes));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        TextView tvTitle, tvCategory, tvLocation, tvTimestamp, tvStatus, tvPriority;
        ImageView ivImageIndicator;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (MaterialCardView) itemView;
            tvTitle = itemView.findViewById(R.id.tvIssueTitle);
            tvCategory = itemView.findViewById(R.id.tvIssueCategory);
            tvLocation = itemView.findViewById(R.id.tvIssueLocation);
            tvTimestamp = itemView.findViewById(R.id.tvIssueTimestamp);
            tvStatus = itemView.findViewById(R.id.tvIssueStatus);
            tvPriority = itemView.findViewById(R.id.tvIssuePriority);
            ivImageIndicator = itemView.findViewById(R.id.ivImageIndicator);
        }
    }
}