package com.example.androidtask_itwolf.MainScreen.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.example.androidtask_itwolf.ContactsScreen.View.ContactsActivity;
import com.example.androidtask_itwolf.MainScreen.JavascriptInterface.WebAppInterface;
import com.example.androidtask_itwolf.R;

public class MainScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_main);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        webView.loadDataWithBaseURL("", getHTML(), "text/html", "UTF-8", "");

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.message) {
            String javaScript = "javascript:window.document.getElementById('myheader').innerHTML='Text Changed';";
            webView.evaluateJavascript(javaScript, null);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private String getHTML() {
        return "<html>\n" +
                "<head>\n" +
                "<script type=\"text/javascript\">\n" +
                "function myFunction() {\n" +
                "  Android.showToastMessage();\n" +
                "}\n" +
                "</script>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<h1 id=\"myheader\" > Hesham Muhammed </h1>\n" +
                "<button type=\"button\" onclick=\"myFunction()\" >Click Here</button>\n" +
                "</body>\n" +
                "</html>";
    }

    public void nextPage(View view){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }
}
