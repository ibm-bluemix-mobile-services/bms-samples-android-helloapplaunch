package engage.com.pizzadelivery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.engage.EngageFailResponse;
import com.engage.EngageResponse;
import com.engage.EngageResponseListener;
import com.engage.api.EngageClient;
import com.engage.api.EngageConfig;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.BMSClient;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        inializeEngage();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(HomeActivity.this,PizzaDetailsActivity.class);
                startActivity(intent);
            }
        });
    }


    private void inializeEngage(){
        EngageConfig engageConfig = new EngageConfig(getApplication(), BMSClient.REGION_US_SOUTH,"e63d4d97-5ce6-459e-b0fc-f5fa032e07e6","4a17f904-8e87-4d92-b7a0-342974955710","norton");
        EngageClient.getInstance().initialize(engageConfig, new EngageResponseListener() {
            @Override
            public void onSuccess(EngageResponse engageResponse) {
                Log.d("HomeActivity","Init Successful - "+engageResponse.getResponseText());
            }

            @Override
            public void onFailure(EngageFailResponse engageFailResponse) {
                Log.d("HomeActivity","Init Failed - "+engageFailResponse.getErrorMsg());
            }
        });
    }
}
