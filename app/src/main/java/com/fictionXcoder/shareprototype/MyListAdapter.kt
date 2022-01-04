package com.fictionXcoder.shareprototype

//import androidx.test.core.app.ApplicationProvider.getApplicationContext
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.RecyclerView
import java.io.File


class MyListAdapter(context: Context,private val mList: List<MyListData>): RecyclerView.Adapter<MyListAdapter.ViewHolder>() {
    val context = context
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.filename


        holder.optionmenu.setOnClickListener {
            val popup = PopupMenu(context, holder.optionmenu)
            popup.inflate(R.menu.options_menu)
            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
                override fun onMenuItemClick(item: MenuItem?): Boolean {
                    Log.d("itemnum", "onMenuItemClick:  $ItemsViewModel")
                    when(item?.itemId){
                        R.id.Load -> {
                            Log.d("OptionMenu","Load Clicked")
                            return true
                        }
                        R.id.Share -> {

                            Log.d("OptionMenu","Share Clicked ${ItemsViewModel.path}")

                            val shareIntent = Intent()
                            val uri = FileProvider.getUriForFile(context,
                                context.applicationContext.packageName + ".provider",
                                File(ItemsViewModel.path))
                            shareIntent.action = Intent.ACTION_SEND
                            context.grantUriPermission("com.fictionXcoder.shareprototype",
                                uri,
                                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            shareIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            shareIntent.type = "*/pdf"
                            shareIntent.putExtra(Intent.EXTRA_TEXT, "Files attached from ..")
                            shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
                            Log.d("OptionMenu",ItemsViewModel.path)
                            context.startActivity(Intent.createChooser(shareIntent, "Share File using"))
                            return true
                        }
                        R.id.Location -> {
                            Log.d("OptionMenu","Location Clicked")
                            return true
                        }
                }
                    return false
            }})
            popup.show()
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val optionmenu : TextView = itemView.findViewById(R.id.textViewOptions)
    }
}