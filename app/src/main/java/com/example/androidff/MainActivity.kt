package com.example.androidff

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var menuVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val showMenuButton: View = findViewById(R.id.show_menu_button)
        showMenuButton.setOnClickListener {
            if (menuVisible) showPopupMenu(showMenuButton)
        }
    }

    override fun onResume() {
        super.onResume()
        menuVisible = true // Show menu when app is resumed
    }

    override fun onPause() {
        super.onPause()
        menuVisible = false // Hide menu when app is paused
    }

    private fun showPopupMenu(view: View) {
        val popup = PopupMenu(this, view)
        popup.menuInflater.inflate(R.menu.main_menu, popup.menu)

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action1 -> {
                    // Handle Action 1
                    true
                }
                R.id.action2 -> {
                    // Handle Action 2: Open Email Intent
                    val emailIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "message/rfc822"
                        putExtra(Intent.EXTRA_EMAIL, arrayOf("example@example.com"))
                        putExtra(Intent.EXTRA_SUBJECT, "Subject Text")
                        putExtra(Intent.EXTRA_TEXT, "Body of the email")
                    }
                    try {
                        startActivity(Intent.createChooser(emailIntent, "Choose an Email client"))
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(this@MainActivity, "No email app found", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

}