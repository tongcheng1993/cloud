package com.zifuji.cloud.server.business.module.school.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "学校控制器")
@RestController
@RequestMapping(value = "/school")
@AllArgsConstructor
public class SchoolController {
}
