package duvanfisi.fisiunmsm.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import duvanfisi.fisiunmsm.Actions.StartActivity
import duvanfisi.fisiunmsm.R


class WelcomeActivity : AppCompatActivity() {

    private var btnempezar : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        initViews()
        onClickButtons()


    }

    fun initViews(){
        btnempezar = findViewById<Button>(R.id.btnempezar)
    }

    fun onClickButtons(){
        btnempezar?.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            StartActivity.startActivity(this, intent)
            finish()
        }
    }

}
