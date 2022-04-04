package com.charusat.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var db = FirebaseDatabase.getInstance()
            var ref = db.getReference("student")
            button.setOnClickListener {
                if (TextUtils.isEmpty(edtFirstName.text.toString()))
                {
                    Toast.makeText(this, "PLease Enter First Name", Toast.LENGTH_SHORT).show()
                }
                else{
                    if(TextUtils.isEmpty(edtLastName.text.toString()))
                    {
                        Toast.makeText(this, "PLease Enter Last Name", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        ref.addListenerForSingleValueEvent(object: ValueEventListener{
                            override fun onDataChange(snapshot: DataSnapshot) {
                                ref.child(edtFirstName.text.toString()).child("FirstName").setValue(edtFirstName.text.toString())
                                ref.child(edtFirstName.text.toString()).child("LastName").setValue(edtLastName.text.toString())
                                Toast.makeText(applicationContext, "Record Inserted Successfully", Toast.LENGTH_SHORT).show()
                            }

                            override fun onCancelled(error: DatabaseError) {
                                Toast.makeText(applicationContext, "Record Not Inserted", Toast.LENGTH_SHORT).show()
                            }


                        })
                    }
                }
            }
        btnView.setOnClickListener {
            var displayrecord:Intent = Intent(applicationContext,displayrecord::class.java)
            startActivity(displayrecord)
        }
    }
}