package ru.marina.githubrepositoriesobserver.repositoriesList

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch
import ru.marina.githubrepositoriesobserver.R
import ru.marina.githubrepositoriesobserver.auth.AuthUserFragment
import ru.marina.githubrepositoriesobserver.databinding.FragmentRepositoriesListBinding
import ru.marina.githubrepositoriesobserver.model.RepositoriesModel
import ru.marina.githubrepositoriesobserver.recycler.RepositoriesListAdapter
import ru.marina.githubrepositoriesobserver.state.RepositoriesListViewModelState
import ru.marina.githubrepositoriesobserver.viewModel.AuthViewModel
import ru.marina.githubrepositoriesobserver.viewModel.RepositoriesListViewModel

@AndroidEntryPoint
class RepositoriesListFragment @Inject constructor() : Fragment() {

    private var binding: FragmentRepositoriesListBinding? = null
    private var viewModel: RepositoriesListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RepositoriesListViewModel::class.java]
        if (savedInstanceState == null) {
            viewModel!!.updateRepositoriesList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRepositoriesListBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = binding ?: return

        binding.logOutButton.setOnClickListener {
            requireActivity()
                .supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, AuthUserFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.repositoriesListRecycler.layoutManager = LinearLayoutManager(context)
        lifecycleScope.launch {
            viewModel?.viewStateFlow?.collect { state ->
                when (state) {
                    is RepositoriesListViewModelState.Error -> {
                        showOrHideErrorContainer(true)
                        showOrHideGifLoading(false)
                    }

                    RepositoriesListViewModelState.Loading -> {
                        showOrHideErrorContainer(false)
                        showOrHideGifLoading(true)
                    }

                    is RepositoriesListViewModelState.Success ->{
                        showOrHideGifLoading(false)
                        showOrHideErrorContainer(false)
                        binding.repositoriesListRecycler.adapter =
                            RepositoriesListAdapter(state.repositoriesModelList)
                    }


                }

            }
        }

    }

    // сделать переход на инфо экран
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


}