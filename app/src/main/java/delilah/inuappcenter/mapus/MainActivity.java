package delilah.inuappcenter.mapus;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

import delilah.inuappcenter.mapus.network.NetworkController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton btn_call, btn_mylocation, fab, fab1, fab2, fab3, fab4;
    private EditText search;
    private Button first_confirm;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckFirstExecute();

        addMapView();

        addMarker();

        first_confirm = findViewById(R.id.first_confirm);

        search = findViewById(R.id.search_bar_edit);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        btn_mylocation = findViewById(R.id.btn_mylocation);
        btn_call = findViewById(R.id.btn_call);
        fab = findViewById(R.id.btn_filter);
        fab1 = findViewById(R.id.btn_conv);
        fab2 = findViewById(R.id.btn_rest);
        fab3 = findViewById(R.id.btn_cafe);
        fab4 = findViewById(R.id.btn_retire);

        clickListenerSetting();
    }

    private void addMapView() {
        mapView = new MapView(this);

        //mapView.setDaumMapApiKey(getResources().getString(R.string.APP_KEY));
        mapView.setDaumMapApiKey(StaticData.APP_KEY);

        ViewGroup map = (ViewGroup) findViewById(R.id.map_view);
        map.addView(mapView);

        // 중심점 변경 + 줌 레벨 변경
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.375293, 126.632889), 1, true);

        setMapViewClickListener();
    }


    private void addMarker(){
        // MapPOIItem markerOne, makerTwo, markerThree, markerFour, markerFive, markerSix, markerSeven, markerEight, markerNine, markerTen, markerEleven, markerTwelve = new MapPOIItem();

        MapPOIItem markerOne = new MapPOIItem();
        // 마커 위치
        markerOne.setMapPoint(MapPoint.mapPointWithGeoCoord(37.376848, 126.634727));
        // 마커 이름
        markerOne.setItemName("1 대학본부");
        markerOne.setTag(1);
        // 기본으로 제공하는 BluePin 마커 모양.
        markerOne.setMarkerType(MapPOIItem.MarkerType.BluePin);
        // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        markerOne.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerOne);

        MapPOIItem markerTwo = new MapPOIItem();
        markerTwo.setMapPoint(MapPoint.mapPointWithGeoCoord(37.377443, 126.633858));
        markerTwo.setItemName("2 교수회관");
        markerTwo.setTag(2);
        markerTwo.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerTwo.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerTwo);

        MapPOIItem markerThree = new MapPOIItem();
        markerThree.setMapPoint(MapPoint.mapPointWithGeoCoord(37.377148, 126.634203));
        markerThree.setItemName("3 홍보관");
        markerThree.setTag(3);
        markerThree.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerThree.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerThree);

        MapPOIItem markerFour = new MapPOIItem();
        markerFour.setMapPoint(MapPoint.mapPointWithGeoCoord(37.376390, 126.635593));
        markerFour.setItemName("4 BM 컨텐츠관");
        markerFour.setTag(4);
        markerFour.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerFour.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerFour);

        MapPOIItem markerFive = new MapPOIItem();
        markerFive.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375580, 126.634649));
        markerFive.setItemName("5 자연과학/생명과학기술대학");
        markerFive.setTag(5);
        markerFive.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerFive.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerFive);

        MapPOIItem markerSix = new MapPOIItem();
        markerSix.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375022, 126.634102));
        markerSix.setItemName("6 학산도서관");
        markerSix.setTag(6);
        markerSix.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerSix.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerSix);

        MapPOIItem markerSeven = new MapPOIItem();
        markerSeven.setMapPoint(MapPoint.mapPointWithGeoCoord(37.374591, 126.633828));
        markerSeven.setItemName("7 정보기술대학");
        markerSeven.setTag(7);
        markerSeven.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerSeven.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerSeven);

        MapPOIItem markerEight = new MapPOIItem();
        markerEight.setMapPoint(MapPoint.mapPointWithGeoCoord(37.373585, 126.632756));
        markerEight.setItemName("8 공과/도시과학대학");
        markerEight.setTag(8);
        markerEight.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerEight.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerEight);

        MapPOIItem markerNine = new MapPOIItem();
        markerNine.setMapPoint(MapPoint.mapPointWithGeoCoord(37.372651, 126.633427));
        markerNine.setItemName("9 공동실험실습관");
        markerNine.setTag(9);
        markerNine.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerNine.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerNine);

        MapPOIItem markerTen = new MapPOIItem();
        markerTen.setMapPoint(MapPoint.mapPointWithGeoCoord(37.372762, 126.631780));
        markerTen.setItemName("10 게스트하우스");
        markerTen.setTag(10);
        markerTen.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerTen.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerTen);

        MapPOIItem markerEleven = new MapPOIItem();
        markerEleven.setMapPoint(MapPoint.mapPointWithGeoCoord(37.374476, 126.631785));
        markerEleven.setItemName("11 복지회관");
        markerEleven.setTag(11);
        markerEleven.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerEleven.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerEleven);

        MapPOIItem markerTwelve = new MapPOIItem();
        markerTwelve.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375303, 126.632563));
        markerTwelve.setItemName("12 컨벤션센터");
        markerTwelve.setTag(12);
        markerTwelve.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerTwelve.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerTwelve);

        MapPOIItem markerThirteen = new MapPOIItem();
        markerThirteen.setMapPoint(MapPoint.mapPointWithGeoCoord(37.376121, 126.633330));
        markerThirteen.setItemName("13 글로벌법정경대학");
        markerThirteen.setTag(13);
        markerThirteen.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerThirteen.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerThirteen);

        MapPOIItem markerFourteen = new MapPOIItem();
        markerFourteen.setMapPoint(MapPoint.mapPointWithGeoCoord(37.376776, 126.633016));
        markerFourteen.setItemName("14 동북아국제통상/경영대학");
        markerFourteen.setTag(14);
        markerFourteen.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerFourteen.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerFourteen);

        MapPOIItem markerFifteen = new MapPOIItem();
        markerFifteen.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375651, 126.631997));
        markerFifteen.setItemName("15 인문대학");
        markerFifteen.setTag(15);
        markerFifteen.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerFifteen.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerFifteen);

        MapPOIItem markerSixteen = new MapPOIItem();
        markerSixteen.setMapPoint(MapPoint.mapPointWithGeoCoord(37.376121, 126.633330));
        markerSixteen.setItemName("16 예술체육대학");
        markerSixteen.setTag(16);
        markerSixteen.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerSixteen.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerSixteen);

        MapPOIItem markerSeventeen = new MapPOIItem();
        markerSeventeen.setMapPoint(MapPoint.mapPointWithGeoCoord(37.376121, 126.633330));
        markerSeventeen.setItemName("17 학생회관");
        markerSeventeen.setTag(17);
        markerSeventeen.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerSeventeen.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerSeventeen);

        MapPOIItem markerEighteen = new MapPOIItem();
        markerEighteen.setMapPoint(MapPoint.mapPointWithGeoCoord(37.373849, 126.629789));
        markerEighteen.setItemName("18 생활원");
        markerEighteen.setTag(18);
        markerEighteen.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerEighteen.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerEighteen);

        MapPOIItem markerNineteen = new MapPOIItem();
        markerNineteen.setMapPoint(MapPoint.mapPointWithGeoCoord(37.374586, 126.630250));
        markerNineteen.setItemName("19 국제교류관");
        markerNineteen.setTag(19);
        markerNineteen.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerNineteen.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerNineteen);

        MapPOIItem markerTwenty = new MapPOIItem();
        markerTwenty.setMapPoint(MapPoint.mapPointWithGeoCoord(37.374893, 126.629708));
        markerTwenty.setItemName("20 스포츠센터");
        markerTwenty.setTag(20);
        markerTwenty.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerTwenty.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerTwenty);

        MapPOIItem markerTwentyOne = new MapPOIItem();
        markerTwentyOne.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375520, 126.630261));
        markerTwentyOne.setItemName("21 체육관");
        markerTwentyOne.setTag(21);
        markerTwentyOne.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerTwentyOne.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerTwentyOne);

        MapPOIItem markerTwentyTwo = new MapPOIItem();
        markerTwentyTwo.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375870, 126.630819));
        markerTwentyTwo.setItemName("22 학군단");
        markerTwentyTwo.setTag(22);
        markerTwentyTwo.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerTwentyTwo.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerTwentyTwo);

        MapPOIItem markerTwentyThree = new MapPOIItem();
        markerTwentyThree.setMapPoint(MapPoint.mapPointWithGeoCoord(37.377856, 126.632536));
        markerTwentyThree.setItemName("23 강당 및 공연장");
        markerTwentyThree.setTag(23);
        markerTwentyThree.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerTwentyThree.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerTwentyThree);

        MapPOIItem markerTwentyFour = new MapPOIItem();
        markerTwentyFour.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375912, 126.635805));
        markerTwentyFour.setItemName("24 전망타워");
        markerTwentyFour.setTag(24);
        markerTwentyFour.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerTwentyFour.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerTwentyFour);

        MapPOIItem markerTwentyFive = new MapPOIItem();
        markerTwentyFive.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375226, 126.636014));
        markerTwentyFive.setItemName("25 어린이집");
        markerTwentyFive.setTag(25);
        markerTwentyFive.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerTwentyFive.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerTwentyFive);

        MapPOIItem markerTwentySix = new MapPOIItem();
        markerTwentySix.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375205, 126.635574));
        markerTwentySix.setItemName("26 온실");
        markerTwentySix.setTag(26);
        markerTwentySix.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerTwentySix.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerTwentySix);

        MapPOIItem markerTwentySeven = new MapPOIItem();
        markerTwentySeven.setMapPoint(MapPoint.mapPointWithGeoCoord(37.371958, 126.633177));
        markerTwentySeven.setItemName("27 제2공동실험실습관");
        markerTwentySeven.setTag(27);
        markerTwentySeven.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerTwentySeven.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerTwentySeven);

        MapPOIItem markerTwentyEight = new MapPOIItem();
        markerTwentyEight.setMapPoint(MapPoint.mapPointWithGeoCoord(37.371758, 126.633016));
        markerTwentyEight.setItemName("28 도시과학대학");
        markerTwentyEight.setTag(28);
        markerTwentyEight.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerTwentyEight.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerTwentyEight);

        MapPOIItem markerTwentyNine = new MapPOIItem();
        markerTwentyNine.setMapPoint(MapPoint.mapPointWithGeoCoord(37.371758, 126.633016));
        markerTwentyNine.setItemName("29 생명공학부");
        markerTwentyNine.setTag(29);
        markerTwentyNine.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerTwentyNine.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerTwentyNine);

        MapPOIItem markerThirty = new MapPOIItem();
        markerThirty.setMapPoint(MapPoint.mapPointWithGeoCoord(37.374367, 126.629185));
        markerThirty.setItemName("30 제2기숙사");
        markerThirty.setTag(30);
        markerThirty.setMarkerType(MapPOIItem.MarkerType.BluePin);
        markerThirty.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(markerThirty);
    }

    //앱최초실행확인 (true - 최초실행)
    public boolean CheckFirstExecute() {
        SharedPreferences execute = getSharedPreferences("IsFirst", Activity.MODE_PRIVATE);
        boolean isFirst = execute.getBoolean("isFirst", false);
        if (!isFirst) { //최초 실행시 true 저장
            SharedPreferences.Editor editor = execute.edit();
            editor.putBoolean("isFirst", true);
            editor.commit();
            Intent intent = new Intent(MainActivity.this, FirstActivity.class);
            startActivity(intent);
        }
        return !isFirst;
    }

    private void clickListenerSetting() {

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhoneBookActivity.class);
                startActivity(intent);
            }
        });

        btn_mylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentLocation();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabAnimation();
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabAnimation();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabAnimation();
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabAnimation();
            }
        });

        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabAnimation();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        first_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhoneBookActivity.class);
                startActivity(intent);
            }
        });

        btn_mylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void fabAnimation() {

        if (isFabOpen) {
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);
            fab4.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            fab4.setClickable(false);
            isFabOpen = false;
        } else {
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            fab4.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            fab4.setClickable(true);
            isFabOpen = true;
        }
    }

    private void setCurrentLocation() {
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
    }

    private void setMapViewClickListener() {
        mapView.setMapViewEventListener(new MapView.MapViewEventListener() {
            @Override
            public void onMapViewInitialized(MapView mapView) {

            }

            @Override
            public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

            }

            @Override
            public void onMapViewZoomLevelChanged(MapView mapView, int i) {

            }

            @Override
            public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

            }

            @Override
            public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

            }

            @Override
            public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

            }

            @Override
            public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

            }

            @Override
            public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

            }

            @Override
            public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

            }
        });

        mapView.setPOIItemEventListener(new MapView.POIItemEventListener() {
            @Override
            public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

            }

            @Override
            public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

            }

            @Override
            public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

            }

            @Override
            public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

            }
        });
    }

    private void callInformation() {
        Call<ArrayList<JsonObject>> call = NetworkController.getInstance().getNetworkInterface().getDelailah("이다은", 24);
        call.enqueue(new Callback<ArrayList<JsonObject>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
            }

            @Override
            public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {

            }
        });
    }
}
