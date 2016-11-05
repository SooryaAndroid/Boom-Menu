package com.android.boommenu;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

public class MainActivity extends AppCompatActivity{

    private boolean init = false;
    private BoomMenuButton boomMenuButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easy_to_use);

        boomMenuButton = (BoomMenuButton)findViewById(R.id.boom);

        }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // Use a param to record whether the boom button has been initialized
        // Because we don't need to init it again when onResume()
        if (init) return;
        init = true;

        Drawable[] subButtonDrawables = new Drawable[3];
        int[] drawablesResource = new int[]{
                R.drawable.boom,
                R.drawable.java,
                R.drawable.github
        };
        for (int i = 0; i < 3; i++)
            subButtonDrawables[i] = ContextCompat.getDrawable(this, drawablesResource[i]);

        String[] subButtonTexts = new String[]{"BoomMenuButton", "View source code", "Follow me"};

        int[][] subButtonColors = new int[3][2];
        for (int i = 0; i < 3; i++) {
            subButtonColors[i][1] = ContextCompat.getColor(this, R.color.material_white);
            subButtonColors[i][0] = Util.getInstance().getPressedColor(subButtonColors[i][1]);
        }


        // Now with Builder, you can init BMB more convenient
        new BoomMenuButton.Builder()
                .addSubButton(ContextCompat.getDrawable(this, R.drawable.boom), subButtonColors[0], "Menu Button")
                .addSubButton(ContextCompat.getDrawable(this, R.drawable.boom), subButtonColors[0], "Menu Button")
                .addSubButton(ContextCompat.getDrawable(this, R.drawable.java), subButtonColors[0], "Menu Buttons")
                .addSubButton(ContextCompat.getDrawable(this, R.drawable.java), subButtonColors[0], "Menu Buttons")
                .addSubButton(ContextCompat.getDrawable(this, R.drawable.github), subButtonColors[0], "android")
                .addSubButton(ContextCompat.getDrawable(this, R.drawable.github), subButtonColors[0], "android")
                .addSubButton(ContextCompat.getDrawable(this, R.drawable.github), subButtonColors[0], "android")
                .addSubButton(ContextCompat.getDrawable(this, R.drawable.github), subButtonColors[0], "android")
                .button(ButtonType.CIRCLE)
                .boom(BoomType.HORIZONTAL_THROW)
                .place(PlaceType.CIRCLE_8_1)
                .subButtonTextColor(ContextCompat.getColor(this, R.color.black))
                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .init(boomMenuButton);


        boomMenuButton.setOnSubButtonClickListener(new BoomMenuButton.OnSubButtonClickListener() {
            @Override
            public void onClick(int buttonIndex) {
                // return the index of the sub button clicked
                startActivity(new Intent(MainActivity.this,NextActivity.class));
                Toast.makeText(MainActivity.this, "clicked"+buttonIndex, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
