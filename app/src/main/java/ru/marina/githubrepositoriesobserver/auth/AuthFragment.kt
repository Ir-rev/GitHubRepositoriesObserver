package ru.marina.githubrepositoriesobserver.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.R
import ru.marina.githubrepositoriesobserver.databinding.FragmentAuthBinding
import ru.marina.githubrepositoriesobserver.repositoriesList.RepositoriesListFragment

@AndroidEntryPoint
class AuthFragment @Inject constructor(): Fragment() {

    private var binding: FragmentAuthBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding=FragmentAuthBinding.inflate(inflater, container, false)
        this.binding=binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding= binding ?: return
        val imageLogoGit: ImageView= binding.logoGit
        val inputToken: EditText= binding.inputToken
        val buttonSingIn: Button= binding.buttonSingIn

        Glide.with(this)
            .load(R.drawable.git_logo)
            .into(imageLogoGit)


        buttonSingIn.setOnClickListener {
            // проверка на токен и переход во второй фрагмент

            requireActivity()
                .supportFragmentManager.
                beginTransaction()
                    .replace(R.id.fragment_auth, RepositoriesListFragment())
                .addToBackStack(null)
                .commit()
        }


    }


}