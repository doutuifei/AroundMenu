package com.muzi.aroundmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.muzi.library.AroundMenu;
import com.muzi.library.MenuOrientation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnInit, btnOpen, btnClose;
    private AroundMenu aroundMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInit = findViewById(R.id.btnInit);
        btnOpen = findViewById(R.id.btnOpen);
        btnClose = findViewById(R.id.btnClose);
        aroundMenu = findViewById(R.id.menu);

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aroundMenu.setMenuOrientation(MenuOrientation.BOTTOM);
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                aroundMenu.closeMenu();
                aroundMenu.setMenuCenterSize(120);
            }
        });

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

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) btnClose.getLayoutParams();
        layoutParams.bottomMargin = 10;
        btnClose.setLayoutParams(layoutParams);
    }

    private void init() {
        List<View> list = new ArrayList<>();
        list.add(LayoutInflater.from(this).inflate(R.layout.item_btn, null, false));
        list.add(LayoutInflater.from(this).inflate(R.layout.item_btn, null, false));
        list.add(LayoutInflater.from(this).inflate(R.layout.item_btn, null, false));
        list.add(LayoutInflater.from(this).inflate(R.layout.item_btn, null, false));
        list.add(LayoutInflater.from(this).inflate(R.layout.item_btn, null, false));
        aroundMenu.setMenuList(list);
    }
}
