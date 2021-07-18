package com.example.jemberatk.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.jemberatk.MainActivity
import com.example.jemberatk.R
import com.example.jemberatk.activity.DetailProdukActivity
import com.example.jemberatk.activity.LoginActivity
import com.example.jemberatk.helper.Helper
import com.example.jemberatk.model.Produk
import com.example.jemberatk.util.Config
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.util.*

class AdapterProduk(var activity: Activity, var data: ArrayList<Produk>) : RecyclerView.Adapter<AdapterProduk.Holder>() {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama = view.findViewById<TextView>(R.id.tv_nama)
        val tvHarga = view.findViewById<TextView>(R.id.tv_harga)
//        val tvHargaAsli = view.findViewById<TextView>(R.id.tv_hargaAsli)
        val imgProduk = view.findViewById<ImageView>(R.id.img_produk)
        val layout = view.findViewById<CardView>(R.id.layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {

        val a = data[position]

        val hargaAsli = Integer.valueOf(a.harga)
        var harga = Integer.valueOf(a.harga)

        holder.tvNama.text = data[position].name
        holder.tvHarga.text = Helper().gantiRupiah(harga)
        val image = Config.produkUrl + data[position].image
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.product)
            .error(R.drawable.product)
            .into(holder.imgProduk)

        holder.layout.setOnClickListener(){
            val activiti = Intent(activity, DetailProdukActivity::class.java)

            val str = Gson().toJson(data[position], Produk::class.java)
            activiti.putExtra("extra", str)

            activity.startActivity(activiti)
        }

    }

}