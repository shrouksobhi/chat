package com.shrouk.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shrouk.chat.databinding.MessageItemListBinding

class MessageAdapter(var message:List<Message>,var context:Context)
    :RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {
    class MessageViewHolder(val binding:MessageItemListBinding)
        :RecyclerView.ViewHolder(binding.root) {
   var newmessage=binding.messageTextview
        var image=binding.image
        var userid=binding.userid

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view= LayoutInflater.from(parent.context)
        val binding=MessageItemListBinding.inflate(view,parent,false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        var _message=message[position]
        holder.newmessage.text=_message.message
        holder.userid.text=_message.userId.toString()
     //Glide.with(View(context)).load(_message.userImage).into(holder.image)
    }

    override fun getItemCount(): Int {
return message.size   }

}