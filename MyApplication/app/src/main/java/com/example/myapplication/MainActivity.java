package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {
    public void upisiUfajl(Context context){
        try {
            for(int i=0;i<5;i++) {
                File file = new File(context.getFilesDir(), "level" + i + ".txt");
                FileWriter writer = new FileWriter(file);
                writer.write("0 0\n");
                writer.write("50 50 50 50\n");
                writer.write("70 80 100 50\n");
                writer.write("50 50 50 50\n");
                writer.write("50 50 50 50");
                writer.close();
            }
        }catch (Exception e){
            System.out.println("greska");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Game game=new Game(this);
        upisiUfajl(this);
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
