package delilah.inuappcenter.mapus;

public class SearchListInfo {
    public String buildingTitle, id;
    public int officeFloor;

    public SearchListInfo(String buildingTitle, String number){
        this.buildingTitle = buildingTitle;
        this.id = number;
    }
}
