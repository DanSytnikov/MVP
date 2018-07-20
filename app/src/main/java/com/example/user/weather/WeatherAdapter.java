package com.example.user.weather;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.TicketViewHolder> {

    @Override
    public TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TicketViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        CheckBox cb;
        TextView Dscr;
        TextView dt;
        ImageView iconView;
        TicketViewHolder(View itemView){
            super(itemView);
            cb = itemView.findViewById(R.id.cbBox);
            Dscr = itemView.findViewById(R.id.wDescr);
            dt = itemView.findViewById(R.id.wDate);
            iconView = itemView.findViewById(R.id.wIcon);
        }
    }
    List<Ticket> tickets;
    WeatherAdapter(List<Ticket> tickets){
        this.tickets = tickets;
    }


}
