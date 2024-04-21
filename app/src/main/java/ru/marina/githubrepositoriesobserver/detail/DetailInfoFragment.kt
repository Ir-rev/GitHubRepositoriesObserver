package ru.marina.githubrepositoriesobserver.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import ru.marina.githubrepositoriesobserver.R

@AndroidEntryPoint
class DetailInfoFragment @Inject constructor() : Fragment() {

    // TODO: Добавь вьюбайдинг не забудь занулить его

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_info, container, false)
    }
}