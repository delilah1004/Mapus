package delilah.inuappcenter.mapus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import delilah.inuappcenter.mapus.model.BuildingModel;
import delilah.inuappcenter.mapus.model.OfficeModel;
import delilah.inuappcenter.mapus.network.NetworkController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    RecyclerView searchRecyclerView;
    RecyclerView.LayoutManager searchLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_building);

        searchRecyclerView = findViewById(R.id.search_recycler_view);
        searchRecyclerView.setHasFixedSize(true);
        searchLayoutManager = new LinearLayoutManager(this);
        searchRecyclerView.setLayoutManager(searchLayoutManager);

        ArrayList<SearchListInfo> searchInfoArrayList = new ArrayList<>();

        searchInfoArrayList.add(new SearchListInfo("사무실이름","건물이름"));

        SearchAdapter searchAdapter = new SearchAdapter(searchInfoArrayList);

        searchRecyclerView.setAdapter(searchAdapter);

        Information();
    }

    public void Information() {

    }

}
