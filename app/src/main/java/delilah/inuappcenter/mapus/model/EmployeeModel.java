package delilah.inuappcenter.mapus.model;

import com.google.gson.annotations.SerializedName;

public class EmployeeModel {
    @SerializedName("id")
    public int id;

    @SerializedName("detailOrgan")
    public String detailOrgan;

    @SerializedName("position")
    public String position;

    @SerializedName("name")
    public String name;

    @SerializedName("telephone")
    public String telephone;

    @SerializedName("officeId")
    public String officeId;
}
