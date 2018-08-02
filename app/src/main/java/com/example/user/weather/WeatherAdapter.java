package com.example.user.weather;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.TicketViewHolder> {

    private List<Ticket> tickets;


    @Override
    public TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        TicketViewHolder tvh = new TicketViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(TicketViewHolder holder, int position) {
        holder.dt.setText(tickets.get(position).wDate);
        holder.Tempr.setText(" " + tickets.get(position).wTemp + "°C " + tickets.get(position).wDescr);
        holder.Dscr.setText(" Wind: " + tickets.get(position).wWind + "m/s");
        holder.textView.setText("Pressure: " + tickets.get(position).wPressure + " hPa" +
                "\nHumidity: " + tickets.get(position).wHumidity + " %" +
        "\nClouds: " + tickets.get(position).wCloudness + " %");
        switch (tickets.get(position).wIcon) {
            case "01d":
                holder.iconView.setImageResource(R.drawable.w01d);
                break;
            case "01n":
                holder.iconView.setImageResource(R.drawable.w01n);
                break;
            case "02d":
                holder.iconView.setImageResource(R.drawable.w02d);
                break;
            case "02n":
                holder.iconView.setImageResource(R.drawable.w02n);
                break;
            case "03d":
                holder.iconView.setImageResource(R.drawable.w03d);
                break;
            case "03n":
                holder.iconView.setImageResource(R.drawable.w03d);
                break;
            case "04d":
                holder.iconView.setImageResource(R.drawable.w04d);
                break;
            case "04n":
                holder.iconView.setImageResource(R.drawable.w04d);
                break;
            case "09d":
                holder.iconView.setImageResource(R.drawable.w09d);
                break;
            case "09n":
                holder.iconView.setImageResource(R.drawable.w09d);
                break;
            case "10d":
                holder.iconView.setImageResource(R.drawable.w10d);
                break;
            case "10n":
                holder.iconView.setImageResource(R.drawable.w10n);
                break;
            case "11d":
                holder.iconView.setImageResource(R.drawable.w11d);
                break;
            case "11n":
                holder.iconView.setImageResource(R.drawable.w11d);
                break;
            case "13d":
                holder.iconView.setImageResource(R.drawable.w13d);
                break;
            case "13n":
                holder.iconView.setImageResource(R.drawable.w13d);
                break;
            case "50d":
                holder.iconView.setImageResource(R.drawable.w50d);
                break;
            case "50n":
                holder.iconView.setImageResource(R.drawable.w50d);
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    WeatherAdapter() {
        this.tickets = new ArrayList<>();
    }


    public void swapItems(List<Ticket> nTickets) {
        tickets = nTickets;
        notifyDataSetChanged();
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView Dscr;
        TextView dt;
        TextView Tempr;
        TextView textView;
        ImageView iconView;
        boolean visible = false;

        TicketViewHolder(View itemView) {
            super(itemView);
            Tempr = itemView.findViewById(R.id.wTempr);
            Dscr = itemView.findViewById(R.id.wDescr);
            dt = itemView.findViewById(R.id.wDate);
            textView = itemView.findViewById(R.id.text);
            iconView = itemView.findViewById(R.id.wIcon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.e("CLICK","Happened");
            final ViewGroup transitionsContainer = view.findViewById(R.id.transitions_container);
            final LinearLayout hidden = transitionsContainer.findViewById(R.id.hidden);
            final ImageButton moreless = transitionsContainer.findViewById(R.id.moreless_button);

            TransitionSet transition = new TransitionSet();
            ChangeBounds changeBounds = new ChangeBounds();
            changeBounds.setDuration(300);
            Fade fadeOut = new Fade(Fade.OUT);
            fadeOut.setDuration(100);
            Fade fadeIn = new Fade(Fade.IN);
            fadeIn.setDuration(800);
            transition
                    .addTransition(fadeOut)
                    .addTransition(changeBounds)
                    .addTransition(fadeIn);
            TransitionManager.beginDelayedTransition(transitionsContainer, transition);

           visible = !visible;
           hidden.setVisibility(visible ? View.VISIBLE : View.GONE);
           moreless.setImageResource(visible ?R.drawable.baseline_expand_less_24px:R.drawable.baseline_expand_more_24px);
        }
    }
}
