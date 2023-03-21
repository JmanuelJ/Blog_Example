package com.juanma.blogmvvm.domain.use_cases.users

import com.juanma.blogmvvm.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserById @Inject constructor(private val repository: UsersRepository){
    operator fun invoke(id: String) = repository.getUserById(id)
}