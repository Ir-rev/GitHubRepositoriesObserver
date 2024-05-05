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
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.R
import ru.marina.githubrepositoriesobserver.databinding.FragmentRepositoriesListBinding

@AndroidEntryPoint
class RepositoriesListFragment @Inject constructor() : Fragment() {

    private var binding: FragmentRepositoriesListBinding? = null

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
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }


}