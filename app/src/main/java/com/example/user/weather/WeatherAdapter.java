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

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.TicketViewHolder> {

    public Context context;
    public Drawable myDrawable;
    ArrayList<String> ImgUrl = new ArrayList<>();


    @Override
    public TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        TicketViewHolder tvh = new TicketViewHolder(v);
        context = MainActivity.context();

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
                myDrawable = context.getResources().getDrawable(R.drawable.w01d);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "01n":
                myDrawable = context.getResources().getDrawable(R.drawable.w01n);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "02d":
                myDrawable = context.getResources().getDrawable(R.drawable.w02d);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "02n":
                myDrawable = context.getResources().getDrawable(R.drawable.w02n);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "03d":
                myDrawable = context.getResources().getDrawable(R.drawable.w03d);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "03n":
                myDrawable = context.getResources().getDrawable(R.drawable.w03d);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "04d":
                myDrawable = context.getResources().getDrawable(R.drawable.w04d);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "04n":
                myDrawable = context.getResources().getDrawable(R.drawable.w04d);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "09d":
                myDrawable = context.getResources().getDrawable(R.drawable.w09d);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "09n":
                myDrawable = context.getResources().getDrawable(R.drawable.w09d);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "10d":
                myDrawable = context.getResources().getDrawable(R.drawable.w10d);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "10n":
                myDrawable = context.getResources().getDrawable(R.drawable.w10n);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "11d":
                myDrawable = context.getResources().getDrawable(R.drawable.w11d);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "11n":
                myDrawable = context.getResources().getDrawable(R.drawable.w11d);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "13d":
                myDrawable = context.getResources().getDrawable(R.drawable.w13d);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "13n":
                myDrawable = context.getResources().getDrawable(R.drawable.w13d);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "50d":
                myDrawable = context.getResources().getDrawable(R.drawable.w50d);
                holder.iconView.setImageDrawable(myDrawable);
                break;
            case "50n":
                myDrawable = context.getResources().getDrawable(R.drawable.w50d);
                holder.iconView.setImageDrawable(myDrawable);
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


}
