package moony.codemonent.api;

import moony.codemonent.model.Joke;
import moony.codemonent.model.NewDetail;
import moony.codemonent.model.News;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * @author: MOONY
 * @data: 15/11/27
 * @Description: ${todo}<API接口连接>
 */
public class CodeMonentClient {
    private static String NEWS_URL = "http://op.juhe.cn";
    private static String JOKE_URL = "http://japi.juhe.cn";
    private static NewsInterface newsInterface;
    private static JokeInterface jokeInterface;

    public static NewsInterface getNewApi() {
        if (newsInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(NEWS_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            newsInterface = retrofit.create(NewsInterface.class);
        }
        return newsInterface;
    }

    public static JokeInterface getJokeApi() {
        if (jokeInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(JOKE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            jokeInterface = retrofit.create(JokeInterface.class);
        }
        return jokeInterface;
    }


    public interface NewsInterface {
        //新闻列表
        @GET("/onebox/news/words?key=31d64a46790cc4797c1856543e65ebb1")
        Call<News> getNewList();

        //新闻详情
        @FormUrlEncoded
        @POST("/onebox/news/query")
        Call<NewDetail> getNewDetail(@Field("q") String word, @Field("key") String key, @Field("dtype") String dtype);
    }

    public interface JokeInterface {
        @GET("/joke/content/list.from?key=43aa7b04b328fb42adf910ff56ba78e5")
        Call<Joke> getJokeData(
                @Query("page") int page,
                @Query("pagesize") int pagesize,
                @Query("sort") String sort,
                @Query("time") long uxtime);
    }

}
