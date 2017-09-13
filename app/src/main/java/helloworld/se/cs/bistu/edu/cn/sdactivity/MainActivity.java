package helloworld.se.cs.bistu.edu.cn.sdactivity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    Button xieBt,xianBt;
    EditText xingEt,xueEt;
    private final static String MyFileName="MytTest.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xingEt=(EditText) findViewById(R.id.xingming_et);
        xueEt=(EditText) findViewById(R.id.xuehao_et);
        xieBt=(Button) findViewById(R.id.xieru_bt);
        xianBt=(Button)findViewById(R.id.xianshi_bt);
        xieBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OutputStream out=null;
                try{
                    FileOutputStream fileOutputStream =openFileOutput(MyFileName,MODE_PRIVATE);
                    out=new BufferedOutputStream(fileOutputStream);
                    String str1=xingEt.getText().toString();
                    String str2=xueEt.getText().toString();
                    StringBuilder str=new StringBuilder(str1+" "+str2);
                    String content=str.toString();
                    try{
                        out.write(content.getBytes(StandardCharsets.UTF_8));
                    }
                    finally {
                        if(out!=null)
                            out.close();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        xianBt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    int Buff=8192;
                    BufferedReader in=null;
                    FileInputStream fileInputStream=openFileInput(MyFileName);
                    in=new BufferedReader(new InputStreamReader(fileInputStream,"UTF-8"),Buff);
                    int c;
                    StringBuilder stringBuilder=new StringBuilder("");
                    try{
                        while((c=in.read())!=-1){
                            stringBuilder.append((char)c);
                        }
                        Toast.makeText(MainActivity.this,stringBuilder.toString(),Toast.LENGTH_LONG).show();
                    }
                    finally {
                        if(in!=null)
                            in.close();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
