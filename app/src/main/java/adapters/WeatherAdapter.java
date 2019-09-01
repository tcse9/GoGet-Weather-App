package adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.binate.gogetweatherapp.R;

import java.util.List;

import core.ApplicationSingleton;
import models.Datum;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {

    private List<Datum> datumList;
    private Context context;

    public WeatherAdapter(List<Datum> datumList, Context context) {
        this.datumList = datumList;
        this.context = context;
    }

    @Override
    public WeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_days,parent,false);
        WeatherHolder mh = new WeatherHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(WeatherHolder holder, int position) {

        holder.txtDate.setText(ApplicationSingleton.getInstance().convertUnixTimeToDateFormat(datumList.get(position).getTime()));
        holder.txtStatus.setText(ApplicationSingleton.getInstance().capitalizeFirstLetter(datumList.get(position).getIcon()));
        holder.txtTemperature.setText(String.valueOf(datumList.get(position).getTemperatureHigh())+"/"+String.valueOf(datumList.get(position).getTemperatureLow())+" \u2109");

    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }


    public class WeatherHolder extends RecyclerView.ViewHolder {

        TextView txtDate,txtStatus,txtTemperature;

        public WeatherHolder(View v) {
            super(v);
            txtDate = v.findViewById(R.id.txtDate);
            txtStatus = v.findViewById(R.id.txtStatus);
            txtTemperature = v.findViewById(R.id.txtTemperature);
        }
    }
}
