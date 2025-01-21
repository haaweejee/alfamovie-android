package id.haaweejee.test.alfagift.alfamovie.presentation.pages.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.haaweejee.test.alfagift.alfamovie.R
import id.haaweejee.test.alfagift.alfamovie.databinding.ActivityMainBinding
import id.haaweejee.test.alfagift.alfamovie.presentation.pages.main.screen.MainScreen
import id.haaweejee.test.alfagift.alfamovie.presentation.pages.main.vm.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.composeView.setContent {
            MainScreen(viewModel = viewModel)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }

    }
}
