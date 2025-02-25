package com.example.myapp.ui.products

import androidx.lifecycle.ViewModel
import com.example.myapp.R

class ProductViewModel: ViewModel() {
    val productList = listOf(
        Product("101","Product 1","Products",10.0,"image " ,R.drawable.watch1),
        Product("102","Product 2","Products",10.0,"image " , R.drawable.tv),
        Product("103","Product 3","Products",10.0,"image " , R.drawable.bag),
        Product("104","Product 4","Products",10.0,"image " , R.drawable.orange),
        Product("105","Product 5","Products",10.0,"image " , R.drawable.phone),
        Product("106","Product 6","Products",10.0,"image " , R.drawable.laptop)

    )
    val buildingList = listOf(
        Product("b1", "House 1", "Modern architecture", 120.0, "image",R.drawable.house1),
        Product("b2","House 2", "Modern architecture", 150.0, "image",R.drawable.house2),
        Product("b3","House 3", "Modern architecture", 120.0, "image",R.drawable.house3),
        Product("b4", "House 4", "Modern architecture", 120.0, "image",R.drawable.house7),
        Product("b5","House 5", "Modern architecture", 150.0, "image",R.drawable.house5),
        Product("b6","House 6", "Modern architecture", 120.0, "image",R.drawable.house6),

    )
}
