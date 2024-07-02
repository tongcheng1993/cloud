package com.zifuji.cloud.server.sys.module.help.service;

import com.zifuji.cloud.server.sys.module.help.mo.HelpContentControllerMo;

public interface HelpService {

    String saveHelpContent(HelpContentControllerMo helpContentMo);

    String getHelpContent();
}
