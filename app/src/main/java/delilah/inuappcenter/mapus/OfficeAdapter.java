package delilah.inuappcenter.mapus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class OfficeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class OfficeViewHolder extends RecyclerView.ViewHolder {

        TextView roomNumber, officeTitle;

        public OfficeViewHolder(@NonNull View itemView) {
            super(itemView);
            roomNumber = itemView.findViewById(R.id.room_number);
            officeTitle = itemView.findViewById(R.id.office_title);
        }
    }

    private ArrayList<OfficeListInfo> officeListInfoArrayList;

    OfficeAdapter(ArrayList<OfficeListInfo> officeListInfoArrayList) {
        this.officeListInfoArrayList = officeListInfoArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.office_recyclerview_row, viewGroup, false);

        return new OfficeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        OfficeViewHolder officeViewHolder = (OfficeViewHolder) viewHolder;

        officeViewHolder.roomNumber.setText(officeListInfoArrayList.get(position).roomNumber);
        officeViewHolder.officeTitle.setText(officeListInfoArrayList.get(position).officeTitle);
    }

    @Override
    public int getItemCount() {
        return officeListInfoArrayList.size();
    }

}
