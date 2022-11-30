package com.example.check;

import androidx.annotation.ChecksSdkIntAtLeast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CheckActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CheckAdapter CheckAdapter;

    private EditText edit_name;
    private Button btn_save;

    //=======다이얼로그
    Dialog dilaog01; // 커스텀 다이얼로그


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        recyclerView = findViewById(R.id.recyclerView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        CheckAdapter = new CheckAdapter(getApplicationContext());
        recyclerView.setAdapter(CheckAdapter);


        //====다이얼얼로그
        dilaog01 = new Dialog(CheckActivity.this);       // Dialog 초기화
        dilaog01.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dilaog01.setContentView(R.layout.check_dialog_pick);             // xml 레이아웃 파일과 연결

        // 버튼: 커스텀 다이얼로그 띄우기
        findViewById(R.id.dialog_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog01(); // 아래 showDialog01() 함수 호출
            }
        });


        //=======

        CheckAdapter.setOnItemClicklistener(new OnCheckItemClickListener() {
            @Override
            public void onItemClick(CheckAdapter.CheckViewHolder holder, View view, int position) {
                Items item = CheckAdapter.getItem(position);
                Toast.makeText(getApplicationContext(),"아이템 선택 " + item.getName(),
                        Toast.LENGTH_LONG).show();




            }
        });







        edit_name = findViewById (R.id.edit_name);
        btn_save = findViewById (R.id.btn_save);




        //이제 어댑터에 목록에 보여줄 아이템들을 추가하겠음
        //마지막으로 추가한 메서드를 사용하면 됨
        CheckAdapter.addItem(new Items(R.drawable.beer, "맥주"));
        CheckAdapter.addItem(new Items(R.drawable.cok, "소주"));
        CheckAdapter.addItem(new Items(R.drawable.mak, "막걸리"));
        CheckAdapter.addItem(new Items(R.drawable.beer, "칵테일"));
        CheckAdapter.addItem(new Items(R.drawable.pro," "));

    }


    //======다이얼로그==================================
    //=========================================================
    // dialog01을 디자인하는 함수
    public void showDialog01(){
        dilaog01.show(); // 다이얼로그 띄우기

        /* 이 함수 안에 원하는 디자인과 기능을 구현하면 된다. */

        // 위젯 연결 방식은 각자 취향대로~
        // '아래 아니오 버튼'처럼 일반적인 방법대로 연결하면 재사용에 용이하고,
        // '아래 네 버튼'처럼 바로 연결하면 일회성으로 사용하기 편함.
        // *주의할 점: findViewById()를 쓸 때는 -> 앞에 반드시 다이얼로그 이름을 붙여야 한다.

        //radio
        TextView check_drink_name = dilaog01.findViewById(R.id.check_drink_name);
        check_drink_name.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {


                Resources res = getResources();
                String[] arraySoju = res.getStringArray(R.array.soju);
                String[] arrayBeer = res.getStringArray(R.array.beer);

                String[] arrayDrink;
                final int[] selectedIndex = {0};

                AlertDialog.Builder dialog = new AlertDialog.Builder(CheckActivity.this);
                dialog.setTitle("관심분야를 선택하세요.")
                        .setSingleChoiceItems(arraySoju,
                                0,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        selectedIndex[0] = which;
                                    }
                                })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CheckActivity.this, arraySoju[selectedIndex[0]], Toast.LENGTH_SHORT).show();
                            }
                        }).create().show();

            }
        });





        // 아니오 버튼
        Button noBtn = dilaog01.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                dilaog01.dismiss(); // 다이얼로그 닫기
            }
        });
        // 네 버튼
        dilaog01.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                finish();           // 앱 종료
            }
        });
    }
}

