package com.example.mvpdb.base;

import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.ObjectUtils;

import java.util.List;

/**
 * Created by Ruansu
 * on 2020/8/5 10:11 AM
 */
public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> list;

    public BaseAdapter(List<T> list) {
        this.list = list;
    }

    public void resetList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public boolean isListEmpty(List<?> list) {
        return CollectionUtils.isEmpty(list);
    }

    public void deleteItem(T t) {
        if (getItemCount() > 0) {
            for (int i = 0; i < getItemCount(); i++) {
                T T = list.get(i);
                if (ObjectUtils.equals(t, T)) {
                    list.remove(T);
                    notifyItemRemoved(i);
                }
            }
        }
    }

    public void addItems(List<T> list) {
        if (!isListEmpty(list)) {
            int start = getItemCount();
            if (this.list != null) {
                this.list.addAll(list);
            } else {
                this.list = list;
            }
            notifyItemRangeInserted(start, getItemCount() - start);
        }
    }

    @Override
    public int getItemCount() {
        return isListEmpty(list) ? 0 : list.size();
    }
}
