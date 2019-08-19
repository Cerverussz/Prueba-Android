package co.com.ceiba.mobile.pruebadeingreso

import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.InfoUserDao
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.InfoUser
import co.com.ceiba.mobile.pruebadeingreso.data.remote.api.ApiService
import co.com.ceiba.mobile.pruebadeingreso.models.repository.UserRepository
import org.junit.Before
import io.reactivex.subscribers.TestSubscriber


class UserRepositoryTest {

    lateinit var userRepository: UserRepository

    lateinit var userApi: ApiService
    lateinit var userDao: InfoUserDao

    lateinit var userFromApi1: InfoUser
    lateinit var userFromApi2: InfoUser
    lateinit var userFromDao1: InfoUser
    lateinit var userFromDao2: InfoUser

    @Before
    fun setUp() {
        //Mocking UserApi
        userFromApi1 = InfoUser(id = 0, name = "test0", email = "test0@mock.com", phone = "1234567890")
        userFromApi2 = InfoUser(id = 1, name = "test1", email = "test1@mock.com", phone = "0987654321")

        // mMockWebServer = MockWebServer()
        //mSubscriber = TestSubscriber<Any>()

    }


}