package applaunch.com.pizzadelivery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.applaunch.AppLaunchFailResponse;
import com.applaunch.AppLaunchResponse;
import com.applaunch.AppLaunchResponseListener;
import com.applaunch.api.AppLaunch;
import com.applaunch.api.AppLaunchActions;
import com.applaunch.api.AppLaunchParameters;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.BMSClient;

public class HomeActivity extends AppCompatActivity implements AppLaunchActions {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.root_layout);
        // showSnackBar("This is super cool i think", linearLayout);
        inializeAppLaunch();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(HomeActivity.this,PizzaDetailsActivity.class);
                startActivity(intent);
            }
        });
    }



    private void inializeAppLaunch(){
        //replace the appguid and client-secret with your values
        AppLaunch.getInstance().initApp(getApplication(), BMSClient.REGION_US_SOUTH,"<appguid>","<client-secret>");
        AppLaunchParameters appLaunchParameters = new AppLaunchParameters();
        appLaunchParameters.put("key","value");
        appLaunchParameters.put("something",false);
        appLaunchParameters.put("another",1);

        //provide the user name to register with
        AppLaunch.getInstance().registerUser("<username>", appLaunchParameters,new AppLaunchResponseListener() {
            @Override
            public void onSuccess(AppLaunchResponse appLaunchResponse) {
                Log.d("HomeActivity","Init Successful - "+ appLaunchResponse.getResponseText());
                AppLaunch.getInstance().getActions(HomeActivity.this);
            }

            @Override
            public void onFailure(AppLaunchFailResponse appLaunchFailResponse) {
                Log.d("HomeActivity","Init Failed - "+ appLaunchFailResponse.getErrorMsg());
            }
        });
    }

    @Override
    public void onFeaturesReceived(String features) {

    }
}
