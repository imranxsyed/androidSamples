package com.isyed.samples

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.ColorInt
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FoodItemAdapter(
        private var menuItems: MutableList<MenuItem> = mutableListOf(),
        private val callbackUpdateItem: (MenuItem) -> Unit,
        private val callbackOnItemStatusChange: (MenuItem) -> Unit
) : RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {

    class FoodItemViewHolder(vendorFoodItemView: View) : RecyclerView.ViewHolder(vendorFoodItemView) {
        private val foodItemImageView: ImageView = vendorFoodItemView.findViewById(R.id.food_item_picture)
        private val foodItemName: TextView = vendorFoodItemView.findViewById(R.id.food_item_name)
        private var foodItemPrice: TextView = vendorFoodItemView.findViewById(R.id.food_item_price)
        private var foodItemDescription: TextView = vendorFoodItemView.findViewById(R.id.food_item_description)

        @SuppressLint("SetTextI18n")
        fun onBind(
                foodItem: MenuItem,
                callbackUpdateItem: (MenuItem) -> Unit,
                callbackOnUpdateStatusChange: (MenuItem) -> Unit,
                position: Int
        ) {
            //TODO CHANGE THE PIC LATER
            Picasso.get()
                    .load(foodItem.image_url)
                    .into(foodItemImageView)

            foodItemPrice.text = "$" + foodItem.price
            foodItemDescription.text = foodItem.description
            foodItemName.text = foodItem.name
            if (position % 2 == 0){
                foodItemName.setTextColor(Color.RED)
            }
            else{

                foodItemName.setTextColor(Color.GREEN)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.vendor_food_item_editable, parent, false)
        return FoodItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        holder.onBind(menuItems[position],
                callbackUpdateItem,
                callbackOnItemStatusChange, position)
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    fun addItem(item: MenuItem) {
        menuItems.add(item)
        notifyDataSetChanged()
    }

    fun editItem(item: MenuItem) {
        menuItems[menuItems.indexOf(item)] = item
        notifyDataSetChanged()
    }

    fun updateDataSet(dataSet: MutableList<MenuItem>) {
        this.menuItems = dataSet
        notifyDataSetChanged()
    }
}