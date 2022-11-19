package com.shrouk.chat

import android.R
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.shrouk.chat.databinding.MessageItemListBinding
import com.shrouk.chat.databinding.SenderItemListBinding


class MessageAdapter(var messageList:List<Message>)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_ONE = "0"
        const val VIEW_TYPE_TWO = "1"

    }

//    open inner class ViewHolder(val binding: SenderItemListBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        var newmessage = binding.messageTextview
//        var userid = binding.userid
//
//        //        val message = messageList[position]
////        newmessage.text = message.message
////        userid.text = message.userId
//        fun bind(position: Int) {
//            val message = messageList[position]
//            newmessage.text = message.message
//            userid.text = message.userId
//            //  var image=binding.image
//            var userid = binding.userid
//        }
//    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun getItemViewType(position: Int): Int {
        Log.e("TAG", "getItemViewType: +${messageList[position].userId!!.toInt()}")
        return messageList[position].userId!!.toInt()

    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//
//        val view = LayoutInflater.from(parent?.context)
//        when (viewType) {
//            0 -> return ViewHolder(
//                SenderItemListBinding.inflate(view, parent, false)
//            )
//            1 -> return ViewHolder(
//
//                MessageItemListBinding.inflate(view, parent, false)
//            )
//        }
//        return ViewHolder(
//            MessageItemListBinding.inflate(view, parent, false)
//        )    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //      var _message=message[position]
//        holder.newmessage.text=_message.message
//        holder.userid.text=_message.userId
//     //Glide.with(View(context)).load(_message.userImage).into(holder.image)
        if (messageList[position].userId == VIEW_TYPE_ONE) {
            (holder as ViewHolder1).bind(position)
        } else {
            (holder as ViewHolder2).bind(position)
        }


    }

    private inner class ViewHolder1(val binding: SenderItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var newmessage = binding.messageTextview
        var userid = binding.userid

        fun bind(position: Int) {
            val message = messageList[position]
            newmessage.text = message.message
            userid.text = message.userId
//      //  var image=binding.image
//        var userid=binding.userid
        }
    }

    private inner class ViewHolder2(val binding: MessageItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var newmessage = binding.messageTextview
        var userid = binding.userid

        fun bind(position: Int) {
            val message = messageList[position]
            newmessage.text = message.message
            userid.text = message.userId
        }
//      //  var image=binding.image
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
//        val binding=MessageItemListBinding.inflate(view,parent,false)
//        return MessageViewHolder(binding)
        if (viewType == 0) {
            return ViewHolder1(
                SenderItemListBinding.inflate(view, parent, false)
            )
        } else
            return ViewHolder2(
                MessageItemListBinding.inflate(view, parent, false)
            )

    }
}

//    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
//        val view = LayoutInflater.from(parent?.context)
//        when (viewType) {
//            0 -> return ViewHolder(
//                 SenderItemListBinding.inflate(view, parent, false)
//            )
//            1 -> return ViewHolder(
//           MessageItemListBinding.inflate(view, parent, false)
//            )
//        }
//        return ViewHolder(
//                MessageItemListBinding.inflate(view, parent, false)
//        )
//    }



