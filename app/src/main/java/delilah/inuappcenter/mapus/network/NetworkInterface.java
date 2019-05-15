package delilah.inuappcenter.mapus.network;

import java.util.ArrayList;

import delilah.inuappcenter.mapus.model.BuildingModel;
import delilah.inuappcenter.mapus.model.EmployeeModel;
import delilah.inuappcenter.mapus.model.FilterModel;
import delilah.inuappcenter.mapus.model.OfficeModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkInterface {

    @GET("dbRouter/building")
    Call<ArrayList<BuildingModel>> getBuildingInfo ();

    @GET("dbRouter/employee")
    Call<ArrayList<EmployeeModel>> getEmployeeInfo ();

    @GET("dbRouter/filter")
    Call<ArrayList<FilterModel>> getFilterInfo ();

    @GET("dbRouter/office")
    Call<ArrayList<OfficeModel>> getOfficeInfo ();
}
