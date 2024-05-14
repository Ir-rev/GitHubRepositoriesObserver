package ru.marina.githubrepositoriesobserver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.auth.AuthUserFragment
import ru.marina.githubrepositoriesobserver.database.DatabaseSaveToken
import ru.marina.githubrepositoriesobserver.repositoriesList.RepositoriesListFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var databaseSaveToken: DatabaseSaveToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, RepositoriesListFragment())
                .commit()
        }
        //создание бд
        databaseSaveToken.bindDB(applicationContext)

    }

    override fun onDestroy() {
        databaseSaveToken.releaseDB()
        super.onDestroy()
    }
}