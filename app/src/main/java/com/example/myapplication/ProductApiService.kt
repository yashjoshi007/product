import com.example.myapp.models.Product
import com.example.myapp.models.Product1
import okhttp3.MultipartBody
import okhttp3.RequestBody

import retrofit2.Call
import retrofit2.http.*

interface ProductApiService {
    @GET("get")
    fun getProducts(): Call<List<Product>>
}



interface ProductService {
    @Multipart
    @POST("api/public/add")
    fun addProduct(
        @Part("product_name") productName: RequestBody,
        @Part("product_type") productType: RequestBody,
        @Part("price") sellingPrice: RequestBody,
        @Part("tax") taxRate: RequestBody
    ): Call<Void>
}
