package com.zifuji.cloud.server.sys.module.score.component;

import com.zifuji.cloud.server.sys.db.score.service.ScoreAccountEntityService;
import com.zifuji.cloud.server.sys.db.score.service.ScoreRecordEntityService;
import com.zifuji.cloud.server.sys.module.score.mapper.ScoreMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ScoreComponent {

    private ScoreMapper scoreMapper;

    private ScoreAccountEntityService scoreAccountEntityService;

    private ScoreRecordEntityService scoreRecordEntityService;





}
