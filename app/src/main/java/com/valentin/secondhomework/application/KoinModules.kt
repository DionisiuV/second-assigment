package com.valentin.secondhomework.application

import com.valentin.secondhomework.model.service.dataService.DataService
import com.valentin.secondhomework.model.service.dataService.DataServiceProvider
import com.valentin.secondhomework.model.service.navService.MainActivityNavService
import com.valentin.secondhomework.model.service.navService.MainActivityNavServiceProvider
import com.valentin.secondhomework.view.activity.main.MainViewModel
import com.valentin.secondhomework.view.screenOne.ScreenOneViewModel
import com.valentin.secondhomework.view.screenTwo.ScreenTwoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModules = module {

    viewModel { ScreenOneViewModel(get(), get()) }

    viewModel { ScreenTwoViewModel(get(), get()) }

    viewModel { MainViewModel(get()) }
}

val serviceModules = module {

    single<DataService> { DataServiceProvider() }

    single<MainActivityNavService> { MainActivityNavServiceProvider() }
}

val networkModules = module {

}
