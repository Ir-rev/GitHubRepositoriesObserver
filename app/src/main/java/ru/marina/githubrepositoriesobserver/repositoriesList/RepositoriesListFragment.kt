package ru.marina.githubrepositoriesobserver.repositoriesList

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.marina.githubrepositoriesobserver.R
import ru.marina.githubrepositoriesobserver.databinding.FragmentRepositoriesListBinding


class RepositoriesListFragment : Fragment() {
    private var binding: FragmentRepositoriesListBinding? = null
    private var repositoriesList: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        repositoriesList= binding.repositoriesListRecycler

        binding.logOutButton.setOnClickListener {
            // переход на гл. экран
        }
        val repositoriesList= repositoriesList ?: return
        repositoriesList.layoutManager=LinearLayoutManager(context)

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }


}