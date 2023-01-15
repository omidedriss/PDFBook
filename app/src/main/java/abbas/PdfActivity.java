package abbas;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import android.text.Html;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.listener.OnLongPressListener;
import com.xeoh.android.texthighlighter.TextHighlighter;
import top.defaults.colorpicker.ColorPickerPopup;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.orbitsoft.pdfbook.MainActivity;
import com.orbitsoft.pdfbook.R;

public class PdfActivity extends AppCompatActivity implements OnDrawListener {
    ActivityResultLauncher<Intent> resultLauncher;
    TextView tvUri;
    ConstraintLayout constraintLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        constraintLayout = findViewById(R.id.constraintForClick);
        show();
    }


    public void show (){
        PDFView pdfView = findViewById(R.id.pdfView);
      //  tvUri = findViewById(R.id.tv_test);
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts
                        .StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(
                            ActivityResult result)
                    {
                        // Initialize result data
                        Intent data = result.getData();
                        // check condition
                        if (data != null) {
                            // When data is not equal to empty
                            // Get PDf uri
                            Uri sUri = data.getData();
                            // Get PDF path
                            String sPath = sUri.getPath();
                            // Set path on text view
                          /*  tvPath.setText(Html.fromHtml(
                                    "<big><b>PDF Path</b></big><br>"
                                            + sPath));*/
                            // set Uri on text view
                            pdfView.fromUri(sUri).enableSwipe(true) // allows to block changing pages using swipe
                                    .swipeHorizontal(false)
                                    .enableDoubletap(false)
                                    .defaultPage(0)
                                    // allows to draw something on the current page, usually visible in the middle of the screen
                                    .onDraw(PdfActivity.this)
                                    // allows to draw something on all pages, separately for every page. Called only for visible pages
                                    // .onDrawAll(onDrawListener)
                                    // .onLoad(onLoadCompleteListener) // called after document is loaded and starts to be rendered
                                    // .onPageChange(onPageChangeListener)
                                    // .onPageScroll(onPageScrollListener)
                                    // .onError(onErrorListener)
                                    // .onPageError(onPageErrorListener)
                                     //.onRender(onRenderListener) // called after document is rendered for the first time
                                    // called on single tap, return true if handled, false to toggle scroll handle visibility
                                    //.onTap(onTapListener)
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
                         /* new TextHighlighter()
                                    .setBackgroundColor( Color.parseColor( "#FFFF00" ) )
                                    .setForegroundColor( Color.GREEN )
                                    .addTarget( tvUri )
                                    .highlight( tvUri.getText().toString(), TextHighlighter.BASE_MATCHER );
*/
                        /*   tvUri.setText(Html.fromHtml(
                                    "<big><b>PDF Uri</b></big><br>"
                                            + sUri));
*/

                        }
                    }
                });

        // Set click listener on button
                        // check condition
                        if (ActivityCompat.checkSelfPermission(
                                PdfActivity.this,
                                Manifest.permission
                                        .READ_EXTERNAL_STORAGE)
                                != PackageManager
                                .PERMISSION_GRANTED) {
                            // When permission is not granted
                            // Result permission
                            ActivityCompat.requestPermissions(
                                    PdfActivity.this,
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



   /* private void getMyColor(View v) {
        new ColorPickerPopup.Builder(this)
                .initialColor(Color.BLUE)
                .enableAlpha(true)
                .okTitle("Choose")
                .cancelTitle("Cancel")
                .showIndicator(true)
                .showValue(true)
                .onlyUpdateOnTouchEventUp(true)
                .build()
                .show(v, new ColorPickerPopup.ColorPickerObserver() {
                    @Override
                    public void onColorPicked(int color) {
                        selectedColor=color;
                    }
                });
    }
*/
    private void selectPDF()
    {
        // Initialize intent
        Intent intent
                = new Intent(Intent.ACTION_GET_CONTENT);
        // set type
        intent.setType("application/pdf");
        // Launch intent
        resultLauncher.launch(intent);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);

        // check condition
        if (requestCode == 1 && grantResults.length > 0
                && grantResults[0]
                == PackageManager.PERMISSION_GRANTED) {
            // When permission is granted
            // Call method
            selectPDF();
        }
        else {
            // When permission is denied
            // Display toast
            Toast
                    .makeText(getApplicationContext(),
                            "Permission Denied",
                            Toast.LENGTH_SHORT)
                    .show();
        }
    }


    @Override
    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

        canvas.drawColor(0);

        // canvas.drawColor();
    }

}
