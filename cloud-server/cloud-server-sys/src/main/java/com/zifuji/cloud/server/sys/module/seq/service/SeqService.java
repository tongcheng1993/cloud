package com.zifuji.cloud.server.sys.module.seq.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.seq.controller.mo.SaveSeqControllerMo;
import com.zifuji.cloud.server.sys.module.seq.controller.qo.SeqPageQo;
import com.zifuji.cloud.server.sys.module.seq.controller.vo.SeqControllerVo;

public interface SeqService {

    List<SeqControllerVo> queryListSeq(SeqPageQo seqPageQo);

    IPage<SeqControllerVo> queryPageSeq(SeqPageQo seqPageQo);

    SeqControllerVo getSeqById(Long id);

    SeqControllerVo saveSeq(SaveSeqControllerMo saveSeqMo);

    // 根据流水号code 获取对应流水号队列下一个流水号
    String getNextSeq(String code);

}
