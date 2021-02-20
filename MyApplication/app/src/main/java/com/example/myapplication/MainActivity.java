package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Game game=new Game(this);

        LayoutInflater inflater=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        final FrameLayout frameLayout=(FrameLayout) inflater.inflate(R.layout.forma,null);
        frameLayout.addView(game);
        game.setZ(1);
        frameLayout.findViewById(R.id.GameMenu).setZ(2);
        frameLayout.findViewById(R.id.PlayBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.findViewById(R.id.GameMenu).setVisibility(View.INVISIBLE);
                game.timer.start();
            }
        });
        setContentView(frameLayout);
//

    }
}
