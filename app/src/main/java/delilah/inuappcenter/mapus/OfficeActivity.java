package delilah.inuappcenter.mapus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import delilah.inuappcenter.mapus.model.OfficeModel;
import delilah.inuappcenter.mapus.network.NetworkController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfficeActivity extends AppCompatActivity {

    RecyclerView officeRecyclerView;
    RecyclerView.LayoutManager officeLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_office);

        officeRecyclerView = findViewById(R.id.office_recycler_view);
        officeRecyclerView.setHasFixedSize(true);
        officeLayoutManager = new LinearLayoutManager(this);
        officeRecyclerView.setLayoutManager(officeLayoutManager);

        ArrayList<OfficeListInfo> officeInfoArrayList = new ArrayList<>();

        officeInfoArrayList.add(new OfficeListInfo("건물이름","건물번호"));

        OfficeAdapter officeAdapter = new OfficeAdapter(officeInfoArrayList);

        officeRecyclerView.setAdapter(officeAdapter);

        OfficeInformation();

        clickListenerSetting();
    }

    public void OfficeInformation() {
        Call<ArrayList<OfficeModel>> office = NetworkController.getInstance().getNetworkInterface().getOfficeInfo();
        office.enqueue(new Callback<ArrayList<OfficeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<OfficeModel>> call, Response<ArrayList<OfficeModel>> response) {
                ArrayList<OfficeModel> office = response.body();
                ArrayList<OfficeListInfo> officeInfoArrayList = new ArrayList<>();

                for (int i = 0; i < office.size(); i++) {

                    Log.d("사무실.번호", office.get(i).roomId);
                    Log.d("사무실.이름", String.valueOf(office.get(i).title));

                    officeInfoArrayList.add(new OfficeListInfo(office.get(i).roomId, String.valueOf(office.get(i).title)));
                }

                OfficeAdapter officeAdapter = new OfficeAdapter(officeInfoArrayList);

                officeRecyclerView.setAdapter(officeAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<OfficeModel>> call, Throwable t) {

            }
        });
    }

    private void clickListenerSetting() {
        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfficeActivity.this, EmployeeActivity.class);
                startActivity(intent);
            }
        });
        */
    }
}
