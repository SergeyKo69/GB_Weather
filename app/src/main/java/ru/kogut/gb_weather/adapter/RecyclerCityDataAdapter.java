package ru.kogut.gb_weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.kogut.gb_weather.R;

public class RecyclerCityDataAdapter extends RecyclerView.Adapter<RecyclerCityDataAdapter.ViewHolder> {
    private String[] data;
    private Context context;
    private int selectedPosition = -1;
    private OnItemClickListener itemClickListener;

    public RecyclerCityDataAdapter(String[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.city_item_layout,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        setText(holder, position);
        setOnItemClickBehavior(holder, position);
        highlightSelectedPosition(holder, position);
    }

    private void setText(@NonNull ViewHolder holder, final int position) {
        holder.cityItemTextView.setText(data[position]);
    }

    private void setOnItemClickBehavior(@NonNull ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    private void highlightSelectedPosition(@NonNull ViewHolder holder, final int position) {
        if(position == selectedPosition) {
            int color = ContextCompat.getColor(context, R.color.colorSetCity);
            holder.itemView.setBackgroundColor(color);
        } else {
            int color = ContextCompat.getColor(context, android.R.color.transparent);
            holder.itemView.setBackgroundColor(color);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.length;
    }

    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView cityItemTextView;
        View itemView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            initViews(itemView);
        }

        public TextView getCityItemTextView() {
            return cityItemTextView;
        }

        private void initViews(View itemView) {
            cityItemTextView = itemView.findViewById(R.id.cityItemTextView);
            cityItemTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }
    }

}
