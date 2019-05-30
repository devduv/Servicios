package duvanfisi.fisiunmsm.Actions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;

import duvanfisi.fisiunmsm.ActivitiesUsers.LoginActivity;
import duvanfisi.fisiunmsm.ActivitiesUsers.MainActivity;
import duvanfisi.fisiunmsm.ActivitiesUsers.WelcomeActivity;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseAccount;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Preferences.isLogin(this)){
           startMainUser();
        }else{
            startMainInvited();
            //StartActivity.startActivity(this, new LoginActivity());
        }

    }

    public void startMainInvited(){
        FirebaseUser user = null;
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Utilidades.FIREBASEUSER, user);
        StartActivity.startActivity(this, intent);
        finish();
    }

    public void startMainUser(){
        FirebaseAccount.loginToken(Preferences.getUserCurrent(this), this);
    }


}
