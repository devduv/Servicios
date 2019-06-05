package duvanfisi.fisiunmsm.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseUser
import duvanfisi.fisiunmsm.Actions.StartActivity
import duvanfisi.fisiunmsm.Actions.Utilidades
import duvanfisi.fisiunmsm.R


class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)


        val btnempezar = findViewById(R.id.btnempezar) as Button
        val btnprincipal = findViewById(R.id.btnprincipal) as Button


        btnprincipal.setOnClickListener{

            val user: FirebaseUser? = null
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(Utilidades.FIREBASEUSER, user)
            StartActivity.startActivity(this, intent)
            finish()
        }
        //val user = null as User
        btnempezar.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            StartActivity.startActivity(this, intent)
            finish()
            }


    }

}
