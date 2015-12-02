package moony.codemonent;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: MOONY
 * @data: 15/12/1
 * @Description: ${todo}<请描述此文件是做什么的>
 */
public class NewsWebActivity extends AppCompatActivity {
    @Bind(R.id.webview)
    WebView webview;
    private String new_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_web);
        ButterKnife.bind(this);

        new_url = getIntent().getStringExtra("url");

        webview.getSettings().setJavaScriptEnabled(true);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });
        webview.loadUrl(new_url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webview.canGoBack()) {
                        webview.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }


}
