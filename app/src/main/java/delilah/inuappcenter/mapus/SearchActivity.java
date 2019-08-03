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

        searchInfoArrayList.add(new SearchListInfo("건물이름","건물번호"));

        SearchAdapter searchAdapter = new SearchAdapter(searchInfoArrayList);

        searchRecyclerView.setAdapter(searchAdapter);

        BuildingInformation();

        clickListenerSetting();
    }

    public void BuildingInformation() {
        Call<ArrayList<BuildingModel>> office = NetworkController.getInstance().getNetworkInterface().getBuildingInfo();
        office.enqueue(new Callback<ArrayList<BuildingModel>>() {
            @Override
            public void onResponse(Call<ArrayList<BuildingModel>> call, Response<ArrayList<BuildingModel>> response) {
                ArrayList<BuildingModel> building = response.body();
                ArrayList<SearchListInfo> searchListInfoArrayList = new ArrayList<>();

                for (int i = 0; i < building.size(); i++) {

                    Log.d("건물.건물명", building.get(i).title);
                    Log.d("건물.번호", String.valueOf(building.get(i).id));

                    searchListInfoArrayList.add(new SearchListInfo(building.get(i).title, String.valueOf(building.get(i).id)));
                }

                SearchAdapter searchAdapter = new SearchAdapter(searchListInfoArrayList);

                searchRecyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<BuildingModel>> call, Throwable t) {

            }
        });
    }

    private void clickListenerSetting() {
        /*
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhoneBookActivity.class);
                startActivity(intent);
            }
        });
        */
    }

}
