package ru.marina.githubrepositoriesobserver.repositoriesList

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch
import ru.marina.githubrepositoriesobserver.R
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
            // переход на гл. экран
            requireActivity()
                .supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_auth, RepositoriesListFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.repositoriesListRecycler.layoutManager = LinearLayoutManager(context)
        lifecycleScope.launch {
            viewModel?.viewStateFlow?.collect { state ->
                when(state){
                    is RepositoriesListViewModelState.Error ->  {
                        // показать на весь экран заглушку
                    }
                    RepositoriesListViewModelState.Loading ->{
                        //нужно сделать вьюшку на весь экран и скрыть ее отображение, при загрузке показываем ее
                        //поискать пример в фильмах
                    }
                    is RepositoriesListViewModelState.Success ->
                        binding.repositoriesListRecycler.adapter = RepositoriesListAdapter(state.repositoriesModelList)
                }

            }
        }

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }


}