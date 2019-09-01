package za.co.jaredfishy.susan.components;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import za.co.jaredfishy.susan.R;
import za.co.jaredfishy.susan.domain.Light;
import za.co.jaredfishy.susan.handler.LightHandler;

public class LightHandlerAdapter extends RecyclerView.Adapter<LightHandlerAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Light light);
    }

    private LightHandler lightHandler;
    private OnItemClickListener onItemClickListener;

    public LightHandlerAdapter(LightHandler lightHandler, OnItemClickListener onItemClickListener) {
        this.lightHandler = lightHandler;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lightlist_viewholder, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Light light = lightHandler.get(position);
        viewHolder.bind(light, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return lightHandler.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtIp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.light_name);
            txtIp = itemView.findViewById(R.id.light_ip);
        }

        public void bind(final Light light, final OnItemClickListener listener) {

            StringBuilder builder = new StringBuilder();

            builder.append(light.toString());
            builder.append(" (");
            builder.append(light.isPowered() ? "On" : "Off");
            builder.append(")");

            txtName.setText(builder.toString());
            txtIp.setText(light.getIp());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(light);
                }
            });

        }
    }
}
