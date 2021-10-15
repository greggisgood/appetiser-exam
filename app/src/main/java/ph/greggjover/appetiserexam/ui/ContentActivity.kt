package ph.greggjover.appetiserexam.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ph.greggjover.appetiserexam.R
import ph.greggjover.appetiserexam.databinding.ActivityContentBinding

/**
 * Activity that serves as the entrypoint for the entire app
 */
@AndroidEntryPoint
class ContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContentBinding

    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHost).navController
    }

    private val appBarConfig by lazy {
        AppBarConfiguration.Builder().setFallbackOnNavigateUpListener {
            finish()
            true
        }.build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_content)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }


}