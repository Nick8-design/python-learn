package com.nickdieda.pythonlearn.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nickdieda.pythonlearn.Fbdb.CompletionList;
import com.nickdieda.pythonlearn.Fbdb.DBAdd_name;
import com.nickdieda.pythonlearn.Lessons.Collections.Ctuple;
import com.nickdieda.pythonlearn.Lessons.Collections.Dictionar_y;
import com.nickdieda.pythonlearn.Lessons.Collections.Listc;
import com.nickdieda.pythonlearn.Lessons.Collections.Setsc;
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
import com.nickdieda.pythonlearn.Lessons.flow_control.BreakCon;
import com.nickdieda.pythonlearn.Lessons.flow_control.For_Loop;
import com.nickdieda.pythonlearn.Lessons.flow_control.If_el;
import com.nickdieda.pythonlearn.Lessons.flow_control.Ifes;
import com.nickdieda.pythonlearn.Lessons.flow_control.WhileActivity;
import com.nickdieda.pythonlearn.Lessons.matopera.ArithOper;
import com.nickdieda.pythonlearn.Lessons.matopera.Assign;
import com.nickdieda.pythonlearn.Lessons.matopera.CompOpera;
import com.nickdieda.pythonlearn.Lessons.matopera.IdentityOp;
import com.nickdieda.pythonlearn.Lessons.matopera.IntOpera;
import com.nickdieda.pythonlearn.Lessons.matopera.LogOp;
import com.nickdieda.pythonlearn.Lessons.matopera.MemberShip;
import com.nickdieda.pythonlearn.Lessons.modules.ActivityJson;
import com.nickdieda.pythonlearn.Lessons.modules.DateTimeModule;
import com.nickdieda.pythonlearn.Lessons.modules.IntroModule;
import com.nickdieda.pythonlearn.Lessons.modules.MathModule;
import com.nickdieda.pythonlearn.Lessons.modules.REactivity;
import com.nickdieda.pythonlearn.Lessons.modules.Rand_ompy;
import com.nickdieda.pythonlearn.Lessons.numpy.ArrIndexing;
import com.nickdieda.pythonlearn.Lessons.numpy.ArrSlicing;
import com.nickdieda.pythonlearn.Lessons.numpy.Int_numpy;
import com.nickdieda.pythonlearn.Lessons.numpy.JoinNumpy;
import com.nickdieda.pythonlearn.Lessons.numpy.NDataType;
import com.nickdieda.pythonlearn.Lessons.numpy.NumSort;
import com.nickdieda.pythonlearn.Lessons.numpy.NumpySearch;
import com.nickdieda.pythonlearn.Lessons.numpy.Numpy_arrays;
import com.nickdieda.pythonlearn.Lessons.numpy.numpyIter;
import com.nickdieda.pythonlearn.Lessons.pyadvance.ClassesPy;
import com.nickdieda.pythonlearn.Lessons.pyadvance.ErrorHandling;
import com.nickdieda.pythonlearn.Lessons.pyadvance.FormatingStrings;
import com.nickdieda.pythonlearn.Lessons.pyadvance.Inheritance;
import com.nickdieda.pythonlearn.Lessons.pyadvance.Iterator;
import com.nickdieda.pythonlearn.Lessons.pyadvance.UserInput;
import com.nickdieda.pythonlearn.Lessons.pyadvance.VarScope;
import com.nickdieda.pythonlearn.Lessons.pyfile.FDel;
import com.nickdieda.pythonlearn.Lessons.pyfile.IntFile;
import com.nickdieda.pythonlearn.Lessons.pyfile.RFile;
import com.nickdieda.pythonlearn.Lessons.pyfile.WFile;
import com.nickdieda.pythonlearn.Lessons.pyfun.FunDef;
import com.nickdieda.pythonlearn.Lessons.pyfun.Introdef;
import com.nickdieda.pythonlearn.Lessons.pyfun.PassPy;
import com.nickdieda.pythonlearn.Lessons.pyfun.lambdaActivity;
import com.nickdieda.pythonlearn.MainActivity;
import com.nickdieda.pythonlearn.R;
import com.nickdieda.pythonlearn.common.AdHelper;
import com.nickdieda.pythonlearn.common.BrightnessUtil;
import com.nickdieda.pythonlearn.common.certificate;
//import com.nickdieda.pythonlearn.common.generateCertificate;

