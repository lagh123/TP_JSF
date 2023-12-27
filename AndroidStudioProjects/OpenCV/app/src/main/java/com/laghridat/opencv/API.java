package com.laghridat.opencv;

import com.laghridat.opencv.entities.Student;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {
    @POST("login")
    Call<ResponseBody> checkUser (
            @Body Student st
    );
}
