package com.zifuji.cloud.server.sys.module.wf.component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class WfComponent {

    private ProcessEngine processEngine;

    private IdentityService identityService;

    private RuntimeService runtimeService;

    private RepositoryService repositoryService;






}
