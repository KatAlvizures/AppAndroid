package app.altamira.com.minitwitter.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import app.altamira.com.minitwitter.R;
import app.altamira.com.minitwitter.ResponseUsuarioInventario;
import app.altamira.com.minitwitter.common.Constantes;
import app.altamira.com.minitwitter.common.GPSTracker;
import app.altamira.com.minitwitter.common.Inventarios;
import app.altamira.com.minitwitter.common.SharedPreferencesManager;
import app.altamira.com.minitwitter.retrofit.AltamiraClient;
import app.altamira.com.minitwitter.retrofit.AltamiraService;
import app.altamira.com.minitwitter.retrofit.request.RequestLogoutApp;
import app.altamira.com.minitwitter.retrofit.request.RequestMarcaInventario;
import app.altamira.com.minitwitter.retrofit.request.RequestUsuarioInventario;
import app.altamira.com.minitwitter.retrofit.response.Reg;
import app.altamira.com.minitwitter.retrofit.response.ResponseLogoutApp;
import app.altamira.com.minitwitter.retrofit.response.ResponseMarcaInventario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EscanerActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button btnEscanear;
    TextView tvNombreCompleto;
    EditText etDescripcion;

    Spinner spInventarioUsuario;

    String usuario;
    String nombreCompleto;
    String latitud = "";
    String longitud = "";
    String descripcion = "";
    int idInventario = 0;

    AltamiraClient altamiraClient;
    AltamiraService altamiraService;

    //private LocationManager ubicacion;
    private GPSTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaner);

        //getSupportActionBar().hide();

        retrofitInit();
        findViews();
        events();

        buscaInventarioActivo(SharedPreferencesManager.getSomeStringValue(Constantes.PREF_USER));

        spInventarioUsuario.setOnItemSelectedListener(this);
        //gpsTracker = new GPSTracker(this);
        //longitud = Double.toString(gpsTracker.getLongitude());
        //latitud = Double.toString(gpsTracker.getLatitude());

    }

    //Método para mostrar y ocultar el menú
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    //Método para asignar las funciones correspondientes a las opciones.
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuSalir) {
            salirApp();
        }
        return super.onOptionsItemSelected(item);
    }

    private void salirApp() {

        String vUsuario = SharedPreferencesManager.getSomeStringValue(Constantes.PREF_USER);

        RequestLogoutApp requestLogoutApp = new RequestLogoutApp(vUsuario, latitud, longitud);

        Call<ResponseLogoutApp> call = altamiraService.doLogoutUser(Constantes.API_KEY, "setLogout", requestLogoutApp);

        call.enqueue(new Callback<ResponseLogoutApp>() {
            @Override
            public void onResponse(Call<ResponseLogoutApp> call, Response<ResponseLogoutApp> response) {
                if (response.isSuccessful()) {
                    String vRst = response.body().getResultado();
                    if (vRst.equals("0")) {
                        finish();
                        //finishAffinity();
                        System.exit(0);


                    }
                } else {
                    Toast.makeText(EscanerActivity.this, "Algo fue mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogoutApp> call, Throwable t) {

            }
        });
    }


    private void retrofitInit() {
        altamiraClient = AltamiraClient.getInstance();
        altamiraService = altamiraClient.getAltamiraService();
    }

    private void findViews() {
        tvNombreCompleto = findViewById(R.id.textViewNombreCompleto);
        etDescripcion = findViewById(R.id.editTextDescripcion);

        spInventarioUsuario = findViewById(R.id.spInventarios);
        btnEscanear = findViewById(R.id.buttonEscanear);

        usuario = SharedPreferencesManager.getSomeStringValue(Constantes.PREF_USER);
        nombreCompleto = SharedPreferencesManager.getSomeStringValue(Constantes.PREF_USERNAME);

        tvNombreCompleto.setText(nombreCompleto);
    }

    private void events() {
        btnEscanear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.buttonEscanear:
                initScanner();
                break;
        }
    }

    private void initScanner() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("ALTAMIRA © 2018-2022 Todos los Derechos Reservados. Versión 1.0.0.1b");
        integrator.setCameraId(0);
        integrator.setTorchEnabled(false);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
            } else {
                String lectura = "";
                lectura = result.getContents();

                //Toast.makeText(this, "El valor escaneado es: " + lectura, Toast.LENGTH_LONG).show();
                grabaCodigo(lectura);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void buscaInventarioActivo(String vUsuario) {
        List<Inventarios> lst = new ArrayList<>();

        RequestUsuarioInventario requestUsuarioInventario = new RequestUsuarioInventario(vUsuario);

        Call<ResponseUsuarioInventario> call = altamiraService.doUsuarioInventario(Constantes.API_KEY, "getInventarios", requestUsuarioInventario);

        call.enqueue(new Callback<ResponseUsuarioInventario>() {
            @Override
            public void onResponse(Call<ResponseUsuarioInventario> call, Response<ResponseUsuarioInventario> response) {
                if (response.isSuccessful()) {

                    if (Integer.parseInt(response.body().getError()) < 0) {

                        Toast.makeText(EscanerActivity.this, "No cuenta con inventario ASIGNADO", Toast.LENGTH_SHORT).show();
                        btnEscanear.setVisibility(View.GONE);
                        btnEscanear.setVisibility(View.INVISIBLE);
                    } else {
                        btnEscanear.setVisibility(View.GONE);
                        btnEscanear.setVisibility(View.VISIBLE);
                        String vOperacion = "";
                        vOperacion = response.body().getOperacion();
                        String total = response.body().getTotalRegs();
                        List<Reg> listaInventario = response.body().getRegs();

                        for (Reg cadena :
                                listaInventario) {
                            Inventarios dato = new Inventarios();
                            dato.setId(Integer.parseInt(cadena.getIdinventario()));
                            dato.setNombre(cadena.getTitulo());
                            lst.add(dato);
                        }

                        ArrayAdapter<Inventarios> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                                androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, lst);
                        idInventario = 0;
                        spInventarioUsuario.setAdapter(arrayAdapter);
                    }

                } else {
                    Toast.makeText(EscanerActivity.this, "Algo fue mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUsuarioInventario> call, Throwable t) {
                Toast.makeText(EscanerActivity.this, "Problemas de conexión. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void grabaCodigo(String codLeido) {
        String vUsuario = SharedPreferencesManager.getSomeStringValue(Constantes.PREF_USER);
        String vCodInventario = String.valueOf(idInventario);
        descripcion = etDescripcion.getText().toString();

        RequestMarcaInventario requestMarcaInventario = new RequestMarcaInventario(codLeido, vUsuario, vCodInventario, latitud, longitud,descripcion);

        Call<ResponseMarcaInventario> call = altamiraService.doMarcaInventario(Constantes.API_KEY, "setInventario", requestMarcaInventario);

        call.enqueue(new Callback<ResponseMarcaInventario>() {
            @Override
            public void onResponse(Call<ResponseMarcaInventario> call, Response<ResponseMarcaInventario> response) {
                if (response.isSuccessful()) {
                    String vRst = response.body().getResultado();
                    if (vRst.equals("0")) {
                        Toast.makeText(EscanerActivity.this, "Guardado con éxito", Toast.LENGTH_SHORT).show();
                        etDescripcion.setText("");
                    }
                } else {
                    Toast.makeText(EscanerActivity.this, "Algo fue mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMarcaInventario> call, Throwable t) {
                Toast.makeText(EscanerActivity.this, "Problemas de conexión. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        idInventario = ((Inventarios) adapterView.getSelectedItem()).getId();
        //Toast.makeText(EscanerActivity.this,"codigo: " + codigo,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}