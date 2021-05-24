package com.bobryshev.genesistest2.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bobryshev.genesistest2.R
import com.bobryshev.genesistest2.databinding.DetailFragmentBinding

class DetailFragment: Fragment() {

    private lateinit var layout: DetailFragmentBinding

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        layout = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout.webView.settings.javaScriptEnabled = true
        layout.webView.loadUrl(args.htmlUrl)
    }
}