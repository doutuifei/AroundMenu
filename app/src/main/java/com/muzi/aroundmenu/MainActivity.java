package com.muzi.aroundmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.muzi.library.AroundMenu;
import com.muzi.library.MenuOrientation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnOri)
    Button btnOri;
    @BindView(R.id.btnNum)
    Button btnNum;
    @BindView(R.id.menu)
    AroundMenu aroundMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<View> list = new ArrayList<>();
        list.add(LayoutInflater.from(this).inflate(R.layout.item_btn, null, false));
        list.add(LayoutInflater.from(this).inflate(R.layout.item_btn, null, false));
        list.add(LayoutInflater.from(this).inflate(R.layout.item_btn, null, false));
        list.add(LayoutInflater.from(this).inflate(R.layout.item_btn, null, false));
        aroundMenu.setMenuViewList(list);
        aroundMenu.setCentBtnView(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_btn, null, false));

        aroundMenu.setMenuClick(new AroundMenu.OnAroundMenuClick() {
            @Override
            public void onCenterClick(boolean isShowing) {
                Toast.makeText(MainActivity.this, "isShowing:" + isShowing, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMenuClick(int position) {
                Toast.makeText(MainActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        List<View> list = new ArrayList<>();
        list.add(LayoutInflater.from(this).inflate(R.layout.item_btn, null, false));
        list.add(LayoutInflater.from(this).inflate(R.layout.item_btn, null, false));
        list.add(LayoutInflater.from(this).inflate(R.layout.item_btn, null, false));
        list.add(LayoutInflater.from(this).inflate(R.layout.item_btn, null, false));
        list.add(LayoutInflater.from(this).inflate(R.layout.item_btn, null, false));
        aroundMenu.setMenuViewList(list);
        aroundMenu.setCentBtnView(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_btn, null, false));
    }

    @OnClick({R.id.btnOri, R.id.btnNum})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnOri:
                aroundMenu.setMenuOrientation(MenuOrientation.RIGHT_BOTTOM);
                break;
            case R.id.btnNum:
                init();
                break;
        }
    }
}
