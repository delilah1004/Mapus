package delilah.inuappcenter.mapus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView officeTitle, buildingTitle;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            officeTitle = itemView.findViewById(R.id.building_title);
            buildingTitle = itemView.findViewById(R.id.building_number);
        }
    }

    private ArrayList<SearchListInfo> searchListInfoArrayList;

    SearchAdapter(ArrayList<SearchListInfo> searchListInfoArrayList) {
        this.searchListInfoArrayList = searchListInfoArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_recyclerview_row, viewGroup, false);

        return new SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        SearchViewHolder searchViewHolder = (SearchViewHolder) viewHolder;

        searchViewHolder.officeTitle.setText(searchListInfoArrayList.get(position).buildingTitle);
        searchViewHolder.buildingTitle.setText(searchListInfoArrayList.get(position).id);
    }

    @Override
    public int getItemCount() {
        return searchListInfoArrayList.size();
    }

}
