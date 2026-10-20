package com.tamilclock.widget;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private int selectedTextColor = Color.WHITE;
    private int selectedBgColor = Color.BLACK;
    private int selectedAlpha = 255;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioGroup rgText = findViewById(R.id.rg_text_color);
        RadioGroup rgBg = findViewById(R.id.rg_bg_color);
        SeekBar sbAlpha = findViewById(R.id.sb_alpha);
        TextView tvAlpha = findViewById(R.id.tv_alpha_label);
        Button btnSave = findViewById(R.id.btn_save);

        rgText.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_txt_white) selectedTextColor = Color.WHITE;
            else if (checkedId == R.id.rb_txt_yellow) selectedTextColor = Color.parseColor("#FFEB3B");
            else if (checkedId == R.id.rb_txt_cyan) selectedTextColor = Color.parseColor("#00E5FF");
        });

        rgBg.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_bg_black) selectedBgColor = Color.BLACK;
            else if (checkedId == R.id.rb_bg_blue) selectedBgColor = Color.parseColor("#0D1B2A");
            else if (checkedId == R.id.rb_bg_maroon) selectedBgColor = Color.parseColor("#2B0A0D");
        });

        sbAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                selectedAlpha = progress;
                int percent = (int) ((progress / 255.0f) * 100);
                tvAlpha.setText("Background Opacity: " + percent + "%");
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnSave.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("WidgetConfig", MODE_PRIVATE);
            prefs.edit()
                .putInt("text_color", selectedTextColor)
                .putInt("bg_color", selectedBgColor)
                .putInt("bg_alpha", selectedAlpha)
                .apply();

            TamilClockWidget.updateAllWidgets(this);
            Toast.makeText(this, "Widget Updated Successfully!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
