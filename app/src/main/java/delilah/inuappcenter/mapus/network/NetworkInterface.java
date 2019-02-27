package delilah.inuappcenter.mapus.network;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkInterface {

    @GET("getData/test")
    Call<ArrayList<JsonObject>> getDelailah (@Query("name") String name, @Query("age") int age);
}
