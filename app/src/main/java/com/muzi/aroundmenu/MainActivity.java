package com.muzi.aroundmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.muzi.library.AroundMenu;

public class MainActivity extends AppCompatActivity {

    private Button btnOpen, btnClose;
    private AroundMenu aroundMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOpen = findViewById(R.id.btnOpen);
        btnClose = findViewById(R.id.btnClose);
        aroundMenu = findViewById(R.id.menu);

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aroundMenu.openMenu();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aroundMenu.closeMenu();
            }
        });

        aroundMenu.setOnAroundMenuClick(new AroundMenu.OnAroundMenuClick() {
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
}
