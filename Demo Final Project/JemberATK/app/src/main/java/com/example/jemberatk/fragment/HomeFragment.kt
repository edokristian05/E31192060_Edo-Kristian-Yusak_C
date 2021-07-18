package com.example.jemberatk.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.jemberatk.MainActivity

import com.example.jemberatk.R
import com.example.jemberatk.adapter.AdapterProduk
import com.example.jemberatk.adapter.AdapterSlider
import com.example.jemberatk.app.ApiConfig
import com.example.jemberatk.model.Produk
import com.example.jemberatk.model.ResponModel
import kotlinx.android.synthetic.main.activity_login.*
import me.relex.circleindicator.CircleIndicator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    lateinit var vpSlider: ViewPager
    lateinit var circleIndicator: CircleIndicator
    lateinit var rvProduk: RecyclerView
    lateinit var rvProdukTerlaris: RecyclerView

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        init(view)
        getProduk()

        return view
    }
    fun displayProduk(){
        val arrSlider = ArrayList<Int>()
        arrSlider.add(R.drawable.slider1)
        arrSlider.add(R.drawable.slider2)
        arrSlider.add(R.drawable.slider3)
        arrSlider.add(R.drawable.slider4)
        arrSlider.add(R.drawable.slider5)
        arrSlider.add(R.drawable.slider6)
        arrSlider.add(R.drawable.slider7)

        val adapterSlider = AdapterSlider(arrSlider, activity)
        vpSlider.adapter = adapterSlider
        circleIndicator.setViewPager(vpSlider)

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL


        rvProduk.adapter = AdapterProduk(requireActivity(), listProduk)
        rvProduk.layoutManager = layoutManager

        rvProdukTerlaris.adapter = AdapterProduk(requireActivity(), listProduk)
        rvProdukTerlaris.layoutManager = layoutManager2
    }

    private var listProduk: ArrayList<Produk> = ArrayList()
    fun getProduk() {
        ApiConfig.instanceRetrofit.getProduk().enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val res = response.body()!!
                if (res.success == 1) {
                    listProduk = res.produks
                    displayProduk()

                }
            }
        })
    }

    fun init(view: View){
        vpSlider = view.findViewById(R.id.vp_slider)
        circleIndicator = view.findViewById(R.id.circleIndicator)
        rvProduk = view.findViewById(R.id.rv_produk)
        rvProdukTerlaris = view.findViewById(R.id.rv_ProdukTerlaris)
    }

//        val arrProduk: ArrayList<Produk>get(){
//            val arr = ArrayList<Produk>()
//            val p1 = Produk()
//            p1.nama = "Hp Core i3"
//            p1.harga = "Rp. 5.900.000"
//            p1.gambar = R.drawable.hp_pavilion_14_ce1507sa
//
//            val p2 = Produk()
//            p2.nama = "Hp Core i5"
//            p2.harga = "Rp. 6.900.000"
//            p2.gambar = R.drawable.hp_notebook_14_bs709tu
//
//            val p3 = Produk()
//            p3.nama = "Hp Core i7"
//            p3.harga = "Rp. 9.900.000"
//            p3.gambar = R.drawable.hp_pavilion_13_an0006na
//
//            arr.add(p1)
//            arr.add(p2)
//            arr.add(p3)
//            return arr
//        }
//
//        val arrProdukTerlaris: ArrayList<Produk>get(){
//            val arr = ArrayList<Produk>()
//            val p1 = Produk()
//            p1.nama = "Hp Core i3"
//            p1.harga = "Rp. 5.900.000"
//            p1.gambar = R.drawable.hp_pavilion_14_ce1507sa
//
//            val p2 = Produk()
//            p2.nama = "Hp Core i5"
//            p2.harga = "Rp. 6.900.000"
//            p2.gambar = R.drawable.hp_notebook_14_bs709tu
//
//            val p3 = Produk()
//            p3.nama = "Hp Core i7"
//            p3.harga = "Rp. 9.900.000"
//            p3.gambar = R.drawable.hp_pavilion_13_an0006na
//
//            arr.add(p1)
//            arr.add(p2)
//            arr.add(p3)
//            return arr
//        }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}