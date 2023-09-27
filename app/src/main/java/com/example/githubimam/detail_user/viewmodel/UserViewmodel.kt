import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubimam.data.response.FollowersResponseItem
import com.example.githubimam.data.response.FollowingResponseItem
import com.example.githubimam.data.retrofit.ApiConfig
import com.example.githubimam.detail_user.viewmodel.FollowingViewmodel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {

    private val _followers = MutableLiveData<List<FollowersResponseItem>>()
    val followers: LiveData<List<FollowersResponseItem>> = _followers

    private val _following = MutableLiveData<List<FollowingResponseItem>>()
    val following: LiveData<List<FollowingResponseItem>> = _following

    companion object {
        private const val TAG = "Followers and Following"

    }

    internal fun getFollowers(username: String) {

        val client = ApiConfig.getApiService().getFollowers(username)

        client.enqueue(object : Callback<List<FollowersResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowersResponseItem>>,
                response: Response<List<FollowersResponseItem>>
            ) {
                if (response.isSuccessful) {
                    _followers.value = response.body()
                } else {
                    Log.d(TAG, "Response not successful")
                }
            }

            override fun onFailure(call: Call<List<FollowersResponseItem>>, t: Throwable) {
                Log.e(TAG, "Followers not get", t)
            }
        })
    }

    internal fun getFollowing(username: String) {

        val client = ApiConfig.getApiService().getFollowing(username)

        client.enqueue(object : Callback<List<FollowingResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowingResponseItem>>,
                response: Response<List<FollowingResponseItem>>
            ) {
                if (response.isSuccessful) {
                    _following.value = response.body()
                    Log.d(FollowingViewmodel.TAG, "Response: ${response.body()}}")

                } else {
                    Log.d(FollowingViewmodel.TAG, "Response not successful")
                }
            }

            override fun onFailure(call: Call<List<FollowingResponseItem>>, t: Throwable) {
                Log.e(FollowingViewmodel.TAG, "Following not get", t)
            }


        })
    }
}
