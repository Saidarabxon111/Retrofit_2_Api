package uz.saidarabxon.retrofit_2_api.retrifit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uz.saidarabxon.retrofit_2_api.models.MyTodoPostRequeest
import uz.saidarabxon.retrofit_2_api.models.MyTodoResponse

interface RetrofitService {

    @GET("plan")
    fun  getAllToDo():Call<List<MyTodoResponse>>
@POST("plan/")
    fun addTodo(@Body myTodoPostRequeest: MyTodoPostRequeest):Call<MyTodoResponse>

}