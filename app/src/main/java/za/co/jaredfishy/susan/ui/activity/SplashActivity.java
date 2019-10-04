package za.co.jaredfishy.susan.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import za.co.jaredfishy.susan.R;
import za.co.jaredfishy.susan.domain.susan.SusanResponse;
import za.co.jaredfishy.susan.handler.status.ServiceStatusHandler;
import za.co.jaredfishy.susan.task.Callback;
import za.co.jaredfishy.susan.task.susan.SusanPokeTask;

public class SplashActivity extends AppCompatActivity {

    private TextView txtStatus;
    private Button btnOk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        txtStatus = findViewById(R.id.splash_txt_text);
        btnOk = findViewById(R.id.splash_button_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        txtStatus.setText("Connecting...");

        SusanPokeTask susanPokeTask = new SusanPokeTask() {

            @Override
            protected void onSuccess(SusanResponse response) {

                ServiceStatusHandler serverStatusHandler = ServiceStatusHandler.getInstance();
                serverStatusHandler.checkAvailableServices(new Callback<SusanResponse>() {
                    @Override
                    public void done(SusanResponse response) {
                        txtStatus.setText(response.getMessage());
                        btnOk.setVisibility(View.VISIBLE);

                    }
                });
            }

            @Override
            protected void onFail(String response) {
                txtStatus.setText(response);
                btnOk.setVisibility(View.VISIBLE);
            }
        };
        susanPokeTask.execute();
    }
}
