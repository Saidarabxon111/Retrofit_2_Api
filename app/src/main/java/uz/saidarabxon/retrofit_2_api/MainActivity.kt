package uz.saidarabxon.retrofit_2_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.saidarabxon.retrofit_2_api.adapters.RvAdapter
import uz.saidarabxon.retrofit_2_api.databinding.ActivityMainBinding
import uz.saidarabxon.retrofit_2_api.databinding.ItemDialogBinding
import uz.saidarabxon.retrofit_2_api.models.MyTodoPostRequeest
import uz.saidarabxon.retrofit_2_api.models.MyTodoResponse
import uz.saidarabxon.retrofit_2_api.retrifit.ApiClient

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
      getData()





    }

    private fun getData() {
        ApiClient.getRetrofitServise().getAllToDo()
            .enqueue(object :Callback<List<MyTodoResponse>>{
                override fun onResponse(
                    call: Call<List<MyTodoResponse>>,
                    response: Response<List<MyTodoResponse>>
                ) {
                    if (response.isSuccessful){

                        rvAdapter = RvAdapter(response.body()!!)
                        binding.rv.adapter =rvAdapter
                    }
                }

                override fun onFailure(call: Call<List<MyTodoResponse>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "internetda xatolik", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        postData()
        return super.onOptionsItemSelected(item)
    }

    private fun postData() {
val dialog =AlertDialog.Builder(this).create()
        val itemDialogBinding =ItemDialogBinding.inflate(layoutInflater)
        dialog.setView(itemDialogBinding.root)
itemDialogBinding.apply {
        itemDialogBinding.btnSave.setOnClickListener {
            val myTodoPostRequeest =MyTodoPostRequeest(
                    spinner.selectedItem.toString(),
                    edtMatn.text.toString(),
                edtMuddat.text.toString(),
                edtSarlavha.text.toString()
                )
            ApiClient.getRetrofitServise().addTodo(myTodoPostRequeest)
                .enqueue(object :Callback<MyTodoResponse>{
                    override fun onResponse(
                        call: Call<MyTodoResponse>,
                        response: Response<MyTodoResponse>
                    ) {
                        if (response.isSuccessful){

                            Toast.makeText(this@MainActivity, "${response.body()?.sarlavha} saqlandi", Toast.LENGTH_SHORT).show()
                        dialog.cancel()
                            getData()
                        }

                    }

                    override fun onFailure(call: Call<MyTodoResponse>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "saqlanmadi", Toast.LENGTH_SHORT).show()
                    }
                })
        }
}
        dialog.show()


    }
}