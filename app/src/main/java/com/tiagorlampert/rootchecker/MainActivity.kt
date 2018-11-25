package com.tiagorlampert.rootchecker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tiagorlampert.rootcheck.util.*
import com.tiagorlampert.rootchecker.util.AppInfo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var deviceBrand: String
    private lateinit var deviceModel: String
    private lateinit var osRelease: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load info
        getDeviceInfo()

        // Set info
        textDeviceModel.text = "$deviceBrand $deviceModel"
        textAndroidVersion.text = "Android Version: " + osRelease
        textVersionApp.text = "(" + AppInfo.version + ")"

        textRootStatus.text = "Unchecked"

        buttonCheck.setOnClickListener {
            try {
                checkRoot()
            } catch (e: Exception) {
                Toast.makeText(this, "An error occurred. " + e.message, Toast.LENGTH_LONG).show()
            }
        }

        buttonClose.setOnClickListener {
            finish();
            System.exit(0);
        }
    }

    fun getDeviceInfo() {
        deviceBrand = android.os.Build.MANUFACTURER
        deviceModel = android.os.Build.MODEL
        osRelease = android.os.Build.VERSION.RELEASE
    }

    fun checkRoot() {

        // Check if device is rooted
        val boolMethodOne = RootChecker.checkRootMethodOne()
        val boolMethodTwo = RootChecker.checkRootMethodTwo()

        if (boolMethodOne && boolMethodTwo) {
            circlePhoneBackground.setBackgroundResource(R.drawable.circle_rooted)
            textRootStatus.text = "# Rooted!"
            textRootStatus.setTextColor(resources.getColor(R.color.colorRooted))

            Toast.makeText(this, "Your device is Rooted!", Toast.LENGTH_LONG).show()
        } else {
            circlePhoneBackground.setBackgroundResource(R.drawable.circle_no_rooted)
            textRootStatus.text = "Not Rooted!"
            textRootStatus.setTextColor(resources.getColor(R.color.colorNoRooted))

            Toast.makeText(this, "Your device isn't Rooted!", Toast.LENGTH_LONG).show()
        }
    }
}