import io.github.rosemoe.sora.widget.CodeEditor;

public class LessonsActivity extends AppCompatActivity {
    LinearLayout homefra,lessonfra,cont,overviews,pyintro,pyinstall,writing,display,statement,syntax,comm;
    LinearLayout variable,datatpye,numb,nummethods,pystrings,stringmeth,typeconversion,booleans;
    private LinearLayout operIntro,arith,assigns,compa,logical,identity,members;
    private LinearLayout containers,lists,tuples,set_s,dictionary,classO;
    private LinearLayout introfun,fu_n,lambdaf,pass_s,if_else,ifshort,forpy,whilepy,conbrk,inherit,varscope;
    private LinearLayout fomstrings,tryexcept,pyiter ,inputm;
    private LinearLayout     intmodule,maolib,randomlib,datetime,jsonn,repy;
    private LinearLayout int_file,fread,fwrite,fdel;
    private LinearLayout int_numpy,numpyarr,numpyindex,arrslice,numpydatatype,numpyiterate,numpyjoin,searchnumpy,sortnum;

    private ProgressBar provar,prodata,pronum,promet,prostring,prostinmeth,protypecon,probolean;
    ProgressBar basicf,prointro,proinst,prowrite,prodis,prostate,prosyn,procom;
    ProgressBar prooperintro,proarith,proassign,procompa,prological,proidentity,promember;
    ProgressBar procontainers,prolists,protuple,prosets,prodictionary;
    ProgressBar prointrofun,profun,prolambdaf,propass,proclassO,protryexc;
    ProgressBar proif_else,proifshort,proforpy,prowhilepy,proconbrk,proinherit,provarscope,profomstring,proinputm,propyiter;
    ProgressBar   prointmodule,promathmod,prorandomlib,prodatetime,projson,prorepy;
            ProgressBar profile,proread,profwrite,prodel;
            ProgressBar pronumpy,proarr,proindex,proarrslice,pronumpydata,proiternum,projoin,proserchnum,prosort;


    TextView t1,t2,t3,t4,t5,t6,t7,t8;
    private  TextView t21,t22,t23,t24,t25,t26,t27,t28;
    private  TextView t31,t32,t33,t34,t35,t36,t37;
    private  TextView t41,t42,t43,t44,t45;
    private  TextView t51,t52,t53,t54;
    private  TextView t61,t62,t63,t64,t65;
    private  TextView t71,t72,t73,t74,t75,t76,t77;
    private  TextView t81,t82,t83,t84,t85,t86;
    private  TextView t91,t92,t93,t94;
    private  TextView t101,t102,t103,t104,t105,t106,t107,t108,t109;


    private    int prog,b2,b3,b4,b5,b6,b7,b8;
    private    int b9,v10,v11,v12,v13,v14,v15,v16;
    private    int m1,m2,m3,m4,m5,m6,m7;
    private    int c1,c2,c3,c4,c5;
    private    int f1,f2,f3,f4;
    private    int if1,if2,if3,if4,if5;
    private int p1,p2,p3,p4,p5,p6,p7;
    private int mo1,mo2,mo3,mo4,mo5,mo6;
    private int fo1,fo2,fo3,fo4;
    private    int pb1,pb2,pb3,pb4,pb5,pb6,pb7,pb8,pb9;


