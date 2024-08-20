package com.nickdieda.pythonlearn.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nickdieda.pythonlearn.Lessons.Collections.introcol;
import com.nickdieda.pythonlearn.Lessons.basic.InstallPy;
import com.nickdieda.pythonlearn.Lessons.basic.Introduction;
import com.nickdieda.pythonlearn.Lessons.basic.OverviewBasic;
import com.nickdieda.pythonlearn.Lessons.basic.PDis;
import com.nickdieda.pythonlearn.Lessons.basic.PyComments;
import com.nickdieda.pythonlearn.Lessons.basic.PyState;
import com.nickdieda.pythonlearn.Lessons.basic.Syntaxs;
import com.nickdieda.pythonlearn.Lessons.basic.WPCodes;
import com.nickdieda.pythonlearn.Lessons.datatypes.Bools;
import com.nickdieda.pythonlearn.Lessons.datatypes.Num_met;
import com.nickdieda.pythonlearn.Lessons.datatypes.PyData;
import com.nickdieda.pythonlearn.Lessons.datatypes.PyString;
import com.nickdieda.pythonlearn.Lessons.datatypes.PyVar;
import com.nickdieda.pythonlearn.Lessons.datatypes.Pynum;
import com.nickdieda.pythonlearn.Lessons.datatypes.Pystrmed;
import com.nickdieda.pythonlearn.Lessons.datatypes.TypeCon;
import com.nickdieda.pythonlearn.Lessons.matopera.ArithOper;
import com.nickdieda.pythonlearn.Lessons.matopera.Assign;
import com.nickdieda.pythonlearn.Lessons.matopera.CompOpera;
import com.nickdieda.pythonlearn.Lessons.matopera.IdentityOp;
import com.nickdieda.pythonlearn.Lessons.matopera.IntOpera;
import com.nickdieda.pythonlearn.Lessons.matopera.LogOp;
import com.nickdieda.pythonlearn.Lessons.matopera.MemberShip;
import com.nickdieda.pythonlearn.MainActivity;
import com.nickdieda.pythonlearn.R;

import io.github.rosemoe.sora.widget.CodeEditor;

public class LessonsActivity extends AppCompatActivity {
    LinearLayout homefra,lessonfra,cont,overviews,pyintro,pyinstall,writing,display,statement,syntax,comm;
    LinearLayout variable,datatpye,numb,nummethods,pystrings,stringmeth,typeconversion,booleans;
    private LinearLayout operIntro,arith,assigns,compa,logical,identity,members;
    private LinearLayout containers;



    private ProgressBar provar,prodata,pronum,promet,prostring,prostinmeth,protypecon,probolean;
    ProgressBar basicf,prointro,proinst,prowrite,prodis,prostate,prosyn,procom;
    ProgressBar prooperintro,proarith,proassign,procompa,prological,proidentity,promember;
    ProgressBar procontainers;

    TextView t1,t2,t3,t4,t5,t6,t7,t8;
    private  TextView t21,t22,t23,t24,t25,t26,t27,t28;
    private  TextView t31,t32,t33,t34,t35,t36,t37;
    private  TextView t41;

    private    int prog,b2,b3,b4,b5,b6,b7,b8;
    private    int b9,v10,v11,v12,v13,v14,v15,v16;
    private    int m1,m2,m3,m4,m5,m6,m7;
    private    int c1;


