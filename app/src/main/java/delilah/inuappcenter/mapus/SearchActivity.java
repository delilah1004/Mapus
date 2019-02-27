package delilah.inuappcenter.mapus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

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

        ArrayList<SearchListInfo> searchListInfoArrayList = new ArrayList<>();

        searchListInfoArrayList.add(new SearchListInfo("감사","본관"));
        searchListInfoArrayList.add(new SearchListInfo("미래전략팀","대학본부"));
        searchListInfoArrayList.add(new SearchListInfo("부서명","위치"));

        SearchAdapter searchAdapter = new SearchAdapter(searchListInfoArrayList);

        searchRecyclerView.setAdapter(searchAdapter);

    }
}
