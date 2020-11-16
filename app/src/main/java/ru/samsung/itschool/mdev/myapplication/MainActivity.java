package ru.samsung.itschool.mdev.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    private StringBuilder sb = new StringBuilder();
    private boolean touchFlag;
    private String str = "";
    private ConstraintLayout cr;
    private TextView tv;
    private float x,y;
    private int downIndex, upIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cr = findViewById(R.id.root);
        tv = findViewById(R.id.result);
        cr.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // событие
                int action = event.getActionMasked();
                // индекс касания
                int indexTouch = event.getActionIndex();
                // число касаний
                int counterTouch = event.getPointerCount();

                switch(action) {
                    // первое касание
                    case MotionEvent.ACTION_DOWN:
                        touchFlag = true;
                        break;
                        // все последующие касания
                    case MotionEvent.ACTION_POINTER_DOWN:
                        downIndex = indexTouch;
                        break;
                        // движение
                    case MotionEvent.ACTION_MOVE:
                        sb.setLength(0);
                        for(int i=0;i<10;i++) {
                            sb.append("Index: "+i + "\n");
                            if(i < counterTouch) {
                                sb.append("id="+event.getPointerId(i)+"; x="+event.getX(i)+"; y="+event.getY(i)+"\n");
                            } else {
                                sb.append("id= ; x= ; y= \n");
                            }
                            sb.append("\n");
                        }
                        break;
                        // отпускаем последний палец
                    case MotionEvent.ACTION_UP:
                        sb.setLength(0);
                        touchFlag = false;
                        break;
                        // отпускаем каждый палец, кроме последнего
                    case MotionEvent.ACTION_POINTER_UP:
                        upIndex = indexTouch;
                        break;
                }

                str = "down: "+downIndex+";\n up: "+upIndex + "\n";

                if(touchFlag) {
                    str += "Counter: "+counterTouch + "\n" + sb.toString() + "\n";
                }

                tv.setText(str);
                return true;
            }
        });
    }
}