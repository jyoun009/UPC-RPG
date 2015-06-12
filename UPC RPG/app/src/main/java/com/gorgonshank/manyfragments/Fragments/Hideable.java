package com.gorgonshank.manyfragments.Fragments;

/**
 * Created by MelloKitty on 6/4/2015.
 */
public interface Hideable {

    public void onHideFragment();
    public void onShowFragment();
    public void setUserVisibleHint(boolean isVisibleToUser);

}
