package nav.com.nfproject2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewDemoActivity extends AppCompatActivity {

    ArrayList<MovieDataModel> dataList = new ArrayList<>();
    RecyclerView mRecyclerView;
    MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        if (getSupportActionBar()!=null)
            getSupportActionBar().setTitle("Recycler View Demo");
        mAdapter = new MoviesAdapter(this, dataList);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager =  new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        initDataInList();
    }

    private void initDataInList(){
        dataList.clear();
        MovieDatabase database = new MovieDatabase(this);
        dataList.addAll(database.getAllMovieData());
        if (dataList.size()==0){
            addToDatabase();
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void addToDatabase(){
        MovieDatabase database = new MovieDatabase(this);
        for (int i = 0; i <DemoData.TITLE.length; i++) {
            database.addMovie(new MovieDataModel(DemoData.TITLE[i],
                    DemoData.DESCRIPTION[i], DemoData.YEAR[i], DemoData.URL[i]));
        }

        initDataInList();
    }
}
