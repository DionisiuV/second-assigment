package com.valentin.secondhomework.application

import com.valentin.secondhomework.services.dataService.DataService
import com.valentin.secondhomework.services.dataService.DataServiceProvider
import com.valentin.secondhomework.services.navService.MainActivityNavService
import com.valentin.secondhomework.services.navService.MainActivityNavServiceProvider
import com.valentin.secondhomework.services.networkService.NetworkService
import com.valentin.secondhomework.services.networkService.NetworkServiceProvider
import com.valentin.secondhomework.view.activity.main.MainActivityViewModel
import com.valentin.secondhomework.view.fragment.screenOne.ScreenOneViewModel
import com.valentin.secondhomework.view.fragment.screenTwo.ScreenTwoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModules = module {

    viewModel { ScreenOneViewModel(get(), get()) }

    viewModel { ScreenTwoViewModel(get(), get()) }

    viewModel { MainActivityViewModel(get()) }
}

val serviceModules = module {

    single<DataService> { DataServiceProvider(get()) }

    single<MainActivityNavService> { MainActivityNavServiceProvider() }
}

val networkModules = module {
    single<NetworkService> { NetworkServiceProvider() }
}
