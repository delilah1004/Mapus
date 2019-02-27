package delilah.inuappcenter.mapus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

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

        phoneBookInfoArrayList.add(new PhoneBookInfo("학과이름","직위","이름","000-0000-0000"));
        phoneBookInfoArrayList.add(new PhoneBookInfo("학과이름","직위","홍길동","032-835-8115"));
        phoneBookInfoArrayList.add(new PhoneBookInfo("국어국문학과","인문학연구소장","송원용","032-835-8115"));

        PhoneBookAdapter phoneBookAdapter = new PhoneBookAdapter(phoneBookInfoArrayList);

        phoneBookRecyclerView.setAdapter(phoneBookAdapter);

    }
}
