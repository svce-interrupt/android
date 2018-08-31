package com.lazytomatostudios.svceinterrupt.events;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.lazytomatostudios.svceinterrupt.R;

import java.util.ArrayList;

/**
 * Created by drpayyne on 6/9/17.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.recyclerViewHolder> {

    private final EventsAdapter.OnItemCheckListener listener;
    ArrayList<Events> mEvents;

    public EventsAdapter(ArrayList<Events> myEvents, EventsAdapter.OnItemCheckListener listener) {
        mEvents = myEvents;
        this.listener = listener;
    }

    @Override
    public recyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);

        EventsAdapter.recyclerViewHolder rvh = new EventsAdapter.recyclerViewHolder(view);
        return rvh;
    }

    @Override
    public void onBindViewHolder(final recyclerViewHolder holder, final int position) {
        final Events currentItem = mEvents.get(position);
        holder.bind(currentItem);

        ((recyclerViewHolder) holder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((recyclerViewHolder) holder).eventsBox.setChecked(
                        !((recyclerViewHolder) holder).eventsBox.isChecked());
                if (((recyclerViewHolder) holder).eventsBox.isChecked()) {
                    listener.onItemCheck(currentItem, position);
                } else {
                    listener.onItemUncheck(currentItem, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public interface OnItemCheckListener {
        void onItemCheck(Events item, int positon);

        void onItemUncheck(Events item, int position);
    }

    public class recyclerViewHolder extends RecyclerView.ViewHolder {

        TextView eventsNumber;
        TextView eventsText;
        CheckBox eventsBox;
        CardView cardView;


        public recyclerViewHolder(View v) {
            super(v);
            eventsNumber = (TextView) v.findViewById(R.id.eventsNumber);
            eventsText = (TextView) v.findViewById(R.id.eventsName);
            eventsBox = (CheckBox) v.findViewById(R.id.eventsBox);
            eventsBox.setClickable(false);
            cardView = (CardView) v.findViewById(R.id.cardView2);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
        }

        public void bind(final Events item) {

            YoYo.with(Techniques.FlipInY)
                    .playOn(cardView);

            eventsText.setText(item.getEventsName());
            eventsNumber.setText(String.valueOf(item.getNumber()));
        }
    }
}
