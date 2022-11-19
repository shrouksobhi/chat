package com.shrouk.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.shrouk.chat.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
   private lateinit var newMessageData:Message

    private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var mref: DatabaseReference =database.reference
       // var ref=database.getReference("Messages")
        mref.child("Messages")



        binding.btnsend.setOnClickListener {

            var message=  binding.message.text.toString()

            newMessageData=Message("0",message)
            mref.child("Messages").push().setValue(newMessageData)
            binding.message.text.clear()

        }
//     var m1=Message(0,R.drawable.send,"Hi user2")
//     var m2=Message(1,R.drawable.send,"Hi user1")
//     var m3=Message(0,R.drawable.send,"How are you")
//     var m4=Message(1,R.drawable.send,"fine!")
//
//
//        mref.child("Messages").push().setValue(m2)
//        mref.child("Messages").push().setValue(m3)
//        mref.child("Messages").push().setValue(m4)
   var list= mutableListOf<Message>()
        val messageListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                  list.clear()
                // Get Post object and use the values to update the UI
                val mymessage= dataSnapshot.children.forEach {
                    Log.e("TAG", "onDataChange: "+it )
                    //val mesage=Message(it.value)

                 // var m1=  it.getValue <Message>()!!
                  var  m1 = it.getValue<Message>()!!
                   list.add(m1)
                    installViews(list)
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("Home", "loadmessage:onCancelled", databaseError.toException())
            }
        }

        mref.child("Messages").addValueEventListener(messageListener)


    }
    private fun installViews(list :List<Message>) {
        var recyclerView=binding.recyclerview

        var layoutManager:LayoutManager= LinearLayoutManager(activity?.applicationContext)
        var messageAdapter=MessageAdapter(list )
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=messageAdapter
     //   recyclerView.scrollToPosition(list.size - 1)
        recyclerView.scrollToPosition(list.size-1)
//        val smoothScroller: SmoothScroller = object : LinearSmoothScroller(context) {
//            override fun getVerticalSnapPreference(): Int {
//                return SNAP_TO_START
//            }
//        }

    }




}