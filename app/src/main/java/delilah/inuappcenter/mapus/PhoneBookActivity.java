package delilah.inuappcenter.mapus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import delilah.inuappcenter.mapus.model.EmployeeModel;
import delilah.inuappcenter.mapus.network.NetworkController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneBookActivity extends AppCompatActivity {

    RecyclerView phoneBookRecyclerView;
    RecyclerView.LayoutManager phoneBookLayoutManager;

    private ArrayList<PhoneBookInfo> arraylist;
    private EditText editSearch;
    private PhoneBookAdapter phoneBookAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);

        editSearch = (EditText) findViewById(R.id.phone_search);

        arraylist = new ArrayList<>();

        Information();

        phoneBookRecyclerView = findViewById(R.id.phone_book_recycler_view);
        phoneBookRecyclerView.setHasFixedSize(true);
        phoneBookLayoutManager = new LinearLayoutManager(this);
        phoneBookRecyclerView.setLayoutManager(phoneBookLayoutManager);

        ArrayList<PhoneBookInfo> phoneBookInfoArrayList = new ArrayList<>();

        phoneBookInfoArrayList.add(new PhoneBookInfo("단과대학","직위","이름" , "010-0000-0000"));

        PhoneBookAdapter phoneBookAdapter = new PhoneBookAdapter(phoneBookInfoArrayList);

        phoneBookRecyclerView.setAdapter(phoneBookAdapter);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String text = editSearch.getText().toString();
                search(text);
            }
        });
    }

    public void search(String charText){

        arraylist.clear();

        if(charText.length() == 0) {
            arraylist.addAll(arraylist);
        }
        else
        {
            for(int i=0; i<arraylist.size();i++){
                if(arraylist.get(i).toString().contains(charText)){
                    arraylist.add(arraylist.get(i));
                }
            }
        }
        phoneBookAdapter.notifyDataSetChanged();
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

                arraylist.addAll(phoneBookInfoArrayList);

                phoneBookAdapter = new PhoneBookAdapter(phoneBookInfoArrayList);

                phoneBookRecyclerView.setAdapter(phoneBookAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<EmployeeModel>> call, Throwable t) {

            }
        });
    }
}
