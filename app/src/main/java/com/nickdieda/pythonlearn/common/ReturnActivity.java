package com.nickdieda.pythonlearn.common;

import android.content.Context;
import android.content.Intent;

import com.nickdieda.pythonlearn.Lessons.basic.InstallPy;
import com.nickdieda.pythonlearn.Lessons.basic.Introduction;
import com.nickdieda.pythonlearn.Lessons.basic.OverviewBasic;
import com.nickdieda.pythonlearn.Lessons.basic.PDis;
import com.nickdieda.pythonlearn.Lessons.basic.PyComments;
import com.nickdieda.pythonlearn.Lessons.basic.PyState;
import com.nickdieda.pythonlearn.Lessons.basic.Syntaxs;
import com.nickdieda.pythonlearn.Lessons.basic.WPCodes;

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
        }



        return bk;
    }
}
