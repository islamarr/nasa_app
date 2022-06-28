package com.adyen.android.assignment.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.adyen.android.assignment.common.Action
import com.adyen.android.assignment.common.ViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseFragment<viewBinding : ViewBinding, STATES : ViewState, ACTIONS : Action> :
    Fragment() {

    abstract val viewModel: BaseViewModel<STATES, ACTIONS>

    private var _binding: viewBinding? = null
    protected val binding: viewBinding
        get() {
            return _binding ?: throw IllegalStateException(
                "data binding should not be requested before onViewCreated is called"
            )
        }

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> viewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupOnViewCreated()
        startObserver()
    }

    abstract fun setupOnViewCreated()

    private fun startObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    handleViewState(it)
                }
            }
        }
    }

    abstract fun handleViewState(it: STATES)

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}