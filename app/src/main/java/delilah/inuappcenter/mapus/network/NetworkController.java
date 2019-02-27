package delilah.inuappcenter.mapus.network;

import android.app.Application;

import delilah.inuappcenter.mapus.StaticData;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkController extends Application {
    public static NetworkController networkController;
    public String mainURL = StaticData.mainURL;
    public static Retrofit retrofitBuilder;
    public static NetworkInterface networkInterface;

    public static NetworkController getInstance(){
        return networkController;
    }

    public void retrofitBuild() {
        this.retrofitBuilder = new Retrofit.Builder()
                .baseUrl(mainURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public NetworkInterface getNetworkInterface(){
        retrofitBuild();
        retrofitBuilder.create(NetworkInterface.class);
        return networkInterface;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        networkController = this;
        getNetworkInterface();
    }
}
