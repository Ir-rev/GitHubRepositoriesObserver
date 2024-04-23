package ru.marina.githubrepositoriesobserver.repository

import javax.inject.Inject
import javax.inject.Singleton
import ru.marina.githubrepositoriesobserver.Const
import ru.marina.githubrepositoriesobserver.provider.UserLoginProviderRetrofit

@Singleton
class AuthUserLoginRepository @Inject constructor(
    private var userLoginProviderRetrofit: UserLoginProviderRetrofit
) {
    //
    suspend fun authLoginUser(token: String): String {
        return userLoginProviderRetrofit.getUserLoginProviderRetrofit()
            .getUserLogin("${Const.START_POINT} $token")
            .login
    }

}