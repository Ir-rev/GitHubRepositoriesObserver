package ru.marina.githubrepositoriesobserver.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.R
import ru.marina.githubrepositoriesobserver.database.DatabaseSaveToken
import ru.marina.githubrepositoriesobserver.databinding.FragmentAuthBinding
import ru.marina.githubrepositoriesobserver.useCase.AuthLoginUseCase
import ru.marina.githubrepositoriesobserver.viewModel.AuthViewModel

private const val LAST_TOKEN_INPUT = "LAST_TOKEN_INPUT"

@AndroidEntryPoint
class AuthUserFragment : Fragment() {

    private var binding: FragmentAuthBinding? = null

    private var authViewModel: AuthViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
    }

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

        if (savedInstanceState != null) {
            binding.inputToken.setText(savedInstanceState.getString(LAST_TOKEN_INPUT) ?: "")
        }

        Glide.with(this)
            .load(R.drawable.git_logo)
            .into(binding.logoGit)

        binding.buttonSingIn.setOnClickListener {
            val authViewModel = this.authViewModel ?: return@setOnClickListener
            authViewModel.tryAuth(binding.inputToken.text.toString())
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

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(LAST_TOKEN_INPUT, binding?.inputToken?.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        authViewModel = null
        binding = null
        super.onDestroyView()
    }


}