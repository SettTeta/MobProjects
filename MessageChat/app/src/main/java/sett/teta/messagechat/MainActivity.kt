package sett.teta.messagechat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.message_in.view.*
import kotlinx.android.synthetic.main.message_out.view.*

class MainActivity : AppCompatActivity() {

    val messagesBank = listOf(Message(0, "Hey", "24/1/23 20.10 hrs."),
                              Message(1, "What’s up?", "24/1/23 20.11hrs."),
                              Message(0, "Wanna come over?", "24/1/23 20.15hrs."),
                              Message(0, "No ones home 😏", "24/1/23 20.15hrs."),
                              Message(1, "Be there in a fewminutes 😍", "24/1/23 20.16hrs."),
                              Message(0, "K", "24/1/23 20.16hrs."),
                              Message(1, "I’m here where are you?", "24/1/23 20.22hrs."),
                              Message(1, "Kitty...", "24/1/23 20.23hrs."),
                              Message(1, "The door is locked", "24/1/23 20.23hrs."),
                              Message(0, "I told you no one is home 🤪", "24/1/23 20.35hrs."))

    override fun onCreate(savedInstanceState: Bundle?)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageView.layoutManager = LinearLayoutManager(this)
        messageView.adapter = MessageAdapter(messagesBank)
    }

    inner class MessageViewHolder(view: View): RecyclerView.ViewHolder(view){
        val messageContent = if (Message().type == 0)itemView.messageInContent else itemView.messageOutContent
        val messageTime = if (Message().type == 0)itemView.messageInTime else itemView.messageOutTime
    }



    inner class MessageAdapter(var messages: List<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            val layout = when(viewType){
                Message().type -> R.layout.message_in
                else -> R.layout.message_out
            }

            val view = layoutInflater.inflate(layout, parent, false)
            return MessageViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//            MessageViewHolder().messageContent.text =  messages[position].message
        }

//        override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
//            holder.messageContent.text = messages[position].message
//            holder.messageTime.text = messages[position].time
//
//        }

        override fun getItemCount(): Int {
            return messages.size
        }



        override fun getItemViewType(position: Int): Int {
            return messages[position].type
        }

    }
}