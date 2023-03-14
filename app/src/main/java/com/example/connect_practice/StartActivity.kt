package com.example.connect_practice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) { // 액티비티가 최초 실행 되면 이곳 수행
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val cartid=findViewById<EditText>(R.id.cartid)
        val btn_login =findViewById<Button>(R.id.btn_login)

        btn_login.setOnClickListener { //에딧 텍스트에 입력되어잇는 값을 가지고와서 텍스트뷰에 뿌림
            val cart_id = cartid.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("cartid", cart_id)
            startActivity(intent)   //intent에 저장되어 있는 엑티비티 쪽으로 이동한다
        }



    }

}