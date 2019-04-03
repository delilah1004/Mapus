package delilah.inuappcenter.mapus.model;

import com.google.gson.annotations.SerializedName;

public class BuildingModel {
    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("lat")
    public double lat;

    @SerializedName("log")
    public double log;
}
