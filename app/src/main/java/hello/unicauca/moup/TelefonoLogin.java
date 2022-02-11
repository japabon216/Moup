package hello.unicauca.moup;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.chaos.view.PinView;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;
//import com.google.firebase.auth.PhoneAuthOptions;

import java.util.concurrent.TimeUnit;


public class TelefonoLogin extends AppCompatActivity {

    private CountryCodePicker ccp;
    private String codigoseleccionado = "+57";
    private EditText phoneEdittext;
    private ConstraintLayout TelefonoLayout;
    private PinView firstPinView;
    private static final int CREDENTIAL_PICKER_REQUEST =120 ;
    private ProgressBar progressBar;

    ////////FIREBASE PHONE AUTH//////////////////////
    private String  mVerificationId ;
    private PhoneAuthProvider.ForceResendingToken mResentToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private FirebaseAuth mAuth;

    @Override
    //////////////////////country code///////////////////////////////
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefono_login);

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        phoneEdittext = (EditText) findViewById(R.id.textView);
        firstPinView = (PinView) findViewById(R.id.firstPinView);
        TelefonoLayout = (ConstraintLayout) findViewById(R.id.TelefonoLayout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();


        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                codigoseleccionado = ccp.getSelectedCountryCodeWithPlus();
            }
        });


        /////////////////text watcher////////////////////////////////////////////
        phoneEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 10){
                    sendOtp();
                    //Toast.makeText(TelefonoLogin.this, "numero de 10 digitos", Toast.LENGTH_SHORT).show();
//                     TelefonoLayout.setVisibility(View.GONE);
//                     firstPinView.setVisibility(View.VISIBLE);



                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ////////////////////////>text watcher/////////////////////////////////////7


///////////////////////////otp text watcher/////////////////////////////////////////
        firstPinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 6){
                    progressBar.setVisibility(View.VISIBLE);

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential( mVerificationId , firstPinView.getText().toString().trim());
                    signInWithAuthCredential(credential);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ///////////////////////////>otp text watcher/////////////////////////////////////////


////////////////////////////////////auto phone select api///////////////////////////////////////////////////////
     //   HintRequest hintRequest = new HintRequest.Builder()
       //         .setPhoneNumberIdentifierSupported(true)
         //       .build();


       // PendingIntent intent = Credentials.getClient(TelefonoLogin.this).getHintPickerIntent(hintRequest);
       // try
       // {
         //   startIntentSender(intent.getIntentSender(), null, 0, 0, 0);
            //startIntentSenderForResult(intent.getIntentSender(), CREDENTIAL_PICKER_REQUEST, null, 0, 0, 0,new Bundle());
        //}
        //catch (IntentSender.SendIntentException e)
        //{
         //   e.printStackTrace();
        //}

////////////////////////////////////////////////////////////////////////////////////



        //////////////////////call back/////////////////////////////////////
        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                String code = phoneAuthCredential.getSmsCode();
                if(code != null)
                {
                    firstPinView.setText(code);

                    signInWithAuthCredential(phoneAuthCredential);
                }
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(TelefonoLogin.this, "Ocurrio un error"+e.toString(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                TelefonoLayout.setVisibility(View.VISIBLE);
                firstPinView.setVisibility(View.GONE);
            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                super.onCodeSent(verificationId, token);

                mVerificationId = verificationId;
                mResentToken = token;

                Toast.makeText(TelefonoLogin.this, "6 digitos enviados", Toast.LENGTH_SHORT).show();
                firstPinView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                TelefonoLayout.setVisibility(View.GONE);

            }
        };
        //////////////////////>call back/////////////////////////////////////

    }


    private void sendOtp() {
        progressBar.setVisibility(View.VISIBLE);
        String phoneNumber = codigoseleccionado+phoneEdittext.getText().toString();

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setPhoneNumber(phoneNumber)
                .setActivity(TelefonoLogin.this)
                .setCallbacks(callbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREDENTIAL_PICKER_REQUEST && resultCode == RESULT_OK)
        {
            // Obtain the phone number from the result
            Credential credentials = data.getParcelableExtra(Credential.EXTRA_KEY);

            phoneEdittext.setText(credentials.getId().substring(3));


        }
        else if (requestCode == CREDENTIAL_PICKER_REQUEST && resultCode == CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE)
        {
            // *** No phone numbers available ***
            Toast.makeText(TelefonoLogin.this, "No phone numbers found", Toast.LENGTH_LONG).show();
        }


    }


    private void  signInWithAuthCredential(PhoneAuthCredential credentials) {
        mAuth.signInWithCredential(credentials)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(TelefonoLogin.this, "Log in exitoso", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(TelefonoLogin.this, MainActivity2.class);
                            startActivity(intent);
                            finish();

                        }
                        else
                        {
                            Toast.makeText(TelefonoLogin.this, "Ocurrio un fallo en la conexion", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(TelefonoLogin.this, Login.class);
                            startActivity(intent);
                            finish();


//
                        }
                    }
                });
    }
}