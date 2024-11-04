package app.altamira.com.minitwitter.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.altamira.com.minitwitter.R;
import app.altamira.com.minitwitter.common.Constantes;
import app.altamira.com.minitwitter.common.GeneraMD5;
import app.altamira.com.minitwitter.retrofit.AltamiraClient;
import app.altamira.com.minitwitter.retrofit.AltamiraService;
import app.altamira.com.minitwitter.retrofit.request.RequestUsuarioApp;
import app.altamira.com.minitwitter.retrofit.response.ResponseUsuarioApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSingUp;
    TextView tvGoLogin;
    EditText etUsuario, etNombre, etApellido, etEmail, etPassword;

    AltamiraClient altamiraClient;
    AltamiraService altamiraService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();

        retrofitInit();
        findViews();
        events();
    }

    private void retrofitInit() {
        AltamiraClient altamiraClient = AltamiraClient.getInstance();
        altamiraService = altamiraClient.getAltamiraService();
    }

    private void findViews() {
        btnSingUp = findViewById(R.id.buttonRegistro);
        tvGoLogin = findViewById(R.id.textViewLogin);
        etUsuario = findViewById(R.id.editTextUsuario);
        etNombre = findViewById(R.id.editTextNombre);
        etApellido = findViewById(R.id.editTextApellido);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
    }

    private void events() {
        btnSingUp.setOnClickListener(this);
        tvGoLogin.setOnClickListener(this);
    }

    private  void limpiar(){
        etUsuario.setText("");
        etNombre.setText("");
        etApellido.setText("");
        etEmail.setText("");
        etPassword.setText("");
        etUsuario.requestFocus();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.buttonRegistro:
                goToRegistro();
                break;
            case R.id.textViewLogin:
                goToLogin();
                break;
        }
    }

    private void goToRegistro() {
        GeneraMD5 gMd5 = new GeneraMD5();
        String vUsuario = etUsuario.getText().toString();
        String vNombre = etNombre.getText().toString();
        String vApellido = etApellido.getText().toString();
        String vPassword = gMd5.getMD5(etPassword.getText().toString());
        String vEmail = etEmail.getText().toString();

        if (vUsuario.isEmpty()) {
            etUsuario.setError("El usuario es requerido");
        } else if (vNombre.isEmpty()) {
            etNombre.setError("El nombre es requerido");
        } else if (vApellido.isEmpty()) {
            etApellido.setError("El apellido es requerido");
        } else if (vPassword.isEmpty()) {
            etPassword.setError("La contraseña es requerida");
        } else if (vEmail.isEmpty()) {
            etEmail.setError("El correo es requerido");
        } else {

            RequestUsuarioApp requestUsuarioApp = new RequestUsuarioApp(vUsuario,vNombre,vApellido,vPassword,vEmail);

            Call<ResponseUsuarioApp> call = altamiraService.doUsuarioApp(Constantes.API_KEY,"setUsuarioApp",requestUsuarioApp);

            call.enqueue(new Callback<ResponseUsuarioApp>() {
                @Override
                public void onResponse(Call<ResponseUsuarioApp> call, Response<ResponseUsuarioApp> response) {
                    if(response.isSuccessful()){
                        String vRst = response.body().getResultado();
                        if (vRst.equals("0")) {
                            Toast.makeText(SignUpActivity.this, "Guardado con éxito", Toast.LENGTH_SHORT).show();
                            limpiar();
                        }else{
                            Toast.makeText(SignUpActivity.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                            limpiar();
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "Algo fue mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseUsuarioApp> call, Throwable t) {

                }
            });


        }

    }

    private void goToLogin() {
        Intent i = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}