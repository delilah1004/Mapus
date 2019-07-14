package delilah.inuappcenter.mapus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import delilah.inuappcenter.mapus.model.BuildingModel;
import delilah.inuappcenter.mapus.model.EmployeeModel;
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
        Call<ArrayList<OfficeModel>> office = NetworkController.getInstance().getNetworkInterface().getOfficeInfo();
        office.enqueue(new Callback<ArrayList<OfficeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<OfficeModel>> call, Response<ArrayList<OfficeModel>> response) {
                ArrayList<OfficeModel> office = response.body();
                ArrayList<SearchListInfo> searchListInfoArrayList = new ArrayList<>();

                for (int i = 0; i < office.size(); i++) {

                    Log.d("사무실.아이디", String.valueOf(office.get(i).id));
                    Log.d("사무실.사무실명", office.get(i).title);
                    Log.d("사무실.사무실호수", office.get(i).roomId);
                    Log.d("사무실.빌딩호관", office.get(i).buildingId);

                    searchListInfoArrayList.add(new SearchListInfo(office.get(i).title, office.get(i).buildingId));
                }

                SearchAdapter searchAdapter = new SearchAdapter(searchListInfoArrayList);

                searchRecyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<OfficeModel>> call, Throwable t) {

            }
        });
    }

}
