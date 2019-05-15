package delilah.inuappcenter.mapus.model;

import com.google.gson.annotations.SerializedName;

public class OfficeModel {
    @SerializedName("id") // 숫자
    public String id;

    @SerializedName("title") // 사무실 이름 - ex.종합상황실
    public String title;

    @SerializedName("roomId") // 사무실 호수 - ex.B101,101 등등
   public String roomId;

    @SerializedName("buildingId") // 숫자 - 호관 번호
    public String buildingId;

    @SerializedName("filterId") // 숫자 - 0
    public String filterId;

    @SerializedName("isMain") // null
    public String isMain;
}
