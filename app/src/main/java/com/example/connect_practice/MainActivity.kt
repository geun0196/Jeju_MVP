package com.example.connect_practice

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {

    val fbdb = Firebase.firestore //파이어베이스.파이어스토어 설정

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cartnum_textView=findViewById<TextView>(R.id.cartnum_textView)

        val text_height=findViewById<TextView>(R.id.height)
        val text_weight=findViewById<TextView>(R.id.weight)
        val text_latitude =findViewById<TextView>(R.id.latitude)
        val text_longitude=findViewById<TextView>(R.id.longitude)

        //-----------------------------------------------------------------------
        //xloc 값이 변경되면 xloc 값을 읽어서 읽어서 textview에 그리는 부분
        //-----------------------------------------------------------------------
        var startPoint = GeoPoint(37.5665, 126.9780);

        var get_cartid:String = ""
        if(intent.hasExtra("cartid")) {
            //서브 액티비티의 존재하는 텍스트뷰에 main에 있는 메세지가 넘겨져 옴
            get_cartid = intent.getStringExtra("cartid").toString()
            cartnum_textView.text = "$get_cartid 번 카트"
        }

        val adocRef = fbdb.collection("JEJU").document(get_cartid)

        adocRef.addSnapshotListener(EventListener<DocumentSnapshot> { snapshot, e ->
            if (snapshot != null && snapshot.exists()) {
                text_height.text = snapshot.data!!["height"].toString()
                text_weight.text = snapshot.data!!["weight"].toString()
                startPoint = snapshot.data!!["gps"]as GeoPoint
                text_latitude.text = startPoint.latitude.toString()
                text_longitude.text = startPoint.longitude.toString()
            }
        })

    } //create
} //MainActivity