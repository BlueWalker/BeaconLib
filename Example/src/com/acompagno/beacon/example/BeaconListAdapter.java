package com.acompagno.beacon.example;

import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.LightingColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.acompagno.beacon.Beacon;

public class BeaconListAdapter extends ArrayAdapter<Beacon> {

    private static final String MAJOR_TEXT_FORMAT = "Major - %d";
    private static final String MINOR_TEXT_FORMAT = "Minor - %d";
    private static final String RSSI_TEXT_FORMAT = "Calibration RSSI - %d";
    private static final String UUID_TEXT_FORMAT = "UUID - %s";

    private final Activity context;
    private final List<Beacon> beacons;

    /**
     * Holds all the views used for the item in the Beacon ListView
     */
    private class ViewHolder {
        public TextView majorText;
        public TextView minorText;
        public TextView rssiText;
        public TextView uuidText;
        public ImageView icon;
    }

    public BeaconListAdapter(final Activity context, final List<Beacon> beacons) {
        super(context, R.layout.list_view_item, beacons);
        this.context = context;
        this.beacons = beacons;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_view_item, null);

            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.majorText = (TextView) rowView.findViewById(R.id.major_text);
            viewHolder.minorText = (TextView) rowView.findViewById(R.id.minor_text);
            viewHolder.rssiText = (TextView) rowView.findViewById(R.id.rssi_text);
            viewHolder.uuidText = (TextView) rowView.findViewById(R.id.uuid_text);
            viewHolder.icon = (ImageView) rowView.findViewById(R.id.icon);

            rowView.setTag(viewHolder);
        }

        final ViewHolder holder = (ViewHolder) rowView.getTag();
        final Beacon beacon = this.beacons.get(position);
        holder.majorText.setText(String.format(MAJOR_TEXT_FORMAT, beacon.getMajor()));
        holder.minorText.setText(String.format(MINOR_TEXT_FORMAT, beacon.getMinor()));
        holder.rssiText.setText(String.format(RSSI_TEXT_FORMAT, beacon.getRSSI()));
        holder.uuidText.setText(String.format(UUID_TEXT_FORMAT, beacon.getUUID().toString()));
        holder.icon.setImageResource(R.drawable.ic_launcher_trans);
        return rowView;
    }
} 