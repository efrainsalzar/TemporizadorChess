package com.example.temporizadorchess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseconection.ItemsViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InformeResult : AppCompatActivity() {

    val db = Firebase.firestore
    val TAG = "Datos2"

    val getdatoss = arrayOf("nombre", "tiempo", "turnos", "promedio")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informe_result)
        getData1()
        getData2()

        val newHome = findViewById<ImageView>(R.id.newHome)
        newHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }



    private fun getData1() {

        val recyclerviewN = findViewById<RecyclerView>(R.id.dbjugador1)
        recyclerviewN.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsViewModel>()

        db.collection("Informe")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var nums = true
                    for (document in task.result) {

                        if (nums) {
                            data.add(
                                ItemsViewModel(
                                    "Jugador1", document.data.get(getdatoss[0]).toString(),
                                    document.data.get(getdatoss[1]).toString(),
                                    document.data.get(getdatoss[2]).toString(),
                                    document.data.get(getdatoss[3]).toString()
                                )
                            )
                            nums = false
                        }
                    }
                    val adapter = CustomAdapter(data)
                    recyclerviewN.adapter = adapter

                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
        }
    }
    private fun getData2() {
        val recyclerviewN = findViewById<RecyclerView>(R.id.dbjugador2)
        recyclerviewN.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsViewModel>()

        db.collection("Informe")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var nums = true
                    for (document in task.result) {

                        if (!nums) {
                            data.add(
                                ItemsViewModel(
                                    "Jugador1", document.data.get(getdatoss[0]).toString(),
                                    document.data.get(getdatoss[1]).toString(),
                                    document.data.get(getdatoss[2]).toString(),
                                    document.data.get(getdatoss[3]).toString()
                                )
                            )

                        }
                        nums = false
                    }
                    val adapter = CustomAdapter(data)
                    recyclerviewN.adapter = adapter

                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
    }
    /*private fun getData2(){

        val recyclerviewN = findViewById<RecyclerView>(R.id.dbNombre2)
        val recyclerviewP = findViewById<RecyclerView>(R.id.dbPromedio2)
        val recyclerviewT = findViewById<RecyclerView>(R.id.dbTiempo2)
        val recyclerviewt = findViewById<RecyclerView>(R.id.dbTurnos2)

        recyclerviewN.layoutManager = LinearLayoutManager(this)
        recyclerviewP.layoutManager = LinearLayoutManager(this)
        recyclerviewT.layoutManager = LinearLayoutManager(this)
        recyclerviewt.layoutManager = LinearLayoutManager(this)


        val data1 = ArrayList<ItemsViewModel>()
        val data12 = ArrayList<ItemsViewModel>()
        val data13 = ArrayList<ItemsViewModel>()
        val data14 = ArrayList<ItemsViewModel>()



        db.collection("Infome")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var nums = true
                    for (document in task.result) {

                        if(nums) {
                            data1.add(
                                ItemsViewModel(
                                    "Oso", document.data.get(getdatoss[0]).toString()/*,
                                                                document.data.get("promedio").toString(),
                                                                document.data.get("tiempo").toString(),
                                                                document.data.get("turnos").toString()*/
                                )
                            )
                            nums = false
                        }
                    }

                    val adapter = CustomAdapter(data1)
                    recyclerviewN.adapter = adapter

                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
        /**/
        db.collection("Infome")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var nums2 = true
                    for (document in task.result) {

                        if(nums2) {
                            data12.add(
                                ItemsViewModel(
                                    "Oso", document.data.get(getdatoss[1]).toString()/*,
                                                                document.data.get("promedio").toString(),
                                                                document.data.get("tiempo").toString(),
                                                                document.data.get("turnos").toString()*/
                                )
                            )
                            nums2 = false
                        }
                    }

                    val adapter = CustomAdapter(data12)
                    recyclerviewP.adapter = adapter

                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
        /***/
        db.collection("Infome")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var nums3 = true
                    for (document in task.result) {

                        if(nums3) {
                            data13.add(
                                ItemsViewModel(
                                    "Oso", document.data.get(getdatoss[2]).toString()/*,
                                                                document.data.get("promedio").toString(),
                                                                document.data.get("tiempo").toString(),
                                                                document.data.get("turnos").toString()*/
                                )
                            )
                            nums3 = false
                        }
                    }

                    val adapter = CustomAdapter(data13)
                    recyclerviewT.adapter = adapter

                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
        /****/
        db.collection("Infome")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var nums4 = true
                    for (document in task.result) {

                        if(nums4) {
                            data14.add(
                                ItemsViewModel(
                                    "Oso", document.data.get(getdatoss[3]).toString()/*,
                                                                document.data.get("promedio").toString(),
                                                                document.data.get("tiempo").toString(),
                                                                document.data.get("turnos").toString()*/
                                )
                            )
                            nums4 = false
                        }
                    }

                    val adapter = CustomAdapter(data14)
                    recyclerviewt.adapter = adapter

                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }

    }*/

}