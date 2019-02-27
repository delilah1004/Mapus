package delilah.inuappcenter.mapus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PhoneBookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class PhoneBookViewHolder extends RecyclerView.ViewHolder {

        TextView detailOrgan, position, name, phoneNumber;

        public PhoneBookViewHolder(@NonNull View itemView) {
            super(itemView);
            detailOrgan = itemView.findViewById(R.id.detail_organ);
            position = itemView.findViewById(R.id.position);
            name = itemView.findViewById(R.id.name_person);
            phoneNumber = itemView.findViewById(R.id.phone_number);
        }
    }

    private ArrayList<PhoneBookInfo> phoneBookInfoArrayList;

    PhoneBookAdapter(ArrayList<PhoneBookInfo> phoneBookInfoArrayList) {
        this.phoneBookInfoArrayList = phoneBookInfoArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.phonebook_recyclerview_row, viewGroup, false);

        return new PhoneBookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        PhoneBookViewHolder phoneBookViewHolder = (PhoneBookViewHolder) viewHolder;

        phoneBookViewHolder.detailOrgan.setText(phoneBookInfoArrayList.get(i).detailOrgan);
        phoneBookViewHolder.position.setText(phoneBookInfoArrayList.get(i).position);
        phoneBookViewHolder.name.setText(phoneBookInfoArrayList.get(i).name);
        phoneBookViewHolder.phoneNumber.setText(phoneBookInfoArrayList.get(i).phoneNumber);
    }

    @Override
    public int getItemCount() {
        return phoneBookInfoArrayList.size();
    }
}
