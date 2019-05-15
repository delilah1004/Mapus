package delilah.inuappcenter.mapus.model;

import com.google.gson.annotations.SerializedName;

public class FilterModel {
    @SerializedName("id") // 숫자
    public int id;

    @SerializedName("title") // 필터 이름 - ex.카페,식당 등
    public String title;

    @SerializedName("marker")
    public String marker;
}
