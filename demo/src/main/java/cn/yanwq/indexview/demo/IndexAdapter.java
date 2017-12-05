package cn.yanwq.indexview.demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yan.indexview.RecyclerIndex;

import java.util.List;

import cn.yanwq.indexview.demo.entity.Data;
import cn.yanwq.indexview.demo.entity.Index;

import static com.yan.indexview.RecyclerIndex.INDEX;

/**
 * yanweiqiang
 * 2017/12/4.
 */

public class IndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RecyclerIndex.IAdapter {
    private List<RecyclerIndex.IData> itemDataList;

    public IndexAdapter(List<RecyclerIndex.IData> itemDataList) {
        super();
        this.itemDataList = itemDataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == INDEX) {
            return new IndexHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_index, parent, false));
        }

        return new ContentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof IndexHolder) {
            IndexHolder indexHolder = (IndexHolder) holder;
            indexHolder.display((Index) itemDataList.get(position));
        }
        if (holder instanceof ContentHolder) {
            ContentHolder contentHolder = (ContentHolder) holder;
            contentHolder.content.setText(((Data) itemDataList.get(position)).getText());
        }
    }

    @Override
    public int getItemCount() {
        return itemDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        RecyclerIndex.IData data = itemDataList.get(position);
        if (data instanceof RecyclerIndex.IIndex) {
            return INDEX;
        }
        return super.getItemViewType(position);
    }

    @Override
    public List getDataList() {
        return itemDataList;
    }

    class IndexHolder extends RecyclerView.ViewHolder implements RecyclerIndex.IViewHolder<Index> {
        TextView title;

        Index index;

        IndexHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }

        @Override
        public View getView() {
            return itemView;
        }

        @Override
        public Index getData() {
            return index;
        }

        @Override
        public void display(Index data) {
            this.index = data;
            title.setText(data.getTitle());
        }
    }

    class ContentHolder extends RecyclerView.ViewHolder {
        TextView content;

        ContentHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
        }
    }
}
