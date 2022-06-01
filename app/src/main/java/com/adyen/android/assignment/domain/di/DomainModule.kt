package com.adyen.android.assignment.domain.di

import android.annotation.SuppressLint
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@SuppressLint("VisibleForTests")
@Module(
    includes = [
        PlanetaryDomainModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
class DomainModule