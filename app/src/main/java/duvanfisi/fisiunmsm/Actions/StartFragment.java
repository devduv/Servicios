package duvanfisi.fisiunmsm.Actions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import java.util.Objects;

import duvanfisi.fisiunmsm.R;
import duvanfisi.fisiunmsm.ActivitiesUsers.MainActivity;


public class StartFragment {
    static int contenedor_principal = R.id.container;
    private static String FRAGMENT_OTHER = "";

    public static void startFragment(final String fragment_name, Fragment fragment, int id){
        Fragment frg = MainActivity.fragmentManager.findFragmentById(fragment.getId());
        FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
        if (fragment.isAdded()) {
            transaction
                    .hide(MainActivity.currentFragment)
                    .show(fragment);
            MainActivity.currentFragment = fragment;
        } else {
            if(MainActivity.currentFragment!=null){
                transaction
                        .hide(MainActivity.currentFragment)
                        .add(contenedor_principal, fragment, fragment_name);
                MainActivity.currentFragment = fragment;
            }else{
                transaction
                        .add(contenedor_principal, fragment, fragment_name);
                MainActivity.currentFragment = fragment;
            }


        }

        MainActivity.item.add(id);
        transaction.commit();
    }

    public static void startFragmentBack(final String fragment_name, Fragment fragment){
        FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
        if (fragment.isAdded()) {
            transaction
                    .hide(MainActivity.currentFragment)
                    .show(fragment);
            MainActivity.currentFragment = fragment;
        } else {
            transaction
                    .add(contenedor_principal, fragment, fragment_name);
            MainActivity.currentFragment = fragment;

        }
        transaction.commit();

    }
    public static void startFragment(final String fragment_name, Fragment fragment, Bundle bundle, int id){

        fragment.setArguments(bundle);
        FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
        if (fragment.isAdded()) {
            transaction
                    .hide(MainActivity.currentFragment)
                    .show(fragment);
            MainActivity.currentFragment = fragment;
        } else {
            if(MainActivity.currentFragment!=null){
                transaction
                        .hide(MainActivity.currentFragment)
                        .add(contenedor_principal, fragment, fragment_name);
                MainActivity.currentFragment = fragment;
            }else{
                transaction
                        .add(contenedor_principal, fragment, fragment_name);
                MainActivity.currentFragment = fragment;
            }


        }

        //MainActivity.item.add(id);
        transaction.commit();

    }

}
