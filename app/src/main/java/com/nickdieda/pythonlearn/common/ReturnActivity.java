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
        }





        return bk;
    }
}
