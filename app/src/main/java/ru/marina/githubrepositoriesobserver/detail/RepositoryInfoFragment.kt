package ru.marina.githubrepositoriesobserver.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalStateException
import javax.inject.Inject
import kotlinx.coroutines.launch
import ru.marina.githubrepositoriesobserver.R
import ru.marina.githubrepositoriesobserver.auth.AuthUserFragment
import ru.marina.githubrepositoriesobserver.database.DatabaseSaveToken
import ru.marina.githubrepositoriesobserver.databinding.FragmentDetailInfoBinding
import ru.marina.githubrepositoriesobserver.recycler.RepositoryDetailAdapter
import ru.marina.githubrepositoriesobserver.repositoriesList.RepositoriesListFragment
import ru.marina.githubrepositoriesobserver.state.RepositoryInfoViewModelState
import ru.marina.githubrepositoriesobserver.useCase.RepositoryInfoUseCase
import ru.marina.githubrepositoriesobserver.viewModel.RepositoryInfoViewModel
import ru.marina.githubrepositoriesobserver.viewModel.RepositoryInfoViewModelFactory

private const val ARG_NAME_KEY_ID = "ARG_NAME_KEY_ID"
private const val ARG_OWNER_KEY_ID = "ARG_OWNER_KEY_ID"

@AndroidEntryPoint
class RepositoryInfoFragment @Inject constructor() : Fragment() {

    private var binding: FragmentDetailInfoBinding? = null
    private var viewModel: RepositoryInfoViewModel? = null

    @Inject
    lateinit var useCase: RepositoryInfoUseCase
    @Inject
    lateinit var databaseSaveToken: DatabaseSaveToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            RepositoryInfoViewModelFactory(
                name = arguments?.getString(ARG_NAME_KEY_ID) ?: throw IllegalStateException(""),
                owner = arguments?.getString(ARG_OWNER_KEY_ID) ?: throw IllegalStateException(""),
                useCase = useCase,
                databaseSaveToken = databaseSaveToken
            )
        )[RepositoryInfoViewModel::class.java]

        if (savedInstanceState == null) {
            viewModel?.updateRepositoryInfo()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailInfoBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return
// name передается не тот
        binding.nameRepository.text=arguments?.getString(ARG_NAME_KEY_ID)

        binding.logOutButton.setOnClickListener {
            requireActivity()
                .supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, AuthUserFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.arrowBack.setOnClickListener {
            requireActivity()
                .supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, RepositoriesListFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.recyclerViewInfo.layoutManager = LinearLayoutManager(context)
        lifecycleScope.launch {
            viewModel?.viewStateFlow?.collect { state ->
                when (state) {
                    is RepositoryInfoViewModelState.Error -> {
                        showOrHideErrorContainer(true)
                        showOrHideGifLoading(false)
                    }

                    RepositoryInfoViewModelState.Loading -> {
                        showOrHideErrorContainer(false)
                        showOrHideGifLoading(true)
                    }

                    is RepositoryInfoViewModelState.Success -> {
                        showOrHideErrorContainer(false)
                        showOrHideGifLoading(false)
                        binding.recyclerViewInfo.adapter = RepositoryDetailAdapter(state.itemList)
                    }
                }

            }

        }
    }

    private fun showOrHideErrorContainer(isShow: Boolean) {
        binding?.containerError?.isVisible = isShow
    }

    private fun showOrHideGifLoading(isShow: Boolean) {
        val image = binding?.imageViewLoading
        binding?.containerLoading?.isVisible = isShow
        Glide.with(this)
            .load(R.drawable.cat_dance)
            .into(image!!)
    }

    override fun onDestroy() {
        (binding?.recyclerViewInfo?.adapter as? RepositoryDetailAdapter)?.dispose()
        binding = null
        super.onDestroy()
    }

    companion object {
        fun newInstance(name: String, owner: String): RepositoryInfoFragment {
            val args = Bundle()
            args.putString(ARG_NAME_KEY_ID, name)
            args.putString(ARG_OWNER_KEY_ID, owner)
            val fragment = RepositoryInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }
}