    SharedPreferences sharedPreferences;
    ImageView homei,lessoni,compi,swi_img,uswi,cont_img,image;
    TextView hometext,lessontext,percentage,hdt;
    private ImageButton menuButton;
    private CodeEditor fra;
    private int totalProgress,mark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lessons);

        sharedPreferences = getSharedPreferences("progress", MODE_PRIVATE);
        int bfp = sharedPreferences.getInt("overv", 0);
        int bint = sharedPreferences.getInt("intro", 0);
        int binsta = sharedPreferences.getInt("installa", 0);
        int bwpc = sharedPreferences.getInt("wpc", 0);
        int bdis = sharedPreferences.getInt("pdis", 0);
        int bsta = sharedPreferences.getInt("pystate", 0);
        int bsin = sharedPreferences.getInt("syntax", 0);
        int bcom = sharedPreferences.getInt("comments", 0);
        int vvar = sharedPreferences.getInt("variables", 0);
        int vdata = sharedPreferences.getInt("pydata", 0);
        int vnum = sharedPreferences.getInt("pynum", 0);
        int vnumed = sharedPreferences.getInt("nummed", 0);
        int vstring = sharedPreferences.getInt("pystring", 0);
        int vstrmed = sharedPreferences.getInt("pystrmed", 0);
        int vconv = sharedPreferences.getInt("typeconv", 0);
        int vbool = sharedPreferences.getInt("bools", 0);
        int mintoper = sharedPreferences.getInt("intopera", 0);
        int marith = sharedPreferences.getInt("arithoper", 0);
        int mass = sharedPreferences.getInt("assig", 0);
        int mcomp = sharedPreferences.getInt("comppera", 0);
        int mlog = sharedPreferences.getInt("logop", 0);
        int mis= sharedPreferences.getInt("identityop", 0);
        int mm= sharedPreferences.getInt("membership", 0);
        int cint= sharedPreferences.getInt("introcon", 0);


        totalProgress=bfp+bint+binsta+bwpc+bdis+bsta+bsin+bcom+vvar+vdata+vnum+vnumed+vstring+vstrmed+vconv+vbool+mintoper+marith+mass+mcomp+mlog+mis+mm+cint;
        mark=(totalProgress*10)/96;
        progressindicator();




        TextView title=findViewById(R.id.title);

            title.setText("Lessons");

        prological = findViewById(R.id.prological);
        menuButton = findViewById(R.id.menu_button);
        prostring = findViewById(R.id.prostring);
        prooperintro=findViewById(R.id.prooperintro);
        logical=findViewById(R.id.logical);
        homefra=findViewById(R.id.home_fra);
        proassign=findViewById(R.id.proassign);
        procompa=findViewById(R.id.procompa);
        prowrite=findViewById(R.id.prowrite);
        datatpye=findViewById(R.id.datatpyes);
        prodata=findViewById(R.id.prodata);
        proarith=findViewById(R.id.proarith);
        display=findViewById(R.id.display);
        promet=findViewById(R.id.promet);
        probolean=findViewById(R.id.probolean);
        procom=findViewById(R.id.procom);
        compa=findViewById(R.id.compa);
        prosyn=findViewById(R.id.prosyn);
        booleans=findViewById(R.id.booleans);
        arith=findViewById(R.id.arith);
        comm=findViewById(R.id.comments);
        protypecon=findViewById(R.id.protypecon);
        numb=findViewById(R.id.numb);
        t23=findViewById(R.id.t23);
        pronum=findViewById(R.id.pronum);
        pystrings=findViewById(R.id.pystrings);
        proidentity=findViewById(R.id.proidentity);
        lessonfra=findViewById(R.id.lesson_fra);
        typeconversion=findViewById(R.id.typeconversion);
        members=findViewById(R.id.members);
        syntax=findViewById(R.id.syntax);
                 statement=findViewById(R.id.statement);
                homei=findViewById(R.id.home_image);
                lessoni=findViewById(R.id.lessons_im);
                hometext=findViewById(R.id.home_text);
                lessontext=findViewById(R.id.lesson_text);
                percentage=findViewById(R.id.percentage);
                image=findViewById(R.id.image);
                cont=findViewById(R.id.contless);
                pyinstall=findViewById(R.id.pyinstall);
                proinst=findViewById(R.id.proinst);
                writing=findViewById(R.id.writing);
                overviews=findViewById(R.id.pyover);
        promember=findViewById(R.id.promember);
        basicf=findViewById(R.id.overpro);

        operIntro=findViewById(R.id.operIntro);
        variable=findViewById(R.id.variable);
        provar=findViewById(R.id.provar);
        nummethods=findViewById(R.id.nummethods);
        identity=findViewById(R.id.identity);
        containers=findViewById(R.id.containers);
        pyintro=findViewById(R.id.pyintro);
        procontainers   =findViewById(R.id.procontainers);
        prointro=findViewById(R.id.prointro);
                assigns=findViewById(R.id.assigns);
                prostate=findViewById(R.id.prostate);
                prodis=findViewById(R.id.prodis);
        prostinmeth=findViewById(R.id.prostinmeth);
        stringmeth=findViewById(R.id.stringmeth);
                t1=findViewById(R.id.t1);
                t2=findViewById(R.id.t2);
                t3=findViewById(R.id.t3);
                t4=findViewById(R.id.t4);
                t5=findViewById(R.id.t5);
                t6=findViewById(R.id.t6);
                t7=findViewById(R.id.t7);
                t8=findViewById(R.id.t8);
        t21=findViewById(R.id.t21);
        t22=findViewById(R.id.t22);
        t24=findViewById(R.id.t24);
        t25=findViewById(R.id.t25);
        t26=findViewById(R.id.t26);
        t27=findViewById(R.id.t27);
        t28=findViewById(R.id.t28);
        t31=findViewById(R.id.t31);
        t32=findViewById(R.id.t32);
        t33=findViewById(R.id.t33);
        t34=findViewById(R.id.t34);
        t35=findViewById(R.id.t35);
        t36=findViewById(R.id.t36);
        t37=findViewById(R.id.t37);
        t41=findViewById(R.id.t41);



                proinst.setProgress(probar(binsta,b3));
                basicf.setProgress(probar(bfp,prog));
                prointro.setProgress(probar(bint,b2));
                prowrite.setProgress(probar(bwpc,b4));
                prodis.setProgress(probar(bdis,b5));
                prostate.setProgress(probar(bsta,b6));
       prosyn.setProgress(probar(bsin,b7));
                procom.setProgress(probar(bcom,b8));
                provar.setProgress(probar(vvar,b9));
        prodata.setProgress(probar(vdata,v10));
        pronum.setProgress(probar(vnum,v11));
        promet.setProgress(probar(vnumed,v12));
        prostring.setProgress(probar(vstring,v13));
        prostinmeth.setProgress(probar(vstrmed,v14));
        protypecon.setProgress(probar(vconv,v15));
        probolean.setProgress(probar(vbool,v16));
        prooperintro.setProgress(probar(mintoper,m1));
        proarith.setProgress(probar(marith,m2));
        proassign.setProgress(probar(mass,m3));
        procompa.setProgress(probar(mcomp,m4));
        prological.setProgress(probar(mlog,m5));
        proidentity.setProgress(probar(mis,m6));
        promember.setProgress(probar(mm,m7));
        procontainers.setProgress(probar(cint,c1));






        containers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t41.getText().toString();
                String strand="1/5";
                String tno="Four";
                int idl=23;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), introcol.class);

                startActivity(insta);
            }
        });

        members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t37.getText().toString();
                String strand="7/7";
                String tno="Three";
                int idl=22;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), MemberShip.class);

                startActivity(insta);
            }
        });


        identity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t36.getText().toString();
                String strand="6/7";
                String tno="Three";
                int idl=21;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), IdentityOp.class);

                startActivity(insta);
            }
        });

        logical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t35.getText().toString();
                String strand="5/7";
                String tno="Three";
                int idl=20;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), LogOp.class);

                startActivity(insta);
            }
        });

        compa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t34.getText().toString();
                String strand="4/7";
                String tno="Three";
                int idl=19;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), CompOpera.class);

                startActivity(insta);
            }
        });

        assigns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t33.getText().toString();
                String strand="3/7";
                String tno="Three";
                int idl=18;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), Assign.class);

                startActivity(insta);
            }
        });

        arith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t32.getText().toString();
                String strand="2/7";
                String tno="Three";
                int idl=17;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), ArithOper.class);

                startActivity(insta);
            }
        });



        operIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t31.getText().toString();
                String strand="1/7";
                String tno="Three";
                int idl=16;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), IntOpera.class);

                startActivity(insta);
            }
        });

        booleans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t28.getText().toString();
                String strand="8/8";
                String tno="two";
                int idl=15;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), Bools.class);

                startActivity(insta);
            }
        });

        typeconversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t27.getText().toString();
                String strand="7/8";
                String tno="two";
                int idl=14;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), TypeCon.class);

                startActivity(insta);
            }
        });

        stringmeth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t26.getText().toString();
                String strand="6/8";
                String tno="two";
                int idl=13;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), Pystrmed.class);

                startActivity(insta);
            }
        });

        pystrings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t25.getText().toString();
                String strand="5/8";
                String tno="two";
                int idl=12;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), PyString.class);

                startActivity(insta);
            }
        });

        nummethods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t24.getText().toString();
                String strand="4/8";
                String tno="two";
                int idl=11;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), Num_met.class);

                startActivity(insta);
            }
        });

        numb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t23.getText().toString();
                String strand="3/8";
                String tno="two";
                int idl=10;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), Pynum.class);

                startActivity(insta);
            }
        });


        datatpye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t22.getText().toString();
                String strand="2/8";
                String tno="two";
                int idl=9;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), PyData.class);

                startActivity(insta);
            }
        });

        variable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t21.getText().toString();
                String strand="1/8";
                String tno="two";
                int idl=8;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), PyVar.class);

                startActivity(insta);
            }
        });


        comm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t8.getText().toString();
                String strand="8/8";
                String tno="one";
                int idl=7;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), PyComments.class);

                startActivity(insta);
            }
        });

        syntax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t7.getText().toString();
                String strand="7/8";
                String tno="one";
                int idl=6;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), Syntaxs.class);

                startActivity(insta);
            }
        });
        statement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t6.getText().toString();
                String strand="6/8";
                String tno="one";
                int idl=5;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), PyState.class);

                startActivity(insta);
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t5.getText().toString();
                String strand="5/8";
                String tno="one";
                int idl=4;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), PDis.class);

                startActivity(insta);
            }
        });

        writing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t4.getText().toString();
                String strand="4/8";
                String tno="one";
                int idl=3;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), WPCodes.class);

                startActivity(insta);
            }
        });
        pyinstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t3.getText().toString();
                String strand="3/8";
                String tno="one";
                int idl=2;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), InstallPy.class);

                startActivity(insta);
            }
        });

        pyintro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t2.getText().toString();
                String strand="2/8";
                String tno="one";
                int idl=1;
                learning(topic,strand,tno,idl);

                Intent intro=new Intent(getApplicationContext(), Introduction.class);
                startActivity(intro);
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });

        homei.setImageResource(R.drawable.unhome);
        lessoni.setImageResource(R.drawable.book);
        hometext.setTextColor(getResources().getColor(R.color.off));
        lessontext.setTextColor(getResources().getColor(R.color.on));
        homei.setBackgroundResource(R.drawable.round_unselected);
        lessoni.setBackgroundResource(R.drawable.round_icon);
                homefra.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        homei.setImageResource(R.drawable.home);
                        lessoni.setImageResource(R.drawable.unbook);
                        hometext.setTextColor(getResources().getColor(R.color.on));
                        lessontext.setTextColor(getResources().getColor(R.color.off));
                        homei.setBackgroundResource(R.drawable.round_icon);
                        lessoni.setBackgroundResource(R.drawable.round_unselected);


                         Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                           startActivity(intent);
                            }


                });



                overviews.setOnClickListener(new View.OnClickListener() {
                    String hdo=t1.getText().toString();
                    @Override
                    public void onClick(View v) {
                        String topic =hdo;
                        String strand="1/8";
                        String tno="one";
                        int idl=0;
                        learning(topic,strand,tno,idl);
                Intent over=new Intent(getApplicationContext(), OverviewBasic.class);
                over.putExtra("tto",hdo);
                startActivity(over);
                    }
                });



            }


    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.kebab, popupMenu.getMenu());
        popupMenu.getMenu().findItem(R.id.action_save).setVisible(false);
        popupMenu.getMenu().findItem(R.id.action_open).setVisible(false);
        popupMenu.getMenu().findItem(R.id.action_sav).setVisible(false);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_settings) {
                    Toast.makeText(getApplicationContext(), "Settings clicked", Toast.LENGTH_SHORT).show();
                    return true;

                }else {
                    return false;
                }
            }

        });

        popupMenu.show();
    }


public int probar(int a ,int b){
    if(a==0){
        b=0;
    }else if(a==1){
        b=25;
    }else if(a==2){
        b=50;
    }else if(a==3){
        b=75;
    }else{
        b=100;
    }
    return b;
}
int p;
public void  progressindicator(){
    SharedPreferences sharedPreferences = getSharedPreferences("app_datas", MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();



    editor.putInt("count", mark);


    editor.apply();
}

public void learning(String top,String stra,String tno,int idl) {
    SharedPreferences currenttopic = getSharedPreferences("app_datas", MODE_PRIVATE);
    SharedPreferences.Editor edit = currenttopic.edit();
    edit.putString("topics",top);
    edit.putString("strands",stra);
    edit.putString("topno",tno);
    edit.putInt("idlearn",idl);
    edit.apply();

}



}




