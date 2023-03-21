package com.juanma.blogmvvm.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.juanma.blogmvvm.core.Constants.Users
import com.juanma.blogmvvm.data.repository.AuthRepositoryImpl
import com.juanma.blogmvvm.data.repository.UsersRepositoryImpl
import com.juanma.blogmvvm.domain.repository.AuthRepository
import com.juanma.blogmvvm.domain.repository.UsersRepository
import com.juanma.blogmvvm.domain.use_cases.auth.*
import com.juanma.blogmvvm.domain.use_cases.users.Create
import com.juanma.blogmvvm.domain.use_cases.users.GetUserById
import com.juanma.blogmvvm.domain.use_cases.users.UsersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent:: class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun providerUserRef(db: FirebaseFirestore): CollectionReference = db.collection(Users)

    @Provides
    fun provideFirebaseAuth():FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl):AuthRepository = impl

    @Provides
    fun providerUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun providesAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signUp = SignUp(repository)
    )

    @Provides
    fun providesUsersUseCases(repository: UsersRepository) = UsersUseCases(
        create = Create(repository),
        getUserById = GetUserById(repository)
    )
}