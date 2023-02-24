package com.zifuji.cloud.server.sys.module.help.service;

import com.zifuji.cloud.server.sys.module.help.mo.HelpContentMo;

public interface HelpService {

    String saveHelpContent(HelpContentMo helpContentMo);

    String getHelpContent();
}
