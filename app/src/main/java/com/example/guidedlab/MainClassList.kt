package com.example.guidedlab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView

class MainClassList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_class_list)

        val swDegreeCert = findViewById<Switch>(R.id.swBar)
        val spnDegree = findViewById<Spinner>(R.id.spnAssociates)
        val spnCertificate = findViewById<Spinner>(R.id.spnCert)
        val txtCertificate = findViewById<TextView>(R.id.idCert)
        val txtDegree = findViewById<TextView>(R.id.idAssociates)
        val btnNext = findViewById<Button>(R.id.idBtnNext)

        val firstName = findViewById<EditText>(R.id.idFirstName)
        val lastName = findViewById<EditText>(R.id.idLastName)
        val phone = findViewById<EditText>(R.id.idPhone)

        val spMonth = findViewById<Spinner>(R.id.spnMonth)
        val txtDay = findViewById<EditText>(R.id.idDay)
        val txtYear = findViewById<EditText>(R.id.idYear)

        firstName.requestFocus()

        swDegreeCert.setOnCheckedChangeListener { swBar, isChecked ->
            if (isChecked){
                spnDegree.visibility = View.VISIBLE
                txtDegree.visibility = View.VISIBLE
                spnCertificate.visibility = View.GONE
                txtCertificate.visibility = View.GONE
            } else {
                spnDegree.visibility = View.GONE
                txtDegree.visibility = View.GONE
                spnCertificate.visibility = View.VISIBLE
                txtCertificate.visibility = View.VISIBLE
            }


        }

        btnNext.setOnClickListener {
            if (checkData()){
                var doBirth = ""
                doBirth = spMonth.selectedItem.toString() + "/" + txtDay.text.toString() + "/" + txtYear.text.toString()

                val nextScreen = Intent(this@MainClassList, ChooseClass::class.java)
                nextScreen.putExtra("FirstName", firstName.text.toString())
                nextScreen.putExtra("LastName", lastName.text.toString())
                nextScreen.putExtra("Phone", phone.text.toString())
                nextScreen.putExtra("Birthdate", doBirth)

                if (spnDegree.visibility == View.VISIBLE){
                    nextScreen.putExtra("isDegreeCert", "Degree")
                    nextScreen.putExtra("degreeCert", spnDegree.selectedItem.toString())
                } else{
                    nextScreen.putExtra("isDegreeCert", "Certificate")
                    nextScreen.putExtra("degreeCert", spnCertificate.selectedItem.toString())
                }

                //Start Activity
                startActivity(nextScreen)



            }
        }
    }

    private fun checkData(): Boolean{
        val firstName = findViewById<EditText>(R.id.idFirstName)
        val lastName = findViewById<EditText>(R.id.idLastName)
        val phone = findViewById<EditText>(R.id.idPhone)
        val txtDay = findViewById<EditText>(R.id.idDay)
        val txtYear = findViewById<EditText>(R.id.idYear)

        if (firstName.text.toString().isEmpty()){
            firstName.error = "Invalid First Name"
            firstName.requestFocus()
            return false
        }
        if (lastName.text.toString().isEmpty()){
            lastName.error = "Invalid Last Name"
            lastName.requestFocus()
            return false
        }
        if (phone.text.toString().isEmpty()){
            phone.error = "Invalid Phone Number"
            phone.requestFocus()
            return false
        }
        if (txtDay.text.toString().isEmpty()){
            txtDay.error = "Invalid Day"
            txtDay.requestFocus()
            return false
        }
        if (txtYear.text.toString().isEmpty()){
            txtYear.error = "Invalid Year"
            txtYear.requestFocus()
            return false
        }

        return true
    }
}