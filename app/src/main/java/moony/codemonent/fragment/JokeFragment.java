package moony.codemonent.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import moony.codemonent.R;
import moony.codemonent.adapter.MyRecyclerAdapter;
import moony.codemonent.api.CodeMonentClient;
import moony.codemonent.model.Joke;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * @author: MOONY
 * @data: 15/11/27
 * @Description: ${todo}<笑话frag>
 */
public class JokeFragment extends Fragment {
    @Bind(R.id.joke_list)
    RecyclerView jokeList;
    private MyRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke, container, false);
        ButterKnife.bind(this, view);
        initView();
        getJoke(1);
        return view;
    }


    public void initView() {
        jokeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyRecyclerAdapter(getActivity());
        jokeList.setAdapter(adapter);
    }

    public void getJoke(int page) {

        long uxtime = System.currentTimeMillis() / 1000;

        Call<Joke> call = CodeMonentClient.getJokeApi().getJokeData(page, 20, "desc", uxtime);
        call.enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Response<Joke> response, Retrofit retrofit) {
                Log.i("onResponse", new Gson().toJson(response));
                if (response.isSuccess()) {
                    if (response.body().getError_code() == 0) {
                        List<Joke.ResultEntity.DataEntity> dataEntities;
                        dataEntities = response.body().getResult().getData();
                        adapter.update(dataEntities);
                    }
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("joke-onFailure", t.toString());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
