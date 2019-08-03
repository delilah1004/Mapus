package delilah.inuappcenter.mapus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BuildingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class BuildingViewHolder extends RecyclerView.ViewHolder {

        TextView buildingTitle, buildingNumber;

        public BuildingViewHolder(@NonNull View itemView) {
            super(itemView);
            buildingTitle = itemView.findViewById(R.id.building_title);
            buildingNumber = itemView.findViewById(R.id.building_number);
        }
    }

    private ArrayList<BuildingListInfo> buildingListInfoArrayList;

    BuildingAdapter(ArrayList<BuildingListInfo> buildingListInfoArrayList) {
        this.buildingListInfoArrayList = buildingListInfoArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.building_recyclerview_row, viewGroup, false);

        return new BuildingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        BuildingViewHolder buildingViewHolder = (BuildingViewHolder) viewHolder;

        buildingViewHolder.buildingTitle.setText(buildingListInfoArrayList.get(position).buildingTitle);
        buildingViewHolder.buildingNumber.setText(buildingListInfoArrayList.get(position).builingNumber);
    }

    @Override
    public int getItemCount() {
        return buildingListInfoArrayList.size();
    }

}
