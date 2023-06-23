import com.example.myapp.models.Product
import com.example.myapp.models.Product1
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Body
import retrofit2.http.POST
interface ProductApiService {
    @GET("get")
    fun getProducts(): Call<List<Product>>
}



interface ProductService {
    @POST("api/public/add")
    fun addProduct(@Body product: Product1): Call<Void>
}
