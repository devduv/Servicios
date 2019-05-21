package duvanfisi.fisiunmsm.Actions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import duvanfisi.fisiunmsm.ActivitiesUsers.MainActivity;
import duvanfisi.fisiunmsm.FirebaseConexion.FirebaseAccount;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Preferences.isLogin(this)){
            FirebaseAccount.loginToken(Preferences.getUserCurrent(this), this);
        }else{
            StartActivity.startActivity(this, new MainActivity(), null);
        }

    }



}
