package com.example.trending;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("/repo")
    Observable<List<ListBean>> getList(@Query("lang") String language,
                                       @Query("since") String since);
}
