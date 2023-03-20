package com.juanma.blogmvvm.di

import com.google.firebase.auth.FirebaseAuth
import com.juanma.blogmvvm.data.repository.AuthRepositoryImpl
import com.juanma.blogmvvm.domain.repository.AuthRepository
import com.juanma.blogmvvm.domain.use_cases.auth.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent:: class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuth():FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl):AuthRepository = impl

    @Provides
    fun providesAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signUp = SignUp(repository)
    )
}