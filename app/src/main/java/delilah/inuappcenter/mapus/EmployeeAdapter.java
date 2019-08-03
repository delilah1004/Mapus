package delilah.inuappcenter.mapus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView detailOrgan, position, name, phoneNumber;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            detailOrgan = itemView.findViewById(R.id.detail_organ);
            position = itemView.findViewById(R.id.position);
            name = itemView.findViewById(R.id.name_person);
            phoneNumber = itemView.findViewById(R.id.phone_number);
        }
    }

    private ArrayList<PhoneBookInfo> employeeListInfoArrayList;

    EmployeeAdapter(ArrayList<PhoneBookInfo> employeeListInfoArrayList) {
        this.employeeListInfoArrayList = employeeListInfoArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.phonebook_recyclerview_row, viewGroup, false);

        return new EmployeeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        EmployeeViewHolder employeeViewHolder = (EmployeeViewHolder) viewHolder;

        employeeViewHolder.detailOrgan.setText(employeeListInfoArrayList.get(position).detailOrgan);
        employeeViewHolder.position.setText(employeeListInfoArrayList.get(position).position);
        employeeViewHolder.name.setText(employeeListInfoArrayList.get(position).name);
        employeeViewHolder.phoneNumber.setText(employeeListInfoArrayList.get(position).phoneNumber);
    }

    @Override
    public int getItemCount() {
        return employeeListInfoArrayList.size();
    }

}
