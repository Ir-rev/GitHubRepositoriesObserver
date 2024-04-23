package ru.marina.githubrepositoriesobserver.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.R
import ru.marina.githubrepositoriesobserver.databinding.FragmentAuthBinding
import ru.marina.githubrepositoriesobserver.databinding.FragmentDetailInfoBinding

@AndroidEntryPoint
class DetailInfoFragment @Inject constructor() : Fragment() {

    private var binding: FragmentDetailInfoBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding=FragmentDetailInfoBinding.inflate(inflater,container,false)
        this.binding=binding
        return binding.root
    }

    override fun onDestroy() {
        binding= null
        super.onDestroy()
    }
}