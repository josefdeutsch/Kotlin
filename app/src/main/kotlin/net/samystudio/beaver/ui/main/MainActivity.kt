package net.samystudio.beaver.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import net.samystudio.beaver.NavigationMainDirections
import net.samystudio.beaver.R
import net.samystudio.beaver.databinding.ActivityMainBinding
import net.samystudio.beaver.ext.toggleLightBars
import net.samystudio.beaver.ext.viewBinding
import net.samystudio.beaver.ui.common.dialog.LaunchDialog
import net.samystudio.beaver.ui.common.dialog.LoaderDialog

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    private val binding by viewBinding { ActivityMainBinding.inflate(it) }
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        // We set launch screen theme from manifest, we need to get back to our Theme to remove
        // launch screen.
        setTheme(R.style.Theme_MyApp)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        toggleLightBars(true)

        /*
        We don't use navigation component here because when starting we'll navigate home screen and
        if a dialog is opened it will be dismissed and we don't want this LaunchDialog to be dismiss
        while it has not finish initializing things up.
        */
        LaunchDialog().also {
            if (supportFragmentManager.findFragmentByTag(LoaderDialog::class.simpleName) == null)
                it.show(supportFragmentManager, LoaderDialog::class.simpleName)
        }

        viewModel.userStatusLiveData.observe(this, {
            if (!it) findNavController(R.id.nav_host).navigate(
                NavigationMainDirections.actionGlobalAuthenticator()
            )
        })
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        findNavController(R.id.nav_host).addOnDestinationChangedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean = findNavController(R.id.nav_host).navigateUp()

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?,
    ) {

    }
}
