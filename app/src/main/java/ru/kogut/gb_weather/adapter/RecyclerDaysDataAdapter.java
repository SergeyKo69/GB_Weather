package ru.kogut.gb_weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.kogut.gb_weather.R;

public class RecyclerDaysDataAdapter extends RecyclerView.Adapter<RecyclerDaysDataAdapter.ViewHolder> {
    private String[] data;

    public RecyclerDaysDataAdapter(String[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.day_item_layout,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        setText(holder, position);
    }

    private void setText(@NonNull ViewHolder holder, final int position) {
        holder.dayItemTextView.setText(data[position]);
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.length;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView dayItemTextView;
        View itemView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            initViews(itemView);
        }

        public TextView getDayItemTextView() {
            return dayItemTextView;
        }

        private void initViews(View itemView) {
            dayItemTextView = itemView.findViewById(R.id.dayItemTextView);
        }
    }

}
