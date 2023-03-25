package com.juanma.blogmvvm.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.juanma.blogmvvm.core.Constants.USERS
import com.juanma.blogmvvm.data.repository.AuthRepositoryImpl
import com.juanma.blogmvvm.data.repository.UsersRepositoryImpl
import com.juanma.blogmvvm.domain.repository.AuthRepository
import com.juanma.blogmvvm.domain.repository.UsersRepository
import com.juanma.blogmvvm.domain.use_cases.auth.*
import com.juanma.blogmvvm.domain.use_cases.users.*
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
    fun providerFirebaseStorage():FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference =storage.reference.child(USERS)

    @Provides
    fun providerUserRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

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
        getUserById = GetUserById(repository),
        update = Update(repository),
        saveImage = SaveImage(repository)
    )
}