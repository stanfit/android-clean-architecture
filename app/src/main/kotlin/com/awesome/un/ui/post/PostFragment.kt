package com.awesome.un.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.awesome.un.R
import com.awesome.un.ext.groupAdapter
import com.awesome.un.ext.setGroupOnItemClickListener
import com.awesome.un.ui.component.RecyclerViewStatefulObserver
import com.awesome.un.ui.post.item.PostItem
import kotlinx.android.synthetic.main.fragment_post.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Post List Screen. [Fragment] subclass.
 */
class PostFragment : Fragment() {

    /**
     * Post List Screen's ViewModel
     */
    private val viewModel: PostViewModel by viewModel()

    // region Lifecycle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        bindInput()
        bindOutput()
    }

    // endregion

    /**
     * View initial settings
     */
    private fun initialize() {
        recyclerView.adapter = groupAdapter.apply {
            setGroupOnItemClickListener<PostItem> { item, _ ->
                findNavController().navigate(PostFragmentDirections.actionToPostDetail(item.post))
            }
        }
    }

    /**
     * Instruction from View to ViewModel
     */
    private fun bindInput() {
    }

    /**
     * Instruction from ViewModel to View
     */
    private fun bindOutput() {
        val observer = RecyclerViewStatefulObserver(recyclerView, emptyView, progressBar)
        viewModel.posts.observe(this, Observer {
            val items = PostItem.from(it)
            recyclerView.groupAdapter?.update(items)
            observer.invalidate()
        })
    }
}
