package app.altamira.com.minitwitter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import app.altamira.com.minitwitter.R;
import app.altamira.com.minitwitter.common.Constantes;
import app.altamira.com.minitwitter.common.GPSTracker;
import app.altamira.com.minitwitter.common.GeneraMD5;
import app.altamira.com.minitwitter.common.SharedPreferencesManager;
import app.altamira.com.minitwitter.retrofit.AltamiraClient;
import app.altamira.com.minitwitter.retrofit.AltamiraService;
import app.altamira.com.minitwitter.retrofit.MiniTwitterClient;
import app.altamira.com.minitwitter.retrofit.MiniTwitterService;
import app.altamira.com.minitwitter.retrofit.request.RequestAltamiraLogin;
import app.altamira.com.minitwitter.retrofit.response.ResponseAltamiraLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    TextView tvGoSignUp;
    EditText etEmail, etPassword;

    AltamiraClient altamiraClient;
    AltamiraService altamiraService;

    String latitud = "", longitud = "";

    private GPSTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        retrofitInit();
        findViews();
        events();
        //fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        //localizacion();
        //gpsTracker = new GPSTracker(this);
        //longitud = Double.toString(gpsTracker.getLongitude());
        //latitud = Double.toString(gpsTracker.getLatitude());
        //getLongitude();
    }

    private void retrofitInit() {
        altamiraClient = AltamiraClient.getInstance();
        altamiraService = altamiraClient.getAltamiraService();
    }

    private void findViews() {
        btnLogin = findViewById(R.id.buttonIngresarL);
        tvGoSignUp = findViewById(R.id.textViewRegistro);
        etEmail = findViewById(R.id.editTextUsuarioL);
        etPassword = findViewById(R.id.editTextPasswordL);
    }

    private void events() {
        btnLogin.setOnClickListener(this);
        tvGoSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.buttonIngresarL:
                goToLoginAltamira();
                break;
            case R.id.textViewRegistro:
                goToSignUp();
                break;
        }

    }

    private void goToLoginAltamira() {
        GeneraMD5 gMd5 = new GeneraMD5();
        String email = etEmail.getText().toString();
        String password = gMd5.getMD5(etPassword.getText().toString());
        //Log.e("password", password);
        if (email.isEmpty()) {
            etEmail.setError("El email es requerido");
        } else if (password.isEmpty()) {
            etPassword.setError("La contraseña es requerida");
        } else {
            RequestAltamiraLogin requestAltamiraLogin = new RequestAltamiraLogin(email, password,latitud,longitud);

            Call<ResponseAltamiraLogin> call = altamiraService.doLoginAltamira(Constantes.API_KEY, "getUsuario", requestAltamiraLogin);

           // Log.e("Altamira ca 1", String.valueOf(call));
            call.enqueue(new Callback<ResponseAltamiraLogin>() {
                @Override
                public void onResponse(Call<ResponseAltamiraLogin> call, Response<ResponseAltamiraLogin> response) {
                    if (response.isSuccessful()) {
                        SharedPreferencesManager.setSomeStringValue(Constantes.PREF_USER, response.body().getUsuario());
                        SharedPreferencesManager.setSomeStringValue(Constantes.PREF_USERNAME, response.body().getNombre());
                        String vUsuario = response.body().getUsuario();

                        //Log.e("Altamira", vUsuario);

                        if (vUsuario.equals("null")) {
                            Toast.makeText(MainActivity.this, "Usuario no Existe", Toast.LENGTH_SHORT).show();
                        } else {
                            //Toast.makeText(MainActivity.this, "Sesión iniciada correctamente ALTAMIRA: " + SharedPreferencesManager.getSomeStringValue(Constantes.PREF_USERNAME), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, EscanerActivity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Algo fue mal, revise sus datos de acceso ALTAMIRA", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseAltamiraLogin> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Problemas de conexión. Inténtelo de nuevo ALTAMIRA", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void goToSignUp() {
        Intent i = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(i);
        finish();
    }


}