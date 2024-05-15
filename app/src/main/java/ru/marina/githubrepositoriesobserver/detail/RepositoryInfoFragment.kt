package ru.marina.githubrepositoriesobserver.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.databinding.FragmentDetailInfoBinding
import ru.marina.githubrepositoriesobserver.model.RepositoriesInfoModel
import ru.marina.githubrepositoriesobserver.viewModel.RepositoryInfoViewModel

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

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}