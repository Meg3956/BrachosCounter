package com.example.shaina.brachoscounter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Meira on 5/10/2016.
 */
public class DaveningAdapter extends BaseAdapter {
    private  Map<String, String[]> mCATEGORIES;
    private  Map<String, int[]> mNUMBER_OF_BRACHOS;

    public DaveningAdapter(boolean maleBrachos, boolean extraMaarivBracha) {
        createCategories(maleBrachos, extraMaarivBracha);
        
    }

    private void createCategories(boolean maleBrachos, boolean extraMaarivBracha) {
        createShachrisArray(maleBrachos);
        createMinchaArray();
        createMaarivArray(extraMaarivBracha);
        createHallelArray();
        createMussafArray();
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    private void createShachrisArray(){
        String title="Shachris";
        String[] shachrisArray={"Birchos HaShachar", "Birchos HaTorah", "Psukei DZimra", "Birchos Krias Shema", "Shemoneh Esrei"};
        int[] brachosArray={17,3,2,3,19};
        mCATEGORIES.put(title, shachrisArray);
        mNUMBER_OF_BRACHOS.put(title, brachosArray );
    }

    public void createMinchaArray() {
        String title="Mincha";
        String[] minchaArray={"Shemoneh Esrei"};
        int[] brachosArray={19};
        mCATEGORIES.put(title, minchaArray);
        mNUMBER_OF_BRACHOS.put(title, brachosArray );
    }
    public void createMaarivArray() {
        String title="Maariv";
        String[] maarivArray={"Birchos Krias Shema","Shemoneh Esrei"};
        int[] brachosArray={4, 19};
        mCATEGORIES.put(title, maarivArray);
        mNUMBER_OF_BRACHOS.put(title, brachosArray );
    }
    public void createHallelArray() {
        String title="Hallel";
        String[] hallelArray={"Hallel"};
        int[] brachosArray={2};
        mCATEGORIES.put(title, hallelArray);
        mNUMBER_OF_BRACHOS.put(title, brachosArray );
    }
    public void createMussafArray() {
        String title="Mussaf";
        String[] mussafArray={"Shemoneh Esrei"};
        int[] brachosArray={8};
        mCATEGORIES.put(title, mussafArray);
        mNUMBER_OF_BRACHOS.put(title, brachosArray );
    }




}
