package delilah.inuappcenter.mapus.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import delilah.inuappcenter.mapus.model.BuildingModel;
import delilah.inuappcenter.mapus.model.EmployeeModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkInterface {

    @GET("dbRouter/building")
    Call<ArrayList<JsonObject>> getBuildingInfo ();

    @GET("dbRouter/employee")
    Call<ArrayList<EmployeeModel>> getEmployee ();
}
