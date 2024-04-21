package ru.marina.githubrepositoriesobserver.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.R
import ru.marina.githubrepositoriesobserver.databinding.FragmentAuthBinding
import ru.marina.githubrepositoriesobserver.repositoriesList.RepositoriesListFragment
import ru.marina.githubrepositoriesobserver.useCase.RepositoryUseCase

@AndroidEntryPoint
class AuthFragment @Inject constructor() : Fragment() {

    private var binding: FragmentAuthBinding? = null
    @Inject
    lateinit var authUseCase: RepositoryUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAuthBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return

        Glide.with(this)
            .load(R.drawable.git_logo)
            .into(binding.logoGit)

        binding.buttonSingIn.setOnClickListener {

            lifecycleScope.launch(Dispatchers.IO) {
                val login = authUseCase.authUser("")
                Log.d("checkResult", "onViewCreated: $login")
            }
            

            // TODO: Это должно быть в месте где ловятся экшены вьюмодели
            // проверка на токен и переход во второй фрагмент
//            requireActivity()
//                .supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_auth, RepositoriesListFragment())
//                .addToBackStack(null)
//                .commit()
            // TODO: end
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


}