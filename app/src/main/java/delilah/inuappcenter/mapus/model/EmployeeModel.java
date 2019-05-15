package delilah.inuappcenter.mapus.model;

import com.google.gson.annotations.SerializedName;

public class EmployeeModel {
    @SerializedName("id") // 숫자
    public int id;

    @SerializedName("detailOrgan") // 단과대학 이름 - ex.인문대학
    public String detailOrgan;

    @SerializedName("position") // 직위 - ex.총장
    public String position;

    @SerializedName("name") // 사람 이름
    public String name;

    @SerializedName("telephone") // 전화번호
    public String telephone;

    @SerializedName("officeId") // null
    public String officeId;
}
