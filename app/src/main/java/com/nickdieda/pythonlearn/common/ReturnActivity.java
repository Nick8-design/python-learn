package com.nickdieda.pythonlearn.common;

import android.content.Context;
import android.content.Intent;

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

public class ReturnActivity {


    public Intent returnINT(Context act, int activityid ){
        Intent bk = new Intent();
        if(activityid==0) {
            bk  =new Intent(act, OverviewBasic.class);
        } else if (activityid==1) {
            bk = new Intent(act, Introduction.class);
        } else if (activityid==2) {
            bk = new Intent(act, InstallPy.class);
        } else if (activityid==3) {
            bk = new Intent(act, WPCodes.class);
        } else if (activityid==4) {
            bk = new Intent(act, PDis.class);
        } else if (activityid==5) {
            bk = new Intent(act, PyState.class);
        } else if (activityid==6) {
            bk = new Intent(act, Syntaxs.class);
        } else if (activityid==7) {
            bk = new Intent(act, PyComments.class);
        } else if (activityid==8) {
            bk = new Intent(act, PyVar.class);
        } else if (activityid==9) {
            bk = new Intent(act, PyData.class);
        } else if (activityid==10) {
            bk = new Intent(act, Pynum.class);
        } else if (activityid==11) {
            bk = new Intent(act, Num_met.class);
        } else if (activityid==12) {
            bk = new Intent(act, PyString.class);
        } else if (activityid==13) {
            bk = new Intent(act, Pystrmed.class);
        } else if (activityid==14) {
            bk = new Intent(act, TypeCon.class);
        } else if (activityid==15) {
            bk = new Intent(act, Bools.class);
        } else if (activityid==16) {
            bk = new Intent(act, IntOpera.class);
        } else if (activityid==17) {
            bk = new Intent(act, ArithOper.class);
        } else if (activityid==18) {
            bk = new Intent(act, Assign.class);
        } else if (activityid==19) {
            bk = new Intent(act, CompOpera.class);
        } else if (activityid==20) {
            bk = new Intent(act, LogOp.class);
        } else if (activityid==21) {
            bk = new Intent(act, IdentityOp.class);
        } else if (activityid==22) {
            bk = new Intent(act, MemberShip.class);
        } else if (activityid==23) {
            bk = new Intent(act, introcol.class);
        } else if (activityid==24) {
            bk = new Intent(act, Listc.class);
        } else if (activityid==25) {
            bk = new Intent(act, Ctuple.class);
        } else if (activityid==26) {
            bk = new Intent(act, Setsc.class);
        } else if (activityid==27) {
            bk = new Intent(act, Dictionar_y.class);
        } else if (activityid==28) {
            bk = new Intent(act, Introdef.class);
        } else if (activityid==29) {
            bk = new Intent(act, FunDef.class);
        } else if (activityid==30) {
            bk = new Intent(act, lambdaActivity.class);
        } else if (activityid==31) {
            bk = new Intent(act, PassPy.class);
        } else if (activityid==32) {
            bk = new Intent(act, If_el.class);
        } else if (activityid==33) {
            bk = new Intent(act, Ifes.class);
        } else if (activityid==34) {
            bk = new Intent(act, For_Loop.class);
        } else if (activityid==35) {
            bk = new Intent(act, WhileActivity.class);
        } else if (activityid==36) {
            bk = new Intent(act, BreakCon.class);
        } else if (activityid==37) {
            bk = new Intent(act, ClassesPy.class);
        } else if (activityid==38) {
            bk = new Intent(act, Inheritance.class);
        } else if (activityid==39) {
            bk = new Intent(act, VarScope.class);
        } else if (activityid==40) {
            bk = new Intent(act, FormatingStrings.class);
        } else if (activityid==41) {
            bk = new Intent(act, ErrorHandling.class);
        } else if (activityid==42) {
            bk = new Intent(act, Iterator.class);
        } else if (activityid==43) {
            bk = new Intent(act, UserInput.class);
        } else if (activityid==44) {
            bk = new Intent(act, IntroModule.class);
        } else if (activityid==45) {
            bk = new Intent(act, MathModule.class);
        } else if (activityid==46) {
            bk = new Intent(act, Rand_ompy.class);
        } else if (activityid==47) {
            bk = new Intent(act, DateTimeModule.class);
        } else if (activityid==48) {
            bk = new Intent(act, ActivityJson.class);
        } else if (activityid==49) {
            bk = new Intent(act, REactivity.class);
        } else if (activityid==50) {
            bk = new Intent(act, IntFile.class);
        } else if (activityid==51) {
            bk = new Intent(act, RFile.class);
        } else if (activityid==52) {
            bk = new Intent(act, WFile.class);
        } else if (activityid==53) {
            bk = new Intent(act, FDel.class);
        } else if (activityid==54) {
            bk = new Intent(act, Int_numpy.class);
        } else if (activityid==55) {
            bk = new Intent(act, Numpy_arrays.class);
        } else if (activityid==56) {
            bk = new Intent(act, ArrIndexing.class);
        } else if (activityid==57) {
            bk = new Intent(act, ArrSlicing.class);
        } else if (activityid==58) {
            bk = new Intent(act, NDataType.class);
        } else if (activityid==59) {
            bk = new Intent(act, numpyIter.class);
        } else if (activityid==60) {
            bk = new Intent(act, JoinNumpy.class);
        } else if (activityid==61) {
            bk = new Intent(act, NumpySearch.class);
        } else if (activityid==62) {
            bk = new Intent(act, NumSort.class);
        }





        return bk;
    }
}
