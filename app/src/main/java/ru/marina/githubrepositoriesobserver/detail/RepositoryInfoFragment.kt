package ru.marina.githubrepositoriesobserver.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.marina.githubrepositoriesobserver.R
import ru.marina.githubrepositoriesobserver.auth.AuthUserFragment
import ru.marina.githubrepositoriesobserver.databinding.FragmentDetailInfoBinding
import ru.marina.githubrepositoriesobserver.model.RepositoriesInfoModel
import ru.marina.githubrepositoriesobserver.recycler.RepositoryDetailAdapter
import ru.marina.githubrepositoriesobserver.repositoriesList.RepositoriesListFragment
import ru.marina.githubrepositoriesobserver.state.RepositoryInfoViewModelState
import ru.marina.githubrepositoriesobserver.viewModel.RepositoryInfoViewModel
private const val KEY_ID = "keyId"

@AndroidEntryPoint
class RepositoryInfoFragment @Inject constructor() : Fragment() {

    private var binding: FragmentDetailInfoBinding? = null
    private var viewModel: RepositoryInfoViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RepositoryInfoViewModel::class.java]
        if (savedInstanceState == null) {
            viewModel
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
        val binding=binding ?: return

        binding.logOutButton.setOnClickListener {
            requireActivity()
                .supportFragmentManager.
                beginTransaction()
                .replace(R.id.main_container, AuthUserFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.arrowBack.setOnClickListener {
            requireActivity()
                .supportFragmentManager.
                beginTransaction()
                .replace(R.id.main_container, RepositoriesListFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.recyclerViewInfo.layoutManager= LinearLayoutManager(context)
        lifecycleScope.launch {
            viewModel?.viewStateFlow?.collect{ state->
                when(state){
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
                        binding.recyclerViewInfo.adapter=RepositoryDetailAdapter(state.itemList)
                    }
                }

        }

        }
    }
    private fun showOrHideErrorContainer(isShow: Boolean) {
        binding?.containerError?.isVisible = isShow
    }

    private fun showOrHideGifLoading(isShow: Boolean) {
        val image= binding?.imageViewLoading
        binding?.containerLoading?.isVisible= isShow
        Glide.with(this)
            .load(R.drawable.cat_dance)
            .into(image!!)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
    companion object {
        fun newInstance(name: Int): RepositoryInfoFragment {
            val args = Bundle()
            args.putInt(KEY_ID, name)
            val fragment = RepositoryInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }
}