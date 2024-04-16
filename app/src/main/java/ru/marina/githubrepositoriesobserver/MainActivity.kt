package ru.marina.githubrepositoriesobserver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.marina.githubrepositoriesobserver.auth.AuthFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.main_container, AuthFragment())

    }
}