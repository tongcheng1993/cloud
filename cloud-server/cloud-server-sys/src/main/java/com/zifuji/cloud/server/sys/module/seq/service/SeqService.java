package com.zifuji.cloud.server.sys.module.seq.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.seq.mo.SaveSeqMo;
import com.zifuji.cloud.server.sys.module.seq.qo.SeqPageQo;
import com.zifuji.cloud.server.sys.module.seq.vo.SeqVo;

public interface SeqService {

    List<SeqVo> queryListSeq(SeqPageQo seqPageQo);

    IPage<SeqVo> queryPageSeq(SeqPageQo seqPageQo);

    SeqVo getSeqById(Long id);

    SeqVo saveSeq(SaveSeqMo saveSeqMo);

    // 根据流水号code 获取对应流水号队列下一个流水号
    String getNextSeq(String code);

}
