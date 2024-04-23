package ru.marina.githubrepositoriesobserver.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.databinding.ItemRepositoriesListBinding
import ru.marina.githubrepositoriesobserver.model.RepositoriesModel

class RepositoriesListAdapter(
    private val repositoriesList: List<RepositoriesModel>
) : RecyclerView.Adapter<RepositoriesListAdapter.RepositoriesListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesListHolder {
        val binding = ItemRepositoriesListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoriesListHolder(binding)

    }

    override fun getItemCount(): Int = repositoriesList.size

    override fun onBindViewHolder(holder: RepositoriesListHolder, position: Int) {
        val repository= repositoriesList[position]
        holder.repositoryName.text=repository.name
        holder.repositoryDescription.text=repository.description
        holder.languageCode.text=repository.kotlinOrJava
    }

    class RepositoriesListHolder(private val binding: ItemRepositoriesListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val repositoryName: TextView = binding.nameRepository
        val repositoryDescription: TextView = binding.descriptionRepository
        val languageCode: TextView = binding.kotlinOrJava

    }

}