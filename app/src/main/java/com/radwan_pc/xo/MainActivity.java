package com.radwan_pc.xo;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.String;
import java.util.Stack;
import android.media.MediaPlayer;

import android.graphics.drawable.Drawable;

public class MainActivity extends AppCompatActivity {

    TextView wintextView ;
    ImageView imageView;
    MediaPlayer mp;
    String wintag="";
    Stack<Integer> undostack=new Stack<Integer>();
    Stack<String> undostacktag=new Stack<String>();
    Boolean allowtopaly = true;
    int xcu=0,ocu=0,reso,lastindex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.b2);
        mp=MediaPlayer.create(this,R.raw.woodhitsound1);
        wintextView=(TextView)findViewById(R.id.winms);
        intial();
    }


    public void clicked(View view) {
        mp=MediaPlayer.create(this,R.raw.woodhitsound1);
        if(allowtopaly)
        {imageView = (ImageView) view;
        if((imageView.getTag()!="x")&&(imageView.getTag()!="o"))
        {
         if(xcu==0||xcu==ocu)
        {
            reso=R.drawable.ximg1;
            undostacktag.push(imageView.getTag().toString());
            imageView.setTag("x");
            xcu++;
        }
        else if(xcu>ocu)
        {
            reso=R.drawable.oimg1;
            undostacktag.push(imageView.getTag().toString());
            imageView.setTag("o");
            ocu++;
        }
        else return;
         imageView.setImageResource(reso);
        // System.out.println("xxco= "+xcu+"image id = "+imageView.getId());
         undostack.push(imageView.getId());
            mp.start();
         //undostacktag.push(imageView.getTag().toString());
            //updat
        win();
        }
        }
        if(xcu==5&&allowtopaly)
        {
            mp=MediaPlayer.create(this,R.raw.draw);
            wintextView.setTextColor(0xFFFF3900);
            wintextView.setTextSize(30);
            wintextView.setText("Draw"+"\n"+" Play Again !!");
            allowtopaly=false;
            mp.start();

            return;
        }
    }

    public void win()
    {

        ImageView img1,img2,img3;
        int[] res={R.id.b1,R.id.b2,R.id.b3,
                R.id.b4,R.id.b5,R.id.b6,R.id.b7,R.id.b8,R.id.b9};
        int po[][]={{0,1,2},{3,4,5},{6,7,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8},{0,4,8}};
        for(int i=0;i<8;i++)
        {
            img1=(ImageView)findViewById(res[po[i][0]]);
            img2=(ImageView)findViewById(res[po[i][1]]);
            img3=(ImageView)findViewById(res[po[i][2]]);
            if((xcu>=3)||(ocu>=3))
            { //System.out.println("img1 "+img1.getTag()+" "+"img2 "+img2.getTag()+" "+"img3 "+img3.getTag());
               // System.out.println("frow win function "+"ID = "+i+"  TAG = "+imageView.getTag().toString());
                if (img1.getTag().toString() == img2.getTag().toString())
                    if (img2.getTag().toString() == img3.getTag().toString()) {
                        mp=MediaPlayer.create(this,R.raw.tadawin);
                        mp.start();
                        allowtopaly = false;
                        wintag = img1.getTag().toString();
                        wintextView.setText(" ");

                        wintextView.setText(wintag.toString()+" Win");
                        //System.out.println("yes");
                        break;

                    }
            }else{break;}
        }
        return;
    }
    public void playagain(View view)
    {
                        //System.out.println("1ooool");
        intial();

    }
    public void Undo(View view)
    {
        if(xcu!=0&&allowtopaly){
            //System.out.println(undostack+"\n"+undostacktag);
        imageView=(ImageView)findViewById(undostack.pop());
        imageView.setImageResource(android.R.drawable.picture_frame);

            if(xcu==ocu)
                ocu--;
            else xcu--;
           // System.out.println("Oc = "+ocu+"\n"+"Xcu = "+xcu);
                imageView.setTag(undostacktag.pop());


        }
    }
    public void intial()
    {

        allowtopaly=true;
        int[] res={R.id.b1,R.id.b2,R.id.b3,R.id.b4,R.id.b5,R.id.b6,R.id.b7,R.id.b8,R.id.b9};
        xcu=ocu=lastindex=0;
        wintextView.setTextColor(0xcD009900);
        wintag="";
        mp.reset();
        undostack.removeAllElements();
        undostacktag.removeAllElements();
        for(int i=0;i<res.length;i++) {
            imageView=(ImageView)findViewById(res[i]);
            //System.out.println("ID = "+i+"  TAG = "+imageView.getTag().toString());
            imageView.setImageResource(android.R.drawable.picture_frame);
            //imageView.clearAnimation();
            imageView.setTag(i);
            //imageView.setImageResource(0);
        }
        wintextView.setText("");
    }
    public void deticedxo()
    {
        xcu=ocu-=0;
        int[] res={R.id.b1,R.id.b2,R.id.b3,R.id.b4,R.id.b5,R.id.b6,R.id.b7,R.id.b8,R.id.b9};
        for(int i=0;i<res.length;i++) {
            imageView=(ImageView)findViewById(res[i]);
            if(imageView.getTag().equals("x"))
                xcu++;
            else if(imageView.getTag().equals("o"))
                ocu++;

    }
}
}