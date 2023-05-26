package com.juanma.blogmvvm.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.juanma.blogmvvm.core.Constants.POSTS
import com.juanma.blogmvvm.core.Constants.USERS
import com.juanma.blogmvvm.data.repository.AuthRepositoryImpl
import com.juanma.blogmvvm.data.repository.PostsRepositoryImpl
import com.juanma.blogmvvm.data.repository.UsersRepositoryImpl
import com.juanma.blogmvvm.domain.repository.AuthRepository
import com.juanma.blogmvvm.domain.repository.PostsRepository
import com.juanma.blogmvvm.domain.repository.UsersRepository
import com.juanma.blogmvvm.domain.use_cases.auth.*
import com.juanma.blogmvvm.domain.use_cases.posts.CreatePost
import com.juanma.blogmvvm.domain.use_cases.posts.DeletePost
import com.juanma.blogmvvm.domain.use_cases.posts.GetPosts
import com.juanma.blogmvvm.domain.use_cases.posts.GetPostsByIdUser
import com.juanma.blogmvvm.domain.use_cases.posts.PostsUseCases
import com.juanma.blogmvvm.domain.use_cases.posts.UpdatePost
import com.juanma.blogmvvm.domain.use_cases.users.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@InstallIn(SingletonComponent:: class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun providerFirebaseStorage():FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Named(USERS)
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference = storage.reference.child(USERS)

    @Provides
    @Named(USERS)
    fun providerUserRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    @Named(POSTS)
    fun provideStoragePostRef(storage: FirebaseStorage): StorageReference = storage.reference.child(POSTS)

    @Provides
    @Named(POSTS)
    fun providerPostRef(db: FirebaseFirestore): CollectionReference = db.collection(POSTS)

    @Provides
    fun provideFirebaseAuth():FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl):AuthRepository = impl

    @Provides
    fun providerUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun providerPostsRepository(impl: PostsRepositoryImpl): PostsRepository = impl

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
    @Provides
    fun providerPostsUseCases(repository: PostsRepository) = PostsUseCases(
        create = CreatePost(repository),
        getPosts = GetPosts(repository),
        getPostsByIdUser = GetPostsByIdUser(repository),
        deletePost = DeletePost(repository),
        updatePost = UpdatePost(repository)
    )
}