    SharedPreferences sharedPreferences;
    ImageView homei,lessoni,compi,swi_img,uswi,cont_img,image;
    TextView hometext,lessontext,percentage,hdt;
    private ImageButton menuButton;
    private CodeEditor fra;
    private int totalProgress,mark;
    private FrameLayout adContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lessons);

        MobileAds.initialize(this, initializationStatus -> {});


        AdHelper.initializeAds(this);
        adContainerView = findViewById(R.id.ad_view_container);
        adContainerView.setVisibility(View.GONE);
        AdHelper.loadBannerAd(this, adContainerView);


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
        int clist= sharedPreferences.getInt("lists", 0);
        int ctup= sharedPreferences.getInt("ctuple", 0);
        int cs= sharedPreferences.getInt("cset", 0);
        int cd= sharedPreferences.getInt("cdic", 0);
        int fint= sharedPreferences.getInt("intfun", 0);
        int fun= sharedPreferences.getInt("funs", 0);
        int fl= sharedPreferences.getInt("lambdas", 0);
        int fp= sharedPreferences.getInt("passpy", 0);
        int ifc= sharedPreferences.getInt("if_els", 0);
        int ifs= sharedPreferences.getInt("if_elss", 0);
        int fors= sharedPreferences.getInt("forl", 0);
        int whiles= sharedPreferences.getInt("whilel", 0);
        int brk= sharedPreferences.getInt("brkc", 0);
        int pya1= sharedPreferences.getInt("classespy", 0);
        int pya2= sharedPreferences.getInt("inheritance", 0);
        int pya3= sharedPreferences.getInt("varscope", 0);
        int pya4= sharedPreferences.getInt("formstring", 0);
        int pya5= sharedPreferences.getInt("handerror", 0);
        int pya6= sharedPreferences.getInt("interator", 0);
        int pya7= sharedPreferences.getInt("inputm", 0);
        int mod1= sharedPreferences.getInt("intromodule", 0);
        int mod2= sharedPreferences.getInt("mathmodule", 0);
        int mod3= sharedPreferences.getInt("randmodule", 0);
        int mod4= sharedPreferences.getInt("dateT", 0);
        int mod5= sharedPreferences.getInt("jsonn", 0);
        int mod6= sharedPreferences.getInt("repy", 0);
        int fl1= sharedPreferences.getInt("intfile", 0);
        int fl2= sharedPreferences.getInt("readf", 0);
        int fl3= sharedPreferences.getInt("writef", 0);
        int fl4= sharedPreferences.getInt("delf", 0);
        int np1= sharedPreferences.getInt("intnumpy", 0);
        int np2= sharedPreferences.getInt("numarr", 0);
        int np3= sharedPreferences.getInt("arrindex", 0);
        int np4= sharedPreferences.getInt("arrslice", 0);
        int np5= sharedPreferences.getInt("ndts", 0);
        int np6= sharedPreferences.getInt("nipy", 0);
        int np7= sharedPreferences.getInt("numjoin", 0);
        int np8= sharedPreferences.getInt("numsearch", 0);
        int np9= sharedPreferences.getInt("numsort", 0);






        //  Toast.makeText(getApplicationContext(),"intro = "+mod3,Toast.LENGTH_SHORT).show();
         totalProgress=bfp+bint+binsta+bwpc+bdis+bsta+bsin+bcom+vvar+vdata+vnum+vnumed+vstring+vstrmed+vconv+vbool+mintoper+marith+mass+mcomp+mlog+mis+mm+cint+clist+ctup+cs+cd;
      totalProgress +=fint+fun+fl+fp+ifc+ifs+fors+whiles+brk+pya1+pya2+pya3+pya4+pya5+pya6+pya7+mod1+mod2+mod3+mod4+mod5+mod6;
        totalProgress +=fl1+fl2+fl3+fl4+np1+np2+np3+np4+np5+np6+np7+np8+np9;
        mark=(totalProgress*10)/254;
        progressindicator();

        SharedPreferences totalm = getSharedPreferences("app_datas", MODE_PRIVATE);
        SharedPreferences.Editor edit = totalm.edit();
      edit.putInt("total_marks",totalProgress);
        edit.apply();

    if (totalProgress >= 254) {
        // Show a dialog to claim the certificate
        new AlertDialog.Builder(this)
                .setTitle("Congratulations!")
                .setMessage("You are eligible to claim your certificate.")
                .setPositiveButton("Claim Now", (dialog, which) -> {

                   // certificate.requestUserNameBeforeDownload(LessonsActivity.this,totalProgress);

                    certificate cet=new certificate();
                    cet.requestUserNameBeforeDownload(LessonsActivity.this,totalProgress);

                })
                .show();
    }



        TextView title=findViewById(R.id.title);

            title.setText("Lessons");

        prological = findViewById(R.id.prological);
        conbrk = findViewById(R.id.conbrk);
        menuButton = findViewById(R.id.menu_button);
        datetime = findViewById(R.id.datetime);
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
        int_file=findViewById(R.id.int_file);
        proinputm=findViewById(R.id.proinputm);
        proarrslice=findViewById(R.id.proarrslice);
        propyiter=findViewById(R.id.propyiter);
        probolean=findViewById(R.id.probolean);
        proiternum=findViewById(R.id.proiternum);
        procom=findViewById(R.id.procom);
        projoin=findViewById(R.id.projoin);
        fomstrings=findViewById(R.id.fomstrings);
        maolib=findViewById(R.id.maolib);
        compa=findViewById(R.id.compa);
        proarr=findViewById(R.id.proarr);
        prodel=findViewById(R.id.prodel);
        proserchnum=findViewById(R.id.proserchnum);
        pronumpy=findViewById(R.id.pronumpy);
        tryexcept=findViewById(R.id.tryexcept);
        proclassO=findViewById(R.id.proclassO);
        prosyn=findViewById(R.id.prosyn);
        booleans=findViewById(R.id.booleans);
        arith=findViewById(R.id.arith);
        varscope=findViewById(R.id.varscope);
        comm=findViewById(R.id.comments);
        protypecon=findViewById(R.id.protypecon);
        numb=findViewById(R.id.numb);
        proindex=findViewById(R.id.proindex);
        t23=findViewById(R.id.t23);
        randomlib=findViewById(R.id.randomlib);
        prowhilepy=findViewById(R.id.prowhilepy);
        pronum=findViewById(R.id.pronum);
        pystrings=findViewById(R.id.pystrings);
        proidentity=findViewById(R.id.proidentity);
        prosort=findViewById(R.id.prosort);
        lessonfra=findViewById(R.id.lesson_fra);
        typeconversion=findViewById(R.id.typeconversion);
        members=findViewById(R.id.members);
        syntax=findViewById(R.id.syntax);
        lists=findViewById(R.id.lists);
        projson=findViewById(R.id.projson);
        profwrite=findViewById(R.id.profwrite);
        proread=findViewById(R.id.proread);
        jsonn=findViewById(R.id.jsonn);
        repy=findViewById(R.id.repy);
        prolists=findViewById(R.id.prolists);
        statement=findViewById(R.id.statement);
        proinherit=findViewById(R.id.proinherit);
        protuple=findViewById(R.id.protuple);
        homei=findViewById(R.id.home_image);
        set_s=findViewById(R.id.set_s);
        lessoni=findViewById(R.id.lessons_im);
        prosets=findViewById(R.id.prosets);
        hometext=findViewById(R.id.home_text);
        dictionary=findViewById(R.id.dictionary);
        lessontext=findViewById(R.id.lesson_text);
        prolambdaf=findViewById(R.id.prolambdaf);
        percentage=findViewById(R.id.percentage);
        pass_s=findViewById(R.id.pass_s);
        image=findViewById(R.id.image);
        classO=findViewById(R.id.classO);
        introfun=findViewById(R.id.introfun);
        cont=findViewById(R.id.contless);
        provarscope=findViewById(R.id.provarscope);
        propass=findViewById(R.id.propass);
        pyinstall=findViewById(R.id.pyinstall);
        prorandomlib=findViewById(R.id.prorandomlib);
        prointrofun=findViewById(R.id.proinfun);
        proinst=findViewById(R.id.proinst);
        if_else=findViewById(R.id.if_else);
        fu_n=findViewById(R.id.fu_n);
        writing=findViewById(R.id.writing);
        fdel=findViewById(R.id.fdel);
        proif_else=findViewById(R.id.proif_else);
        overviews=findViewById(R.id.pyover);
        prodictionary=findViewById(R.id.prodictionary);
        promember=findViewById(R.id.promember);
        basicf=findViewById(R.id.overpro);
        proifshort=findViewById(R.id.proifshort);
        operIntro=findViewById(R.id.operIntro);
        variable=findViewById(R.id.variable);
        provar=findViewById(R.id.provar);
        arrslice=findViewById(R.id.arrslice);
        nummethods=findViewById(R.id.nummethods);
        prodatetime=findViewById(R.id.prodatetime);
        identity=findViewById(R.id.identity);
        containers=findViewById(R.id.containers);
        pyintro=findViewById(R.id.pyintro);
        sortnum=findViewById(R.id.sortnum);
        inherit=findViewById(R.id.inherit);
        searchnumpy=findViewById(R.id.searchnumpy);
        procontainers=findViewById(R.id.procontainers);
        prointro=findViewById(R.id.prointro);
        profomstring=findViewById(R.id.profomstring);
        lambdaf=findViewById(R.id.lambdaf);
        numpyiterate=findViewById(R.id.numpyiterate);
        assigns=findViewById(R.id.assigns);
        tuples=findViewById(R.id.tuples);
        prostate=findViewById(R.id.prostate);
        ifshort=findViewById(R.id.ifshort);
        numpydatatype=findViewById(R.id.numpydatatype);
        prodis=findViewById(R.id.prodis);
        proforpy=findViewById(R.id.proforpy);
        prostinmeth=findViewById(R.id.prostinmeth);
        stringmeth=findViewById(R.id.stringmeth);
        forpy=findViewById(R.id.forpy);
        profun=findViewById(R.id.profun);
        t1=findViewById(R.id.t1);
        whilepy=findViewById(R.id.whilepy);
        pronumpydata=findViewById(R.id.pronumpydata);
        t2=findViewById(R.id.t2);
        protryexc=findViewById(R.id.protryexc);
        t3=findViewById(R.id.t3);
        pyiter=findViewById(R.id.pyiter);
        inputm=findViewById(R.id.inputm);
        t4=findViewById(R.id.t4);
        int_numpy=findViewById(R.id.int_numpy);
        intmodule=findViewById(R.id.intmodule);
        proconbrk=findViewById(R.id.proconbrk);
        promathmod=findViewById(R.id.promathmod);
        t5=findViewById(R.id.t5);
        fread=findViewById(R.id.fread);
        t6=findViewById(R.id.t6);
        profile=findViewById(R.id.profile);
        numpyjoin=findViewById(R.id.numpyjoin);
        t7=findViewById(R.id.t7);
        numpyindex=findViewById(R.id.numpyindex);
        prointmodule=findViewById(R.id.prointmodule);
        numpyarr=findViewById(R.id.numpyarr);
        fwrite=findViewById(R.id.fwrite);
        t8=findViewById(R.id.t8);
        prorepy=findViewById(R.id.prorepy);
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
        t42=findViewById(R.id.t42);
        t43=findViewById(R.id.t43);
        t44=findViewById(R.id.t44);
        t45=findViewById(R.id.t45);
        t51=findViewById(R.id.t51);
        t52=findViewById(R.id.t52);
        t53=findViewById(R.id.t53);
        t54=findViewById(R.id.t54);
        t61=findViewById(R.id.t61);
        t62=findViewById(R.id.t62);
        t63=findViewById(R.id.t63);
        t64=findViewById(R.id.t64);
        t65=findViewById(R.id.t65);
        t71=findViewById(R.id.t71);
        t72=findViewById(R.id.t72);
        t73=findViewById(R.id.t73);
        t74=findViewById(R.id.t74);
        t75=findViewById(R.id.t75);
        t76=findViewById(R.id.t76);
        t77=findViewById(R.id.t77);
        t81=findViewById(R.id.t81);
        t82=findViewById(R.id.t82);
        t83=findViewById(R.id.t83);
        t84=findViewById(R.id.t84);
        t85=findViewById(R.id.t85);
        t86=findViewById(R.id.t86);
        t91=findViewById(R.id.t91);
        t92=findViewById(R.id.t92);
        t93=findViewById(R.id.t93);
        t94=findViewById(R.id.t94);
        t101=findViewById(R.id.t101);
        t102=findViewById(R.id.t102);
        t103=findViewById(R.id.t103);
        t104=findViewById(R.id.t104);
        t105=findViewById(R.id.t105);
        t106=findViewById(R.id.t106);
        t107=findViewById(R.id.t107);
        t108=findViewById(R.id.t108);
        t109=findViewById(R.id.t109);


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
        prolists.setProgress(probar(clist,c2));
        protuple.setProgress(probar(ctup,c3));
        prosets.setProgress(probar(cs,c4));
        prodictionary.setProgress(probar(cd,c5));
        prointrofun.setProgress(probar(fint,f1));
        profun.setProgress(probar(fun,f2));
        prolambdaf.setProgress(probar(fl,f3));
        propass.setProgress(probar(fp,f4));
        proif_else.setProgress(probar(ifc,if1));
        proifshort.setProgress(probar(ifs,if2));
        proforpy.setProgress(probar(fors,if3));
        prowhilepy.setProgress(probar(whiles,if4));
        proconbrk.setProgress(probar(brk,if5));
        proclassO.setProgress(probar(pya1,p1));
        proinherit.setProgress(probar(pya2,p2));
        provarscope.setProgress(probar(pya3,p3));
        profomstring.setProgress(probar(pya4,p4));
        protryexc.setProgress(probar(pya5,p5));
        proinputm.setProgress(probar(pya7,p7));
                propyiter.setProgress(probar(pya6,p6));
        prointmodule.setProgress(probar(mod1,mo1));
        promathmod.setProgress(probar(mod2,mo2));
        prorandomlib.setProgress(probar(mod3,mo3));
        prodatetime.setProgress(probar(mod4,mo4));
        projson.setProgress(probar(mod5,mo5));
        prorepy.setProgress(probar(mod6,mo6));
        profile.setProgress(probar(fl1,fo1));
        proread.setProgress(probar(fl2,fo2));
        profwrite.setProgress(probar(fl3,fo3));
        prodel.setProgress(probar(fl4,fo4));
        pronumpy.setProgress(probar(np1,pb1));
        proarr.setProgress(probar(np2,pb2));
        proindex.setProgress(probar(np3,pb3));
        proarrslice.setProgress(probar(np4,pb4));
        pronumpydata.setProgress(probar(np5,pb5));
        proiternum.setProgress(probar(np6,pb6));
        projoin.setProgress(probar(np7,pb7));
        proserchnum.setProgress(probar(np8,pb8));
        prosort.setProgress(probar(np9,pb9));







        sortnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t109.getText().toString();
                String strand="9/9";
                String tno="Ten";
                int idl=63;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), NumSort.class);

                startActivity(insta);
            }
        });
        searchnumpy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t108.getText().toString();
                String strand="8/9";
                String tno="Ten";
                int idl=62;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), NumpySearch.class);

                startActivity(insta);
            }
        });
        numpyjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t107.getText().toString();
                String strand="7/9";
                String tno="Ten";
                int idl=61;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), JoinNumpy.class);

                startActivity(insta);
            }
        });


        numpyiterate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t106.getText().toString();
                String strand="6/9";
                String tno="Ten";
                int idl=60;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), numpyIter.class);

                startActivity(insta);
            }
        });
        numpydatatype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t105.getText().toString();
                String strand="5/9";
                String tno="Ten";
                int idl=59;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), NDataType.class);

                startActivity(insta);
            }
        });
        arrslice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t104.getText().toString();
                String strand="4/9";
                String tno="Ten";
                int idl=58;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), ArrSlicing.class);

                startActivity(insta);
            }
        });
        numpyindex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t103.getText().toString();
                String strand="3/9";
                String tno="Ten";
                int idl=57;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), ArrIndexing.class);

                startActivity(insta);
            }
        });
        numpyarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t102.getText().toString();
                String strand="2/9";
                String tno="Ten";
                int idl=56;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), Numpy_arrays.class);

                startActivity(insta);
            }
        });

        int_numpy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t101.getText().toString();
                String strand="1/9";
                String tno="Ten";
                int idl=55;
                learning(topic,strand,tno,idl);
                    Intent insta=new Intent(getApplicationContext(), Int_numpy.class);

                startActivity(insta);
            }
        });

        fdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t94.getText().toString();
                String strand="4/4";
                String tno="Nine";
                int idl=54;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), FDel.class);

                startActivity(insta);
            }
        });
        fwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t93.getText().toString();
                String strand="3/4";
                String tno="Nine";
                int idl=52;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), WFile.class);

                startActivity(insta);
            }
        });


        fread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t92.getText().toString();
                String strand="2/4";
                String tno="Nine";
                int idl=51;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), RFile.class);

                startActivity(insta);
            }
        });


        int_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t91.getText().toString();
                String strand="1/4";
                String tno="Nine";
                int idl=50;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), IntFile.class);

                startActivity(insta);
            }
        });

        repy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t86.getText().toString();
                String strand="6/6";
                String tno="Eight";
                int idl=49;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), REactivity.class);

                startActivity(insta);
            }
        });

        jsonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t85.getText().toString();
                String strand="5/6";
                String tno="Eight";
                int idl=48;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), ActivityJson.class);

                startActivity(insta);
            }
        });



        datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t84.getText().toString();
                String strand="4/6";
                String tno="Eight";
                int idl=47;
                learning(topic,strand,tno,idl);
                    Intent insta=new Intent(getApplicationContext(), DateTimeModule.class);

                startActivity(insta);
            }
        });

        randomlib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t83.getText().toString();
                String strand="3/6";
                String tno="Eight";
                int idl=46;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), Rand_ompy.class);

                startActivity(insta);
            }
        });

        maolib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t82.getText().toString();
                String strand="2/6";
                String tno="Eight";
                int idl=45;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), MathModule.class);

                startActivity(insta);
            }
        });
        intmodule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t81.getText().toString();
                String strand="1/6";
                String tno="Eight";
                int idl=44;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), IntroModule.class);

                startActivity(insta);
            }
        });

        inputm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t77.getText().toString();
                String strand="7/7";
                String tno="Seven";
                int idl=43;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), UserInput.class);

                startActivity(insta);
            }
        });

        pyiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t76.getText().toString();
                String strand="6/7";
                String tno="Seven";
                int idl=42;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), Iterator.class);

                startActivity(insta);
            }
        });

        tryexcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t75.getText().toString();
                String strand="5/7";
                String tno="Seven";
                int idl=41;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), ErrorHandling.class);

                startActivity(insta);
            }
        });
        fomstrings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t74.getText().toString();
                String strand="4/7";
                String tno="Seven";
                int idl=40;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), FormatingStrings.class);

                startActivity(insta);
            }
        });

        varscope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t73.getText().toString();
                String strand="3/7";
                String tno="Seven";
                int idl=39;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), VarScope.class);

                startActivity(insta);
            }
        });


        inherit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t72.getText().toString();
                String strand="2/7";
                String tno="Seven";
                int idl=38;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), Inheritance.class);

                startActivity(insta);
            }
        });
        classO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t71.getText().toString();
                String strand="1/7";
                String tno="Seven";
                int idl=37;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), ClassesPy.class);

                startActivity(insta);
            }
        });


        conbrk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t65.getText().toString();
                String strand="5/5";
                String tno="Six";
                int idl=36;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), BreakCon.class);

                startActivity(insta);
            }
        });



        whilepy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t64.getText().toString();
                String strand="4/5";
                String tno="Six";
                int idl=35;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), WhileActivity.class);

                startActivity(insta);
            }
        });




        forpy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t63.getText().toString();
                String strand="3/5";
                String tno="Six";
                int idl=34;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), For_Loop.class);

                startActivity(insta);
            }
        });
        ifshort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t62.getText().toString();
                String strand="2/5";
                String tno="Six";
                int idl=33;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), Ifes.class);

                startActivity(insta);
            }
        });


        if_else.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t61.getText().toString();
                String strand="1/5";
                String tno="Six";
                int idl=32;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), If_el.class);

                startActivity(insta);
            }
        });

        pass_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t54.getText().toString();
                String strand="4/4";
                String tno="Five";
                int idl=31;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), PassPy.class);

                startActivity(insta);
            }
        });
        lambdaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t53.getText().toString();
                String strand="3/4";
                String tno="Five";
                int idl=30;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), lambdaActivity.class);

                startActivity(insta);
            }
        });
