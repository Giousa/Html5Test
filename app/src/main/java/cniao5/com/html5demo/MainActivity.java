package cniao5.com.html5demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private WebView mWebView;
    private Button button;

    private WebAppInterface appInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        mWebView = (WebView) findViewById(R.id.webview);

        mWebView.loadUrl("file:///android_asset/index.html");

        mWebView.getSettings().setJavaScriptEnabled(true);

        appInterface = new WebAppInterface(this);
        mWebView.addJavascriptInterface(appInterface,"app");

    }


    @Override
    public void onClick(View v) {

        appInterface.showName("不笑猫");
    }

    class  WebAppInterface{


        private Context context;

        public WebAppInterface(Context ct){

            this.context  = ct;
        }

        @JavascriptInterface
        public void sayHello(String name){


            Toast.makeText(context,"name="+name,Toast.LENGTH_LONG).show();

        }


        public void showName(final String name){


            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    mWebView.loadUrl("javascript:showName('"+name+"')");
                }
            });

        }




    }





}
