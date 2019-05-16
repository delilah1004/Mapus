package delilah.inuappcenter.mapus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import delilah.inuappcenter.mapus.model.EmployeeModel;
import delilah.inuappcenter.mapus.network.NetworkController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneBookActivity extends AppCompatActivity {

    RecyclerView phoneBookRecyclerView;
    RecyclerView.LayoutManager phoneBookLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);

        phoneBookRecyclerView = findViewById(R.id.phone_book_recycler_view);
        phoneBookRecyclerView.setHasFixedSize(true);
        phoneBookLayoutManager = new LinearLayoutManager(this);
        phoneBookRecyclerView.setLayoutManager(phoneBookLayoutManager);

        Information();
    }

    public void Information() {
        Call<ArrayList<EmployeeModel>> employee = NetworkController.getInstance().getNetworkInterface().getEmployeeInfo();
        employee.enqueue(new Callback<ArrayList<EmployeeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<EmployeeModel>> call, Response<ArrayList<EmployeeModel>> response) {
                ArrayList<EmployeeModel> employee = response.body();
                ArrayList<PhoneBookInfo> phoneBookInfoArrayList = new ArrayList<>();

                for (int i = 0; i < employee.size(); i++) {

                    Log.d("직원.아이디", String.valueOf(employee.get(i).id));
                    Log.d("직원.소속-단과대학이름", employee.get(i).detailOrgan);
                    Log.d("직원.직위", employee.get(i).position);
                    Log.d("직원.이름", employee.get(i).name);
                    Log.d("직원.전화번호", employee.get(i).telephone);

                    phoneBookInfoArrayList.add(new PhoneBookInfo(employee.get(i).detailOrgan, employee.get(i).position ,employee.get(i).name,employee.get(i).telephone));
                }
                PhoneBookAdapter phoneBookAdapter = new PhoneBookAdapter(phoneBookInfoArrayList);

                phoneBookRecyclerView.setAdapter(phoneBookAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<EmployeeModel>> call, Throwable t) {

            }
        });
    }
}
