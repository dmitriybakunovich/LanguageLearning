package com.dmitriybakunovich.languagelearning.ui.text

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.dmitriybakunovich.languagelearning.R
import kotlinx.android.synthetic.main.activity_text_container.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class TextContainerActivity : AppCompatActivity() {

    private lateinit var viewModel: TextViewModel
    private val args: TextContainerActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_container)

        viewModel = getViewModel { parametersOf(args.book) }
        initToolbar()
        initView()
        observeView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.text_menu, menu)
        val textNext = menu?.findItem(R.id.textNext)
        val textBack = menu?.findItem(R.id.textBack)
        textNext?.setOnMenuItemClickListener {
            viewModel.nextPageClick()
            return@setOnMenuItemClickListener true
        }
        textBack?.setOnMenuItemClickListener {
            viewModel.backPageClick()
            return@setOnMenuItemClickListener true
        }
        return true
    }

    private fun initToolbar() {
        setSupportActionBar(toolbarTextContainer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbarTextContainer.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initView() {
        txtPageList.setOnClickListener {
            viewModel.finishReadBook()
            finish()
        }
    }

    private fun observeView() {
        viewModel.lastPageState.observe(this, {
            if (it) {
                txtPageList.visibility = View.VISIBLE
            } else {
                txtPageList.visibility = View.GONE
            }
        })
    }
}
