package com.nettest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.nettest.R.id.btn_msg_type;

public class ChatLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recycleViewChat;
    private EditText etMsgContent;
    private Button btnMsgType;
    private Button btnMsgSend;
    private List<Msg> msgList;

    private MsgAdapter mMsgApapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_layout);

        msgList = new ArrayList<>();
        initMsgList();

        recycleViewChat = (RecyclerView) findViewById(R.id.recycle_view_chat);
        etMsgContent = (EditText) findViewById(R.id.et_msg_content);
        btnMsgType = (Button) findViewById(btn_msg_type);
        btnMsgSend = (Button) findViewById(R.id.btn_msg_send);

        btnMsgType.setOnClickListener(this);
        btnMsgSend.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleViewChat.setLayoutManager(layoutManager);
        mMsgApapter = new MsgAdapter(msgList);
        recycleViewChat.setAdapter(mMsgApapter);

        // 设置Item增加、移除动画
//        recycleViewChat.setItemAnimator(new DefaultItemAnimator());
        // 添加分割线
//        recycleViewChat.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case btn_msg_type:
                int type1 = Integer.parseInt(btnMsgType.getText().toString());

                if (type1 == Msg.TYPE_RECEIVED) {
                    btnMsgType.setText(Msg.TYPE_SEND + "");
                } else {
                    btnMsgType.setText(Msg.TYPE_RECEIVED + "");
                }
                break;
            case R.id.btn_msg_send:
                String content = etMsgContent.getText().toString();
                if (!"".equals(content)) {
                    int type2 = Integer.parseInt(btnMsgType.getText().toString());

                    Msg msg = new Msg(type2, content);
                    msgList.add(msg);
                    mMsgApapter.notifyItemInserted(msgList.size() - 1);
                    recycleViewChat.scrollToPosition(msgList.size() - 1);
                    etMsgContent.setText("");
                } else {
                    Toast.makeText(this, "不能发送空内容", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 初始化消息列表
     *
     * @return
     */
    private void initMsgList() {
        Msg msg1 = new Msg(Msg.TYPE_RECEIVED, "Hello, Anna");
        msgList.add(msg1);
        Msg msg2 = new Msg(Msg.TYPE_SEND, "Hello, Who is that?");
        msgList.add(msg2);
        Msg msg3 = new Msg(Msg.TYPE_RECEIVED, "this is yuyuyu");
        msgList.add(msg3);
    }

}

class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.MsgViewHolder> {

    private List<Msg> msgList;

    public MsgAdapter(List<Msg> list) {
        msgList = list;
    }

    @Override
    public MsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        MsgViewHolder viewHolder = new MsgViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MsgViewHolder holder, int position) {
        Msg msg = msgList.get(position);
        if (msg.getType() == Msg.TYPE_RECEIVED) {
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
        } else if (msg.getType() == Msg.TYPE_SEND) {
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    class MsgViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout leftLayout;
        public TextView leftMsg;
        public LinearLayout rightLayout;
        public TextView rightMsg;

        public MsgViewHolder(View itemView) {
            super(itemView);
            leftLayout = (LinearLayout) itemView.findViewById(R.id.left_layout);
            leftMsg = (TextView) itemView.findViewById(R.id.left_msg);
            rightLayout = (LinearLayout) itemView.findViewById(R.id.right_layout);
            rightMsg = (TextView) itemView.findViewById(R.id.right_msg);
        }
    }

}