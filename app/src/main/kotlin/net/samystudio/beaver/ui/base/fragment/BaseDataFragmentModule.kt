package net.samystudio.beaver.ui.base.fragment

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import net.samystudio.beaver.di.qualifier.FragmentContext
import net.samystudio.beaver.di.scope.FragmentScope
import net.samystudio.beaver.ext.GlideApp
import net.samystudio.beaver.ext.GlideRequests
import net.samystudio.beaver.ui.base.viewmodel.FragmentViewModelFactory

@Module
abstract class BaseDataFragmentModule
{
    @Binds
    @FragmentScope
    @FragmentContext
    abstract fun bindViewModelFactory(viewModelFactory: FragmentViewModelFactory): ViewModelProvider.Factory

    @Module
    companion object
    {
        @Provides
        @FragmentScope
        @FragmentContext
        @JvmStatic
        fun provideViewModelProvider(fragment: Fragment,
                                     @FragmentContext
                                     viewModelFactory: ViewModelProvider.Factory): ViewModelProvider =
            ViewModelProviders.of(fragment, viewModelFactory)

        @Provides
        @FragmentScope
        @FragmentContext
        @JvmStatic
        fun provideGlideRequests(fragment: Fragment): GlideRequests =
            GlideApp.with(fragment)
    }
}
