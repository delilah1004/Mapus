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

public class EmployeeActivity extends AppCompatActivity {

    RecyclerView employeeRecyclerView;
    RecyclerView.LayoutManager employeeLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        employeeRecyclerView = findViewById(R.id.employee_recycler_view);
        employeeRecyclerView.setHasFixedSize(true);
        employeeLayoutManager = new LinearLayoutManager(this);
        employeeRecyclerView.setLayoutManager(employeeLayoutManager);

        ArrayList<PhoneBookInfo> employeeInfoArrayList = new ArrayList<>();

        employeeInfoArrayList.add(new PhoneBookInfo("단과대학","직위","이름" , "010-0000-0000"));

        EmployeeAdapter employeeAdapter = new EmployeeAdapter(employeeInfoArrayList);

        employeeRecyclerView.setAdapter(employeeAdapter);

        EmployeeInformation();
    }

    public void EmployeeInformation() {
        Call<ArrayList<EmployeeModel>> employee = NetworkController.getInstance().getNetworkInterface().getEmployeeInfo();
        employee.enqueue(new Callback<ArrayList<EmployeeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<EmployeeModel>> call, Response<ArrayList<EmployeeModel>> response) {
                ArrayList<EmployeeModel> employee = response.body();
                ArrayList<PhoneBookInfo> employeeInfoArrayList = new ArrayList<>();

                for (int i = 0; i < employee.size(); i++) {

                    Log.d("직원.소속-단과대학이름", employee.get(i).detailOrgan);
                    Log.d("직원.직위", employee.get(i).position);
                    Log.d("직원.이름", employee.get(i).name);
                    Log.d("직원.전화번호", employee.get(i).telephone);

                    employeeInfoArrayList.add(new PhoneBookInfo(employee.get(i).detailOrgan, employee.get(i).position ,employee.get(i).name,employee.get(i).telephone));
                }

                EmployeeAdapter employeeAdapter = new EmployeeAdapter(employeeInfoArrayList);

                employeeRecyclerView.setAdapter(employeeAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<EmployeeModel>> call, Throwable t) {

            }
        });
    }
}
