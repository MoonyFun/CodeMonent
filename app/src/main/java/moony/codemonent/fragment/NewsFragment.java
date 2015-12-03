package moony.codemonent.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import moony.codemonent.NewsWebActivity;
import moony.codemonent.R;
import moony.codemonent.api.CodeMonentClient;
import moony.codemonent.model.NewDetail;
import moony.codemonent.model.News;
import moony.codemonent.view.ScaleImageView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * @author: MOONY
 * @data: 15/11/27
 * @Description: ${todo}<新闻frag>
 */
public class NewsFragment extends Fragment {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.news_progressBar)
    ProgressBar newsProgressBar;

    private List<String> newsList = new ArrayList<>();
    private List<NewDetail.ResultEntity> newDetail = new ArrayList<>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x9527) {
                getNewDetail();
            }
            if (msg.what == 0x5173) {
                recyclerView.setAdapter(new MyNewsAdapter(newDetail));
                newsProgressBar.setVisibility(View.INVISIBLE);
                Log.i("新闻长度", newDetail.size() + "");
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getNewsList();
    }

    public void getNewsList() {
        Call<News> newsCall = CodeMonentClient.getNewApi().getNewList();
        newsCall.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Response<News> response, Retrofit retrofit) {
                newsList = response.body().getResult();
                Log.i("r-=-=-=-", response.body().getResult().get(1));
                Log.i("r-=-=-=-", newsList.size() + "");

                handler.sendEmptyMessage(0x9527);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("List-onFailure", t.toString());
            }
        });
        Log.i("newLIst", newsList.size() + "");
    }

    public void getNewDetail() {
        for (int i = 0; i < newsList.size(); i++) {
            Call<NewDetail> detailCall = CodeMonentClient.getNewApi().getNewDetail(newsList.get(i), "31d64a46790cc4797c1856543e65ebb1", "json");
            final int finalI = i;
            detailCall.enqueue(new Callback<NewDetail>() {
                                   @Override
                                   public void onResponse(Response<NewDetail> response, Retrofit retrofit) {

                                       if (response.isSuccess()) {
                                           if (response.body().getError_code() == 0) {
                                               newDetail.add(response.body().getResult().get(0));
//                                               Log.i("Tag", "size=" + newDetail.size() + " Request=" + new Gson().toJson(response));

                                               if (finalI == newsList.size() - 1) {
                                                   handler.sendEmptyMessage(0x5173);
//
                                               }
                                           }
                                       }
                                   }

                                   @Override
                                   public void onFailure(Throwable t) {
                                       Log.e("getNewDetail-onFailure", t.toString());
                                   }
                               }

            );
        }
    }


    /**
     * 新闻recycler适配器
     */
    class MyNewsAdapter extends RecyclerView.Adapter<MyNewsAdapter.ViewHolder> {

        private List<NewDetail.ResultEntity> datas = new ArrayList<>();
        private String content = "";

        public MyNewsAdapter(List<NewDetail.ResultEntity> datas) {
            this.datas = datas;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView newsTitle;
            ScaleImageView newsImage;
            TextView newsContent;

            public ViewHolder(View itemView) {
                super(itemView);
                newsTitle = ButterKnife.findById(itemView, R.id.news_title);
                newsImage = ButterKnife.findById(itemView, R.id.news_image);
                newsContent = ButterKnife.findById(itemView, R.id.news_content);

                itemView.setOnClickListener(v -> {
                    Log.d("NormalTextViewHolder", "onClick--> position = " + getAdapterPosition());
                        Intent intent = new Intent(getActivity(), NewsWebActivity.class);
                        intent.putExtra("url", datas.get(getAdapterPosition()).getUrl());
                        getActivity().startActivity(intent);
                });
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (!datas.get(position).getImg().equals("")) {

                holder.newsImage.setImageWidth(Integer.parseInt(datas.get(position).getImg_length()));
                holder.newsImage.setImageHeight(Integer.parseInt(datas.get(position).getImg_width()));
                Glide.with(getActivity())
                        .load(datas.get(position).getImg())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(Integer.parseInt(datas.get(position).getImg_length()), Integer.parseInt(datas.get(position).getImg_width()))
                        .placeholder(R.color.default_color)
                        .error(R.color.default_color)
                        .into(holder.newsImage);
            } else {
                holder.newsImage.setVisibility(View.GONE);
            }
            holder.newsTitle.setText(datas.get(position).getTitle());
            content = datas.get(position).getContent();
            content = content.replace("<em>", "");
            content = content.replace("</em>", "");
            holder.newsContent.setText(content);

//            holder.itemView.setClickable(true);

        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
