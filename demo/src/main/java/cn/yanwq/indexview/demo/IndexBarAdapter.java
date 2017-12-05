package cn.yanwq.indexview.demo;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yan.indexview.RecyclerIndex;
import com.yan.indexview.RecyclerIndexBar;

import java.util.List;

import cn.yanwq.indexview.demo.entity.Index;

/**
 * yanweiqiang
 * 2017/12/4.
 */

public class IndexBarAdapter extends RecyclerView.Adapter<IndexBarAdapter.ViewHolder> implements RecyclerIndexBar.IAdapter {
    private RecyclerIndexBar recyclerIndexBar;
    private List<RecyclerIndex.IData> indexList;
    private int selectedPosition;
    private int prePosition;

    public IndexBarAdapter(RecyclerIndexBar recyclerIndexBar, List<RecyclerIndex.IData> indexList) {
        super();
        this.recyclerIndexBar = recyclerIndexBar;
        this.indexList = indexList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_index, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final int fp = position;
        final String title = ((Index) indexList.get(position)).getTitle();
        holder.title.setText(title);

        if (selectedPosition == position) {
            holder.title.setBackgroundColor(Color.parseColor("#44000000"));
        } else {
            holder.title.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), title + " clicked", Toast.LENGTH_SHORT).show();
                recyclerIndexBar.scrollToMappingPosition(fp);
            }
        });
    }

    @Override
    public int getItemCount() {
        return indexList.size();
    }

    @Override
    public void onCurrentIndexPosition(int position) {
        selectedPosition = position;
        notifyItemChanged(position);
        notifyItemChanged(prePosition);
        prePosition = position;
        recyclerIndexBar.scrollToPosition(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }
}
