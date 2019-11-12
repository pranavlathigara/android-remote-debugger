package com.zerobranch.remotedebugger.api.home;

import android.content.Context;

import com.zerobranch.remotedebugger.api.base.Controller;
import com.zerobranch.remotedebugger.http.Host;
import com.zerobranch.remotedebugger.settings.InternalSettings;
import com.zerobranch.remotedebugger.source.models.Settings;
import com.zerobranch.remotedebugger.utils.FileUtils;

import java.util.List;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

public class HomeController extends Controller {

    public HomeController(Context context, InternalSettings internalSettings) {
        super(context, internalSettings);
    }

    @Override
    public String execute(Map<String, List<String>> params) throws NanoHTTPD.ResponseException {
        if (params == null || params.isEmpty()) {
            return FileUtils.getTextFromAssets(context.getAssets(), Host.INDEX.getPath());
        } else if (params.containsKey(HomeKey.GET_SETTINGS)) {
            return getSettings();
        }
        return EMPTY;
    }

    private String getSettings() {
        return serialize(new Settings(context.getPackageName()));
    }
}