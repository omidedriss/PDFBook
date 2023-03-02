package com.orbitsoft.pdfbook;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static java.nio.file.Files.walk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class libraryFragment extends Fragment implements recyclerInterface {
    RecyclerView RecyclerView;

    private  boolean readPermisson=false;
    private  boolean writPermission=false;
    private ArrayList<File> myPdfFiles;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private String[] permissions = {READ_EXTERNAL_STORAGE};




    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflatne the layout for this fragment


        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_library, container, false);

        RecyclerView = view.findViewById(R.id.recycler);
        checkPermission2();


        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult( ActivityResult result ) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if (Environment.isExternalStorageManager()){
                        Toast.makeText(getContext(),"Permission is Granted",Toast.LENGTH_SHORT).show();


                    }
                    else{
                        if (ActivityCompat.checkSelfPermission(getContext(),READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(getContext(), "Permission is Granted", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Permission is Denied", Toast.LENGTH_SHORT).show();

                        }

                    }
                }
            }
        });


        return view;
    }




    private void checkPermission2() {

        if (checkPermission()) {


            displayPdfFiles();   // WE have a permission just start your work.
        } else {
            requestPermission(); // Request Permission

        }
    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int readCheck = ContextCompat.checkSelfPermission(getContext(), READ_EXTERNAL_STORAGE);
            return readCheck == PackageManager.PERMISSION_GRANTED ;
        }
    }



    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Permission")
                    .setMessage("To Add Your PDF Files We Need Your Permission")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick( DialogInterface dialog, int which ) {
                            try {
                                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                                intent.addCategory("android.intent.category.DEFAULT");
                                intent.setData(Uri.parse(String.format("package:%s", new Object[]{getActivity().getApplicationContext().getPackageName()})));
                                activityResultLauncher.launch(intent);
                            } catch (Exception e) {
                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                                activityResultLauncher.launch(intent);
                            }
                        }
                    })
                    .setCancelable(false)
                    .show();

        } else {

            ActivityCompat.requestPermissions((Activity) getContext()
                    , new String[]{READ_EXTERNAL_STORAGE} , 30);
        }
    }




    public ArrayList<File> findPdfFiles(File file){
        ArrayList<File> arrayList=new ArrayList<>();
        File[] files=file.listFiles();
        if (files!=null){
            for (File singleFile:files) {
                if (singleFile.isDirectory() && !singleFile.isHidden()) {
                    arrayList.addAll(findPdfFiles(singleFile));
                } else {
                    if (singleFile.getName().toLowerCase().endsWith(".pdf") )
                        arrayList.add(singleFile);
                }
            }
        }return arrayList;
    }

    public void displayPdfFiles(){
         myPdfFiles = findPdfFiles(Environment.getExternalStorageDirectory());

        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 4));
        recyclerAdapter adapter = new recyclerAdapter(this.getContext(), myPdfFiles,this);
        RecyclerView.setAdapter(adapter);


    }

    //gozashten click lisner baraye file  va ferestadane file be HomeFragment
    @Override
    public void onItemClick(int position) {

        String fileAddress= myPdfFiles.get(position).getAbsolutePath();
        Bundle bundle=new Bundle();
        bundle.putString("address",fileAddress);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment, "");
        fragmentTransaction.commit();


    }


}