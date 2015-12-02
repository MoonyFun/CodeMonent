package moony.codemonent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import moony.codemonent.R;
import moony.codemonent.model.Joke;

/**
 * @author: MOONY
 * @data: 15/12/1
 * @Description: ${todo}<joke recycler适配器>
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private List<Joke.ResultEntity.DataEntity> datas = new ArrayList<>();
    private Context mContext;
    public MyRecyclerAdapter(Context context) {
        this.mContext = context;
    }

    public void update(List<Joke.ResultEntity.DataEntity> jokeDatas) {
        if (datas.isEmpty()) {
            Log.i("首次加载", "======");
            this.datas = jokeDatas;
        } else {
            this.datas.addAll(jokeDatas);
        }
        notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView joke_content, joke_time;

        public MyViewHolder(View itemView) {
            super(itemView);
            joke_content = ButterKnife.findById(itemView, R.id.joke_content);
            joke_time = ButterKnife.findById(itemView, R.id.joke_time);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.joke_list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.joke_content.setText(datas.get(position).getContent());
        holder.joke_time.setText(datas.get(position).getUpdatetime());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
