package net.samystudio.beaver.ui.main

import android.arch.lifecycle.LifecycleOwner
import dagger.Binds
import dagger.Module
import net.samystudio.beaver.di.scope.PerFragment
import net.samystudio.beaver.ui.base.fragment.BaseFragment
import net.samystudio.beaver.ui.base.fragment.BaseFragmentModule

@Module(includes = [BaseFragmentModule::class])
abstract class MainFragmentModule
{
    @Binds
    @PerFragment
    abstract fun bindFragment(fragment: MainFragment): BaseFragment

    @Binds
    @PerFragment
    abstract fun bindLifeCycleOwner(fragment: MainFragment): LifecycleOwner

    /*@Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @PerFragment
    abstract fun bindMainViewModel(model: MainViewModel): ViewModel*/
}