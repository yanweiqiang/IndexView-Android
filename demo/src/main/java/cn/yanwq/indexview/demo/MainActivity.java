package cn.yanwq.indexview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.yan.indexview.RecyclerIndex;
import com.yan.indexview.RecyclerIndexBar;

import java.util.List;

import cn.yanwq.indexview.demo.util.DataUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<RecyclerIndex.IData> itemDataList = DataUtil.getItemDataList(555, 10);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new IndexAdapter(itemDataList));

        RecyclerIndex<RecyclerIndex.IData> recyclerIndex = new RecyclerIndex<>(recyclerView);
        recyclerIndex.attachIndex();

        RecyclerIndexBar<RecyclerIndex.IData> recyclerIndexBar = findViewById(R.id.index_bar);
        recyclerIndexBar.setLayoutManager(new LinearLayoutManager(this));
        recyclerIndexBar.setAdapter(new IndexBarAdapter(recyclerIndexBar, recyclerIndexBar.transformData(itemDataList)));
        recyclerIndexBar.attach(recyclerView, recyclerIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
