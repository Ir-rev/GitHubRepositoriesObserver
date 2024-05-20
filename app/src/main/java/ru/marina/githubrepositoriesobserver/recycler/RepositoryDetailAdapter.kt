package ru.marina.githubrepositoriesobserver.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalStateException
import ru.marina.githubrepositoriesobserver.databinding.ItemActiveLinkBinding
import ru.marina.githubrepositoriesobserver.databinding.ItemDescriptionBinding
import ru.marina.githubrepositoriesobserver.databinding.ItemLicenseBinding
import ru.marina.githubrepositoriesobserver.databinding.ItemStarForkWatcherBinding
import ru.marina.githubrepositoriesobserver.state.RepositoryInfoItem

private const val LINK = 0
private const val STATISTIC = 1
private const val LICENSE = 2
private const val DESCRIPTION = 3


class RepositoryDetailAdapter(
    private val repositoryInfoItem: List<RepositoryInfoItem>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class RepositoryActiveLinkHolder(
        bindingLink: ItemActiveLinkBinding
    ) : RecyclerView.ViewHolder(bindingLink.root) {
        val linkText: TextView = bindingLink.linkActive

    }

    class RepositoryLicenseHolder(bindingLicense: ItemLicenseBinding) :
        RecyclerView.ViewHolder(bindingLicense.root) {
        val licenseKey: TextView = bindingLicense.licenseKey


    }

    class RepositoryStarForkWatcherHolder(bindingStarForkWatcher: ItemStarForkWatcherBinding) :
        RecyclerView.ViewHolder(bindingStarForkWatcher.root) {
        val numberStars: TextView = bindingStarForkWatcher.starsNumber
        val numberForks: TextView = bindingStarForkWatcher.forksNumber
        val numberWatchers: TextView = bindingStarForkWatcher.watchersNumber

    }

    class RepositoryDescriptionHolder(bindingDescription: ItemDescriptionBinding) :
        RecyclerView.ViewHolder(bindingDescription.root) {
        val descriptionText: TextView = bindingDescription.descriptionText


    }

    override fun getItemViewType(position: Int): Int {
        val item = repositoryInfoItem[position]
        return when (item) {
            is RepositoryInfoItem.Description -> DESCRIPTION
            is RepositoryInfoItem.License -> LICENSE
            is RepositoryInfoItem.Link -> LINK
            is RepositoryInfoItem.Statistic -> STATISTIC
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LINK -> {
                val binding = ItemActiveLinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RepositoryActiveLinkHolder(binding)
            }

            STATISTIC -> {
                val binding = ItemStarForkWatcherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RepositoryStarForkWatcherHolder(binding)
            }

            LICENSE -> {
                val binding = ItemLicenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RepositoryLicenseHolder(binding)
            }

            DESCRIPTION -> {
                val binding = ItemDescriptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RepositoryDescriptionHolder(binding)
            }

            else -> throw IllegalStateException("")
        }

    }

    override fun getItemCount(): Int = repositoryInfoItem.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RepositoryActiveLinkHolder -> {
                val item = repositoryInfoItem[position] as? RepositoryInfoItem.Link ?: return
                holder.linkText.text = item.link
            }

            is RepositoryLicenseHolder -> {
                val item = repositoryInfoItem[position] as? RepositoryInfoItem.License ?: return
                holder.licenseKey.text = item.nameLicense
            }

            is RepositoryDescriptionHolder -> {
                val item = repositoryInfoItem[position] as? RepositoryInfoItem.Description ?: return
                holder.descriptionText.text = item.description
            }

            is RepositoryStarForkWatcherHolder -> {
                val item = repositoryInfoItem[position] as? RepositoryInfoItem.Statistic ?: return
                holder.numberForks.text = item.fork
                holder.numberStars.text = item.star
                holder.numberWatchers.text = item.watchers

            }
        }


    }


}