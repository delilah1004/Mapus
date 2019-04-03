package delilah.inuappcenter.mapus;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import java.net.NetworkInterface;
import java.util.ArrayList;

import delilah.inuappcenter.mapus.model.BuildingModel;
import delilah.inuappcenter.mapus.model.EmployeeModel;
import delilah.inuappcenter.mapus.network.NetworkController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton btn_call, btn_mylocation, fab, fab1, fab2, fab3, fab4;
    private TextView search;
    private Button first_confirm;
    private MapView mapView;

    private MapReverseGeoCoder mReverseGeoCoder = null;
    private boolean isUsingCustomLocationMarker = false;

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Information();

//        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
//        Location userLocation = getMyLocation();
//        currentLongitude = userLocation.getLongitude();
//        currentLatitude = userLocation.getLatitude();

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

    private void Information(){
        final ArrayList<EmployeeModel> employeeList = new ArrayList<>();
        final Call<ArrayList<EmployeeModel>> employee = NetworkController.getInstance().getNetworkInterface().getEmployee();
        employee.enqueue(new Callback<ArrayList<EmployeeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<EmployeeModel>> call, Response<ArrayList<EmployeeModel>> response) {
                employeeList.addAll(response.body().toArray());
                Log.d("정보1", employeeList.get(0).title);
            }

            @Override
            public void onFailure(Call<ArrayList<EmployeeModel>> call, Throwable t) {

            }
        });

        Call<ArrayList<JsonObject>> building = NetworkController.getInstance().getNetworkInterface().getBuildingInfo();
        building.enqueue(new Callback<ArrayList<JsonObject>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                Log.d("정보2", response.body().toString());
            }

            @Override
            public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {

            }
        });
    }

    private Location getMyLocation() {
        Location currentLocation = null;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        else{
            String locationProvider = LocationManager.GPS_PROVIDER;
            currentLocation = locationManager.getLastKnownLocation(locationProvider);
        }
        return  currentLocation;
    }

    private void addMapView() {
        mapView = new MapView(this);

        //mapView.setDaumMapApiKey(getResources().getString(R.string.APP_KEY));
        mapView.setDaumMapApiKey(StaticData.APP_KEY);

        ViewGroup map = (ViewGroup) findViewById(R.id.map_view);
        map.addView(mapView);

        // 중심점 변경 + 줌 레벨 변경
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.375293, 126.632889), 1, true);
//        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(currentLatitude, currentLongitude), 1, true);

        setMapViewClickListener();
    }

    //마커 추가
    private void addMarker(){
        // MapPOIItem markerOne, makerTwo, markerThree, markerFour, markerFive, markerSix, markerSeven, markerEight, markerNine, markerTen, markerEleven, markerTwelve = new MapPOIItem();

        MapPOIItem markerOne = new MapPOIItem();
        // 마커 위치
        markerOne.setMapPoint(MapPoint.mapPointWithGeoCoord(37.376848, 126.634727));
        // 마커 이름
        markerOne.setItemName("1 대학본부");
        markerOne.setTag(1);
        // 커스텀 마커 모양.
        markerOne.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        // 마커 이미지.
        markerOne.setCustomImageResourceId(R.drawable.ic_marker);
        markerOne.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerOne);

        MapPOIItem markerTwo = new MapPOIItem();
        markerTwo.setMapPoint(MapPoint.mapPointWithGeoCoord(37.377443, 126.633858));
        markerTwo.setItemName("2 교수회관");
        markerTwo.setTag(2);
        markerTwo.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerTwo.setCustomImageResourceId(R.drawable.ic_marker);
        markerTwo.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerTwo);

        MapPOIItem markerThree = new MapPOIItem();
        markerThree.setMapPoint(MapPoint.mapPointWithGeoCoord(37.377148, 126.634203));
        markerThree.setItemName("3 홍보관");
        markerThree.setTag(3);
        markerThree.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerThree.setCustomImageResourceId(R.drawable.ic_marker);
        markerThree.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerThree);

        MapPOIItem markerFour = new MapPOIItem();
        markerFour.setMapPoint(MapPoint.mapPointWithGeoCoord(37.376390, 126.635593));
        markerFour.setItemName("4 BM 컨텐츠관");
        markerFour.setTag(4);
        markerFour.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerFour.setCustomImageResourceId(R.drawable.ic_marker);
        markerFour.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerFour);

        MapPOIItem markerFive = new MapPOIItem();
        markerFive.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375580, 126.634649));
        markerFive.setItemName("5 자연과학/생명과학기술대학");
        markerFive.setTag(5);
        markerFive.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerFive.setCustomImageResourceId(R.drawable.ic_marker);
        markerFive.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerFive);

        MapPOIItem markerSix = new MapPOIItem();
        markerSix.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375022, 126.634102));
        markerSix.setItemName("6 학산도서관");
        markerSix.setTag(6);
        markerSix.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerSix.setCustomImageResourceId(R.drawable.ic_marker);
        markerSix.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerSix);

        MapPOIItem markerSeven = new MapPOIItem();
        markerSeven.setMapPoint(MapPoint.mapPointWithGeoCoord(37.374591, 126.633828));
        markerSeven.setItemName("7 정보기술대학");
        markerSeven.setTag(7);
        markerSeven.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerSeven.setCustomImageResourceId(R.drawable.ic_marker);
        markerSeven.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerSeven);

        MapPOIItem markerEight = new MapPOIItem();
        markerEight.setMapPoint(MapPoint.mapPointWithGeoCoord(37.373585, 126.632756));
        markerEight.setItemName("8 공과/도시과학대학");
        markerEight.setTag(8);
        markerEight.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerEight.setCustomImageResourceId(R.drawable.ic_marker);
        markerEight.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerEight);

        MapPOIItem markerNine = new MapPOIItem();
        markerNine.setMapPoint(MapPoint.mapPointWithGeoCoord(37.372651, 126.633427));
        markerNine.setItemName("9 공동실험실습관");
        markerNine.setTag(9);
        markerNine.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerNine.setCustomImageResourceId(R.drawable.ic_marker);
        markerNine.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerNine);

        MapPOIItem markerTen = new MapPOIItem();
        markerTen.setMapPoint(MapPoint.mapPointWithGeoCoord(37.372762, 126.631780));
        markerTen.setItemName("10 게스트하우스");
        markerTen.setTag(10);
        markerTen.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerTen.setCustomImageResourceId(R.drawable.ic_marker);
        markerTen.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerTen);

        MapPOIItem markerEleven = new MapPOIItem();
        markerEleven.setMapPoint(MapPoint.mapPointWithGeoCoord(37.374476, 126.631785));
        markerEleven.setItemName("11 복지회관");
        markerEleven.setTag(11);
        markerEleven.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerEleven.setCustomImageResourceId(R.drawable.ic_marker);
        markerEleven.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerEleven);

        MapPOIItem markerTwelve = new MapPOIItem();
        markerTwelve.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375303, 126.632563));
        markerTwelve.setItemName("12 컨벤션센터");
        markerTwelve.setTag(12);
        markerTwelve.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerTwelve.setCustomImageResourceId(R.drawable.ic_marker);
        markerTwelve.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerTwelve);

        MapPOIItem markerThirteen = new MapPOIItem();
        markerThirteen.setMapPoint(MapPoint.mapPointWithGeoCoord(37.376121, 126.633330));
        markerThirteen.setItemName("13 글로벌법정경대학");
        markerThirteen.setTag(13);
        markerThirteen.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerThirteen.setCustomImageResourceId(R.drawable.ic_marker);
        markerThirteen.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerThirteen);

        MapPOIItem markerFourteen = new MapPOIItem();
        markerFourteen.setMapPoint(MapPoint.mapPointWithGeoCoord(37.376435, 126.632810));
        markerFourteen.setItemName("14 동북아국제통상/경영대학");
        markerFourteen.setTag(14);
        markerFourteen.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerFourteen.setCustomImageResourceId(R.drawable.ic_marker);
        markerFourteen.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerFourteen);

        MapPOIItem markerFifteen = new MapPOIItem();
        markerFifteen.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375651, 126.631997));
        markerFifteen.setItemName("15 인문대학");
        markerFifteen.setTag(15);
        markerFifteen.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerFifteen.setCustomImageResourceId(R.drawable.ic_marker);
        markerFifteen.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerFifteen);

        MapPOIItem markerSixteen = new MapPOIItem();
        markerSixteen.setMapPoint(MapPoint.mapPointWithGeoCoord(37.374732, 126.631260));
        markerSixteen.setItemName("16 예술체육대학");
        markerSixteen.setTag(16);
        markerSixteen.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerSixteen.setCustomImageResourceId(R.drawable.ic_marker);
        markerSixteen.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerSixteen);

        MapPOIItem markerSeventeen = new MapPOIItem();
        markerSeventeen.setMapPoint(MapPoint.mapPointWithGeoCoord(37.374213, 126.630748));
        markerSeventeen.setItemName("17 학생회관");
        markerSeventeen.setTag(17);
        markerSeventeen.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerSeventeen.setCustomImageResourceId(R.drawable.ic_marker);
        markerSeventeen.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerSeventeen);

        MapPOIItem markerEighteen = new MapPOIItem();
        markerEighteen.setMapPoint(MapPoint.mapPointWithGeoCoord(37.373849, 126.629789));
        markerEighteen.setItemName("18 생활원");
        markerEighteen.setTag(18);
        markerEighteen.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerEighteen.setCustomImageResourceId(R.drawable.ic_marker);
        markerEighteen.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerEighteen);

        MapPOIItem markerNineteen = new MapPOIItem();
        markerNineteen.setMapPoint(MapPoint.mapPointWithGeoCoord(37.374586, 126.630250));
        markerNineteen.setItemName("19 국제교류관");
        markerNineteen.setTag(19);
        markerNineteen.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerNineteen.setCustomImageResourceId(R.drawable.ic_marker);
        markerNineteen.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerNineteen);

        MapPOIItem markerTwenty = new MapPOIItem();
        markerTwenty.setMapPoint(MapPoint.mapPointWithGeoCoord(37.374893, 126.629708));
        markerTwenty.setItemName("20 스포츠센터");
        markerTwenty.setTag(20);
        markerTwenty.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerTwenty.setCustomImageResourceId(R.drawable.ic_marker);
        markerTwenty.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerTwenty);

        MapPOIItem markerTwentyOne = new MapPOIItem();
        markerTwentyOne.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375520, 126.630261));
        markerTwentyOne.setItemName("21 체육관");
        markerTwentyOne.setTag(21);
        markerTwentyOne.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerTwentyOne.setCustomImageResourceId(R.drawable.ic_marker);
        markerTwentyOne.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerTwentyOne);

        MapPOIItem markerTwentyTwo = new MapPOIItem();
        markerTwentyTwo.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375870, 126.630819));
        markerTwentyTwo.setItemName("22 학군단");
        markerTwentyTwo.setTag(22);
        markerTwentyTwo.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerTwentyTwo.setCustomImageResourceId(R.drawable.ic_marker);
        markerTwentyTwo.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerTwentyTwo);

        MapPOIItem markerTwentyThree = new MapPOIItem();
        markerTwentyThree.setMapPoint(MapPoint.mapPointWithGeoCoord(37.377856, 126.632536));
        markerTwentyThree.setItemName("23 강당 및 공연장");
        markerTwentyThree.setTag(23);
        markerTwentyThree.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerTwentyThree.setCustomImageResourceId(R.drawable.ic_marker);
        markerTwentyThree.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerTwentyThree);

        MapPOIItem markerTwentyFour = new MapPOIItem();
        markerTwentyFour.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375912, 126.635805));
        markerTwentyFour.setItemName("24 전망타워");
        markerTwentyFour.setTag(24);
        markerTwentyFour.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerTwentyFour.setCustomImageResourceId(R.drawable.ic_marker);
        markerTwentyFour.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerTwentyFour);

        MapPOIItem markerTwentyFive = new MapPOIItem();
        markerTwentyFive.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375226, 126.636014));
        markerTwentyFive.setItemName("25 어린이집");
        markerTwentyFive.setTag(25);
        markerTwentyFive.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerTwentyFive.setCustomImageResourceId(R.drawable.ic_marker);
        markerTwentyFive.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerTwentyFive);

        MapPOIItem markerTwentySix = new MapPOIItem();
        markerTwentySix.setMapPoint(MapPoint.mapPointWithGeoCoord(37.375205, 126.635574));
        markerTwentySix.setItemName("26 온실");
        markerTwentySix.setTag(26);
        markerTwentySix.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerTwentySix.setCustomImageResourceId(R.drawable.ic_marker);
        markerTwentySix.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerTwentySix);

        MapPOIItem markerTwentySeven = new MapPOIItem();
        markerTwentySeven.setMapPoint(MapPoint.mapPointWithGeoCoord(37.372260, 126.633069));
        markerTwentySeven.setItemName("27 제2공동실험실습관");
        markerTwentySeven.setTag(27);
        markerTwentySeven.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerTwentySeven.setCustomImageResourceId(R.drawable.ic_marker);
        markerTwentySeven.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerTwentySeven);

        MapPOIItem markerTwentyEight = new MapPOIItem();
        markerTwentyEight.setMapPoint(MapPoint.mapPointWithGeoCoord(37.371896, 126.632616));
        markerTwentyEight.setItemName("28 도시과학대학");
        markerTwentyEight.setTag(28);
        markerTwentyEight.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerTwentyEight.setCustomImageResourceId(R.drawable.ic_marker);
        markerTwentyEight.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerTwentyEight);

        MapPOIItem markerTwentyNine = new MapPOIItem();
        markerTwentyNine.setMapPoint(MapPoint.mapPointWithGeoCoord(37.372468, 126.631209));
        markerTwentyNine.setItemName("29 생명공학부");
        markerTwentyNine.setTag(29);
        markerTwentyNine.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerTwentyNine.setCustomImageResourceId(R.drawable.ic_marker);
        markerTwentyNine.setCustomImageAutoscale(false);
        mapView.addPOIItem(markerTwentyNine);

        MapPOIItem markerThirty = new MapPOIItem();
        markerThirty.setMapPoint(MapPoint.mapPointWithGeoCoord(37.374367, 126.629185));
        markerThirty.setItemName("30 제2기숙사");
        markerThirty.setTag(30);
        markerThirty.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        markerThirty.setCustomImageResourceId(R.drawable.ic_marker);
        markerThirty.setCustomImageAutoscale(false);
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
        if (isUsingCustomLocationMarker) {
            mapView.setCurrentLocationRadius(0);
            mapView.setDefaultCurrentLocationMarker();
        }
        else {
            mapView.setCurrentLocationRadius(100);
            mapView.setCurrentLocationRadiusFillColor(R.color.light);

            MapPOIItem.ImageOffset trackingImageAnchorPointOffset = new MapPOIItem.ImageOffset(16, 16); // 좌하단(0,0) 기준 앵커포인트 오프셋
            MapPOIItem.ImageOffset offImageAnchorPointOffset = new MapPOIItem.ImageOffset(15, 15);

            mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);

            mapView.getCurrentLocationTrackingMode();
            mapView.setShowCurrentLocationMarker(true);
            mapView.isShowingCurrentLocationMarker();

            mapView.setCustomCurrentLocationMarkerTrackingImage(R.drawable.ic_mylocation, trackingImageAnchorPointOffset);
            mapView.setCustomCurrentLocationMarkerImage(R.drawable.ic_mylocation, offImageAnchorPointOffset);
        }
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

}