fu_n.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String topic =t52.getText().toString();
        String strand="2/4";
        String tno="Five";
        int idl=29;
        learning(topic,strand,tno,idl);
        Intent insta=new Intent(getApplicationContext(), FunDef.class);

        startActivity(insta);
    }
});

        introfun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t51.getText().toString();
                String strand="1/4";
                String tno="Five";
                int idl=28;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), Introdef.class);

                startActivity(insta);
            }
        });

        dictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t45.getText().toString();
                String strand="5/5";
                String tno="Four";
                int idl=27;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), Dictionar_y.class);

                startActivity(insta);
            }
        });

        set_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t44.getText().toString();
                String strand="4/5";
                String tno="Four";
                int idl=26;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), Setsc.class);

                startActivity(insta);
            }
        });

        tuples.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t43.getText().toString();
                String strand="3/5";
                String tno="Four";
                int idl=25;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), Ctuple.class);

                startActivity(insta);
            }
        });

        lists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic =t42.getText().toString();
                String strand="2/5";
                String tno="Four";
                int idl=24;
                learning(topic,strand,tno,idl);
                Intent insta=new Intent(getApplicationContext(), Listc.class);

                startActivity(insta);
            }
        });

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

                    BrightnessUtil.showBrightnessDialog(LessonsActivity.this);  // 'this' refers to the current Activity context

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

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }






}




