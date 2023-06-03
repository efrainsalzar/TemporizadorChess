package com.example.temporizadorchess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseconection.ItemsViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InformeResult : AppCompatActivity() {

    val db = Firebase.firestore
    val TAG = "Datos2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informe_result)
        getData2()
    }

    private fun getData2(){

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsViewModel>()



        db.collection("Infome")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {

                        data.add(ItemsViewModel("Oso", document.data.get("nombre").toString(),
                                                                document.data.get("promedio").toString(),
                                                                document.data.get("tiempo").toString(),
                                                                document.data.get("turnos").toString()))

                    }

                    val adapter = CustomAdapter(data)
                    recyclerview.adapter = adapter

                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
    }

}