package com.example.check;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CheckAdapter CheckAdaper;

    private EditText edit_name;
    private Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        CheckAdaper = new CheckAdapter(getApplicationContext());
        recyclerView.setAdapter(CheckAdaper);



        edit_name = findViewById (R.id.edit_name);
        btn_save = findViewById (R.id.btn_save);




        //이제 어댑터에 목록에 보여줄 아이템들을 추가하겠음
        //마지막으로 추가한 메서드를 사용하면 됨
        CheckAdaper.addItem(new Items(R.drawable.beer, "맥주"));
        CheckAdaper.addItem(new Items(R.drawable.cok, "소주"));
        CheckAdaper.addItem(new Items(R.drawable.mak, "막걸리"));
        CheckAdaper.addItem(new Items(R.drawable.beer, "칵테일"));
        CheckAdaper.addItem(new Items(R.drawable.pro," "));

    }
}

