package delilah.inuappcenter.mapus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import delilah.inuappcenter.mapus.model.BuildingModel;
import delilah.inuappcenter.mapus.network.NetworkController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildingActivity extends AppCompatActivity {

    RecyclerView buildingRecyclerView;
    RecyclerView.LayoutManager buildingLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        buildingRecyclerView = findViewById(R.id.building_recycler_view);
        buildingRecyclerView.setHasFixedSize(true);
        buildingLayoutManager = new LinearLayoutManager(this);
        buildingRecyclerView.setLayoutManager(buildingLayoutManager);

        ArrayList<BuildingListInfo> buildingInfoArrayList = new ArrayList<>();

        buildingInfoArrayList.add(new BuildingListInfo("건물이름","건물번호"));

        BuildingAdapter buildingAdapter = new BuildingAdapter(buildingInfoArrayList);

        buildingRecyclerView.setAdapter(buildingAdapter);

        BuildingInformation();

        clickListenerSetting();
    }

    public void BuildingInformation() {
        Call<ArrayList<BuildingModel>> building = NetworkController.getInstance().getNetworkInterface().getBuildingInfo();
        building.enqueue(new Callback<ArrayList<BuildingModel>>() {
            @Override
            public void onResponse(Call<ArrayList<BuildingModel>> call, Response<ArrayList<BuildingModel>> response) {
                ArrayList<BuildingModel> building = response.body();
                ArrayList<BuildingListInfo> buildingInfoArrayList = new ArrayList<>();

                for (int i = 0; i < building.size(); i++) {

                    Log.d("건물.건물명", building.get(i).title);
                    Log.d("건물.번호", String.valueOf(building.get(i).id));

                    buildingInfoArrayList.add(new BuildingListInfo(building.get(i).title, String.valueOf(building.get(i).id)));
                }

                BuildingAdapter buildingAdapter = new BuildingAdapter(buildingInfoArrayList);

                buildingRecyclerView.setAdapter(buildingAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<BuildingModel>> call, Throwable t) {

            }
        });
    }

    private void clickListenerSetting() {
        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingActivity.this, OfficeActivity.class);
                startActivity(intent);
            }
        });
        */
    }
}
