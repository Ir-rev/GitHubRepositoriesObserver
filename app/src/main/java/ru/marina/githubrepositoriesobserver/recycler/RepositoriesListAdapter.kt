package ru.marina.githubrepositoriesobserver.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ru.marina.githubrepositoriesobserver.databinding.ItemRepositoriesListBinding
import ru.marina.githubrepositoriesobserver.model.RepositoriesModel

class RepositoriesListAdapter(
    private val repositoriesList: List<RepositoriesModel>,
    private val onCardClicked: (name: Int) -> Unit
) : RecyclerView.Adapter<RepositoriesListAdapter.RepositoriesListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesListHolder {
        val binding = ItemRepositoriesListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoriesListHolder(binding)

    }

    override fun getItemCount(): Int = repositoriesList.size

    override fun onBindViewHolder(holder: RepositoriesListHolder, position: Int) {
        val repository = repositoriesList[position]
        holder.repositoryName.text = repository.name
        holder.repositoryDescription.text = repository.description
        holder.languageCode.text = repository.language
        holder.repositoryListRoot.setOnClickListener{
            onCardClicked(position)
        }
    }

    class RepositoriesListHolder(
        binding: ItemRepositoriesListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val repositoryName: TextView = binding.nameRepository
        val repositoryDescription: TextView = binding.descriptionRepository
        val languageCode: TextView = binding.kotlinOrJava
        val repositoryListRoot: CardView= binding.repoListRoot

    }

}