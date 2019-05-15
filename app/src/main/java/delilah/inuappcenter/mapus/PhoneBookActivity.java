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

        ArrayList<PhoneBookInfo> phoneBookInfoArrayList = new ArrayList<>();

        phoneBookInfoArrayList.add(new PhoneBookInfo("학과이름", "직위" ,"이름","000-0000-0000"));

        PhoneBookAdapter phoneBookAdapter = new PhoneBookAdapter(phoneBookInfoArrayList);

        phoneBookRecyclerView.setAdapter(phoneBookAdapter);

    }
}
