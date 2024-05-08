package ru.marina.githubrepositoriesobserver.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.marina.githubrepositoriesobserver.R
import ru.marina.githubrepositoriesobserver.databinding.FragmentAuthBinding
import ru.marina.githubrepositoriesobserver.repositoriesList.RepositoriesListFragment
import ru.marina.githubrepositoriesobserver.state.AuthUserTokenViewModelState
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding=FragmentAuthBinding.inflate(inflater, container, false)
        this.binding=binding
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
            // передали токен вью модели
            authViewModel.tryAuth(binding.inputToken.text.toString())

        }
        observeViewModelState()

    }
    private fun observeViewModelState(){
        lifecycleScope.launch {
            authViewModel?.viewStateFlow?.collect{state->
                when(state){
                    is AuthUserTokenViewModelState.Error -> Toast.makeText(context,"Введите токен", Toast.LENGTH_SHORT).show()
                    AuthUserTokenViewModelState.Idle -> {}
                    AuthUserTokenViewModelState.Loading -> Toast.makeText(context,"Загрузка...", Toast.LENGTH_SHORT).show()
                    is AuthUserTokenViewModelState.Success ->
                        requireActivity()
                            .supportFragmentManager.beginTransaction()
                            .replace(R.id.main_container, RepositoriesListFragment())
                            .addToBackStack(null)
                            .commit()
                }

            }
        }


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