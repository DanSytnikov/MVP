package com.example.user.weather;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.TicketViewHolder> {

    ArrayList<String> ImgUrl = new ArrayList<>();


    @Override
    public TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        TicketViewHolder tvh = new TicketViewHolder(v);

        ImgUrl.add("http://openweathermap.org/img/w/01d.png");
        ImgUrl.add("http://openweathermap.org/img/w/01n.png");
        ImgUrl.add("http://openweathermap.org/img/w/02d.png");
        ImgUrl.add("http://openweathermap.org/img/w/02n.png");
        ImgUrl.add("http://openweathermap.org/img/w/03d.png");
        ImgUrl.add("http://openweathermap.org/img/w/03n.png");
        ImgUrl.add("http://openweathermap.org/img/w/04d.png");
        ImgUrl.add("http://openweathermap.org/img/w/04n.png");
        ImgUrl.add("http://openweathermap.org/img/w/09d.png");
        ImgUrl.add("http://openweathermap.org/img/w/09n.png");
        ImgUrl.add("http://openweathermap.org/img/w/10d.png");
        ImgUrl.add("http://openweathermap.org/img/w/10n.png");
        ImgUrl.add("http://openweathermap.org/img/w/11d.png");
        ImgUrl.add("http://openweathermap.org/img/w/11n.png");
        ImgUrl.add("http://openweathermap.org/img/w/13d.png");
        ImgUrl.add("http://openweathermap.org/img/w/13n.png");
        ImgUrl.add("http://openweathermap.org/img/w/50d.png");
        ImgUrl.add("http://openweathermap.org/img/w/50n.png");

        return tvh;
    }

    @Override
    public void onBindViewHolder(TicketViewHolder holder, int position) {
        holder.dt.setText(tickets.get(position).wDate);
        holder.Tempr.setText(" " + tickets.get(position).wTemp + "°C " + tickets.get(position).wDescr);
        holder.Dscr.setText(" Wind: " + tickets.get(position).wWind + "m/s");
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

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView Dscr;
        TextView dt;
        TextView Tempr;
        ImageView iconView;

        TicketViewHolder(View itemView) {
            super(itemView);
            Tempr = itemView.findViewById(R.id.wTempr);
            Dscr = itemView.findViewById(R.id.wDescr);
            dt = itemView.findViewById(R.id.wDate);
            iconView = itemView.findViewById(R.id.wIcon);
        }
    }

    ArrayList<Ticket> tickets;

    WeatherAdapter(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }


    public void swapItems(ArrayList<Ticket> nTickets) {
        tickets = nTickets;
        notifyDataSetChanged();
    }


}
