package com.radiant.rpl.testa_kotlin.adapter

import android.content.Context
import com.radiant.rpl.testa_kotlin.model.ItemModal
import androidx.recyclerview.widget.RecyclerView
import com.radiant.rpl.testa_kotlin.adapter.IconsAdapter.viewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.radiant.rpl.testa_kotlin.R
import android.widget.TextView
import java.util.ArrayList

class IconsAdapter(var context: Context, var arrayList: ArrayList<ItemModal>) :
    RecyclerView.Adapter<viewHolder>() {

    lateinit var onClickItem:OnClickItem
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): viewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.icon_list, viewGroup, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: viewHolder, position: Int) {
        viewHolder.icon.setImageResource(arrayList[position].image)
        viewHolder.icon2.setImageResource(arrayList[position].image2)
        viewHolder.iconName.text = arrayList[position].name

        viewHolder.itemView.setOnClickListener {
            onClickItem.onClick(position)

            if (position == 0) {
                /* Intent intent = new Intent(context, CompanyDropdownActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);*/

            }
            if (position == 1) {
                /*       Intent intent2 = new Intent(context, CompanyDropdownActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent2.putExtra("naps", "5");
                        context.startActivity(intent2);*/
            }
            if (position == 2) {

                /* Intent intent1 = new Intent(context, VisualMerchandisingTradeActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent1);*/
            }
            if (position == 5) {

//                    Intent vCall = new Intent(context, AppRTCMainActivity.class);
//                    vCall.putExtra(freeCall, "free");
//                    vCall.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(vCall);


//                    Intent intent1 = new Intent(context, VisualMerchandisingTradeActivity.class);
//                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent1);
            }
            if (position == 4) {

                /* Intent intentCorporate = new Intent(context, CompanyDropdownActivity.class);
                        intentCorporate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intentCorporate.putExtra("corporate", "6");
                        context.startActivity(intentCorporate);*/
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView
        var icon2: ImageView
        var iconName: TextView

        init {
            icon = itemView.findViewById(R.id.icon)
            iconName = itemView.findViewById(R.id.icon_name)
            icon2 = itemView.findViewById(R.id.icon1)
        }
    }

    fun setOnClickListener(onClickItem: OnClickItem) {
        this.onClickItem = onClickItem
    }

    interface OnClickItem {
        fun onClick(position: Int)
    }
}