package com.orbitsoft.pdfbook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import abbas.PdfActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
Button bt_select;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
   public static PDFView pdfView;
    public static View v;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static HomeFragment Instance;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForBottonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       v=  inflater.inflate(R.layout.fragment_home, container, false);
      Button btSelect = v.findViewById(R.id.bt_select);
         pdfView = v.findViewById(R.id.pdfView);
        // Set click listener on button
        btSelect.setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View v)
                    {
                        // check condition
                        if (ActivityCompat.checkSelfPermission(
                                MainActivity.Instance,
                                Manifest.permission
                                        .READ_EXTERNAL_STORAGE)
                                != PackageManager
                                .PERMISSION_GRANTED) {
                            // When permission is not granted
                            // Result permission
                            ActivityCompat.requestPermissions(
                                    MainActivity.Instance,
                                    new String[] {
                                            Manifest.permission
                                                    .READ_EXTERNAL_STORAGE },
                                    1);
                        }
                        else {
                            // When permission is granted
                            // Create method
                            selectPDF();
                        }
                    }
                });


        return v;

    }
    private void selectPDF()
    {
        // Initialize intent
        Intent intent
                = new Intent(Intent.ACTION_GET_CONTENT);
        // set type
        intent.setType("application/pdf");
        // Launch intent
       // MainActivity.Instance.setResult(1,intent);
        MainActivity.Instance.startActivityForResult(intent, 1);

//        ActivityResultLauncher<String> mGetContent = MainActivity.Instance.registerForActivityResult(new ActivityResultContracts.GetContent(),
//                new ActivityResultCallback<Uri>() {
//                    @Override
//                    public void onActivityResult(Uri uri) {
//                        // Handle the returned Uri
//                        // Initialize result data
////                        Intent data = uri;
//                        // check condition
////                        if (data != null) {
//                            // When data is not equal to empty
//                            // Get PDf uri
//                            Uri sUri = uri;
//                            // Get PDF path
//                            String sPath = sUri.getPath();
//                            // Set path on text view
//
//                            // set Uri on text view
//                            pdfView.fromUri(sUri).enableSwipe(true) // allows to block changing pages using swipe
//                                    .swipeHorizontal(false)
//                                    .enableDoubletap(true)
//                                    .defaultPage(0)
//                                    // allows to draw something on the current page, usually visible in the middle of the screen
//                                    .onDraw(MainActivity.Instance)
//                                    // allows to draw something on all pages, separately for every page. Called only for visible pages
//                                    // .onDrawAll(onDrawListener)
//                                    // .onLoad(onLoadCompleteListener) // called after document is loaded and starts to be rendered
//                                    // .onPageChange(onPageChangeListener)
//                                    // .onPageScroll(onPageScrollListener)
//                                    // .onError(onErrorListener)
//                                    // .onPageError(onPageErrorListener)
//                                    // .onRender(onRenderListener) // called after document is rendered for the first time
//                                    // called on single tap, return true if handled, false to toggle scroll handle visibility
//                                    //  .onTap(onTapListener)
//                                    //  .onLongPress(onLongPressListener)
//                                    .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
//                                    .password(null)
//                                    .scrollHandle(null)
//                                    .enableAntialiasing(true) // improve rendering a little bit on low-res screens
//                                    // spacing between pages in dp. To define spacing color, set view background
//                                    .spacing(0)
//                                    .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
//                                    //   .linkHandler(DefaultLinkHandler)
//                                    .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
//                                    .fitEachPage(false) // fit each page to the view, else smaller pages are scaled relative to largest page.
//                                    .pageSnap(false) // snap pages to screen boundaries
//                                    .pageFling(false) // make a fling change only a single page like ViewPager
//                                    .nightMode(false) // toggle night mode
//                                    .load();
////                            new TextHighlighter()
////                                    .setBackgroundColor( Color.parseColor( "#FFFF00" ) )
////                                    .setForegroundColor( Color.GREEN )
////                                    .addTarget( tvUri )
////                                    .highlight( tvUri.getText().toString(), TextHighlighter.BASE_MATCHER );
//
////                            tvUri.setText(Html.fromHtml(
////                                    "<big><b>PDF Uri</b></big><br>"
////                                            + sUri));
//
//                        }
//                });
//        MainActivity.Instance.getActivityResultRegistry(registerForActivityResult(
//                new ActivityResultContracts
//                        .StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result)
//                    {
//                        // Initialize result data
//                        Intent data = result.getData();
//                        // check condition
//                        if (data != null) {
//                            // When data is not equal to empty
//                            // Get PDf uri
//                            Uri sUri = data.getData();
//                            // Get PDF path
//                            String sPath = sUri.getPath();
//                            // Set path on text view
//
//                            // set Uri on text view
//                            pdfView.fromUri(sUri).enableSwipe(true) // allows to block changing pages using swipe
//                                    .swipeHorizontal(false)
//                                    .enableDoubletap(true)
//                                    .defaultPage(0)
//                                    // allows to draw something on the current page, usually visible in the middle of the screen
//                                    .onDraw(MainActivity.Instance)
//                                    // allows to draw something on all pages, separately for every page. Called only for visible pages
//                                    // .onDrawAll(onDrawListener)
//                                    // .onLoad(onLoadCompleteListener) // called after document is loaded and starts to be rendered
//                                    // .onPageChange(onPageChangeListener)
//                                    // .onPageScroll(onPageScrollListener)
//                                    // .onError(onErrorListener)
//                                    // .onPageError(onPageErrorListener)
//                                    // .onRender(onRenderListener) // called after document is rendered for the first time
//                                    // called on single tap, return true if handled, false to toggle scroll handle visibility
//                                    //  .onTap(onTapListener)
//                                    //  .onLongPress(onLongPressListener)
//                                    .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
//                                    .password(null)
//                                    .scrollHandle(null)
//                                    .enableAntialiasing(true) // improve rendering a little bit on low-res screens
//                                    // spacing between pages in dp. To define spacing color, set view background
//                                    .spacing(0)
//                                    .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
//                                    //   .linkHandler(DefaultLinkHandler)
//                                    .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
//                                    .fitEachPage(false) // fit each page to the view, else smaller pages are scaled relative to largest page.
//                                    .pageSnap(false) // snap pages to screen boundaries
//                                    .pageFling(false) // make a fling change only a single page like ViewPager
//                                    .nightMode(false) // toggle night mode
//                                    .load();
////                            new TextHighlighter()
////                                    .setBackgroundColor( Color.parseColor( "#FFFF00" ) )
////                                    .setForegroundColor( Color.GREEN )
////                                    .addTarget( tvUri )
////                                    .highlight( tvUri.getText().toString(), TextHighlighter.BASE_MATCHER );
//
////                            tvUri.setText(Html.fromHtml(
////                                    "<big><b>PDF Uri</b></big><br>"
////                                            + sUri));
//
//
//                        }
//                    }
//                }));
//        MainActivity.Instance.getActivityResultRegistry().resultLauncher.launch(intent);
    }
    public static void pdf(Uri sUri){
        pdfView = v.findViewById(R.id.pdfView);
        pdfView.fromUri(sUri).enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                // allows to draw something on the current page, usually visible in the middle of the screen
                .onDraw(MainActivity.Instance)
                // allows to draw something on all pages, separately for every page. Called only for visible pages
                // .onDrawAll(onDrawListener)
                // .onLoad(onLoadCompleteListener) // called after document is loaded and starts to be rendered
                // .onPageChange(onPageChangeListener)
                // .onPageScroll(onPageScrollListener)
                // .onError(onErrorListener)
                // .onPageError(onPageErrorListener)
                // .onRender(onRenderListener) // called after document is rendered for the first time
                // called on single tap, return true if handled, false to toggle scroll handle visibility
                //  .onTap(onTapListener)
                //  .onLongPress(onLongPressListener)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
                //   .linkHandler(DefaultLinkHandler)
                .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
                .fitEachPage(false) // fit each page to the view, else smaller pages are scaled relative to largest page.
                .pageSnap(false) // snap pages to screen boundaries
                .pageFling(false) // make a fling change only a single page like ViewPager
                .nightMode(false) // toggle night mode
                .load();
    }
}