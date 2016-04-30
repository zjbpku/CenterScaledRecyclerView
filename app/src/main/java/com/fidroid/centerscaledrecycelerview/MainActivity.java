package com.fidroid.centerscaledrecycelerview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fidroid.centerscalerecyclerview.CenterScaledRecyclerView;

import java.util.ArrayList;

public class MainActivity extends Activity implements CenterScaledRecyclerView.ClickListener {
    private static final String TAG = "MainActivity";
    private CenterScaledRecyclerView mListView;
    private MyListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mListView = (CenterScaledRecyclerView) findViewById(R.id.listView1);
        mAdapter = new MyListAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setClickListener(this);
        mListView.addOnCenterPositionLastSelectedListener(new CenterScaledRecyclerView.OnCenterPositionLastSelectedListener() {
            @Override
            public void onCenterSelectedPosition(int position) {
                Log.d(TAG, "onCenterSelectedPosition: " + position);
            }
        });
    }


    private static ArrayList<Integer> listItems;

    static {
        listItems = new ArrayList<Integer>();
        listItems.add(R.drawable.ic_action_attach);
        listItems.add(R.drawable.ic_action_call);
        listItems.add(R.drawable.ic_action_copy);
        listItems.add(R.drawable.ic_action_cut);
        listItems.add(R.drawable.ic_action_delete);
        listItems.add(R.drawable.ic_action_done);
        listItems.add(R.drawable.ic_action_edit);
        listItems.add(R.drawable.ic_action_locate);
        listItems.add(R.drawable.ic_action_mail);
        listItems.add(R.drawable.ic_action_mail_add);
        listItems.add(R.drawable.ic_action_microphone);
        listItems.add(R.drawable.ic_action_photo);
    }

    @Override
    public void onClick(CenterScaledRecyclerView.ViewHolder viewHolder) {
        Toast.makeText(this, String.format("You selected item #%s", viewHolder.getPosition()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTopEmptyRegionClick() {
        Toast.makeText(this, "You tapped Top empty area", Toast.LENGTH_SHORT).show();
    }

    public class MyListAdapter extends CenterScaledRecyclerView.Adapter {

        @Override
        public CenterScaledRecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new CenterScaledRecyclerView.ViewHolder(new MyItemView(MainActivity.this));
        }

        @Override
        public void onBindViewHolder(CenterScaledRecyclerView.ViewHolder viewHolder, int i) {
            MyItemView itemView = (MyItemView) viewHolder.itemView;

            TextView txtView = (TextView) itemView.findViewById(R.id.text);
            txtView.setText(String.format("Item %d", i));

            Integer resourceId = listItems.get(i);
            ImageView imgView = (ImageView) itemView.findViewById(R.id.image);
            imgView.setImageResource(resourceId);
        }

        @Override
        public int getItemCount() {
            return listItems.size();
        }
    }

    private final class MyItemView extends FrameLayout implements CenterScaledRecyclerView.OnCenterProximityListener {

        final ImageView imgView;
        final TextView txtView;

        public MyItemView(Context context) {
            super(context);
            View.inflate(context, R.layout.row_advanced_item_layout, this);
            imgView = (ImageView) findViewById(R.id.image);
            txtView = (TextView) findViewById(R.id.text);
        }

        @Override
        public void onCenterPosition(boolean b) {
            imgView.setAlpha(1f);
            txtView.setAlpha(1f);
            txtView.setScaleX(1f);
            imgView.setScaleX(1f);
            txtView.setScaleY(1f);
            imgView.setScaleY(1f);
        }

        @Override
        public void onNonCenterPosition(boolean b) {
            imgView.setAlpha(0.5f);
            txtView.setAlpha(0.5f);
            txtView.setScaleX(0.5f);
            imgView.setScaleX(0.5f);
            txtView.setScaleY(0.5f);
            imgView.setScaleY(0.5f);
        }
    }
}
