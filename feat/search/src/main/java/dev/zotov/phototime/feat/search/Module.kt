package dev.zotov.phototime.feat.search

import org.koin.dsl.module

val searchModule = module {
    single { SearchViewModel() }
}