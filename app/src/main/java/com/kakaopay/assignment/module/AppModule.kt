package com.kakaopay.assignment.module

import com.kakaopay.assignment.data.api.DaumSearchApi
import com.kakaopay.assignment.data.datasource.SearchDataSource
import com.kakaopay.assignment.data.datasource.SearchDataSourceRemoteImpl
import com.kakaopay.assignment.domain.repository.PagedSearchRepository
import com.kakaopay.assignment.domain.repository.SearchRepository
import com.kakaopay.assignment.domain.usecase.SearchBookUseCase
import com.kakaopay.assignment.domain.usecase.SearchUseCase
import com.kakaopay.assignment.module.network.createNetworkClient
import com.kakaopay.assignment.presentation.ui.MainViewModel
import com.kakaopay.assignment.presentation.ui.SearchBookListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single { createNetworkClient(androidContext()) }
    single { get<Retrofit>().create(DaumSearchApi::class.java) }
}

val repositoryModules = module {
    single<SearchDataSource> { SearchDataSourceRemoteImpl(api = get()) }
    factory<SearchRepository> { PagedSearchRepository(api = get(), remoteDataSource = get()) }
}

val useCaseModules = module {
    factory<SearchUseCase> { SearchBookUseCase(repository = get()) }
}

val viewModuleModules = module {
    viewModel { MainViewModel() }
    viewModel { SearchBookListViewModel(searchUseCase = get()) }
}
